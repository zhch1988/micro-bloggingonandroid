package main.logic;

import java.util.ArrayList;
import java.util.List;

import main.myweibo.Main;
import main.myweibo.R;
import main.myweibo.WeiboDetail;
import main.myweibo.WeiboListAdapter;
import weibo.constant.Constant;
import weibo.constant.Status;
import weibo.status.Friends_Timeline;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class GetWeiboDetail extends AsyncTask<Void, Void, Integer> {

	String url = null;
	Bitmap bitmap;
	Activity context;
	Bitmap[] bitmaps;
	public GetWeiboDetail(Activity context){
		this.context = context;
		this.bitmaps = new Bitmap[3];
	}
	
	@Override
	protected Integer doInBackground(Void... params) {
		// TODO Auto-generated method stub
		 //博主头像
        url =Main.aStatus.getUser_Author().getProfile_image_url();
        bitmap = main.logic.DataHelper.returnBitMap(url);
        bitmaps[0] = bitmap;
        //博客图片
        url =Main.aStatus.getBmiddle_pic();
        bitmap = main.logic.DataHelper.returnBitMap(url);
        bitmaps[1] = bitmap;
        
        //转发微博的图片
        weibo.constant.Status retweeted = Main.aStatus.getRetweeted_status();
        if(retweeted!=null){
        	url = retweeted.getThumbnail_pic();
            bitmap = main.logic.DataHelper.returnBitMap(url);
            bitmaps[2] = bitmap;
        }
        else
        {
        	bitmaps[2] = null;
        }
        
//        if(bitmaps[0]==null||bitmaps[1]==null||bitmaps[2]==null)
//        	return 0;
//        else
        	return 1;
	}

	@Override
	protected void onPostExecute(Integer result) {
		// TODO Auto-generated method stub
		if(result==1&&!this.isCancelled()){
			 //博主头像
	        ImageView iv_authImage = (ImageView)context.findViewById(R.id.weibozhu);
	        iv_authImage.setImageBitmap(bitmaps[0]);
	        //博客图片
	        ImageView iv_weibopic = (ImageView)context.findViewById(R.id.user_weibopic);
	        iv_weibopic.setImageBitmap(bitmaps[1]);
	        //转发微博的图片
	        ImageView iv_retweet = (ImageView)context.findViewById(R.id.retweetimage);
	        iv_retweet.setImageBitmap(bitmaps[2]);
	      //博主名字
	  		TextView tv_Name=(TextView)context.findViewById(R.id.weibozhum);
	  		tv_Name.setText(Main.aStatus.getUser_Author().getName());
	  		//微博内容
	        TextView tv_Text=(TextView)context.findViewById(R.id.user_weibotext);
	        tv_Text.setText(new StatusHelper(context, Main.aStatus.getText()).getReplaced());
	       
	        //转发微博内容
	        weibo.constant.Status retweeted = Main.aStatus.getRetweeted_status();
	        TextView tv_retweet = (TextView)context.findViewById(R.id.retweettext);
	        if(retweeted!=null){
	        	 
	        	
		        tv_retweet.setText(new StatusHelper(context,"@"+Main.aStatus.getRetweeted_status().getUser_Author().getName()+":"+Main.aStatus.getRetweeted_status().getText()).getReplaced());
	        }
	        else
	        {
	        	tv_retweet.setText("");
	        }
	        
	        
	       
		}
		else{
			Toast.makeText(context, "获取微博详细信息失败，请刷新", Toast.LENGTH_SHORT).show();
			Log.v("Get weibo LIST", "ERROR");
		}
		//super.onPostExecute(result);
	}

	@Override
	protected void onProgressUpdate(Void... values) {}

	
}
