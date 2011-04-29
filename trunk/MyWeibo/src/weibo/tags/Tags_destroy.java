package weibo.tags;

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
 * @author ������
 * 
 */
// ɾ����ǩ�б�
public class Tags_destroy {
	public Tags_destroy(int tag_id) {
		// api��ַ
		String url = "http://api.t.sina.com.cn/tags/destroy.json";
		// �½�һ��POST
		HttpPost post = new HttpPost(url);
		// ��Ӳ���
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("source", Constant.consumerKey));
		nvps.add(new BasicNameValuePair("tag_id", tag_id + ""));
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
				JSONObject data = new JSONObject(string);
				String result = data.getString("result");
				Log.v("result", result);
				// try {
				// JSONArray data = new JSONArray(string);
				// if (data.isNull(0)) {
				// Log.v("Tags", "NULL");
				// } else {
				// for (int i = 0; i < data.length(); i++) {
				// JSONObject jo = data.getJSONObject(i);
				// String name = jo.names().getString(0);
				// String value = jo.getString(name);
				// Log.v("name " + i, name);
				// Log.v("value " + i, value);
				// name = jo.names().getString(1);
				// value = jo.getString(name);
				// Log.v("name " + i, name);
				// Log.v("value " + i, value);
				// // Iterator it =jo.keys();
				// // Log.v(it.getClass(),jo.getString((String)it));
				//
				// }
				//
				// Log.v("Index ", "");
				// }
				// } catch (JSONException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// }

				// String ImgPath = data.getString("profile_image_url");
				// //�������־
				// Log.v("ImgPath", ImgPath);
				// String userName = data.getString("screen_name");
				// //�������־
				// Log.v("screen_name", userName);
				// // ��jsonǶ�׽���
				// String status = data.getString("status");
				// JSONObject data1 = new JSONObject(status);
				// String source = data1.getString("source");
				// Log.v("source", source);

			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
