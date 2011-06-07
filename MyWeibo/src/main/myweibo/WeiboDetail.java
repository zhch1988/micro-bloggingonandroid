package main.myweibo;

import main.logic.GetWeiboDetail;
import main.logic.StatusHelper;
import weibo.favorites.Favorites_create;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class WeiboDetail extends Activity {
  
	String url;
	Bitmap bitmap;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weibodetail);
		
		new GetWeiboDetail(this).execute();
        //刷新按钮
        ImageButton ib_refresh = (ImageButton)this.findViewById(R.id.detail_refresh);
        ib_refresh.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(WeiboDetail.this, WeiboDetail.class);
				WeiboDetail.this.startActivity(intent);
				WeiboDetail.this.finish();
			}
        	
        });
        //转发微博
        ImageButton ib_forward = (ImageButton)this.findViewById(R.id.detail_forward);
        ib_forward.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(WeiboDetail.this, Forward.class);
				WeiboDetail.this.startActivity(intent);
//				WeiboDetail.this.finish();
				
			}
        	
        });
        //评论微博
        ImageButton ib_comment = (ImageButton)this.findViewById(R.id.detail_comment);
        ib_comment.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(WeiboDetail.this, Comment.class);
				WeiboDetail.this.startActivity(intent);
//				WeiboDetail.this.finish();
				
			}
        	
        });
        //收藏微博
        ImageButton ib_fav = (ImageButton)this.findViewById(R.id.detail_fav);
        ib_fav.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new Favorites_create(Main.aStatus.getSId());
				Toast.makeText(WeiboDetail.this, "收藏成功", Toast.LENGTH_SHORT).show();
				
			}
        	
        });
        
	}

	private void showpic() {
		// TODO Auto-generated method stub
		
	}
}
