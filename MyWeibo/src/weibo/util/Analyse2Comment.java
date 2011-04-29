package weibo.util;

import org.json.JSONObject;

import weibo.constant.Comment;
import weibo.constant.Status;
import weibo.constant.User;

/**
 * 
 * @author 郑璨
 * 
 */
public class Analyse2Comment {

	public static Comment analyse2Comment(JSONObject jComment) {

		Comment comment = new Comment();
		JSONObject jRComment = jComment.optJSONObject("reply_comment");
		if (jRComment != null) {
			Comment rComment = Analyse2Comment.analyse2Comment(jRComment);
			comment.setReply_comment(rComment);
		}
		JSONObject jUser = jComment.optJSONObject("user");
		if (jUser != null) {
			User userAuthor = Analyse2UserInfo.json2UserInfo(jUser);
			comment.setUser(userAuthor); // 新评论的作者
		}
		JSONObject jStatus = jComment.optJSONObject("status");
		if (jStatus != null) {
			Status status = Analyse2Status.json2Status(jStatus);
			comment.setStatus(status);
		}
		comment.setCreated_at(jComment.optString("created_at"));
		comment.setCid(jComment.optString("id"));
		comment.setSource(jComment.optString("source"));
		comment.setText(jComment.optString("text"));

		return comment;
	}
}
