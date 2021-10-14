package service.searchServiceImpl;

import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;

import service.SearchService;
import utils.SearchConst;

public class SearchBlogService extends SearchService {
	
	
	public List<Object> setSearchApi(String input) {
		
		String clientId = SearchConst.CLIENT_ID; //애플리케이션 클라이언트 아이디값"
        String clientSecret = SearchConst.CLIENT_SECRET; //애플리케이션 클라이언트 시크릿값"


        String text = null;
        try {
            text = URLEncoder.encode(input, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }
        String apiURL = "https://openapi.naver.com/v1/search/blog.json?query=" + text; // json 결과

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String jsonString = get(apiURL,requestHeaders);

        int idx = 0;
		for(int i = 0 ; i < jsonString.length(); i++) {
			if(jsonString.charAt(i) == '[') {
				idx = i;
				break;
			}
		}
		jsonString = jsonString.substring(idx,jsonString.length());

		JSONArray jArray = new JSONArray(jsonString); 
		int length = jArray.length();
		
		List<Object> blogForms = createSearchForms(jArray, length);
		return blogForms;
	}
	


}
