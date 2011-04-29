package weibo.constant;

/**
 * 7个基本属性
 * 
 * @author 郑璨
 * 
 */
public class DirectMessage {
	private String dmid;
	private String sender_id;
	private String sender_screen_name;
	private String recipient_id;
	private String recipient_screen_name;
	private String text;
	private String created_at;

	public String getDmid() {
		return dmid;
	}

	public void setDmid(String dmid) {
		this.dmid = dmid;
	}

	public String getSender_id() {
		return sender_id;
	}

	public void setSender_id(String senderId) {
		sender_id = senderId;
	}

	public String getSender_screen_name() {
		return sender_screen_name;
	}

	public void setSender_screen_name(String senderScreenName) {
		sender_screen_name = senderScreenName;
	}

	public String getRecipient_id() {
		return recipient_id;
	}

	public void setRecipient_id(String recipientId) {
		recipient_id = recipientId;
	}

	public String getRecipient_screen_name() {
		return recipient_screen_name;
	}

	public void setRecipient_screen_name(String recipientScreenName) {
		recipient_screen_name = recipientScreenName;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String createdAt) {
		created_at = createdAt;
	}
}
