package weibo.statuses_interface;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.json.JSONException;
import org.json.JSONObject;

import weibo.constant.Constant;
import weibo.constant.Status;
import weibo.util.Analyse2Status;
import android.util.Log;

public class Statuses_upload {
	/**
	 * 发表图片微博消息
	 * 
	 * 王晓龙更改于4月29号
	 * @param statusText
	 * @return
	 */
	public Status uploadStatus(String statusText, String fileName) {
		Status status = null;
		String url = "http://api.t.sina.com.cn/statuses/upload.json";
		HttpPost post = new HttpPost(url);
		post.getParams().setBooleanParameter(
				CoreProtocolPNames.USE_EXPECT_CONTINUE, false);
		CommonsHttpOAuthConsumer choc = new CommonsHttpOAuthConsumer(
				Constant.consumerKey, Constant.consumerSecret);
		choc.setTokenWithSecret(Constant.userKey, Constant.userSecret);
		/*
		 * wangxiaolong
		 */
		post.getParams().setParameter("source", Constant.consumerKey);
		post.getParams().setParameter("status", statusText);
		
		
		try {
			choc.sign(post);
		} catch (OAuthMessageSignerException e) {
			e.printStackTrace();
		} catch (OAuthExpectationFailedException e) {
			e.printStackTrace();
		} catch (OAuthCommunicationException e) {
			e.printStackTrace();
		}
		MultipartEntity entity = new MultipartEntity();
		File file = new File(fileName);
		Log.v("File ", "exit" + file.exists());
//		StringBody source;
//		StringBody statusTemp;
		ContentBody cbFile = new FileBody(file, "image/jpeg");
//		try {
//			source = new StringBody(Constant.consumerKey);
//			statusTemp = new StringBody(statusText);
//			entity.addPart("source", source);
//			entity.addPart("status", statusTemp);
			entity.addPart("pic", cbFile);
			post.setEntity(entity);
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		HttpClient hc = new DefaultHttpClient();
		HttpResponse rp = null;
		//
		hc.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
		try {
			System.out.println("executing request " + post.getRequestLine());
			rp = hc.execute(post);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (200 == rp.getStatusLine().getStatusCode()) {
			try {
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
				String string = buffer.toString();// 将buffer转为json可以支持的String类型
				rp.getEntity().consumeContent(); // 销毁rp

				// 解析json ???有没有其他方法不要这么多try not found
				try {
					JSONObject jStatus = new JSONObject(string);
					if (jStatus != null) {
						status = Analyse2Status.json2Status(jStatus);
					}
				} catch (JSONException e) {
					Log.v("Error", "15");
					e.printStackTrace();
				}
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return status;
	}
}
