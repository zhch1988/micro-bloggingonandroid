package weibo.direct_messages_interface;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import weibo.constant.Constant;
import weibo.constant.DirectMessage;
import weibo.util.Analyse2DirectMessage;
import weibo.util.ExecutePost;
import android.util.Log;

public class Direct_messages_destroy_batch {
	/**
	 * 批量删除当前登录用户的私信,多个id之间用半角逗号分割,返回被删除的这些私信
	 * 
	 * @param ids
	 * @return
	 */
	public List<DirectMessage> destroyDMBatch(String ids) {
		List<DirectMessage> dmList = null;
		String url = "http://api.t.sina.com.cn/direct_messages/destroy_batch.json";

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("source", Constant.consumerKey));
		nvps.add(new BasicNameValuePair("ids", ids));

		// 建立请求，返回结果
		String string = ExecutePost.executePost(url, nvps);
		if (string != null) {
			// 解析json
			try {
				JSONArray jArray = new JSONArray(string);
				if (jArray != null) {
					dmList = new ArrayList<DirectMessage>();
					Log.v("length", new Integer(jArray.length()).toString());
					for (int i = 0; i < jArray.length(); i++) {
						DirectMessage directMessage = new DirectMessage();
						JSONObject jDM = jArray.optJSONObject(i);
						if (jDM != null) {
							directMessage = Analyse2DirectMessage
									.json2DirectMessage(jDM);
						}
						dmList.add(directMessage);
					}
				}
			} catch (JSONException e) {
				Log.v("Error_jarray", "not found!");
				e.printStackTrace();
			}
		}
		return dmList;
	}
}
