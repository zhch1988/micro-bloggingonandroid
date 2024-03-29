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
	 * comment ： 谁可以评论此账号的微薄。0：所有人，1：我关注的人。 dm ： 谁可以给此账号发私信。0：所有人，1：我关注的人。
	 * real_name： 是否允许别人通过真实姓名搜索到我， 0允许，1不允许。 geo ： 发布微博，是否允许微博保存并显示所处的地理位置信息。
	 * 0允许，1不允许。 badge ： 勋章展现状态。1私密状态，0公开状态。
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
		// 新建一个POST
		HttpPost post = new HttpPost(url);

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
		if (200 == rp.getStatusLine().getStatusCode()) {
			try {
				Log.v("getStatusLine", "OK");
				// 将HttpResponse中的内容读到buffer中
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
					 * comment ： 谁可以评论此账号的微薄。0：所有人，1：我关注的人。 dm ：
					 * 谁可以给此账号发私信。0：所有人，1：我关注的人。 real_name： 是否允许别人通过真实姓名搜索到我，
					 * 0允许，1不允许。 geo ： 发布微博，是否允许微博保存并显示所处的地理位置信息。 0允许，1不允许。
					 * badge ： 勋章展现状态。1私密状态，0公开状态。
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
