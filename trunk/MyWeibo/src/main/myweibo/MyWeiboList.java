package main.myweibo;

import main.logic.GetMyWeiboList;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class MyWeiboList extends Activity {
	TextView text;
	ImageButton revert;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.afterprofile);
		
		text = (TextView) this.findViewById(R.id.apName);
		text.setText("ÎÒµÄÎ¢²©");
		revert = (ImageButton) this.findViewById(R.id.revertBtn);
		revert.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				MyWeiboList.this.finish();
			}
			
		});
		
		new GetMyWeiboList(this).execute();
	}

}
