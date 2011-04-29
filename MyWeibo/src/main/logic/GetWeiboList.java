package main.logic;

import java.util.ArrayList;
import java.util.List;

import main.myweibo.R;
import main.myweibo.WeiboListAdapter;

import org.json.JSONException;

import weibo.constant.Constant;
import weibo.status.Friends_Timeline;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

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
		
		try {
			Friends_Timeline ft = new Friends_Timeline();
			List<weibo.constant.Status> status = ft.getFriends_Timeline();
			//bitmaps = null;
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
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			Log.v("Get weibo LIST", "ERROR");
		}
		super.onPostExecute(result);
	}

	@Override
	protected void onProgressUpdate(Void... values) {
		// TODO Auto-generated method stub
		//增加一条微博
		if(!this.isCancelled()){
			if(adapter==null){
				adapter = new WeiboListAdapter(this.publish_status,this.context,this.bitmaps);
				ListView listview = (ListView) context.findViewById(R.id.Msglist);
				listview.setAdapter(adapter);
			}
			else{
				adapter.notifyDataSetChanged();
			}
			
		}
		
	}

	
}
