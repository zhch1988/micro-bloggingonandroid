package weibo.constant;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author Íõ¾°
 * 
 */
public class Status {

	private User user_Author = null;
	private String created_at;
	private String sid;
	private String text;
	private String source;
	private boolean truncated;
	private String in_reply_to_status_id;
	private String in_reply_to_user_id;
	private boolean favorited;
	private String in_reply_to_screen_name;
	private double latitude = -1;
	private double longitude = -1;
	private String thumbnail_pic;
	private String bmiddle_pic;
	private String original_pic;
	private Status retweeted_status;

	public User getUser_Author() {
		return user_Author;
	}

	public void setUser_Author(User userAuthor) {
		user_Author = userAuthor;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String createdAt) {
		created_at = createdAt;
	}

	public String getSId() {
		return sid;
	}

	public void setSId(String sid) {
		this.sid = sid;
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

	public boolean isTruncated() {
		return truncated;
	}

	public void setTruncated(boolean truncated) {
		this.truncated = truncated;
	}

	public String getIn_reply_to_status_id() {
		return in_reply_to_status_id;
	}

	public void setIn_reply_to_status_id(String inReplyToStatusId) {
		in_reply_to_status_id = inReplyToStatusId;
	}

	public String getIn_reply_to_user_id() {
		return in_reply_to_user_id;
	}

	public void setIn_reply_to_user_id(String inReplyToUserId) {
		in_reply_to_user_id = inReplyToUserId;
	}

	public boolean isFavorited() {
		return favorited;
	}

	public void setFavorited(boolean favorited) {
		this.favorited = favorited;
	}

	public String getIn_reply_to_screen_name() {
		return in_reply_to_screen_name;
	}

	public void setIn_reply_to_screen_name(String inReplyToScreenName) {
		in_reply_to_screen_name = inReplyToScreenName;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getThumbnail_pic() {
		return thumbnail_pic;
	}

	public void setThumbnail_pic(String thumbnailPic) {
		thumbnail_pic = thumbnailPic;
	}

	public String getBmiddle_pic() {
		return bmiddle_pic;
	}

	public void setBmiddle_pic(String bmiddlePic) {
		bmiddle_pic = bmiddlePic;
	}

	public String getOriginal_pic() {
		return original_pic;
	}

	public void setOriginal_pic(String originalPic) {
		original_pic = originalPic;
	}

	public Status getRetweeted_status() {
		return retweeted_status;
	}

	public void setRetweeted_status(Status retweetedStatus) {
		retweeted_status = retweetedStatus;
	}

	public Status(JSONObject json) throws JSONException {
		if (!json.isNull("id"))
			this.sid = json.getString("id");
		if (!json.isNull("text"))
			text = json.getString("text");
		if (!json.isNull("source"))
			source = json.getString("source");
		if (!json.isNull("created_at"))
			created_at = json.getString("created_at");
		if (!json.isNull("favorited"))
			favorited = json.getBoolean("favorited");
		if (!json.isNull("truncated"))
			truncated = json.getBoolean("truncated");
		if (!json.isNull("in_reply_to_status_id"))
			in_reply_to_status_id = json.getString("in_reply_to_status_id");
		if (!json.isNull("in_reply_to_user_id"))
			in_reply_to_user_id = json.getString("in_reply_to_user_id");
		if (!json.isNull("in_reply_to_screen_name"))
			in_reply_to_screen_name = json.getString("in_reply_to_screen_name");
		if (!json.isNull("thumbnail_pic"))
			thumbnail_pic = json.getString("thumbnail_pic");
		if (!json.isNull("bmiddle_pic"))
			bmiddle_pic = json.getString("bmiddle_pic");
		if (!json.isNull("original_pic"))
			original_pic = json.getString("original_pic");
		if (!json.isNull("user"))
			user_Author = new User(json.getJSONObject("user"));
		if (!json.isNull("retweeted_status")) {
			retweeted_status = new Status(
					json.getJSONObject("retweeted_status"));
		}
	}

	public Status() {

	}
}