package main.logic;

import main.myweibo.Main;
import weibo.statuses_interface.Statuses_update;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

public class Update_status_go extends AsyncTask<Void, Void, weibo.constant.Status> {
	private Context context;
	private String newblog;

	public Update_status_go(Context context, String newblog){
		this.context = context;
		this.newblog = newblog;
	}

	@Override
	protected weibo.constant.Status doInBackground(Void... params) {
		// TODO Auto-generated method stub
		Statuses_update su = new Statuses_update();
		weibo.constant.Status newstatus = su.createStatus(newblog);
		return newstatus;
	}
	
	@Override
	protected void onPostExecute(weibo.constant.Status newstatus){
		if(newstatus == null){
			Toast.makeText(context, "微博发布失败", Toast.LENGTH_SHORT).show();
		}else{
			Intent intent = new Intent();
			intent.setClass(context, Main.class);
			context.startActivity(intent);
			Toast.makeText(context, "微博发布成功", Toast.LENGTH_SHORT).show();
			((Activity)context).finish();
		}
	}

}
