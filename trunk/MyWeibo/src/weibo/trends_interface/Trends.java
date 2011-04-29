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
import weibo.constant.Trend;
import android.util.Log;

/**
 * @author Zheng Chen
 */

public class Trends {
	private List<Trend> trendlist = new ArrayList<Trend>();

	public List<Trend> getTrendlist() {
		return trendlist;
	}

	public Trends() {
		String url = "http://api.t.sina.com.cn/trends.json";
		// 新建一个POST
		HttpPost post = new HttpPost(url);
		// 添加参数
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("source", Constant.consumerKey));
		nvps.add(new BasicNameValuePair("user_id", Constant.user_id));
		// 将参数加到post中
		try {
			post.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 关闭Expect:100-Continue握手
		// 100-Continue握手需谨慎使用，因为遇到不支持HTTP/1.1协议的服务器或者代理时会引起问题
		post.getParams().setBooleanParameter(
				CoreProtocolPNames.USE_EXPECT_CONTINUE, false);
		// 生成CommonsHttpOAuthConsumer对象
		CommonsHttpOAuthConsumer choc = new CommonsHttpOAuthConsumer(
				Constant.consumerKey, Constant.consumerSecret);
		choc.setTokenWithSecret(Constant.userKey, Constant.userSecret);
		// 对post进行签名
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
		// 发送post用来获得HttpResponse
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
		// 将HttpResponse中的内容读到buffer中
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
				// 将buffer转为json可以支持的String类型
				String string = buffer.toString();
				// 销毁rp
				rp.getEntity().consumeContent();
				// 新建JSONObject来处理其中的数据
				try {
					// 接受到的JSONArray数组，可利用下标访问每个元素
					JSONArray data = new JSONArray(string);
					Log.v("nums_of_trends", String.valueOf(data.length()));
					if (data.isNull(0))
						Log.v("isNull", "no trend");
					else {
						// 访问每个JSONObject
						for (int i = 0; i < data.length(); i++) {
							JSONObject trend = data.getJSONObject(i);
							trendlist.add(new Trend(trend));
							// String num = trend.getString("num");
							// Log.v("num " + i, num);
							// String hotword = trend.getString("hotword");
							// Log.v("hotword " + i, hotword);
							// String trend_id = trend.getString("trend_id");
							// Log.v("trend_id " + i, trend_id);
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
