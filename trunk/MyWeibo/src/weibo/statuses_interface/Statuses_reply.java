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
 * @author ֣�
 */
public class Statuses_reply {
	/**
	 * 
	 * @param cid
	 *            Ҫ�ظ�������ID
	 * @param com
	 * @param sid
	 *            Ҫ���۵�΢����ϢID
	 * @return
	 */
	public Comment replyComment(String cid, String com, String sid) {
		Comment comment = null;
		String url = "http://api.t.sina.com.cn/statuses/reply.json";
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("source", Constant.consumerKey));
		nvps.add(new BasicNameValuePair("cid", cid));
		nvps.add(new BasicNameValuePair("comment", com));
		nvps.add(new BasicNameValuePair("id", sid));
		// �������󣬷��ؽ��
		String string = ExecutePost.executePost(url, nvps);
		if (string != null) {
			// ����json
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
