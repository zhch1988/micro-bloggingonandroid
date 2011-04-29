package weibo.constant;

import org.json.JSONException;
import org.json.JSONObject;

public class Unread {
	private String comments;
	private String followers;
	private String new_status;
	private String dm;
	private String mentions;

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getFollowers() {
		return followers;
	}

	public void setFollowers(String followers) {
		this.followers = followers;
	}

	public String getNew_status() {
		return new_status;
	}

	public void setNew_status(String newStatus) {
		new_status = newStatus;
	}

	public String getDm() {
		return dm;
	}

	public void setDm(String dm) {
		this.dm = dm;
	}

	public String getMentions() {
		return mentions;
	}

	public void setMentions(String mentions) {
		this.mentions = mentions;
	}

	public Unread(JSONObject json) throws JSONException {
		if (!json.isNull("comments"))
			comments = json.getString("comments");
		if (!json.isNull("followers"))
			followers = json.getString("followers");
		if (!json.isNull("new_status"))
			new_status = json.getString("new_status");
		if (!json.isNull("dm"))
			dm = json.getString("dm");
		if (!json.isNull("mentions"))
			mentions = json.getString("mentions");

	}
}
