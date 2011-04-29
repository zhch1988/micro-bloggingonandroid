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

/**
 * @author ֣�
 * 
 */
public class Statuses_friends {
	/**
	 * ����user_id�ʹ����ҳ�뷵����Ӧ�Ĺ�ע�б���һҳ��1
	 * 
	 * @param page
	 * @return ��ע�û��б�
	 */
	public List<User> getFriendsListByUId(String uid, int page) {
		List<User> friendsList = null;
		if (page < 1) {
			Log.v("page", "pageError!");
			return null;
		}
		String cursor = new Integer((page - 1) * 20).toString(); // cursor
		String url = "http://api.t.sina.com.cn/statuses/friends.json";
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("source", Constant.consumerKey));
		nvps.add(new BasicNameValuePair("user_id", uid));
		nvps.add(new BasicNameValuePair("cursor", cursor));

		// �������󣬷��ؽ��
		String string = ExecutePost.executePost(url, nvps);
		if (string != null) {
			// ����json
			try {
				JSONObject jData = new JSONObject(string);
				Log.v("cursor", jData.optString("next_cursor")); // ��һҳ���α�
				JSONArray jUsers = jData.optJSONArray("users");
				if (jUsers != null) {
					friendsList = new ArrayList<User>(); // ���޸�
					int length = jUsers.length();
					Log.v("length", new Integer(length).toString());
					for (int i = 0; i < length; i++) {
						User friend = new User(); // ��ע�б��ÿһ��
						JSONObject jUser = jUsers.optJSONObject(i);// user
						if (jUser != null) {
							friend = Analyse2UserInfo.json2UserInfo(jUser);
						}
						Log.v("i", new Integer(i).toString());
						friendsList.add(friend);
					}
				}
			} catch (JSONException e) {
				Log.v("Error_jUsers", "not found!");
				e.printStackTrace();
			}
		}
		return friendsList; // û�оͷ���null,w������Ը����Ƿ���null���ж�
	}

	/**
	 * ����screen_name�ʹ����ҳ�뷵����Ӧ�Ĺ�ע�б���һҳ��1
	 * 
	 * @param page
	 * @return ��ע�û��б�
	 */
	public List<User> getFriendsListByScreenName(String sName, int page) {
		List<User> friendsList = null;
		if (page < 1) {
			Log.v("page", "pageError!");
			return null;
		}
		String cursor = new Integer((page - 1) * 20).toString(); // cursor
		String url = "http://api.t.sina.com.cn/statuses/friends.json";

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("source", Constant.consumerKey));
		nvps.add(new BasicNameValuePair("screen_name", sName));
		nvps.add(new BasicNameValuePair("cursor", cursor));

		// �������󣬷��ؽ��
		String string = ExecutePost.executePost(url, nvps);
		if (string != null) {
			// ����json
			try {
				JSONObject jData = new JSONObject(string);
				Log.v("cursor", jData.optString("next_cursor")); // ��һҳ���α�
				JSONArray jUsers = jData.optJSONArray("users");
				if (jUsers != null) {
					friendsList = new ArrayList<User>(); // ���޸�
					int length = jUsers.length();
					Log.v("length", new Integer(length).toString());
					for (int i = 0; i < length; i++) {
						User friend = new User(); // ��ע�б��ÿһ��
						JSONObject jUser = jUsers.optJSONObject(i);// user
						if (jUser != null) {
							friend = Analyse2UserInfo.json2UserInfo(jUser);
						}
						Log.v("i", new Integer(i).toString());
						friendsList.add(friend);
					}
				}
			} catch (JSONException e) {
				Log.v("Error_jUsers", "not found!");
				e.printStackTrace();
			}
		}
		return friendsList; // û�оͷ���null,w������Ը����Ƿ���null���ж�
	}
}
