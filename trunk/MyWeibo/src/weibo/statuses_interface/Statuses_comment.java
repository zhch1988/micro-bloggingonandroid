package weibo.statuses_interface;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import weibo.constant.Comment;
import weibo.constant.Constant;
import weibo.util.Analyse2Comment;
import weibo.util.ExecutePost;
import android.util.Log;

/**
 * @author 郑璨 只设置了必要的信息
 * 
 */
public class Statuses_comment {
	/**
	 * 对一条微博信息进行评论
	 * 
	 * @param sid
	 * @param com
	 * @param comment_ori
	 *            当评论一条转发微博时，是否评论给原微博 不填默认0：不评论给原微博
	 * @return
	 */
	public Comment sendComment(String sid, String com, String comment_ori) {
		Comment comment = null;

		String url = "http://api.t.sina.com.cn/statuses/comment.json";

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("source", Constant.consumerKey));
		nvps.add(new BasicNameValuePair("id", sid));
		nvps.add(new BasicNameValuePair("comment", com));
		nvps.add(new BasicNameValuePair("comment_ori", comment_ori));

		// 建立请求，返回结果
		String string = ExecutePost.executePost(url, nvps);
		if (string != null) {
			// 解析json
			try {
				JSONObject jComment = new JSONObject(string);
				if (jComment != null) {
					comment = Analyse2Comment.analyse2Comment(jComment);
				}
			} catch (JSONException e) {
				Log.v("Error", "1");
				e.printStackTrace();
			}
		}
		return comment;
	}
}
