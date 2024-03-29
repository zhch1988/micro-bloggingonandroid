package main.myweibo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import main.logic.StatusHelper;
import weibo.constant.Status;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WeiboListAdapter extends BaseAdapter {
	List<Status> status;
	Context act;
	Bitmap[][] bitmaps;

	public WeiboListAdapter(List<Status> status, Context act,Bitmap[][] bitmaps) {
		super();
		// TODO Auto-generated constructor stub
		this.bitmaps = bitmaps;
		this.status = status;
		this.act = act;
	}

	@Override
	public int getCount() {
		return status.size();
	}

	@Override
	public Object getItem(int position) {
		return status.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = LayoutInflater.from(act).inflate(
				R.layout.list_item, null);

		ImageView iv = (ImageView) convertView.findViewById(R.id.usericon);
		ImageView iv_1 = (ImageView) convertView.findViewById(R.id.wbimage);
		ImageView iv_3=(ImageView)convertView.findViewById(R.id.retweetimage);

		TextView tv = (TextView) convertView.findViewById(R.id.wbuser);
		TextView tv_1 = (TextView) convertView.findViewById(R.id.wbtext);
		TextView tv_2 = (TextView) convertView.findViewById(R.id.wbtime);
		TextView tv_3 =(TextView)convertView.findViewById(R.id.retweettext);
		LinearLayout ll = (LinearLayout)convertView.findViewById(R.id.retweet);
		
		
		Status aStatus = status.get(position);
		try {
			// 设置图片显示
			Bitmap profile_image = bitmaps[position][0];
			Bitmap bmiddle_pic = bitmaps[position][1];
			Bitmap retweet_image = bitmaps[position][2];
			iv.setImageBitmap(profile_image);
			iv_1.setImageBitmap(bmiddle_pic);
			// 设置信息
			tv.setText(aStatus.getUser_Author().getName());
			
//			tv_1.setText(aStatus.getText());
			//设置高亮
			tv_1.setText(new StatusHelper(act, aStatus.getText()).getReplaced());
			tv_1.setMovementMethod(LinkMovementMethod.getInstance());
			
			if(aStatus.getRetweeted_status()!=null){	
				iv_3.setImageBitmap(retweet_image);
	            tv_3.setText(new StatusHelper(act,"@"+aStatus.getRetweeted_status().getUser_Author().getName()+":"+aStatus.getRetweeted_status().getText()).getReplaced());
	            tv_3.setMovementMethod(LinkMovementMethod.getInstance());
	            ll.setVisibility(View.VISIBLE);
				}
			
			String date = aStatus.getCreated_at();
			
			 SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy",Locale.US);
			  Date d=sdf.parse(date);

			  sdf=new SimpleDateFormat("MM月dd日 HH时mm分ss秒 ");
			Log.v("Created_at",aStatus.getCreated_at());
			Log.v("Date",sdf.format(d));
			tv_2.setText(sdf.format(d));


		} catch (Exception e) {
			e.printStackTrace();
		}
		return convertView;
	}

}