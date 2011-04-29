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
 * @author ֣�
 */
public class Direct_messages_destroy {
	/**
	 * ����IDɾ����¼�û���˽�ţ�˽�ſ����Ƿ��͵Ļ���յ�
	 * 
	 * @param mid
	 * @return
	 */
	public DirectMessage destroyDircteMessageByID(String mid) {
		DirectMessage directMessage = null;

		String url = "http://api.t.sina.com.cn/direct_messages/destroy/" + mid
				+ ".json";

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("source", Constant.consumerKey));

		// �������󣬷��ؽ��
		String string = ExecutePost.executePost(url, nvps);
		if (string != null) {
			// ����json
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
