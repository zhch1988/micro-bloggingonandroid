package weibo.user_interface;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import weibo.constant.Constant;
import weibo.constant.User;
import weibo.util.Analyse2UserInfo;
import weibo.util.ExecutePost;
import android.util.Log;

public class Users_suggestions {
	/**
	 * 返回当前用户可能感兴趣的用户及其最新微博
	 * 
	 * @return
	 */
	public List<User> getSuggestionsList() {
		List<User> suggestList = null;
		String url = "http://api.t.sina.com.cn/users/suggestions.json";

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("source", Constant.consumerKey));

		// 建立请求，返回结果
		String string = ExecutePost.executePost(url, nvps);
		if (string != null) {
			// 解析json
			try {
				JSONArray jArray = new JSONArray(string);
				if (jArray != null) {
					suggestList = new ArrayList<User>();
					Log.v("length", new Integer(jArray.length()).toString());
					for (int i = 0; i < jArray.length(); i++) {
						User suggestUser = new User();
						JSONObject jSuggestUser = jArray.optJSONObject(i);// user
						if (jSuggestUser != null) {
							suggestUser = Analyse2UserInfo
									.json2UserInfo(jSuggestUser);
						}
						Log.v("i", new Integer(i).toString());
						suggestList.add(suggestUser);
					}
				}
			} catch (JSONException e) {
				Log.v("Error_jarray", "not found!");
				e.printStackTrace();
			}
		}
		return suggestList;
	}
}
