package main.logic;

import java.util.List;

import main.myweibo.Login;
import main.myweibo.OAuth;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class GetOAuth extends AsyncTask<Void, Void, UserInfo> {
	Activity context;
	Intent intent;
	OAuth auth;
	
	public GetOAuth(Context context,Intent intent,OAuth auth){
		this.context = (Activity)context;
		this.intent = intent;
		this.auth = auth;
	}
	
	@Override
	protected UserInfo doInBackground(Void... params) {
		// TODO Auto-generated method stub
		Log.v("xxxxxintent", "aaaaaaaaccept");
		// 在这里处理获取返回的oauth_verifier参数
		UserInfo user;
		try {
			user = auth.GetAccessToken(intent);
			if (user != null) {
				DataHelper helper = new DataHelper(context);
				String uid = user.getUserId();
				if (helper.HaveUserInfo(uid)) {
					helper.UpdateUserInfo(user);
					Log.e("UserInfo", "update");
				} else {
					helper.SaveUserInfo(user);
					Log.e("UserInfo", "add");
				}
				List<UserInfo> userlist = helper.GetUserList();
				//更新DB
				helper.UpdateUserInfo(context,userlist);
				userlist = helper.GetUserList();
				// 获得SharedPreferences对象
				SharedPreferences MyPreferences = context.getSharedPreferences("NameList",
						Context.MODE_PRIVATE);
				// 获得SharedPreferences.Editor对象
				SharedPreferences.Editor editor = MyPreferences.edit();
				// 保存组件中的值
				String name = getUserNameById(user,userlist);
				editor.putString("name", name);
				editor.commit();
				Intent intent1 = new Intent(context,
						Login.class);
				context.startActivity(intent1);
				context.finish();
				return user;
			} else {		
				new StartOAuth(context,auth).execute();
				return null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			new StartOAuth(context,auth).execute();
			return null;
		}
		
	}

	private String getUserNameById(UserInfo user,List<UserInfo> userlist) {
		for (UserInfo u : userlist) {
			if (u.getUserId().equals(user.getUserId())) {
				return u.getUserName();
			}
		}
		return null;
	}
	
	
	@Override
	protected void onPostExecute(UserInfo user) {
		// TODO Auto-generated method stub
		if(user!=null&&!isCancelled()){
			Toast.makeText(context, "认证成功！", Toast.LENGTH_LONG).show();
		}
		else{
			Toast.makeText(context, "认证失败，请重试！", Toast.LENGTH_LONG).show();
//			Intent newIntent = new Intent(context,AuthorizeActivity.class);
//			context.startActivity(newIntent);
		}
	}

}
