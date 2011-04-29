package weibo.trends_interface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
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
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreProtocolPNames;
import org.json.JSONException;
import org.json.JSONObject;

import weibo.constant.Constant;
import android.util.Log;

/**
 * @author Zheng Chen
 */

public class Trends_destroy {
	private boolean result = false;

	/**
	 * 
	 * @return ����ɾ���ɹ����
	 */
	public boolean getResult() {
		return result;
	}

	/**
	 * 
	 * @param trend_id
	 *            :��ɾ������id
	 */
	public Trends_destroy(String trend_id) {

		// ��Ӳ���
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("source", Constant.consumerKey));
		nvps.add(new BasicNameValuePair("trend_id", trend_id));
		// �½�HttpDelete����
		HttpDelete del = null;
		try {
			URI uri = URIUtils.createURI("http", "api.t.sina.com.cn", -1,
					"/trends/destroy.json",
					URLEncodedUtils.format(nvps, "UTF-8"), null);
			del = new HttpDelete(uri);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// �ر�Expect:100-Continue����
		// 100-Continue���������ʹ�ã���Ϊ������֧��HTTP/1.1Э��ķ��������ߴ���ʱ����������
		del.getParams().setBooleanParameter(
				CoreProtocolPNames.USE_EXPECT_CONTINUE, false);
		// ����CommonsHttpOAuthConsumer����
		CommonsHttpOAuthConsumer choc = new CommonsHttpOAuthConsumer(
				Constant.consumerKey, Constant.consumerSecret);
		choc.setTokenWithSecret(Constant.userKey, Constant.userSecret);
		// ��del����ǩ��
		try {
			choc.sign(del);
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
		// ����del�������HttpResponse
		HttpClient hc = new DefaultHttpClient();
		HttpResponse rp = null;
		try {
			rp = hc.execute(del);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ��ȡstatuscode
		int status_code = rp.getStatusLine().getStatusCode();
		Log.v("statuscode", status_code + "");
		if (200 == status_code) {
			try {
				Log.v("getStatusLine", "OK(200)");
				// ��HttpResponse�е����ݶ���buffer��
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
					JSONObject data = new JSONObject(string);
					result = data.getBoolean("result");

				} catch (JSONException e) {
					e.printStackTrace();
				}

			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}/*
		 * else Log.v("other_status", "unexpected status");
		 */
	}

}
