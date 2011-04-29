package weibo.direct_messages_interface;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import weibo.constant.Constant;
import weibo.constant.DirectMessage;
import weibo.util.Analyse2DirectMessage;
import weibo.util.ExecutePost;
import android.util.Log;

/**
 * 
 * @author 郑璨
 * 
 */
public class Direct_messages_new {
	/**
	 * 通过接收方的ID发送一条私信，请求必须使用POST方式提交。 发送成功将返回完整的私信消息，包括发送者和接收者的详细信息
	 * 
	 * @param rid
	 *            私信接收方的用户ID(int64)或者微博昵称(string)
	 * @param message
	 * @return
	 */
	public DirectMessage sendDirectMessageByID(String rid, String message) {
		DirectMessage directMessage = null;
		String url = "http://api.t.sina.com.cn/direct_messages/new.json";

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("source", Constant.consumerKey));
		nvps.add(new BasicNameValuePair("id", rid)); // uid
		// 或者screen_name，故去掉bySname方法了
		nvps.add(new BasicNameValuePair("text", message));

		// 建立请求，返回结果
		String string = ExecutePost.executePost(url, nvps);
		if (string != null) {
			// 解析json
			try {
				JSONObject jDM = new JSONObject(string);
				if (jDM != null) {
					directMessage = Analyse2DirectMessage
							.json2DirectMessage(jDM);
				}
			} catch (JSONException e) {
				Log.v("Error_jarray", "not found!");
				e.printStackTrace();
			}
		}
		return directMessage;
	}
}