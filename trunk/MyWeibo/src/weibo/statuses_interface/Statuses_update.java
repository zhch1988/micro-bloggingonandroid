package weibo.statuses_interface;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import weibo.constant.Constant;
import weibo.constant.Status;
import weibo.util.Analyse2Status;
import weibo.util.ExecutePost;
import android.util.Log;

public class Statuses_update {
	/**
	 * 发布一条微博信息。
	 * 
	 * @return
	 */
	public Status createStatus(String newStatusText) {
		Status status = null;
		String url = "http://api.t.sina.com.cn/statuses/update.json";

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("source", Constant.consumerKey));
		nvps.add(new BasicNameValuePair("status", newStatusText));

		// 建立请求，返回结果
		String string = ExecutePost.executePost(url, nvps);
		if (string != null) {
			// 解析json
			try {
				JSONObject jStatus = new JSONObject(string);
				if (jStatus != null) {
					status = Analyse2Status.json2Status(jStatus);
				}
			} catch (JSONException e) {
				Log.v("Error", "Statuses_update");
				e.printStackTrace();
			}
		}
		return status;
	}
}
