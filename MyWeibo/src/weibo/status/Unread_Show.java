package weibo.status;

/**
 * @author 王景
 */
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
import weibo.constant.Unread;
import android.util.Log;

/**
 * @author 王景
 */

public class Unread_Show {
	Unread unread;
	String result;

	public Unread_Show() {
		String url = "http://api.t.sina.com.cn/statuses/unread.json";
		HttpPost post = new HttpPost(url);
		// HttpDelete del=new HttpDelete(url);

		List<NameValuePair> demo = new ArrayList<NameValuePair>();
		demo.add(new BasicNameValuePair("source", Constant.consumerKey));
		Log.v("demo", "ok");
		try {
			post.setEntity(new UrlEncodedFormEntity(demo, HTTP.UTF_8));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		post.getParams().setBooleanParameter(
				CoreProtocolPNames.USE_EXPECT_CONTINUE, false);
		CommonsHttpOAuthConsumer coc = new CommonsHttpOAuthConsumer(
				Constant.consumerKey, Constant.consumerSecret);
		coc.setTokenWithSecret(Constant.userKey, Constant.userSecret);

		try {
			coc.sign(post);
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
		HttpClient hc = new DefaultHttpClient();
		org.apache.http.HttpResponse hr = null;
		try {
			hr = hc.execute(post);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.v("code", hr.getStatusLine().getStatusCode() + "");
		if (200 == hr.getStatusLine().getStatusCode()) {
			try {
				Log.v("getStatusLine", "OK");
				InputStream is = hr.getEntity().getContent();
				Reader reader = new BufferedReader(new InputStreamReader(is),
						4000);
				StringBuilder buffer = new StringBuilder((int) hr.getEntity()
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
				// 将buffer转为json可以支持的String类型
				result = buffer.toString();
				Log.v("result", "ok");
				// 销毁hr
				hr.getEntity().consumeContent();
				// JSONArray data = new JSONArray(string);

			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 返回一个Unread类的实例，可以调用各种get方法
	 * 
	 * @return Unread object
	 */
	public Unread GetUnread() {
		try {
			JSONObject json = new JSONObject(result);
			unread = new Unread(json);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return unread;

	}
}
