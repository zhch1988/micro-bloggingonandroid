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
 * @author 郑璨
 */
public class Statuses_comment_destroy {
	/**
	 * 删除评论。注意：只能删除登录用户自己发布的评论，不可以删除其他人的评论
	 * 
	 * @param cid
	 * @return
	 */
	public Comment destroyComment(String cid) {
		Comment comment = null;

		String url = "http://api.t.sina.com.cn/statuses/comment_destroy/" + cid
				+ ".json";

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("source", Constant.consumerKey));

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
