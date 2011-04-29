package main.myweibo;

import main.logic.Update_status_go;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;

public class Write extends Activity {
	ImageButton writeOK;
	ImageButton addtrend;
	ImageButton mention;
	EditText text;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.write);

		//EditText中输入的文本内容
		text = (EditText) this.findViewById(R.id.edit1);
		/**
		 * writeOK按钮，输入完毕，发布微博
		 */
		writeOK = (ImageButton) this.findViewById(R.id.writeOKBtn);
		writeOK.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/*
				Statuses_update su = new Statuses_update();
				Status newstatus = su.createStatus(text.getText().toString());
				if(newstatus!=null){
				Intent intent = new Intent();
				intent.setClass(Write.this, Main.class);
				startActivity(intent);
				Toast.makeText(Write.this, "微博发布成功", Toast.LENGTH_SHORT).show();
				}else
					 Toast.makeText(Write.this, "微博发布失败", Toast.LENGTH_SHORT).show();
				 */
				new Update_status_go(Write.this, text.getText().toString()).execute();
			}

		});
		/**
		 * addtrend按钮，添加话题
		 */
		addtrend = (ImageButton) this.findViewById(R.id.im3);
		addtrend.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				text.append("#请在这里输入自定义话题#");
				// 选中“请在这里输入自定义话题”
				Editable editable=text.getText();
				Selection.setSelection(editable, text.getText().length()-12, text.getText().length()-1);
			}
	});
		/**
		 * mention按钮，添加@(坑爹的按钮啊...)
		 */
		mention = (ImageButton) this.findViewById(R.id.im4);
		mention.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				text.append("@");
			}
			
		});
		
	}
}
