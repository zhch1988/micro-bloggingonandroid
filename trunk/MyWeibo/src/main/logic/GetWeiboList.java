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
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class GetWeiboList extends AsyncTask<Void, Void, Integer> {

	Activity context;
	WeiboListAdapter adapter;
	Bitmap[][] bitmaps;
	List<weibo.constant.Status> publish_status;
	public GetWeiboList(Activity context){
		this.context = context;
		this.bitmaps = new Bitmap[new Integer(Constant.weibocount)][3];
	}
	
	@Override
	protected Integer doInBackground(Void... params) {
		// TODO Auto-generated method stub
		

			Friends_Timeline ft = new Friends_Timeline();
			List<weibo.constant.Status> status = ft.getFriends_Timeline();
			if(status!=null){
				publish_status= new ArrayList<weibo.constant.Status>();
				adapter = null;
				for(int i=0;i<status.size();i++){
					weibo.constant.Status aStatus = status.get(i);
					String url =aStatus.getUser_Author().getProfile_image_url();
					bitmaps[i][0]=main.logic.DataHelper.returnBitMap(url);
					url =aStatus.getThumbnail_pic();
					bitmaps[i][1]=main.logic.DataHelper.returnBitMap(url);
					if(aStatus.getRetweeted_status()!=null){
						url = aStatus.getRetweeted_status().getThumbnail_pic();
						bitmaps[i][2]=main.logic.DataHelper.returnBitMap(url);
					}
					else{
						bitmaps[i][2]=null;
					}
					publish_status.add(aStatus);
					
					this.publishProgress();
				}
				return 1;
			}
			else{
				return 0;
			}
	}

	@Override
	protected void onPostExecute(Integer result) {
		// TODO Auto-generated method stub
		if(result==1&&!this.isCancelled()){
			Log.v("Get weibo LIST", "OK");
		}
		else{
			Toast.makeText(context, "��ȡ΢���б�ʧ�ܣ���ˢ��", Toast.LENGTH_SHORT).show();
			Log.v("Get weibo LIST", "ERROR");
		}
		//super.onPostExecute(result);
	}

	@Override
	protected void onProgressUpdate(Void... values) {
		// TODO Auto-generated method stub
		//����һ��΢��
		if(!this.isCancelled()){
			if(adapter==null){
				adapter = new WeiboListAdapter(this.publish_status,this.context,this.bitmaps);
				ListView listview = (ListView) context.findViewById(R.id.Msglist);
				listview.setAdapter(adapter);
				listview.setClickable(true);
				listview.setOnItemClickListener(new OnItemClickListener(){

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
							long arg3) {
						// TODO Auto-generated method stub
						Bundle bund=new Bundle();
						weibo.constant.Status listStatus=(weibo.constant.Status)arg0.getAdapter().getItem(arg2);
						String user_Name=listStatus.getUser_Author().getName();
						bund.putString("user_Name", user_Name);
						String  user_Text=listStatus.getText();
						bund.putString("user_Text",user_Text);
						String user_bumpic=listStatus.getUser_Author().getProfile_image_url();
						bund.putString("user_bumpic", user_bumpic);
						String weibo_pic=listStatus.getBmiddle_pic();
						bund.putString("weibo_pic", weibo_pic);
						Intent itemItent=new Intent();
						itemItent.putExtras(bund);
						itemItent.setClass(context, WeiboDetail.class);
						
						context.startActivity(itemItent);
						context.finish();
						//itemItent.putExtra("status", arg0.getAdapter().getItem(arg2))
					}
					
				});
			}
			else{
				adapter.notifyDataSetChanged();
			}
			
		}
		
	}

	
}