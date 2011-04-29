package main.myweibo;

import java.util.List;

import main.logic.UserInfo;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class UserAdapater extends BaseAdapter {
	List<UserInfo> userList;
	Context act;

	public UserAdapater(List<UserInfo> userList, Context act) {
		super();
		// TODO Auto-generated constructor stub
		this.userList = userList;
		this.act = act;
	}

	@Override
	public int getCount() {
		return userList.size();
	}

	@Override
	public Object getItem(int position) {
		return userList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = LayoutInflater.from(act.getApplicationContext()).inflate(
				R.layout.login_item, null);

		ImageView iv = (ImageView) convertView.findViewById(R.id.icon);
		TextView tv = (TextView) convertView.findViewById(R.id.useraccount);
		UserInfo user = userList.get(position);
		try {
			// 设置图片显示
			iv.setImageDrawable(user.getUserIcon());
			// 设置信息
			tv.setText(user.getUserName());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return convertView;
	}

}