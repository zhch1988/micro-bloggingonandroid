package main.logic;

import main.myweibo.OAuth;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

public class StartOAuth extends AsyncTask<Void, Void, Void> {
	Activity context;
	OAuth auth;
	
	public StartOAuth(Context context,OAuth auth){
		this.context = (Activity)context;
		this.auth = auth;
	}
	
	@Override
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub
		String CallBackUrl = "myapp://AuthorizeActivity";
		auth.RequestAccessToken(context, CallBackUrl);
		return null;
	}

}
