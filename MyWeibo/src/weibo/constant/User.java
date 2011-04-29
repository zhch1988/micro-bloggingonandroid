package weibo.constant;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author Íõ¾°
 * 
 */

public class User {

	private String uid;
	private String name;
	private String screen_name;
	private Status status;
	private List<Status> statuseList;
	private String location;
	private String gender; // ÐÔ±ð
	private String province;
	private String city;
	private String description;
	private String profile_image_url;
	private String url;
	private boolean isProtected;
	private String followers_count;
	private long statusId = -1;
	private String statusText = null;
	private String statusSource = null;
	private boolean statusTruncated = false;
	private long statusInReplyToStatusId = -1;
	private int statusInReplyToUserId = -1;
	private boolean statusFavorited = false;
	private String statusInReplyToScreenName = null;
	private String profileBackgroundColor;
	private String profileTextColor;
	private String profileLinkColor;
	private String profileSidebarFillColor;
	private String profileSidebarBorderColor;
	private String friends_count;
	private String created_at;
	private String favourites_count;
	private int utcOffset;
	private String timeZone;
	private String profileBackgroundImageUrl;
	private String profileBackgroundTile;
	private boolean following;
	private boolean notificationEnabled;
	private String statuses_count;
	private boolean geo_enabled;
	private boolean verified;
	private boolean allow_all_act_msg;
	private String remark;
	private String domain;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getScreen_name() {
		return screen_name;
	}

	public void setScreen_name(String screenName) {
		screen_name = screenName;
	}

	public List<Status> getStatuseList() {
		return statuseList;
	}

