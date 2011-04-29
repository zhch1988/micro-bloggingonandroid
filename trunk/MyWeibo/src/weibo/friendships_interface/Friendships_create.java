package weibo.friendships_interface;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
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
public class Friendships_create {
	/**
	 * ͨ��ID��עһ���û�����ע�ɹ��򷵻ع�ע�˵�����
	 * 
	 * @param fid
	 * @return
	 */
	public User createFriendshipByUID(String fid) {
		User user = null;
		String url = "http://api.t.sina.com.cn/friendships/create.json";

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("source", Constant.consumerKey));
		nvps.add(new BasicNameValuePair("user_id", fid));

		// �������󣬷��ؽ��
		String string = ExecutePost.executePost(url, nvps);
		if (string != null) {
			// ����json
			try {
				JSONObject jUser = new JSONObject(string);
				if (jUser != null) {
					user = Analyse2UserInfo.json2UserInfo(jUser);
				}
			} catch (JSONException e) {
				Log.v("Error", "Friendships_create");
				e.printStackTrace();
			}
		}
		return user;
	}

	/**
	 * ͨ���ǳƹ�עһ���û�����ע�ɹ��򷵻ع�ע�˵�����
	 * 
	 * @param sName
	 * @return
	 */
	public User createFriendshipBySName(String sName) {
		User user = null;
		String url = "http://api.t.sina.com.cn/friendships/create.json";

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("source", Constant.consumerKey));
		nvps.add(new BasicNameValuePair("screen_name", sName));

		// �������󣬷��ؽ��
		String string = ExecutePost.executePost(url, nvps);
		if (string != null) {
			// ����json
			try {
				JSONObject jUser = new JSONObject(string);
				if (jUser != null) {
					user = Analyse2UserInfo.json2UserInfo(jUser);
				}
			} catch (JSONException e) {
				Log.v("Error", "Friendships_create");
				e.printStackTrace();
			}
		}
		return user;
	}
}
