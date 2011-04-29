package weibo.constant;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author Íõ¾°
 * 
 */
public class Counts {
	private String id;
	private String comments;
	private String rt;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getRt() {
		return rt;
	}

	public void setRt(String rt) {
		this.rt = rt;
	}

	public Counts(JSONObject json) throws JSONException {
		if (!json.isNull("id"))
			this.id = json.getString("id");
		if (!json.isNull("comments"))
			this.comments = json.getString("comments");
		if (!json.isNull("rt"))
			this.rt = json.getString("rt");
	}
}
