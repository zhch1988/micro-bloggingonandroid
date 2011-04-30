package main.myweibo;

import main.logic.CheckDB;
import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class UI_welcome_Activity extends Activity {
	/**
	 * Called when the activity is first created.
	 * 
	 * @throws InterruptedException
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
		// 背景自动适应
		AndroidHelper
				.AutoBackground(this, layout, R.drawable.bg, R.drawable.bg);
		//检查数据库和网络
		new CheckDB(this).execute();
	}

}