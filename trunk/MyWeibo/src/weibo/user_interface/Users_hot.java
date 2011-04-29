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
 * 返回系统推荐的用户列表
 * 
 * @author 郑璨
 * 
 */
public class Users_hot {
	/**
	 * 根据类别返回推荐列表,全部的推荐，不分页
	 * 
	 * @param category
	 * @return
	 */
	public List<User> getHotUserList(String category) {
		List<User> hotUserList = null;

		String url = "http://api.t.sina.com.cn/users/hot.json";

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("source", Constant.consumerKey));
		nvps.add(new BasicNameValuePair("category ", category));

		// 建立请求，返回结果
		String string = ExecutePost.executePost(url, nvps);
		if (string != null) {
			// 解析json
			try {
				JSONArray jArray = new JSONArray(string);
				if (jArray != null) {
					hotUserList = new ArrayList<User>();
					Log.v("length", new Integer(jArray.length()).toString());
					for (int i = 0; i < jArray.length(); i++) {
						User hotUser = new User();
						JSONObject jHotUser = jArray.optJSONObject(i);// user
						if (jHotUser != null) {
							hotUser = Analyse2UserInfo.json2UserInfo(jHotUser);
						}
						Log.v("i", new Integer(i).toString());
						hotUserList.add(hotUser);
					}
				}
			} catch (JSONException e) {
				Log.v("Error_jarray", "not found!");
				e.printStackTrace();
			}
		}
		return hotUserList;
	}
}
