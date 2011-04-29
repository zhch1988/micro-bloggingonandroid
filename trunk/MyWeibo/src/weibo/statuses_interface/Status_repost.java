package weibo.statuses_interface;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import weibo.constant.Constant;
import weibo.constant.Status;
import weibo.util.Analyse2Status;
import weibo.util.ExecutePost;
import android.util.Log;

/**
 * 
 * @author 郑璨
 * 
 */
public class Status_repost {
	/**
	 * 转发一条微博消息
	 * 
	 * @param rsid
	 * @param addToStatus
	 *            添加的转发文本。信息内容不超过140个汉字。如不填则默认为“转发微博”。
	 * @param is_comment是否在转发的同时发表评论
	 *            （内容为添加的转发文本） 0表示不发表评论，1表示发表评论给"当前微博"(从哪转的就发到哪，即使别人也是转的)，
	 *            2表示发表评论给原微博（要转发的微博也是转发来的），3是1、2都发表。默认为0。""不填均为默认
	 * @return
	 */
	public Status repostStatus(String rsid, String addToStatus,
			String is_comment) {
		Status status = null;

		String url = "http://api.t.sina.com.cn/statuses/repost.json";

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("source", Constant.consumerKey));
		nvps.add(new BasicNameValuePair("id", rsid));
		nvps.add(new BasicNameValuePair("status", addToStatus));
		nvps.add(new BasicNameValuePair("is_comment", is_comment));

		// 建立请求，返回结果
		String string = ExecutePost.executePost(url, nvps);
		if (string != null) {
			// 解析json
			try {
				JSONObject jStatus = new JSONObject(string);
				if (jStatus != null) {
					status = Analyse2Status.json2Status(jStatus);
				}
			} catch (JSONException e) {
				Log.v("Error", "15");
				e.printStackTrace();
			}
		}
		return status;
	}
}
