package weibo.user_interface;

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

// �û��ӿ�
// -------------->users/show �����û�ID��ȡ�û����ϣ���Ȩ�û���
// JSON
// {
// "name" : "΢������ƽ̨",
// "domain" : "openapi",
// "geo_enabled" : true,
// "followers_count" : 13247,
// "statuses_count" : 158,
// "favourites_count" : 0,
// "city" : "8",
// "description" : "����΢������ƽ̨�г��ƹ�ٷ��˺ţ����м������⣬��@΢��API���߷�˽�Ÿ�΢��API",
// "verified" : true,
// "status" :
// {
// "created_at" : "Mon Nov 29 16:08:43 +0800 2010",
// "text" : "��λ�����ߣ����ǵ���̳������~http://sinaurl.cn/h4FWc7
// ��ӭ��ҵĲ���~���⣬���ڼ�����ص����⣬��������̳�������Ҳ����@΢��API ����ٷ�����֧���˺�Ŷ~��л��ҶԿ���ƽ̨��֧��~[�Ǻ�]",
// "truncated" : false,
// "in_reply_to_status_id" : "",
// "in_reply_to_screen_name" : "",
// "geo" : null,
// "favorited" : false,
// "in_reply_to_user_id" : "",
// "id" : 3958728723,
// "source" : "<a href=\"http://t.sina.com.cn\" rel=\"nofollow\">����΢��</a>"
// },
// "id" : 11051,
// "gender" : "m",
// "friends_count" : 5,
// "screen_name" : "΢������ƽ̨",
// "allow_all_act_msg" : true,
// "following" : false,
// "url" : "http://open.t.sina.com.cn/",
// "profile_image_url" : "http://tp4.sinaimg.cn/11051/50/1280283165/1",
// "created_at" : "Wed Jan 20 00:00:00 +0800 2010",
// "province" : "11",
// "location" : "���� ������"
// }
public class Users_show {
	public Users_show() {
		//json�����ʽ
		//curl -u "username:password" "http://api.t.sina.com.cn/users/show.json?source=appkey&user_id=11051
		//api��ַ
		String url = "http://api.t.sina.com.cn/users/show.json";
		//�½�һ��POST
		HttpPost post = new HttpPost(url);
		//��Ӳ���
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("source", Constant.consumerKey));
		nvps.add(new BasicNameValuePair("user_id", Constant.user_id));
		//�������ӵ�post��
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
		//����CommonsHttpOAuthConsumer����
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
		//��HttpResponse�е����ݶ���buffer��
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
				//��bufferתΪjson����֧�ֵ�String����
				String string = buffer.toString();
				//����rp
				rp.getEntity().consumeContent();
				//�½�JSONObject���������е�����
				JSONObject data = new JSONObject(string);
				
				String ImgPath = data.getString("profile_image_url");
				//�������־
				Log.v("ImgPath", ImgPath);
				String userName = data.getString("screen_name");
				//�������־
				Log.v("screen_name", userName);
				// ��jsonǶ�׽���
				String status = data.getString("status");
				JSONObject data1 = new JSONObject(status);
				String source = data1.getString("source");
				Log.v("source", source);

			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}
}
