package service.searchServiceImpl;


import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import service.SearchService;


public class SearchKinService extends SearchService{

	@Override
	public List<Object> setSearchApi(String input) {
        String text = encodeInputText(input);
        String apiURL = urlResolver("kin", text);

        Map<String, String> requestHeaders = createRequestHeaders();
        String jsonString = parseJson(get(apiURL,requestHeaders));

		List<Object> Forms = createSearchForms(jsonString);
		return Forms;
	}

}
