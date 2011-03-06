package main.wang;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class login extends Activity {
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	   setContentView(R.layout.login);
	   Button login_btn=(Button)this.findViewById(R.id.login_btn);
	   login_btn.setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View v) {
			Intent itent=new Intent();
			itent.setClass(login.this, main.class);
			startActivity(itent);
			
		}
		   
	   });
   }
}
