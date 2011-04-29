package weibo.social_graph_interface;

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
import android.util.Log;

/**
 * 
 * @author Zheng Chen
 * 
 */
public class Friends_ids {
	private String[] ids;
	private int next_cursor;
	private int previous_cursor;

	/**
	 * 
	 * @return �û��ķ�˿�û�ID�б�
	 */
	public String[] getIds() {
		return ids;
	}

	/**
	 * 
	 * @return ��ҳ�α꣨��ҳ��ʾ��
	 */
	public int getNext_cursor() {
		return next_cursor;
	}

	/**
	 * 
	 * @return ��ҳ�α꣨��ҳ��ʾ��
	 */
	public int getPrevious_cursor() {
		return previous_cursor;
	}

	/**
	 * Ĭ���޲���Ϊ����5000����ǰ�û��ķ�˿
	 */
	public Friends_ids() {
		// ��Ӳ���
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("source", Constant.consumerKey));
		this.dealing(nvps);
	}

	/**
	 * 
	 * @param user_id
	 *            ����ѯ���û�id
	 */
	public Friends_ids(String user_id) {
		// ��Ӳ���
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("source", Constant.consumerKey));
		nvps.add(new BasicNameValuePair("user_id", user_id));
		this.dealing(nvps);
	}

	/**
	 * 
	 * @param user_id
	 *            :����ѯ���û�id
	 * @param count
	 *            �����ص�����
	 */
	public Friends_ids(String user_id, String count) {
		// ��Ӳ���
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("source", Constant.consumerKey));
		nvps.add(new BasicNameValuePair("user_id", user_id));
		nvps.add(new BasicNameValuePair("count", count));
		this.dealing(nvps);
	}

	// ������
	private void dealing(List<NameValuePair> nvps) {
		String url = "http://api.t.sina.com.cn/friends/ids.json";
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
					JSONArray ids = data.getJSONArray("ids");
					Log.v("nums_of_ids", ids.length() + "");
					this.ids = new String[ids.length()];
					for (int i = 0; i < ids.length(); i++) {
						this.ids[i] = ids.getString(i);
						Log.v("id " + i, this.ids[i]);
					}
					next_cursor = data.getInt("next_cursor");
					Log.v("next_cursor", next_cursor + "");
					previous_cursor = data.getInt("previous_cursor");
					Log.v("previous_cursor", previous_cursor + "");

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
