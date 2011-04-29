package main.myweibo;

import main.logic.DataHelper;
import main.logic.InitLogin;
import main.logic.InitLoginList;
import main.logic.LoginGo;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Login extends Activity {
	private ImageView icon;
	private EditText iconSelect;
	private String Select_Name="NameList";
	
	@Override
	protected void onStop() {
		// 获得SharedPreferences对象
		SharedPreferences MyPreferences = getSharedPreferences(Select_Name,
				Context.MODE_PRIVATE);
		// 获得SharedPreferences.Editor对象
		SharedPreferences.Editor editor = MyPreferences.edit();
		// 保存组件中的值
		editor.putString("name", iconSelect.getText().toString());
		editor.commit();
		super.onStop();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userinfo);
		LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
		// 背景自动适应
		AndroidHelper
				.AutoBackground(this, layout, R.drawable.bg, R.drawable.bg);
		iconSelect = (EditText) this.findViewById(R.id.iconSelect);
		icon = (ImageView) this.findViewById(R.id.icon);
		// 初始化数据
		new InitLogin(this,Select_Name,icon,iconSelect).execute();
		
		// 添加账号按钮
		ImageButton iconAdd = (ImageButton) findViewById(R.id.addIcon);
		iconAdd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(Login.this, AuthorizeActivity.class);
				startActivity(intent);
				finish();
			}

		});
		// 选择账户按钮
		ImageButton iconSelectBtn = (ImageButton) findViewById(R.id.iconSelectBtn);
		iconSelectBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new InitLoginList(Login.this,iconSelect,icon).execute();
			}

		});
		// 登陆按钮
		ImageButton iconLogin = (ImageButton) findViewById(R.id.login);
		iconLogin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new LoginGo(Login.this,iconSelect).execute();
			}
		});

	}




}