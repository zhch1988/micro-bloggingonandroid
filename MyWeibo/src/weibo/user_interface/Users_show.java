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
 * @author 郑璨
 * 
 */
public class Users_show {
	/**
	 * 按用户ID返回用户资料以及用户的最新发布的一条微博消息。
	 * 
	 * @param page
	 * @return 关注用户列表
	 */
	public User getUserInfoByUId(String uid) {
		User user = null; // new了以后不是null了，外面就不知道怎么判定服务器有没有返回数据，用null判定,返回了才赋值才new
		String url = "http://api.t.sina.com.cn/users/show.json";

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("source", Constant.consumerKey));
		nvps.add(new BasicNameValuePair("user_id", uid));

		// 建立请求，返回结果
		String string;
		string = ExecutePost.executePost(url, nvps);
		if (string != null) {
			// 解析json
			try {
				JSONObject jUser = new JSONObject(string);
				if (jUser != null) {
					user = Analyse2UserInfo.json2UserInfo(jUser); // 解析JSON
				}
			} catch (JSONException e) {
				Log.v("Error", "Users_show");
				e.printStackTrace();
			}
		}
		return user;
	}

	/**
	 * 按用户昵称返回用户资料以及用户的最新发布的一条微博消息。
	 * 
	 * @param sName
	 * @return
	 */
	public User getUserInfoByScreenName(String sName) {
		User user = null; // new了以后不是null了，外面就不知道怎么判定服务器有没有返回数据，用null判定,返回了才赋值才new

		String url = "http://api.t.sina.com.cn/users/show.json";

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("source", Constant.consumerKey));
		nvps.add(new BasicNameValuePair("screen_name", sName));

		// 建立请求，返回结果
		String string;
		string = ExecutePost.executePost(url, nvps);

		if (string != null) {
			// 解析json
			try {
				JSONObject jUser = new JSONObject(string);
				if (jUser != null) {
					user = Analyse2UserInfo.json2UserInfo(jUser); // 解析JSON
				}
			} catch (JSONException e) {
				Log.v("Error", "Users_show");
				e.printStackTrace();
			}
		}
		return user;
	}
}
