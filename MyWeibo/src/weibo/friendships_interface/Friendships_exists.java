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
 * @author ֣�
 * 
 */
public class Friendships_exists {
	/**
	 * �鿴�û�A�Ƿ��ע���û�B�� ����û�A��ע���û�B���򷵻�true�����򷵻�false��
	 * 
	 * @param user_a
	 *            �û�A���û�ID
	 * @param user_b
	 *            �û�B���û�ID
	 * @return
	 */
	public boolean isFriends(String user_a, String user_b) {
		boolean result = false;
		String url = "http://api.t.sina.com.cn/friendships/exists.json";

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("source", Constant.consumerKey));
		nvps.add(new BasicNameValuePair("user_a", user_a));
		nvps.add(new BasicNameValuePair("user_b", user_b));

		// �������󣬷��ؽ��
		String string = ExecutePost.executePost(url, nvps);
		if (string != null) {
			// ����json
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
