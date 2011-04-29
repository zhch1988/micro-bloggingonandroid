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

/**
 * 
 * @author ֣�
 * 
 */
public class Direct_messages_sent {
	/**
	 * ���ص�¼�û��ѷ��͵�����n��˽�ţ�ÿ��˽�Ű��������ߺͽ����ߵ���ϸ��Ϣ��
	 * 
	 * @return
	 */
	public List<DirectMessage> getSendedDirectMessage() {
		List<DirectMessage> dmList = null;
		String url = "http://api.t.sina.com.cn/direct_messages/sent.json";

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("source", Constant.consumerKey));
		// �������󣬷��ؽ��
		String string = ExecutePost.executePost(url, nvps);
		if (string != null) {
			// ����json
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