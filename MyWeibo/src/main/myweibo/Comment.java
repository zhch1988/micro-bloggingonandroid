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

public class Comment extends Activity {
  
	String url;
	Bitmap bitmap;
	CheckBox cb;
	TextView tv_Comment;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.comment);
		tv_Comment=(TextView)findViewById(R.id.edit1);
		tv_Comment.setHint("˵��ʲô��...");
		cb = (CheckBox)this.findViewById(R.id.checkbox);
		//���Ͱ�ť
      ImageButton ib_writeOK = (ImageButton)this.findViewById(R.id.writeOKBtn);
      ib_writeOK.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if(cb.isChecked()){
					//ת��
					Status_repost sr =new Status_repost();
					if(sr.repostStatus(Main.aStatus.getSId(),tv_Comment.getText().toString(),"1")!=null){
						Toast.makeText(Comment.this, "���۲����ͳɹ�", Toast.LENGTH_SHORT).show();
						Comment.this.finish();
					}
					else{
						Toast.makeText(Comment.this, "���۲�����ʧ��", Toast.LENGTH_SHORT).show();
					}
					
				}
				else{
					//����
					Statuses_comment sc = new Statuses_comment();
					if(sc.sendComment(Main.aStatus.getSId(), tv_Comment.getText().toString(), "")!=null){
						Toast.makeText(Comment.this, "���۳ɹ�", Toast.LENGTH_SHORT).show();
						Comment.this.finish();
					}
					else{
						Toast.makeText(Comment.this, "����ʧ��", Toast.LENGTH_SHORT).show();
					}
				}
				
			}
      	
      });
    //#��ť
      ImageButton ib_im3 = (ImageButton)this.findViewById(R.id.im3);
      ib_im3.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				tv_Comment.append("##");
			}
      	
      });
      //@��ť
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
