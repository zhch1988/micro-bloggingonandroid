package main.logic;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.HTTP;

import weibo.constant.Constant;

public class CheckNetWork {
	
	public static boolean checkNetWorkStatus() {   
		String url = "http://api.t.sina.com.cn/help/test.json";
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
			if(rp==null){
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	
    }   
}
