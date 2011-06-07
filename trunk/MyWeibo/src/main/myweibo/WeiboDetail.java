package main.myweibo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class WeiboDetail extends Activity {
  
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weibodetail);
  		TextView tv_Name=(TextView)findViewById(R.id.weibozhum);
  		tv_Name.setText(Main.aStatus.getUser_Author().getName());
        TextView tv_Text=(TextView)findViewById(R.id.user_weibotext);
        tv_Text.setText(Main.aStatus.getText());
	}

	private void showpic() {
		// TODO Auto-generated method stub
		
	}
}
