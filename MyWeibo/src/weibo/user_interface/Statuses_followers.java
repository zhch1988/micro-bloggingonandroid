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

public class Statuses_followers {
	/**
	 * ����user_id�ʹ����ҳ���ȡ�û���˿�б���ÿ����˿�û�����һ��΢������һҳ��1
	 * 
	 * @param page
	 * @return ��˿�б�
	 */
	public List<User> getFollowersListByUId(String uid, int page) {
		List<User> followersList = null;
		if (page < 1) {
			Log.v("page", "pageError!");
			return null;
		}
		String url = "http://api.t.sina.com.cn/statuses/followers.json";
		String cursor = new Integer((page - 1) * 20).toString(); // cursor,ÿҳ20��

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
					followersList = new ArrayList<User>(); // ���޸�
					int length = jUsers.length();
					Log.v("length", new Integer(length).toString());
					for (int i = 0; i < length; i++) {
						User follower = new User(); // ��ע�б��ÿһ��
						JSONObject jUser = jUsers.optJSONObject(i);// user
						if (jUser != null) {
							follower = Analyse2UserInfo.json2UserInfo(jUser);
						}
						Log.v("i", new Integer(i).toString());
						followersList.add(follower);
					}
				}
			} catch (JSONException e) {
				Log.v("Error_jarray", "not found!");
				e.printStackTrace();
			}
		}
		return followersList;
	}

	/**
	 * ����screen_name�ʹ����ҳ���ȡ�û���˿�б���ÿ����˿�û�����һ��΢��
	 * 
	 * @param sName
	 * @param page
	 * @return
	 */
	public List<User> getFollowersListByScreenName(String sName, int page) {
		List<User> followersList = null;
		if (page < 1) {
			Log.v("page", "pageError!");
			return null;
		}
		String url = "http://api.t.sina.com.cn/statuses/followers.json";
		String cursor = new Integer((page - 1) * 20).toString(); // cursor,ÿҳ20��

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
					followersList = new ArrayList<User>(); // ���޸�
					int length = jUsers.length();
					Log.v("length", new Integer(length).toString());
					for (int i = 0; i < length; i++) {
						User follower = new User(); // ��ע�б��ÿһ��
						JSONObject jUser = jUsers.optJSONObject(i);// user
						if (jUser != null) {
							follower = Analyse2UserInfo.json2UserInfo(jUser);
						}
						Log.v("i", new Integer(i).toString());
						followersList.add(follower);
					}
				}
			} catch (JSONException e) {
				Log.v("Error_jarray", "not found!");
				e.printStackTrace();
			}
		}
		return followersList;
	}
}
