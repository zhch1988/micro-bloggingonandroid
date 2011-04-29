package weibo.statuses_interface;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import weibo.constant.Constant;
import weibo.util.ExecutePost;

/**
 * @author 郑璨
 * 
 */
public class User_statuses_id {
	/**
	 * 跳转到单条微博的Web地址。可以通过此url跳转到微博对应的Web网页。
	 * 
	 * @param uid
	 * @param sid
	 */
	public void gotoStatusWeb(String uid, String sid) {
		String url = "http://api.t.sina.com.cn/" + uid + "/statuses/" + sid
				+ ".json";

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("source", Constant.consumerKey));
		// 建立请求，返回结果
		ExecutePost.executePost(url, nvps);
	}
}
