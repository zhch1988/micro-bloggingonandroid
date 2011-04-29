package weibo.privacy_setting_interface;

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
import org.json.JSONException;
import org.json.JSONObject;

import weibo.constant.Constant;
import android.util.Log;

/**
 * 
 * @author Zheng Chen
 * 
 */
public class Account_get_privacy {
	/**
	 * comment �� ˭�������۴��˺ŵ�΢����0�������ˣ�1���ҹ�ע���ˡ� dm �� ˭���Ը����˺ŷ�˽�š�0�������ˣ�1���ҹ�ע���ˡ�
	 * real_name�� �Ƿ��������ͨ����ʵ�����������ң� 0����1������ geo �� ����΢�����Ƿ�����΢�����沢��ʾ�����ĵ���λ����Ϣ��
	 * 0����1������ badge �� ѫ��չ��״̬��1˽��״̬��0����״̬��
	 */
	private int comment;
	private int dm;
	private int real_name;
	private int geo;
	private int badge;

	public int getComment() {
		return comment;
	}

	public int getDm() {
		return dm;
	}

	public int getReal_name() {
		return real_name;
	}

	public int getGeo() {
		return geo;
	}

	public int getBadge() {
		return badge;
	}

	public Account_get_privacy() {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("source", Constant.consumerKey));
		this.dealing(nvps);
	}

	private void dealing(List<NameValuePair> nvps) {
		String url = "http://api.t.sina.com.cn/account/get_privacy.json";
		// �½�һ��POST
		HttpPost post = new HttpPost(url);

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
		if (200 == rp.getStatusLine().getStatusCode()) {
			try {
				Log.v("getStatusLine", "OK");
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
					comment = data.getInt("comment");
					Log.v("comment", comment + "");
					dm = data.getInt("dm");
					Log.v("dm", dm + "");
					real_name = data.getInt("real_name");
					Log.v("real_name", real_name + "");
					geo = data.getInt("geo");
					Log.v("geo", geo + "");
					badge = data.getInt("badge");
					Log.v("badge", badge + "");

					/**
					 * comment �� ˭�������۴��˺ŵ�΢����0�������ˣ�1���ҹ�ע���ˡ� dm ��
					 * ˭���Ը����˺ŷ�˽�š�0�������ˣ�1���ҹ�ע���ˡ� real_name�� �Ƿ��������ͨ����ʵ�����������ң�
					 * 0����1������ geo �� ����΢�����Ƿ�����΢�����沢��ʾ�����ĵ���λ����Ϣ�� 0����1������
					 * badge �� ѫ��չ��״̬��1˽��״̬��0����״̬��
					 */
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
