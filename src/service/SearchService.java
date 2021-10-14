package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import utils.SearchConst;
import web.form.SearchForm;

public abstract class SearchService {
	
	public abstract List<Object> setSearchApi(String input);
	
	
	// call naver search api (authorization check)
	protected String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }


    protected HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }


    protected String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);


        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
    
    // put search result(json arr) in forms
    protected List<Object> createSearchForms(String jsonString) {
		JSONArray jArray = new JSONArray(jsonString); 
		int length = jArray.length();
		
		List<Object> forms = new ArrayList<>(); 
		for(int i = 0 ; i < length; i++) {
			JSONObject object = jArray.getJSONObject(i);
			String title = (String) object.get("title");
			String link = (String) object.get("link");
			String description = (String) object.get("description");
			
			SearchForm form = new SearchForm(title, link, description);
			forms.add(form);
			
		}
		return forms;
	}
    
    // encode search word
    protected String encodeInputText(String input) {
    	 String text = null;
         try {
             text = URLEncoder.encode(input, "UTF-8");
         } catch (UnsupportedEncodingException e) {
             throw new RuntimeException("검색어 인코딩 실패",e);
         }
         return text;
    }
    
    // parse json to make JSONArray
    protected String parseJson(String jsonString) {
        int idx = 0;
		for(int i = 0 ; i < jsonString.length(); i++) {
			if(jsonString.charAt(i) == '[') {
				idx = i;
				break;
			}
		}
		jsonString = jsonString.substring(idx,jsonString.length());
		return jsonString;
    }
    
    // put authorization info in headers
    protected Map<String, String> createRequestHeaders(){
    	Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", SearchConst.CLIENT_ID);
        requestHeaders.put("X-Naver-Client-Secret", SearchConst.CLIENT_SECRET);
        return requestHeaders;
    }
    
    protected String urlResolver(String searchType, String text) {
    	return "https://openapi.naver.com/v1/search/" + searchType + ".json?query=" + text;
    }
	
}
