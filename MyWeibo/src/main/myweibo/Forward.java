package main.myweibo;

import weibo.statuses_interface.Status_repost;
import weibo.statuses_interface.Statuses_comment;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Forward extends Activity {
  
	String url;
	Bitmap bitmap;
	CheckBox cb;
	TextView tv_Comment;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.comment);
		tv_Comment=(TextView)findViewById(R.id.edit1);
		tv_Comment.setText("//@"+Main.aStatus.getText());
		
		cb = (CheckBox)this.findViewById(R.id.checkbox);
		//发送按钮
      ImageButton ib_writeOK = (ImageButton)this.findViewById(R.id.writeOKBtn);
      ib_writeOK.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if(cb.isChecked()){
					//转发
					Status_repost sr =new Status_repost();
					if(sr.repostStatus(Main.aStatus.getSId(),tv_Comment.getText().toString(),"1")!=null){
						Toast.makeText(Forward.this, "转发并评论成功", Toast.LENGTH_SHORT).show();
						Forward.this.finish();
					}
					else{
						Toast.makeText(Forward.this, "转发并评论失败", Toast.LENGTH_SHORT).show();
					}
				}
				else{
					//只转发不评论
					Status_repost sr =new Status_repost();
					if(sr.repostStatus(Main.aStatus.getSId(),"".toString(),"1")!=null){
						Toast.makeText(Forward.this, "转发成功", Toast.LENGTH_SHORT).show();
						Forward.this.finish();
					}
					else{
						Toast.makeText(Forward.this, "转发失败", Toast.LENGTH_SHORT).show();
					}
				}
				
			}
      	
      });
    //#按钮
      ImageButton ib_im3 = (ImageButton)this.findViewById(R.id.im3);
      ib_im3.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				tv_Comment.append("##");
			}
      	
      });
      //@按钮
      ImageButton ib_im4 = (ImageButton)this.findViewById(R.id.im4);
      ib_im4.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				tv_Comment.append("@");
			}
      	
      });
	
	}
}
