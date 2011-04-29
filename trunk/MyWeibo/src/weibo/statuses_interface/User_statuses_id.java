package weibo.statuses_interface;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import weibo.constant.Constant;
import weibo.util.ExecutePost;

/**
 * @author ֣�
 * 
 */
public class User_statuses_id {
	/**
	 * ��ת������΢����Web��ַ������ͨ����url��ת��΢����Ӧ��Web��ҳ��
	 * 
	 * @param uid
	 * @param sid
	 */
	public void gotoStatusWeb(String uid, String sid) {
		String url = "http://api.t.sina.com.cn/" + uid + "/statuses/" + sid
				+ ".json";

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("source", Constant.consumerKey));
		// �������󣬷��ؽ��
		ExecutePost.executePost(url, nvps);
	}
}
