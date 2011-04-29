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

		//EditText��������ı�����
		text = (EditText) this.findViewById(R.id.edit1);
		/**
		 * writeOK��ť��������ϣ�����΢��
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
				Toast.makeText(Write.this, "΢�������ɹ�", Toast.LENGTH_SHORT).show();
				}else
					 Toast.makeText(Write.this, "΢������ʧ��", Toast.LENGTH_SHORT).show();
				 */
				new Update_status_go(Write.this, text.getText().toString()).execute();
			}

		});
		/**
		 * addtrend��ť����ӻ���
		 */
		addtrend = (ImageButton) this.findViewById(R.id.im3);
		addtrend.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				text.append("#�������������Զ��廰��#");
				// ѡ�С��������������Զ��廰�⡱
				Editable editable=text.getText();
				Selection.setSelection(editable, text.getText().length()-12, text.getText().length()-1);
			}
	});
		/**
		 * mention��ť�����@(�ӵ��İ�ť��...)
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
