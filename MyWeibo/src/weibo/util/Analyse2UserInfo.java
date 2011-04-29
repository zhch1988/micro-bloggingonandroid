package weibo.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import weibo.constant.Status;
import weibo.constant.User;

/**
 * @author 郑璨
 */
public class Analyse2UserInfo {

	public static User json2UserInfo(JSONObject jUser) {

		User user = new User();

		JSONObject jStatus = jUser.optJSONObject("status");
		if (jStatus != null) { // 保存返回的最新发布的一条微博消息
			Status s = Analyse2Status.json2Status(jStatus);
			List<Status> stemp = new ArrayList<Status>();
			stemp.add(s); // 只取第一条就是最新的微博了
			user.setStatuseList(stemp);
		}
		// 保存返回的用户资料 21个基本属性
		user.setUid(jUser.optString("id"));
		user.setProfile_image_url(jUser.optString("profile_image_url"));
		user.setScreen_name(jUser.optString("screen_name"));
		user.setName(jUser.optString("name"));
		user.setGender(jUser.optString("gender"));
		user.setProvince(jUser.optString("province"));
		user.setCity(jUser.optString("city"));
		user.setLocation(jUser.optString("location"));
		user.setUrl(jUser.optString("url"));
		user.setDomain(jUser.optString("domain"));
		user.setDescription(jUser.optString("description"));
		user.setCreated_at(jUser.optString("created_at"));
		user.setFriends_count(jUser.optString("friends_count"));
		user.setFollowers_count(jUser.optString("followers_count"));
		user.setStatuses_count(jUser.optString("statuses_count"));
		user.setFavourites_count(jUser.optString("favourites_count"));
		user.setVerified(jUser.optBoolean("verified"));
		user.setFollowing(jUser.optBoolean("following"));
		user.setAllow_all_act_msg(jUser.optBoolean("allow_all_act_msg"));
		user.setGeo_enabled(jUser.optBoolean("geo_enabled"));
		user.setRemark(jUser.optString("remark"));
		return user;
	}
}
