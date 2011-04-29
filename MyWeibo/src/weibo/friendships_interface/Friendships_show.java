package weibo.friendships_interface;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import weibo.constant.Constant;
import weibo.constant.Relationship;
import weibo.util.Analyse2Relationship;
import weibo.util.ExecutePost;
import android.util.Log;

/**
 * @author 郑璨
 * 
 */
public class Friendships_show {
	/**
	 * 返回当前登录用户与另外一个用户关注关系的详细情况
	 * 
	 * @param tid
	 *            仅根据目标id判断关系
	 * @return
	 */
	public Relationship getRelationshipByTID(String tid) {
		Relationship relation = null;
		String url = "http://api.t.sina.com.cn/friendships/show.json";

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("source", Constant.consumerKey));
		nvps.add(new BasicNameValuePair("target_id", tid));

		// 建立请求，返回结果
		String string = ExecutePost.executePost(url, nvps);
		if (string != null) {
			// 解析json
			try {
				JSONObject jRelationship = new JSONObject(string);
				if (jRelationship != null) {
					relation = Analyse2Relationship
							.json2Relationship(jRelationship);
				}
			} catch (JSONException e) {
				Log.v("Error", "Friendships_show");
				e.printStackTrace();
			}
		}
		return relation;
	}

	/*
	 * 返回两个用户关注关系的详细情况
	 * 
	 * @param tSName 仅根据目标昵称判断关系
	 * 
	 * @return
	 */
	public Relationship getRelationshipByTargetSName(String targetSname) {
		Relationship relation = null;
		String url = "http://api.t.sina.com.cn/friendships/show.json";

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("source", Constant.consumerKey));
		nvps.add(new BasicNameValuePair("target_screen_name", targetSname));

		// 建立请求，返回结果
		String string = ExecutePost.executePost(url, nvps);
		if (string != null) {
			// 解析json
			try {
				JSONObject jRelationship = new JSONObject(string);
				if (jRelationship != null) {
					relation = Analyse2Relationship
							.json2Relationship(jRelationship);
				}
			} catch (JSONException e) {
				Log.v("Error", "Friendships_show");
				e.printStackTrace();
			}
		}
		return relation;
	}

	/**
	 * 返回两个用户关注关系的详细情况
	 * 
	 * @param sid
	 *            ，如果用户已登录，此接口将自动使用当前用户ID作为source_id。但是可强制指定source_id来查询关注关系
	 * @param tid
	 * @return
	 */
	public Relationship getRelationshipByID(String sid, String tid) {
		Relationship relation = null;
		String url = "http://api.t.sina.com.cn/friendships/show.json";

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("source", Constant.consumerKey));
		nvps.add(new BasicNameValuePair("source_id", sid));
		nvps.add(new BasicNameValuePair("target_id", tid));
		// 建立请求，返回结果
		String string = ExecutePost.executePost(url, nvps);

		// 解析json
		try {
			JSONObject jRelationship = new JSONObject(string);
			if (jRelationship != null) {
				relation = Analyse2Relationship
						.json2Relationship(jRelationship);
			}
		} catch (JSONException e) {
			Log.v("Error", "Friendships_show");
			e.printStackTrace();
		}
		return relation;
	}

	/**
	 * 返回两个用户关注关系的详细情况
	 * 
	 * @param ssName
	 *            ，
	 * @param tSName
	 * @return
	 */
	public Relationship getRelationshipBySName(String ssName, String tSName) {
		Relationship relation = null;
		String url = "http://api.t.sina.com.cn/friendships/show.json";

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("source", Constant.consumerKey));
		nvps.add(new BasicNameValuePair("source_screen_name", ssName));
		nvps.add(new BasicNameValuePair("target_screen_name", tSName));

		// 建立请求，返回结果
		String string = ExecutePost.executePost(url, nvps);

		// 解析json
		try {
			JSONObject jRelationship = new JSONObject(string);
			if (jRelationship != null) {
				relation = Analyse2Relationship
						.json2Relationship(jRelationship);
			}
		} catch (JSONException e) {
			Log.v("Error", "Friendships_show");
			e.printStackTrace();
		}
		return relation;
	}
}
