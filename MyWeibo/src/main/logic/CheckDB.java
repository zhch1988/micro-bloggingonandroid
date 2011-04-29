package main.logic;

import main.myweibo.Login;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

public class CheckDB extends AsyncTask<Void, Void, DataHelper> {
	
	private Context context;
	
	public CheckDB(Context context){
		this.context = context;
	}
	
	@Override
	protected DataHelper doInBackground(Void... params) {
		// TODO Auto-generated method stub
		// �����µ����ݿ�
		DataHelper dbHelper = new DataHelper(context);
		return dbHelper;
	}

	@Override
	protected void onPostExecute(DataHelper result) {
		// TODO Auto-generated method stub
		//����Login����
		
		result.Close();
		Intent intent = new Intent();
		intent.setClass(context, Login.class);
		context.startActivity(intent);
		((Activity)context).finish();
		
	}



}
