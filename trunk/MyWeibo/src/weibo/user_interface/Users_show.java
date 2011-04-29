package weibo.user_interface;

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
 * 
 * @author ֣�
 * 
 */
public class Users_show {
	/**
	 * ���û�ID�����û������Լ��û������·�����һ��΢����Ϣ��
	 * 
	 * @param page
	 * @return ��ע�û��б�
	 */
	public User getUserInfoByUId(String uid) {
		User user = null; // new���Ժ���null�ˣ�����Ͳ�֪����ô�ж���������û�з������ݣ���null�ж�,�����˲Ÿ�ֵ��new
		String url = "http://api.t.sina.com.cn/users/show.json";

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("source", Constant.consumerKey));
		nvps.add(new BasicNameValuePair("user_id", uid));

		// �������󣬷��ؽ��
		String string;
		string = ExecutePost.executePost(url, nvps);
		if (string != null) {
			// ����json
			try {
				JSONObject jUser = new JSONObject(string);
				if (jUser != null) {
					user = Analyse2UserInfo.json2UserInfo(jUser); // ����JSON
				}
			} catch (JSONException e) {
				Log.v("Error", "Users_show");
				e.printStackTrace();
			}
		}
		return user;
	}

	/**
	 * ���û��ǳƷ����û������Լ��û������·�����һ��΢����Ϣ��
	 * 
	 * @param sName
	 * @return
	 */
	public User getUserInfoByScreenName(String sName) {
		User user = null; // new���Ժ���null�ˣ�����Ͳ�֪����ô�ж���������û�з������ݣ���null�ж�,�����˲Ÿ�ֵ��new

		String url = "http://api.t.sina.com.cn/users/show.json";

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("source", Constant.consumerKey));
		nvps.add(new BasicNameValuePair("screen_name", sName));

		// �������󣬷��ؽ��
		String string;
		string = ExecutePost.executePost(url, nvps);

		if (string != null) {
			// ����json
			try {
				JSONObject jUser = new JSONObject(string);
				if (jUser != null) {
					user = Analyse2UserInfo.json2UserInfo(jUser); // ����JSON
				}
			} catch (JSONException e) {
				Log.v("Error", "Users_show");
				e.printStackTrace();
			}
		}
		return user;
	}
}
