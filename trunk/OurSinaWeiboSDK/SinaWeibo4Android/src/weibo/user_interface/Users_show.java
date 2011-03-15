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
 * @author 王晓龙
 * 
 */

// 用户接口
// -------------->users/show 根据用户ID获取用户资料（授权用户）
// JSON
// {
// "name" : "微博开放平台",
// "domain" : "openapi",
// "geo_enabled" : true,
// "followers_count" : 13247,
// "statuses_count" : 158,
// "favourites_count" : 0,
// "city" : "8",
// "description" : "新浪微博开放平台市场推广官方账号，如有技术问题，请@微博API或者发私信给微博API",
// "verified" : true,
// "status" :
// {
// "created_at" : "Mon Nov 29 16:08:43 +0800 2010",
// "text" : "各位开发者，我们的论坛上线啦~http://sinaurl.cn/h4FWc7
// 欢迎大家的参与~另外，关于技术相关的问题，可以在论坛上提出，也可以@微博API 这个官方技术支持账号哦~感谢大家对开放平台的支持~[呵呵]",
// "truncated" : false,
// "in_reply_to_status_id" : "",
// "in_reply_to_screen_name" : "",
// "geo" : null,
// "favorited" : false,
// "in_reply_to_user_id" : "",
// "id" : 3958728723,
// "source" : "<a href=\"http://t.sina.com.cn\" rel=\"nofollow\">新浪微博</a>"
// },
// "id" : 11051,
// "gender" : "m",
// "friends_count" : 5,
// "screen_name" : "微博开放平台",
// "allow_all_act_msg" : true,
// "following" : false,
// "url" : "http://open.t.sina.com.cn/",
// "profile_image_url" : "http://tp4.sinaimg.cn/11051/50/1280283165/1",
// "created_at" : "Wed Jan 20 00:00:00 +0800 2010",
// "province" : "11",
// "location" : "北京 海淀区"
// }
public class Users_show {
	public Users_show() {
		//json请求格式
		//curl -u "username:password" "http://api.t.sina.com.cn/users/show.json?source=appkey&user_id=11051
		//api地址
		String url = "http://api.t.sina.com.cn/users/show.json";
		//新建一个POST
		HttpPost post = new HttpPost(url);
		//添加参数
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("source", Constant.consumerKey));
		nvps.add(new BasicNameValuePair("user_id", Constant.user_id));
		//将参数加到post中
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
		//生成CommonsHttpOAuthConsumer对象
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
		//将HttpResponse中的内容读到buffer中
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
				//将buffer转为json可以支持的String类型
				String string = buffer.toString();
				//销毁rp
				rp.getEntity().consumeContent();
				//新建JSONObject来处理其中的数据
				JSONObject data = new JSONObject(string);
				
				String ImgPath = data.getString("profile_image_url");
				//输出到日志
				Log.v("ImgPath", ImgPath);
				String userName = data.getString("screen_name");
				//输出到日志
				Log.v("screen_name", userName);
				// 对json嵌套解析
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
