package main.logic;

import weibo.constant.Constant;
import weibo.constant.User;
import weibo.user_interface.Users_show;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

public class GetMainMyInfo extends AsyncTask<Void, Void, User> {
	
	Context context;
	User user; 
	TextView address;
	TextView accountInfo;
	TextView friendsCount;
	TextView statusesCount;
	TextView followersCount;
	TextView topic;
	
	public GetMainMyInfo(Context context,TextView address, TextView accountInfo, TextView friendsCount, TextView statusesCount, TextView followersCount, TextView trends) {
		this.context = context;
		this.address = address;
		this.accountInfo = accountInfo;
		this.friendsCount = friendsCount;
		this.statusesCount = statusesCount;
		this.followersCount = followersCount;
		this.topic = trends;
	}

	@Override
	protected User doInBackground(Void... params) {
		// TODO Auto-generated method stub
		Users_show userShow = new Users_show();
		user = userShow.getUserInfoByUId(Constant.user_id);
		return user;
	}
	
	@Override
	protected void onPostExecute(User user) {
		// TODO Auto-generated method stub
		if (user != null && !isCancelled()) {
			address.setText(user.getLocation());
			accountInfo.setText(user.getName());
			friendsCount.setText(user.getFriends_count());
			statusesCount.setText(user.getStatuses_count());
			followersCount.setText(user.getFollowers_count());
			topic.setText("0");
		}
	}
}
