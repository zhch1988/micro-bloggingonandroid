package weibo.constant;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author 王景
 * 
 */
public class Comment {
	private String cid;
	private String text;
	private String source;
	private Boolean favorited;
	private Boolean truncated;
	private String created_at;
	private Comment reply_comment;
	private User user;
	private Status status;

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Boolean getFavorited() {
		return favorited;
	}

	public void setFavorited(Boolean favorited) {
		this.favorited = favorited;
	}

	public Boolean getTruncated() {
		return truncated;
	}

	public void setTruncated(Boolean truncated) {
		this.truncated = truncated;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String createdAt) {
		created_at = createdAt;
	}

	public Comment getReply_comment() {
		return reply_comment;
	}

	public void setReply_comment(Comment replyComment) {
		reply_comment = replyComment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * 
	 * @param JSONObject
	 *            ，其他参数不考虑
	 * @throws JSONException
	 */
	public Comment(JSONObject json) throws JSONException {
		if (!json.isNull("id"))
			this.cid = json.getString("id");
		if (!json.isNull("text"))
			this.text = json.getString("text");
		if (!json.isNull("source"))
			this.source = json.getString("source");
		if (!json.isNull("favorited"))
			this.favorited = json.getBoolean("favorited");
		if (!json.isNull("truncated"))
			this.truncated = json.getBoolean("truncated");
		if (!json.isNull("created_at"))
			this.created_at = json.getString("created_at");
		if (!json.isNull("user"))
			// this.user=new User(json.getJSONObject("user"));
			if (!json.isNull("status"))
				this.status = new Status(json.getJSONObject("status"));
		if (!json.isNull("reply_comment"))
			this.reply_comment = new Comment(
					json.getJSONObject("reply_comment"));
	}

	public Comment() {

	}
}
