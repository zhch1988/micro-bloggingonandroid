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
  		Intent intent=this.getIntent();
  		Bundle bund=intent.getExtras();
  		TextView tv_Name=(TextView)findViewById(R.id.weibozhum);
  		tv_Name.setText(bund.getString("user_Name"));
        TextView tv_Text=(TextView)findViewById(R.id.user_weibotext);
        tv_Text.setText(bund.getString("user_Text"));
        showpic();
  		
	}

	private void showpic() {
		// TODO Auto-generated method stub
		
	}
}
