package main.logic;

import main.myweibo.Main;
import weibo.statuses_interface.Statuses_update;
import weibo.statuses_interface.Statuses_upload;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class Update_status_go extends
		AsyncTask<Void, Void, weibo.constant.Status> {
	private Context context;
	private String blogtext;
	private String picpath;

	public Update_status_go(Context context, String blogtext, String picpath) {
		this.context = context;
		this.blogtext = blogtext;
		this.picpath = picpath;
	}

	@Override
	protected weibo.constant.Status doInBackground(Void... params) {
		// TODO Auto-generated method stub
		weibo.constant.Status newstatus = null;
		if (picpath == null) {
			Statuses_update su = new Statuses_update();
			newstatus = su.createStatus(blogtext);
		} else {
			Log.v("picpath", picpath);
			Statuses_upload su = new Statuses_upload();
			newstatus = su.uploadStatus(blogtext, picpath);
		}
		return newstatus;
	}

	@Override
	protected void onPostExecute(weibo.constant.Status newstatus) {
		if (newstatus == null) {
			Toast.makeText(context, "微博发布失败", Toast.LENGTH_SHORT).show();
		} else {
			Intent intent = new Intent();
			intent.setClass(context, Main.class);
			context.startActivity(intent);
			Toast.makeText(context, "微博发布成功", Toast.LENGTH_SHORT).show();
			((Activity) context).finish();
		}
	}

}
