package main.logic;

import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

public class InitLogin extends AsyncTask<Void, Void, UserInfo> {

	Context context;
	List<UserInfo> userList;
	String select_Name;
	ImageView icon;
	EditText iconSelect;

	public InitLogin(Context context, String select_Name, ImageView icon,
			EditText iconSelect) {
		this.context = context;
		this.select_Name = select_Name;
		this.icon = icon;
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
			SharedPreferences preferences = context.getSharedPreferences(
					select_Name, Context.MODE_PRIVATE);
			String str = preferences.getString("name", "");
			UserInfo user = null;
			if (!str.equals("")) {
				user = getUserByName(str);
				Log.v("Before icon.setImageDrawable", " here");
				if (user.getUserIcon() != null) {
					Log.v("getusericon", " not null");
				}
				return user;
			} else {
				return null;
			}
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
			icon.setImageDrawable(user.getUserIcon());
			Log.v("After icon.setImageDrawable", " here");
			if (user.getUserName() != null) {
				Log.v(user.getUserName(), "here");
			}
			iconSelect.setText(user.getUserName());
			Log.v("After iconSelect.setText", " here");
		}

	}

}
