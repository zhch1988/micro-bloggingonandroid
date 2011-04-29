package weibo.util;

import org.json.JSONObject;

import weibo.constant.Relationship;
import weibo.constant.User;

/**
 * 
 * @author Ö£è²
 * 
 */
public class Analyse2Relationship {

	public static Relationship json2Relationship(JSONObject jRelationship) {

		Relationship relation = new Relationship();
		JSONObject jSource = jRelationship.optJSONObject("source");
		JSONObject jTarget = jRelationship.optJSONObject("target");
		if (jSource != null) {
			User source = Analyse2UserInfo.json2UserInfo(jSource);
			relation.setSource(source);
		}
		if (jTarget != null) {
			User target = Analyse2UserInfo.json2UserInfo(jTarget);
			relation.setTarget(target);
		}
		return relation;
	}
}
