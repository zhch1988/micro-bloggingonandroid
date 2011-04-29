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
 * 更新当前登录用户所关注的某个好友的备注信息
 * 
 * @author 郑璨
 * 
 */
public class User_friends_update_remark {
	/**
	 * 修改当前登录用户所关注的用户的备注信息
	 * 
	 * @param updatedId
	 * @param newRemark
	 */
	public User updateRemark(String updatedId, String newRemark) {
		User user = null;
		String url = "http://api.t.sina.com.cn/user/friends/update_remark.json";

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("source", Constant.consumerKey));
		nvps.add(new BasicNameValuePair("user_id", updatedId));
		nvps.add(new BasicNameValuePair("remark", newRemark));

		// 建立请求，返回结果
		String string = ExecutePost.executePost(url, nvps);
		if (string != null) {
			// 解析json ???有没有其他方法不要这么多try not found
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
