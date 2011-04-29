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
 * @author 郑璨
 * 
 */
public class Friendships_create {
	/**
	 * 通过ID关注一个用户。关注成功则返回关注人的资料
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

		// 建立请求，返回结果
		String string = ExecutePost.executePost(url, nvps);
		if (string != null) {
			// 解析json
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
	 * 通过昵称关注一个用户。关注成功则返回关注人的资料
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

		// 建立请求，返回结果
		String string = ExecutePost.executePost(url, nvps);
		if (string != null) {
			// 解析json
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
