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
 * @author ֣�
 * 
 */
public class Direct_messages_new {
	/**
	 * ͨ�����շ���ID����һ��˽�ţ��������ʹ��POST��ʽ�ύ�� ���ͳɹ�������������˽����Ϣ�����������ߺͽ����ߵ���ϸ��Ϣ
	 * 
	 * @param rid
	 *            ˽�Ž��շ����û�ID(int64)����΢���ǳ�(string)
	 * @param message
	 * @return
	 */
	public DirectMessage sendDirectMessageByID(String rid, String message) {
		DirectMessage directMessage = null;
		String url = "http://api.t.sina.com.cn/direct_messages/new.json";

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("source", Constant.consumerKey));
		nvps.add(new BasicNameValuePair("id", rid)); // uid
		// ����screen_name����ȥ��bySname������
		nvps.add(new BasicNameValuePair("text", message));

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