package main.logic;

import java.util.List;

import main.myweibo.Main;
import weibo.constant.Constant;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.EditText;

public class LoginGo extends AsyncTask<Void, Void, UserInfo> {

	Context context;
	List<UserInfo> userList;
	EditText iconSelect;

	public LoginGo(Context context, EditText iconSelect) {
		this.context = context;
		this.iconSelect = iconSelect;
	}

	@Override
	protected UserInfo doInBackground(Void... params) {
		// TODO Auto-generated method stub
		DataHelper dbHelper = new DataHelper(context);
		userList = dbHelper.GetUserList();
		dbHelper.Close();
		if (userList.isEmpty()) {
			return null;
		} else {
			String name = iconSelect.getText().toString();
			UserInfo user = getUserByName(name);
			return user;
		}
	}

	private UserInfo getUserByName(String name) {
		for (UserInfo u : userList) {
			if (u.getUserName().equals(name)) {
				return u;
			}
		}
		return null;
	}

	@Override
	protected void onPostExecute(UserInfo user) {
		// TODO Auto-generated method stub
		if (user != null && !isCancelled()) {
			ConfigHelper.nowUser = user;// ��ȡ��ǰѡ����û����ұ���
			Constant.user_id = user.getUserId();
			Constant.userKey = user.getToken();
			Constant.userSecret = user.getTokenSecret();
		}
		if (ConfigHelper.nowUser != null) {
			// �����û���ҳ
			Intent intent = new Intent();
			intent.setClass(context, Main.class);
			context.startActivity(intent);
			((Activity)context).finish();
		}
	}
}
