package service.searchServiceImpl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;

import service.SearchService;
import utils.SearchConst;

public class SearchKinService extends SearchService{

	@Override
	public List<Object> setSearchApi(String input) {
		String clientId = SearchConst.CLIENT_ID; //���ø����̼� Ŭ���̾�Ʈ ���̵�"
        String clientSecret = SearchConst.CLIENT_SECRET; //���ø����̼� Ŭ���̾�Ʈ ��ũ����"


        String text = null;
        try {
            text = URLEncoder.encode(input, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("�˻��� ���ڵ� ����",e);
        }
        String apiURL = "https://openapi.naver.com/v1/search/kin.json?query=" + text; // json ���

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
		
		List<Object> cafeForms = createSearchForms(jArray, length);
		return cafeForms;
	}

}
