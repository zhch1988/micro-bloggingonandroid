package weibo.friendships_interface;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import weibo.constant.Constant;
import weibo.util.ExecutePost;
import android.util.Log;

/**
 * @author 郑璨
 * 
 */
public class Friendships_exists {
	/**
	 * 查看用户A是否关注了用户B。 如果用户A关注了用户B，则返回true，否则返回false。
	 * 
	 * @param user_a
	 *            用户A的用户ID
	 * @param user_b
	 *            用户B的用户ID
	 * @return
	 */
	public boolean isFriends(String user_a, String user_b) {
		boolean result = false;
		String url = "http://api.t.sina.com.cn/friendships/exists.json";

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("source", Constant.consumerKey));
		nvps.add(new BasicNameValuePair("user_a", user_a));
		nvps.add(new BasicNameValuePair("user_b", user_b));

		// 建立请求，返回结果
		String string = ExecutePost.executePost(url, nvps);
		if (string != null) {
			// 解析json
			try {
				JSONObject jFriends = new JSONObject(string);
				if (jFriends != null) {
					result = jFriends.optBoolean("friends");
				}
			} catch (JSONException e) {
				Log.v("Error", "Friendships_exists");
				e.printStackTrace();
			}
		}
		return result;
	}
}
