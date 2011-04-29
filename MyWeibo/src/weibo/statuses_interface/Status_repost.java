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
public class Status_repost {
	/**
	 * ת��һ��΢����Ϣ
	 * 
	 * @param rsid
	 * @param addToStatus
	 *            ��ӵ�ת���ı�����Ϣ���ݲ�����140�����֡��粻����Ĭ��Ϊ��ת��΢������
	 * @param is_comment�Ƿ���ת����ͬʱ��������
	 *            ������Ϊ��ӵ�ת���ı��� 0��ʾ���������ۣ�1��ʾ�������۸�"��ǰ΢��"(����ת�ľͷ����ģ���ʹ����Ҳ��ת��)��
	 *            2��ʾ�������۸�ԭ΢����Ҫת����΢��Ҳ��ת�����ģ���3��1��2������Ĭ��Ϊ0��""�����ΪĬ��
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
