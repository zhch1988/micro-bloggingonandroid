package main.logic;

import main.myweibo.Login;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;

public class CheckDB extends AsyncTask<Void, Void, DataHelper> {
	
	private Activity context;
	
	public CheckDB(Activity context){
		this.context = context;
	}
	
	@Override
	protected DataHelper doInBackground(Void... params) {
		// TODO Auto-generated method stub
		// 建立新的数据库
		DataHelper dbHelper = new DataHelper(context);
		return dbHelper;
	}

	@Override
	protected void onPostExecute(DataHelper result) {
		// TODO Auto-generated method stub
		//开启Login界面
		//检测数据库
		result.Close();
		Intent intent = new Intent();
		intent.setClass(context, Login.class);
		context.startActivity(intent);
		context.finish();
		
	}
	
	



}
