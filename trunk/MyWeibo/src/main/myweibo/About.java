package main.myweibo;

import main.myweibo.R;
import android.app.Activity;
import android.os.Bundle;

public class About extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		this.setTitle("关于本客户端");
	}
}