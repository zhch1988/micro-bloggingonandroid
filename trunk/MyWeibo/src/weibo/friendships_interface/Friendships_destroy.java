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
public class Friendships_destroy {
	/**
	 * ͨ��ȡ����ĳ�û��Ĺ�ע,���ع�ע�˵����ϣ�����ȡ����˿�Ĺ�ע
	 * 
	 * @param fid
	 * @return
	 */
	public User destroyFriendshipByID(String fid) {
		User user = null;
		String url = "http://api.t.sina.com.cn/friendships/destroy.json";

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
				Log.v("Error", "1");
				e.printStackTrace();
			}
		}
		return user;
	}

	/**
	 * ͨ���ǳ�ȡ����עһ���û����ɹ��򷵻�ȡ����ע�˵�����
	 * 
	 * @param sName
	 * @return
	 */
	public User destroyFriendshipBySName(String sName) {
		User user = null;
		String url = "http://api.t.sina.com.cn/friendships/destroy.json";

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
				Log.v("Error", "1");
				e.printStackTrace();
			}
		}
		return user;
	}
}
