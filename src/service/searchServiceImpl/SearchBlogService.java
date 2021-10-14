package service.searchServiceImpl;


import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import service.SearchService;


public class SearchBlogService extends SearchService {
	
	
	public List<Object> setSearchApi(String input) {
		String text = encodeInputText(input);
        String apiURL = urlResolver("blog", text);

        Map<String, String> requestHeaders = createRequestHeaders();
        String jsonString = parseJson(get(apiURL,requestHeaders));
		
        List<Object> forms = createSearchForms(jsonString);
		return forms;
	}
	


}
