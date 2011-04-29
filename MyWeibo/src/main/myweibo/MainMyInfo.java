package main.myweibo;

import main.logic.GetMainMyInfo;
import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainMyInfo extends Activity {
	TextView address;
	TextView accountInfo;
	TextView friendsCount;
	TextView statusesCount;
	TextView followersCount;
	TextView topic;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.ziliao);
//		LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
//		// ±³¾°×Ô¶¯ÊÊÓ¦
//		AndroidHelper.AutoBackground(this, layout, R.drawable.bg, R.drawable.bg);

		address = (TextView) this.findViewById(R.id.tvAddress_content);
		accountInfo = (TextView) this.findViewById(R.id.tvAccount_info_content);
		friendsCount = (TextView) this.findViewById(R.id.tvAttention_count);
		statusesCount = (TextView) this.findViewById(R.id.tvWeibo_count);
		followersCount = (TextView) this.findViewById(R.id.tvFans_count);
		topic = (TextView) this.findViewById(R.id.tvTopic_count);
		new GetMainMyInfo(this,address,accountInfo,friendsCount,statusesCount,followersCount,topic).execute();
	}
}
