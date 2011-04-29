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
public class Friendships_destroy {
	/**
	 * 通过取消对某用户的关注,返回关注人的资料，不能取消粉丝的关注
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
				Log.v("Error", "1");
				e.printStackTrace();
			}
		}
		return user;
	}

	/**
	 * 通过昵称取消关注一个用户，成功则返回取消关注人的资料
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
				Log.v("Error", "1");
				e.printStackTrace();
			}
		}
		return user;
	}
}
