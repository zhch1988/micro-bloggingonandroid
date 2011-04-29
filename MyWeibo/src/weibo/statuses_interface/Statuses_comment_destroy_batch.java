package weibo.statuses_interface;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
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
public class Statuses_comment_destroy_batch {
	/**
	 * ����ɾ�����ۡ�ע�⣺ֻ��ɾ����¼�û��Լ����������ۣ�������ɾ�������˵�����
	 * 
	 * @param ids
	 * @return
	 */
	public List<Comment> destroyCommentBatch(String ids) {
		List<Comment> comList = null;
		String url = "http://api.t.sina.com.cn/statuses/comment/destroy_batch.json";

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("source", Constant.consumerKey));
		nvps.add(new BasicNameValuePair("ids", ids));
		// �������󣬷��ؽ��
		String string = ExecutePost.executePost(url, nvps);
		if (string != null) {
			// ����json
			try {
				JSONArray jArray = new JSONArray(string);
				if (jArray != null) {
					comList = new ArrayList<Comment>();
					Log.v("length", new Integer(jArray.length()).toString());
					for (int i = 0; i < jArray.length(); i++) {
						Comment comment = new Comment();
						JSONObject jComment = jArray.optJSONObject(i);
						if (jComment != null) {
							comment = Analyse2Comment.analyse2Comment(jComment);
						}
						comList.add(comment);
					}
				}
			} catch (JSONException e) {
				Log.v("Error_jarray", "not found!");
				e.printStackTrace();
			}
		}
		return comList;
	}
}