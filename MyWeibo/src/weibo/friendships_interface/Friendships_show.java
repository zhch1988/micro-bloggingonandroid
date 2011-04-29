package weibo.friendships_interface;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import weibo.constant.Constant;
import weibo.constant.Relationship;
import weibo.util.Analyse2Relationship;
import weibo.util.ExecutePost;
import android.util.Log;

/**
 * @author ֣�
 * 
 */
public class Friendships_show {
	/**
	 * ���ص�ǰ��¼�û�������һ���û���ע��ϵ����ϸ���
	 * 
	 * @param tid
	 *            ������Ŀ��id�жϹ�ϵ
	 * @return
	 */
	public Relationship getRelationshipByTID(String tid) {
		Relationship relation = null;
		String url = "http://api.t.sina.com.cn/friendships/show.json";

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("source", Constant.consumerKey));
		nvps.add(new BasicNameValuePair("target_id", tid));

		// �������󣬷��ؽ��
		String string = ExecutePost.executePost(url, nvps);
		if (string != null) {
			// ����json
			try {
				JSONObject jRelationship = new JSONObject(string);
				if (jRelationship != null) {
					relation = Analyse2Relationship
							.json2Relationship(jRelationship);
				}
			} catch (JSONException e) {
				Log.v("Error", "Friendships_show");
				e.printStackTrace();
			}
		}
		return relation;
	}

	/*
	 * ���������û���ע��ϵ����ϸ���
	 * 
	 * @param tSName ������Ŀ���ǳ��жϹ�ϵ
	 * 
	 * @return
	 */
	public Relationship getRelationshipByTargetSName(String targetSname) {
		Relationship relation = null;
		String url = "http://api.t.sina.com.cn/friendships/show.json";

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("source", Constant.consumerKey));
		nvps.add(new BasicNameValuePair("target_screen_name", targetSname));

		// �������󣬷��ؽ��
		String string = ExecutePost.executePost(url, nvps);
		if (string != null) {
			// ����json
			try {
				JSONObject jRelationship = new JSONObject(string);
				if (jRelationship != null) {
					relation = Analyse2Relationship
							.json2Relationship(jRelationship);
				}
			} catch (JSONException e) {
				Log.v("Error", "Friendships_show");
				e.printStackTrace();
			}
		}
		return relation;
	}

	/**
	 * ���������û���ע��ϵ����ϸ���
	 * 
	 * @param sid
	 *            ������û��ѵ�¼���˽ӿڽ��Զ�ʹ�õ�ǰ�û�ID��Ϊsource_id�����ǿ�ǿ��ָ��source_id����ѯ��ע��ϵ
	 * @param tid
	 * @return
	 */
	public Relationship getRelationshipByID(String sid, String tid) {
		Relationship relation = null;
		String url = "http://api.t.sina.com.cn/friendships/show.json";

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("source", Constant.consumerKey));
		nvps.add(new BasicNameValuePair("source_id", sid));
		nvps.add(new BasicNameValuePair("target_id", tid));
		// �������󣬷��ؽ��
		String string = ExecutePost.executePost(url, nvps);

		// ����json
		try {
			JSONObject jRelationship = new JSONObject(string);
			if (jRelationship != null) {
				relation = Analyse2Relationship
						.json2Relationship(jRelationship);
			}
		} catch (JSONException e) {
			Log.v("Error", "Friendships_show");
			e.printStackTrace();
		}
		return relation;
	}

	/**
	 * ���������û���ע��ϵ����ϸ���
	 * 
	 * @param ssName
	 *            ��
	 * @param tSName
	 * @return
	 */
	public Relationship getRelationshipBySName(String ssName, String tSName) {
		Relationship relation = null;
		String url = "http://api.t.sina.com.cn/friendships/show.json";

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("source", Constant.consumerKey));
		nvps.add(new BasicNameValuePair("source_screen_name", ssName));
		nvps.add(new BasicNameValuePair("target_screen_name", tSName));

		// �������󣬷��ؽ��
		String string = ExecutePost.executePost(url, nvps);

		// ����json
		try {
			JSONObject jRelationship = new JSONObject(string);
			if (jRelationship != null) {
				relation = Analyse2Relationship
						.json2Relationship(jRelationship);
			}
		} catch (JSONException e) {
			Log.v("Error", "Friendships_show");
			e.printStackTrace();
		}
		return relation;
	}
}
