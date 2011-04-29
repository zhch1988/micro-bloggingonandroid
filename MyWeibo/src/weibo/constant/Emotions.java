package weibo.constant;

import org.json.JSONException;
import org.json.JSONObject;

public class Emotions {
	private String phrase;
	private String type;
	private String url;
	private String is_hot;
	private String order_number;
	private String category;

	public String getPhrase() {
		return phrase;
	}

	public void setPhrase(String phrase) {
		this.phrase = phrase;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIs_hot() {
		return is_hot;
	}

	public void setIs_hot(String isHot) {
		is_hot = isHot;
	}

	public String getOrder_number() {
		return order_number;
	}

	public void setOrder_number(String orderNumber) {
		order_number = orderNumber;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Emotions(JSONObject json) throws JSONException {
		if (!json.isNull("category"))
			category = json.getString("category");
		if (!json.isNull(is_hot))
			is_hot = json.getString("is_hot");
		if (!json.isNull("order_number"))
			order_number = json.getString("order_number");
		if (!json.isNull("phrase"))
			phrase = json.getString("phrase");
		if (!json.isNull("type"))
			type = json.getString("type");
		if (!json.isNull("url"))
			url = json.getString("url");
	}
}
