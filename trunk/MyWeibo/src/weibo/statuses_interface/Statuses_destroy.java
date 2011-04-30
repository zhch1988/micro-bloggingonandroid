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
 * @author ֣�
 * 
 */
public class Statuses_destroy {
	/**
	 * ����IDɾ��΢����Ϣ,���ر�ɾ����΢��
	 * 
	 * @param sid
	 * @return
	 */
	public Status DestroyStatusBySID(String sid) {
		Status status = null;

		String url = "http://api.t.sina.com.cn/statuses/destroy/" + sid
				+ ".json";

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("source", Constant.consumerKey));

		// �������󣬷��ؽ��
		String string = ExecutePost.executePost(url, nvps);
		if (string != null) {
			// ����json
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