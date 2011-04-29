package weibo.util;

import org.json.JSONObject;

import weibo.constant.DirectMessage;

/**
 * 
 * @author Ö£è²
 * 
 */
public class Analyse2DirectMessage {

	public static DirectMessage json2DirectMessage(JSONObject jDM) {

		DirectMessage newMessage = new DirectMessage();
		newMessage.setDmid(jDM.optString("id"));
		newMessage.setSender_id(jDM.optString("sender_id"));
		newMessage.setSender_screen_name(jDM.optString("sender_screen_name"));
		newMessage.setRecipient_id(jDM.optString("recipient_id"));
		newMessage.setRecipient_screen_name(jDM
				.optString("recipient_screen_name"));
		newMessage.setCreated_at(jDM.optString("created_at"));
		newMessage.setText(jDM.optString("text"));
		return newMessage;
	}
}
