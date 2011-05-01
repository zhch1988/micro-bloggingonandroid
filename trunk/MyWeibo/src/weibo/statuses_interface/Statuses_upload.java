package weibo.statuses_interface;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.http.HttpParameters;

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
	 * 王晓龙更改于5月1号
	 * @param statusText
	 * @return
	 */
	public Status uploadStatus(String statusText, String fileName) {
		System.setProperty("Debug", "1");
		Status status = null;
		String url;
		try {
			url = "http://api.t.sina.com.cn/statuses/upload.json";		
			OAuthConsumer choc = new DefaultOAuthConsumer(
					Constant.consumerKey, Constant.consumerSecret);
			choc.setTokenWithSecret(Constant.userKey, Constant.userSecret);
			URL url1 = new URL("http://api.t.sina.com.cn/statuses/upload.json");
	    	HttpURLConnection request = (HttpURLConnection) url1.openConnection();
	    	request.setDoOutput(true);
	    	request.setRequestMethod("POST");
	    	HttpParameters para = new HttpParameters();
	    	String status1 = URLEncoder.encode(statusText,"UTF-8").replaceAll("\\+", "%20");
	    	para.put("status", status1);
	    	String boundary = "---------------------------37531613912423";
	    	String content = "--"+boundary+"\r\nContent-Disposition: form-data; name=\"status\"\r\n\r\n";
			String pic = "\r\n--"+boundary+"\r\nContent-Disposition: form-data; name=\"pic\"; filename=\"postpic.jpg\"\r\nContent-Type: image/jpeg\r\n\r\n";
	    	byte[] end_data = ("\r\n--" + boundary + "--\r\n").getBytes();  
	    	File f = new File(fileName);
			FileInputStream stream = new FileInputStream(f);
			byte[] file = new byte[(int)f.length()];
			stream.read(file);
			request.setRequestProperty("Connection", "Keep-Alive");
			request.setRequestProperty("Content-Type", "multipart/form-data; boundary="+boundary); //设置表单类型和分隔符  
		//	request.setRequestProperty("Content-Length", String.valueOf(content.getBytes().length+"test".getBytes().length+pic.getBytes().length+f.length()+end_data.length)); //设置内容长度  
			choc.setAdditionalParameters(para);
			choc.sign(request);
	        OutputStream ot = request.getOutputStream();
	        ot.write(content.getBytes());
	        ot.write(status1.getBytes());
	        ot.write(pic.getBytes());
	        ot.write(file);
	        ot.write(end_data);
	        ot.flush();
	        ot.close();
	    	System.out.println("Sending request...");
	    	request.connect();
	    	System.out.println("Response: " + request.getResponseCode() + " "
	    			+ request.getResponseMessage());
			BufferedReader reader =new BufferedReader(new InputStreamReader(request.getInputStream()));
			String b = null;
			String jstring = null;
			while((b = reader.readLine())!=null){
				System.out.println(b);
				jstring = b; 
			}
			if (200 == request.getResponseCode()) {
					request.disconnect();
					// 解析json ???有没有其他方法不要这么多try not found
				
						JSONObject jStatus = new JSONObject(jstring);
						if (jStatus != null) {
							status = Analyse2Status.json2Status(jStatus);
						}
						return status;
					
		} 
			return null;
		
		
		
	}catch (Exception e) {
		Log.v("Error", "15");
		e.printStackTrace();
		return null;
	}
	
	}
	
}
