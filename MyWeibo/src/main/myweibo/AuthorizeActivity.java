package main.myweibo;

import main.logic.GetOAuth;
import main.logic.StartOAuth;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

public class AuthorizeActivity extends Activity {
	OAuth auth = new OAuth();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.authorize);

		LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
		// 背景自动适应
		AndroidHelper
				.AutoBackground(this, layout, R.drawable.bg, R.drawable.bg);

		// 设置dialog的监听器
		OnClickListener pocl = new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				new StartOAuth(AuthorizeActivity.this,auth).execute();
			}
		};
		OnClickListener nocl = new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				AuthorizeActivity.this.finish();
			}
		};
		
		// 设置dialog
		Dialog dialog = new AlertDialog.Builder(this)
				.setMessage("请授权本应用")
				.setPositiveButton("开始", pocl).setNegativeButton("取消", nocl)
				.create();
		dialog.show();

	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		new GetOAuth(this,intent,auth).execute();

	}
}