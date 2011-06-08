package main.logic;

import weibo.constant.Constant;
import weibo.constant.User;
import weibo.trends_interface.Trends;
import weibo.user_interface.Users_show;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

public class GetMainMyInfo extends AsyncTask<Void, Void, GetMainMyInfo.Infounion> {
	
	class Infounion{
		private User user;
		private int trend_count;
		public User getUser() {
			return user;
		}
		public int getTrend_count() {
			return trend_count;
		}
		public Infounion(User user, int trend_count){
			this.user = user;
			this.trend_count = trend_count;
		}
	}
	
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
	protected Infounion doInBackground(Void... params) {
		// TODO Auto-generated method stub
		Users_show userShow = new Users_show();
		user = userShow.getUserInfoByUId(Constant.user_id);
		Trends trend = new Trends();
		Infounion info = new Infounion(user, trend.getTrendlist().size());
		return info;
	}
	
	@Override
	protected void onPostExecute(Infounion info) {
		// TODO Auto-generated method stub
		if (user != null && !isCancelled()) {
			address.setText(info.getUser().getLocation());
			accountInfo.setText(info.getUser().getName());
			friendsCount.setText(info.getUser().getFriends_count());
			statusesCount.setText(info.getUser().getStatuses_count());
			followersCount.setText(info.getUser().getFollowers_count());
			topic.setText(info.getTrend_count()+"");
		}
	}
}
