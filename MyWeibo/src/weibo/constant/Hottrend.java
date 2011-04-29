package weibo.constant;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author Zheng Chen
 * 
 */
public class Hottrend {
	private String name;
	private String query;

	public String getName() {
		return name;
	}

	public String getQuery() {
		return query;
	}

	public Hottrend(JSONObject trend) throws JSONException {
		name = trend.getString("name");
		query = trend.getString("query");
	}
}
