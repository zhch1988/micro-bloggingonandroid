package main.logic;

import java.util.List;

import main.myweibo.R;
import main.myweibo.UserAdapater;
import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class InitLoginList extends AsyncTask<Void, Void, List<UserInfo> > {

	Context context;
	String select_Name;
	ImageView icon;
	EditText iconSelect;
	Dialog dialog;
	
	public InitLoginList(Context context,EditText iconSelect,ImageView icon){
		this.context = context;
		this.iconSelect = iconSelect;
		this.icon = icon;
	}

	@Override
	protected List<UserInfo> doInBackground(Void... params) {
		// TODO Auto-generated method stub
		DataHelper dbHelper = new DataHelper(context);
		List<UserInfo> userList = dbHelper.GetUserList();
		dbHelper.Close();
		if (userList.isEmpty()){
			return null;
		}
		else{	
			return userList;
		}
	}

	@Override
	protected void onPostExecute(List<UserInfo> userList) {
		// TODO Auto-generated method stub
		if(userList!=null&&!isCancelled()){
			View diaView = View.inflate(context, R.layout.dialog2, null);
			this.dialog = new Dialog(context, R.style.dialog2);
			dialog.setContentView(diaView);
			dialog.show();			
			//初始化列表
			UserAdapater adapater = new UserAdapater(userList, context);
			ListView listview = (ListView) diaView.findViewById(R.id.list);
			listview.setVerticalScrollBarEnabled(false);// ListView去掉下拉条
			listview.setAdapter(adapater);
			listview.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View view,
						int arg2, long arg3) {
					TextView tv = (TextView) view
							.findViewById(R.id.useraccount);
					iconSelect.setText(tv.getText());
					ImageView iv = (ImageView) view.findViewById(R.id.icon);
					icon.setImageDrawable(iv.getDrawable());
					dialog.dismiss();
				}

			});
		
		}
		
	}
	
}
