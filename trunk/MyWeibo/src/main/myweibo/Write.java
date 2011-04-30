package main.myweibo;

import main.logic.Update_status_go;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.Selection;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;

public class Write extends Activity {
	ImageButton writeOK;
	ImageButton addtrend;
	ImageButton mention;
	ImageButton addimage;
	EditText text;
	String picpath = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.write);

		// EditText��������ı�����
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
				 * Statuses_update su = new Statuses_update(); Status newstatus
				 * = su.createStatus(text.getText().toString());
				 * if(newstatus!=null){ Intent intent = new Intent();
				 * intent.setClass(Write.this, Main.class);
				 * startActivity(intent); Toast.makeText(Write.this, "΢�������ɹ�",
				 * Toast.LENGTH_SHORT).show(); }else Toast.makeText(Write.this,
				 * "΢������ʧ��", Toast.LENGTH_SHORT).show();
				 */

				new Update_status_go(Write.this, text.getText().toString(),
						picpath).execute();

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
				Editable editable = text.getText();
				Selection.setSelection(editable, text.getText().length() - 12,
						text.getText().length() - 1);
			}
		});
		/**
		 * mention��ť�����@(�ӵ��İ�ť��...)
		 */
		mention = (ImageButton) this.findViewById(R.id.im4);
		mention.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				text.append("@�������û���");
				Editable editable = text.getText();
				Selection.setSelection(editable, text.getText().length() - 5,
						text.getText().length());
			}

		});

		/**
		 * addimage��ť�����΢��ͼƬ
		 */
		addimage = (ImageButton) this.findViewById(R.id.im2);
		addimage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String[] selections = { "����ͼƬ", "�����ϴ�" };
				AlertDialog.Builder builder = new AlertDialog.Builder(
						Write.this);
				builder.setTitle("��ѡ��ͼƬ��Դ");
				builder.setItems(selections,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								if (which == 0) {
									Log.v("v", "files");
									// Intent localIntent1 = new Intent();
									// localIntent1.setType("image/*");
									// localIntent1
									// .setAction("android.intent.action.GET_CONTENT");
									// Intent localIntent2 =
									// Intent.createChooser(
									// localIntent1, "Select Picture");
									// startActivityForResult(localIntent2, 1);

									Intent intent = new Intent();
									/* ����Pictures����Type�趨Ϊimage */
									intent.setType("image/*");
									/* ʹ��Intent.ACTION_GET_CONTENT���Action */
									intent.setAction(Intent.ACTION_GET_CONTENT);
									/* ȡ����Ƭ�󷵻ر����� */
									startActivityForResult(intent, 1);

								} else {
									Log.v("v", "photos");
								}
							}

						});
				AlertDialog selectiondialog = builder.create();
				selectiondialog.show();
			}

		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			Uri uri = data.getData();
			Log.v("uri", uri.toString());
//			ContentResolver cr = this.getContentResolver();
			String[] proj = { MediaStore.Images.Media.DATA };
			Cursor cursor = managedQuery(uri, proj, // Which columns to return
					null, // WHERE clause; which rows to return (all rows)
					null, // WHERE clause selection arguments (none)
					null); // Order-by clause (ascending by name)
			cursor.moveToFirst();
			int column_index = cursor
					.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			picpath = cursor.getString(column_index);
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
}
