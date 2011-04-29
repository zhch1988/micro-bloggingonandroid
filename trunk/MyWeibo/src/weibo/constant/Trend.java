package weibo.constant;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author Zheng Chen
 * 
 */
public class Trend {
	private String num;
	private String hotword;
	private String trend_id;

	public Trend(JSONObject trend) throws JSONException {
		this.num = trend.getString("num");
		this.hotword = trend.getString("hotword");
		this.trend_id = trend.getString("trend_id");
	}

	public String getNum() {
		return num;
	}

	public String getHotword() {
		return hotword;
	}

	public String getTrend_id() {
		return trend_id;
	}

}