	public void setStatuseList(List<Status> statuseList) {
		this.statuseList = statuseList;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public User(JSONObject json) {
		super();
		try {
			init(json);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void init(JSONObject json) throws JSONException {
		if (json != null) {
			if (!json.isNull("id"))
				uid = json.getString("id");
			if (!json.isNull("name"))
				name = json.getString("name");
			if (!json.isNull("screen_name"))
				screen_name = json.getString("screen_name");
			if (!json.isNull("location"))
				location = json.getString("location");
			if (!json.isNull("description"))
				description = json.getString("description");
			if (!json.isNull("profile_image_url"))
				profile_image_url = json.getString("profile_image_url");
			if (!json.isNull("url"))
				url = json.getString("url");
			if (!json.isNull("protected"))
				isProtected = json.getBoolean("protected");
			if (!json.isNull("followers_count"))
				followers_count = json.getString("followers_count");
			if (!json.isNull("profile_background_color"))
				profileBackgroundColor = json
						.getString("profile_background_color");
			if (!json.isNull("profile_text_color"))
				profileTextColor = json.getString("profile_text_color");
			if (!json.isNull("profile_link_color"))
				profileLinkColor = json.getString("profile_link_color");
			if (!json.isNull("profile_sidebar_fill_color"))
				profileSidebarFillColor = json
						.getString("profile_sidebar_fill_color");
			if (!json.isNull("profile_sidebar_border_color"))
				profileSidebarBorderColor = json
						.getString("profile_sidebar_border_color");
			if (!json.isNull("friends_count"))
				friends_count = json.getString("friends_count");
			if (!json.isNull("created_at"))
				created_at = json.getString("created_at");
			if (!json.isNull("favourites_count"))
				favourites_count = json.getString("favourites_count");
			// utcOffset = json.getInt("utc_offset");
			// timeZone = json.getString("time_zone");
			// profileBackgroundImageUrl =
			// json.getString("profile_background_image_url");
			// profileBackgroundTile =
			// json.getString("profile_background_tile");
			if (!json.isNull("following"))
				following = json.getBoolean("following");
			if (!json.isNull("notifications"))
				notificationEnabled = json.getBoolean("notifications");
			if (!json.isNull("statuses_count"))
				statuses_count = json.getString("statuses_count");
			if (!json.isNull("status")) {
				status = new Status(json.getJSONObject("status"));
			}

		}
	}

	static final String[] POSSIBLE_ROOT_NAMES = new String[] { "user",
			"sender", "recipient", "retweeting_user" };

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProfile_image_url() {
		return profile_image_url;
	}

	public void setProfile_image_url(String profileImageUrl) {
		this.profile_image_url = profileImageUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isProtected() {
		return isProtected;
	}

	public void setProtected(boolean isProtected) {
		this.isProtected = isProtected;
	}

	public String getFollowers_count() {
		return followers_count;
	}

	public void setFollowers_count(String followersCount) {
		followers_count = followersCount;
	}

	public long getStatusId() {
		return statusId;
	}

	public void setStatusId(long statusId) {
		this.statusId = statusId;
	}

	public String getStatusText() {
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	public String getStatusSource() {
		return statusSource;
	}

	public void setStatusSource(String statusSource) {
		this.statusSource = statusSource;
	}

	public boolean isStatusTruncated() {
		return statusTruncated;
	}

	public void setStatusTruncated(boolean statusTruncated) {
		this.statusTruncated = statusTruncated;
	}

	public long getStatusInReplyToStatusId() {
		return statusInReplyToStatusId;
	}

	public void setStatusInReplyToStatusId(long statusInReplyToStatusId) {
		this.statusInReplyToStatusId = statusInReplyToStatusId;
	}

	public int getStatusInReplyToUserId() {
		return statusInReplyToUserId;
	}

	public void setStatusInReplyToUserId(int statusInReplyToUserId) {
		this.statusInReplyToUserId = statusInReplyToUserId;
	}

	public boolean isStatusFavorited() {
		return statusFavorited;
	}

	public void setStatusFavorited(boolean statusFavorited) {
		this.statusFavorited = statusFavorited;
	}

	public String getStatusInReplyToScreenName() {
		return statusInReplyToScreenName;
	}

	public void setStatusInReplyToScreenName(String statusInReplyToScreenName) {
		this.statusInReplyToScreenName = statusInReplyToScreenName;
	}

	public String getProfileBackgroundColor() {
		return profileBackgroundColor;
	}

	public void setProfileBackgroundColor(String profileBackgroundColor) {
		this.profileBackgroundColor = profileBackgroundColor;
	}

	public String getProfileTextColor() {
		return profileTextColor;
	}

	public void setProfileTextColor(String profileTextColor) {
		this.profileTextColor = profileTextColor;
	}

	public String getProfileLinkColor() {
		return profileLinkColor;
	}

	public void setProfileLinkColor(String profileLinkColor) {
		this.profileLinkColor = profileLinkColor;
	}

	public String getProfileSidebarFillColor() {
		return profileSidebarFillColor;
	}

	public void setProfileSidebarFillColor(String profileSidebarFillColor) {
		this.profileSidebarFillColor = profileSidebarFillColor;
	}

	public String getProfileSidebarBorderColor() {
		return profileSidebarBorderColor;
	}

	public void setProfileSidebarBorderColor(String profileSidebarBorderColor) {
		this.profileSidebarBorderColor = profileSidebarBorderColor;
	}

	public String getFriends_count() {
		return friends_count;
	}

	public void setFriends_count(String friendsCount) {
		friends_count = friendsCount;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String createdAt) {
		created_at = createdAt;
	}

	public String getFavourites_count() {
		return favourites_count;
	}

	public void setFavourites_count(String favouritesCount) {
		favourites_count = favouritesCount;
	}

	public int getUtcOffset() {
		return utcOffset;
	}

	public void setUtcOffset(int utcOffset) {
		this.utcOffset = utcOffset;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getProfileBackgroundImageUrl() {
		return profileBackgroundImageUrl;
	}

	public void setProfileBackgroundImageUrl(String profileBackgroundImageUrl) {
		this.profileBackgroundImageUrl = profileBackgroundImageUrl;
	}

	public String getProfileBackgroundTile() {
		return profileBackgroundTile;
	}

	public void setProfileBackgroundTile(String profileBackgroundTile) {
		this.profileBackgroundTile = profileBackgroundTile;
	}

	public boolean isFollowing() {
		return following;
	}

	public void setFollowing(boolean following) {
		this.following = following;
	}

	public boolean isNotificationEnabled() {
		return notificationEnabled;
	}

	public void setNotificationEnabled(boolean notificationEnabled) {
		this.notificationEnabled = notificationEnabled;
	}

	public String getStatuses_count() {
		return statuses_count;
	}

	public void setStatuses_count(String statusesCount) {
		statuses_count = statusesCount;
	}

	public boolean isGeo_enabled() {
		return geo_enabled;
	}

	public void setGeo_enabled(boolean geoEnabled) {
		geo_enabled = geoEnabled;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public boolean isAllow_all_act_msg() {
		return allow_all_act_msg;
	}

	public void setAllow_all_act_msg(boolean allowAllActMsg) {
		allow_all_act_msg = allowAllActMsg;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public static String[] getPossibleRootNames() {
		return POSSIBLE_ROOT_NAMES;
	}

	public User() {

	}
}
