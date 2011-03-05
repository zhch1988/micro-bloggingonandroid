package main.wang;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class main extends Activity {
	Button ButTest;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ButTest =(Button)this.findViewById (R.id.ButTest);
        TextView text=(TextView) this.findViewById(R.id.showName);
        text.setText("");
        ButTest.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent =new Intent();
				intent.setClass(main.this, login.class);
				startActivity(intent);
			}
        	
        });
   
    }
    public boolean onCreateOptionsMenu(Menu menu){
    	super.onCreateOptionsMenu(menu);
    	MenuInflater menuinf=this.getMenuInflater();
    	menuinf.inflate(R.menu.menu, menu);
		return true;
    	
    }
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
    	case R.id.bout:
    	startActivity(new Intent(this, about.class));
    	return true;
    	case R.id.set:
    		return true;
    	case R.id.account:
    	return true;
    	
    	}
    	return false;
    	}
}