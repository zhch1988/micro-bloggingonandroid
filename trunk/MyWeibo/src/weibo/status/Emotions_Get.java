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
import org.json.JSONArray;
import org.json.JSONException;

import weibo.constant.Constant;
import weibo.constant.Emotions;
import android.util.Log;

/**
 * @author 王景
 */

public class Emotions_Get {
	List<NameValuePair> demo;
	List<Emotions> emotions;
	String result;
	String url = "http://api.t.sina.com.cn/emotions.json";

	public Emotions_Get() {

		HttpPost post = new HttpPost(url);
		demo = new ArrayList<NameValuePair>();
		demo.add(new BasicNameValuePair("source", Constant.consumerKey));
		Log.v("demo", "ok");
		init(demo, post);
	}

	public Emotions_Get(String type) {
		HttpPost post = new HttpPost(url);
		demo = new ArrayList<NameValuePair>();
		demo.add(new BasicNameValuePair("source", Constant.consumerKey));
		if (type == "face" || type == "ani" || type == "cartoon")
			demo.add(new BasicNameValuePair("type", type));
		else if (type == "" || type == "")
			demo.add(new BasicNameValuePair("language", type));
		Log.v("demo", "ok");
		init(demo, post);
	}

	public Emotions_Get(String language, String type) {
		HttpPost post = new HttpPost(url);
		demo = new ArrayList<NameValuePair>();
		demo.add(new BasicNameValuePair("source", Constant.consumerKey));
		demo.add(new BasicNameValuePair("type", type));
		demo.add(new BasicNameValuePair("language", language));
		init(demo, post);
	}

	private void init(List<NameValuePair> demo, HttpPost post) {
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

	public List<Emotions> GetEmotions() {
		JSONArray array;
		try {
			array = new JSONArray(result);
			emotions = new ArrayList<Emotions>(array.length());
			for (int i = 0; i < array.length(); i++)
				emotions.add(new Emotions(array.getJSONObject(i)));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return emotions;

	}
}
