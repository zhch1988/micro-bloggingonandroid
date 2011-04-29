package weibo.trends_interface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import weibo.constant.Constant;
import weibo.constant.Status;
import android.util.Log;

/**
 * @author Zheng Chen
 */

public class Trends_statuses {
	private List<Status> statuslist = new ArrayList<Status>();

	/**
	 * 
	 * @return ���ظû����µ�״̬��List
	 */
	public List<Status> getStatuslist() {
		return statuslist;
	}

	/**
	 * 
	 * @param trend_name
	 *            :�������ƣ����Ի�ȡ�û����µ�΢��
	 */
	public Trends_statuses(String trend_name) {
		String url = "http://api.t.sina.com.cn/trends/statuses.json";
		// �½�һ��POST
		HttpPost post = new HttpPost(url);
		// ��Ӳ���
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("source", Constant.consumerKey));
		nvps.add(new BasicNameValuePair("trend_name", trend_name));
		// �������ӵ�post��
		try {
			post.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// �ر�Expect:100-Continue����
		// 100-Continue���������ʹ�ã���Ϊ������֧��HTTP/1.1Э��ķ��������ߴ���ʱ����������
		post.getParams().setBooleanParameter(
				CoreProtocolPNames.USE_EXPECT_CONTINUE, false);
		// ����CommonsHttpOAuthConsumer����
		CommonsHttpOAuthConsumer choc = new CommonsHttpOAuthConsumer(
				Constant.consumerKey, Constant.consumerSecret);
		choc.setTokenWithSecret(Constant.userKey, Constant.userSecret);
		// ��post����ǩ��
		try {
			choc.sign(post);
		} catch (OAuthMessageSignerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OAuthExpectationFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OAuthCommunicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ����post�������HttpResponse
		HttpClient hc = new DefaultHttpClient();
		HttpResponse rp = null;
		try {
			rp = hc.execute(post);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ��HttpResponse�е����ݶ���buffer��
		if (200 == rp.getStatusLine().getStatusCode()) {
			try {
				Log.v("getStatusLine", "OK");
				InputStream is = rp.getEntity().getContent();
				Reader reader = new BufferedReader(new InputStreamReader(is),
						4000);
				StringBuilder buffer = new StringBuilder((int) rp.getEntity()
						.getContentLength());
				try {
					char[] tmp = new char[1024];
					int l;
					while ((l = reader.read(tmp)) != -1) {
						buffer.append(tmp, 0, l);
					}
				} finally {
					reader.close();
				}
				// ��bufferתΪjson����֧�ֵ�String����
				String string = buffer.toString();
				// ����rp
				rp.getEntity().consumeContent();
				// �½�JSONObject���������е�����
				try {
					JSONArray data = new JSONArray(string);
					Log.v("nums_of_statuses", String.valueOf(data.length()));
					if (data.isNull(0))
						Log.v("isNull", "no status");
					else {
						for (int i = 0; i < data.length(); i++) {
							JSONObject status = data.getJSONObject(i);
							statuslist.add(new Status(status));
							// String created_at =
							// status.getString("created_at");
							// Log.v("created_at", created_at);
							// if (!status.isNull("retweeted_status")) {
							// JSONObject retweeted_status = new JSONObject(
							// status.getString("retweeted_status"));
							// int id = retweeted_status.getInt("id");
							// Log.v("retweeted_status_id", String.valueOf(id));
							// JSONObject retweeted_status_user = new
							// JSONObject(
							// retweeted_status.getString("user"));
							// Log.v("retweeted_status_user_province",
							// retweeted_status_user
							// .getString("province"));
							// } else
							// Log.v("not_retweeted_status",
							// "not a retweeted status");
						}
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}

			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
