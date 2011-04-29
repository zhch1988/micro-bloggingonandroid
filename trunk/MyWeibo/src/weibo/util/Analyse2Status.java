package weibo.util;

import org.json.JSONObject;

import weibo.constant.Status;
import weibo.constant.User;

/**
 * 
 * @author 郑璨
 * 
 */
public class Analyse2Status {

	public static Status json2Status(JSONObject jStatus) {

		Status status = new Status();
		JSONObject jRetweeted_Status = jStatus
				.optJSONObject("retweeted_status");
		if (jRetweeted_Status != null) { // 是否是转发的微博
			Status rStatus = Analyse2Status.json2Status(jRetweeted_Status);
			status.setRetweeted_status(rStatus);
		}
		JSONObject jUser = jStatus.optJSONObject("user");
		if (jUser != null) {
			User userAuthor = Analyse2UserInfo.json2UserInfo(jUser);
			status.setUser_Author(userAuthor);
		}
		// 12个属性
		status.setCreated_at(jStatus.optString("created_at"));
		status.setSId(jStatus.optString("id"));
		status.setText(jStatus.optString("text"));
		status.setSource(jStatus.optString("source"));
		status.setFavorited(jStatus.optBoolean("favorited"));
		status.setTruncated(jStatus.optBoolean("truncated"));
		status.setIn_reply_to_status_id(jStatus
				.optString("in_reply_to_status_id"));
		status.setIn_reply_to_screen_name(jStatus
				.optString("in_reply_to_screen_name"));
		status.setIn_reply_to_user_id(jStatus.optString("in_reply_to_user_id"));
		status.setBmiddle_pic(jStatus.optString("bmiddle_pic"));
		status.setOriginal_pic(jStatus.optString("original_pic"));
		status.setThumbnail_pic(jStatus.optString("thumbnail_pic"));
		return status;
	}
}
