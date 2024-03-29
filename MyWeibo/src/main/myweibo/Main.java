package main.myweibo;


import main.logic.GetMainMyInfo;
import main.logic.GetWeiboList;
import weibo.constant.Status;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class Main extends Activity {
	ImageButton ButTest;
	ImageButton write;
	ImageButton mainMyInfo;
	ImageButton refresh;
	ImageButton mainhome ;
	LinearLayout l1;
	LinearLayout l2;
	public static Status aStatus = null;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weibolist);
		ButTest = (ImageButton) this.findViewById(R.id.refreshBtn);
		write = (ImageButton) this.findViewById(R.id.writeBtn);
		TextView text = (TextView) this.findViewById(R.id.showName);
		ListView list = (ListView) this.findViewById(R.id.Msglist);
		 l1=(LinearLayout)this.findViewById(R.id.main);
		 l2=(LinearLayout)this.findViewById(R.id.ziliao);
		text.setText("欢迎使用稚菊");
		this.getIntent().setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		ButTest.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(Main.this, Login.class);
				startActivity(intent);
			}

		});

		write.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(Main.this, Write.class);
				startActivity(intent);
			}

		});
		// 郑璨 获得用户个人资料： 关注 微博 粉丝 话题
		//Zheng Chen

		mainMyInfo = (ImageButton) this.findViewById(R.id.main_my_info);
		mainMyInfo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				l1.setVisibility(l1.GONE);
				l2.setVisibility(l2.VISIBLE);
				TextView address = (TextView) findViewById(R.id.tvAddress_content);
				TextView accountInfo = (TextView) findViewById(R.id.tvAccount_info_content);
				TextView friendsCount = (TextView) findViewById(R.id.tvAttention_count);
				TextView statusesCount = (TextView) findViewById(R.id.tvWeibo_count);
				TextView followersCount = (TextView) findViewById(R.id.tvFans_count);
				TextView topic = (TextView) findViewById(R.id.tvTopic_count);
				new GetMainMyInfo(Main.this, address, accountInfo,
						friendsCount, statusesCount, followersCount, topic)
						.execute();

				LinearLayout rlWeibo = (LinearLayout) Main.this
						.findViewById(R.id.rlWeibo);
				rlWeibo.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Log.v("myweibo", "clicked");
						Intent intent = new Intent();
						intent.setClass(Main.this, MyWeiboList.class);
						Main.this.startActivity(intent);
					}

				});
			}
		});

		this.getWeiboList();


		//王晓龙  详细微博
		list.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Main.aStatus = (Status)(arg0.getAdapter().getItem(arg2));
				
				Log.v("aStatus",Main.aStatus.getUser_Author().getName());
				if(Main.aStatus == null){
					Log.v("Status", "null");
				}
				else{
					Intent intent = new Intent();
					intent.setClass(Main.this, WeiboDetail.class);
					startActivity(intent);
				}

			}
			
		});
		
		
		
		
		//刷新界面
		refresh = (ImageButton)this.findViewById(R.id.refreshBtn);
		refresh.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getWeiboList();
			}
			
		});
		mainhome=(ImageButton)this.findViewById(R.id.main_home);
		mainhome.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				l1.setVisibility(l1.VISIBLE);
				l2.setVisibility(l2.GONE);
			}
			
		});
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater menuinf = this.getMenuInflater();
		menuinf.inflate(R.menu.menu, menu);
		return true;

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.bout:
			startActivity(new Intent(this, About.class));
			return true;
		case R.id.set:
			return true;
		case R.id.account:
			return true;

		}
		return false;
	}
	
	public void getWeiboList(){
		new GetWeiboList(this).execute();
		
	}
}