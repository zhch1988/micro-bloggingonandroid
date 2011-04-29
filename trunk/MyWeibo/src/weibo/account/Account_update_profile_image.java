package weibo.account;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;

import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.json.JSONException;
import org.json.JSONObject;

import weibo.constant.Constant;
import android.util.Log;

/**
 * 
 * @author ������
 * 
 */

public class Account_update_profile_image {
	public Account_update_profile_image(String imageUri) {
		System.setProperty("debug", "1");
		// json�����ʽ
		// api��ַ
		String url = "http://api.t.sina.com.cn/account/update_profile_image.json";
		// �½�һ��POST
		HttpPost post = new HttpPost(url);
		// �������ӵ�post��
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

		// ��Ӳ���
		MultipartEntity entity = new MultipartEntity(HttpMultipartMode.STRICT,
				null, Charset.forName("UTF-8"));
		File file = new File(imageUri);
		Log.v("File ", "exit" + file.exists());
		FileBody cbFile = new FileBody(file, "image/jpeg");
		try {
			// ��Ӳ���
			// ContentBody cbString = new
			// StringBody(Constant.consumerKey,Charset.forName("UTF-8"));
			// entity.addPart(URLEncoder.encode("source","UTF-8"), cbString);
			entity.addPart("image", cbFile);
			post.setEntity(entity);
			// ����post�������HttpResponse
			HttpClient hc = new DefaultHttpClient();
			HttpResponse rp = null;
			rp = hc.execute(post);
			// ��HttpResponse�е����ݶ���buffer��
			int ret = rp.getStatusLine().getStatusCode();
			if (200 == ret) {
				try {
					Log.v("getStatusLine", "OK");
					InputStream is = rp.getEntity().getContent();
					Reader reader = new BufferedReader(
							new InputStreamReader(is), 4000);
					StringBuilder buffer = new StringBuilder((int) rp
							.getEntity().getContentLength());
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
					String ImgPath = data.getString("profile_image_url");
					// �������־
					Log.v("ImgPath", ImgPath);
					String userName = data.getString("screen_name");
					// �������־
					Log.v("screen_name", userName);
					// ��jsonǶ�׽���
					String status = data.getString("status");
					JSONObject data1 = new JSONObject(status);
					String source = data1.getString("source");
					Log.v("source", source);
					Log.v("upload image file ok", imageUri);
					// Log.v("file deleted", imageUri+file.delete());
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				// Log.v("post",post.g);
				Log.v("ret code", ret + "");
				Log.v("rp", rp.toString());
			}

		} catch (IllegalCharsetNameException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedCharsetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
