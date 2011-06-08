package main.myweibo;

import main.logic.GetMainMyInfo;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainMyInfo extends Activity implements OnClickListener {
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
		// LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
		// // ±³¾°×Ô¶¯ÊÊÓ¦
		// AndroidHelper.AutoBackground(this, layout, R.drawable.bg,
		// R.drawable.bg);

		address = (TextView) this.findViewById(R.id.tvAddress_content);
		accountInfo = (TextView) this.findViewById(R.id.tvAccount_info_content);
		friendsCount = (TextView) this.findViewById(R.id.tvAttention_count);
		statusesCount = (TextView) this.findViewById(R.id.tvWeibo_count);
		followersCount = (TextView) this.findViewById(R.id.tvFans_count);
		topic = (TextView) this.findViewById(R.id.tvTopic_count);
		new GetMainMyInfo(this, address, accountInfo, friendsCount,
				statusesCount, followersCount, topic).execute();
		
		 LinearLayout ll = (LinearLayout) this.findViewById(R.id.rlWeibo);
		 ll.setOnClickListener(this);
		//
		// @Override
		// public void onClick(View arg0) {
		// // TODO Auto-generated method stub
		// Log.v("textview", "clicked");
		// Intent intent = new Intent();
		// intent.setClass(MainMyInfo.this, MyWeiboList.class);
		// MainMyInfo.this.startActivity(intent);
		// }
		// });

		// statusesCount.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View arg0) {
		// // TODO Auto-generated method stub
		// Log.v("textview", "clicked");
		// Intent intent = new Intent();
		// intent.setClass(MainMyInfo.this, MyWeiboList.class);
		// MainMyInfo.this.startActivity(intent);
		// }
		// });

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Log.v("something", "clicked");
		switch(v.getId()){
		case R.id.rlWeibo:
			Log.v("rlWeibo", "clicked");
		}
	}

//	public void Clicked(View v) {
//		Log.v("textview", "clicked");
//
//		Intent intent = new Intent();
//		intent.setClass(MainMyInfo.this, MyWeiboList.class);
//		MainMyInfo.this.startActivity(intent);
//
//	}
//
}
