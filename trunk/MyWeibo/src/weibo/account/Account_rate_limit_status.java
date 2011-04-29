package weibo.account;

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
 * @author 王晓龙
 * 
 */
// {
// "hourly_limit":150,
// "reset_time_in_seconds":1264994122,
// "reset_time":"Mon Feb 01 03:15:22 +0800 2010",
// "remaining_hits":150
// }
public class Account_rate_limit_status {
	public Account_rate_limit_status() {
		// json请求格式
		// api地址
		String url = "http://api.t.sina.com.cn/account/rate_limit_status.json";
		// 新建一个POST
		HttpPost post = new HttpPost(url);
		// 添加参数
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("source", Constant.consumerKey));
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
				JSONObject data = new JSONObject(string);

				String hourly_limit = data.getString("hourly_limit");
				// 输出到日志
				Log.v("hourly_limit", hourly_limit);

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
