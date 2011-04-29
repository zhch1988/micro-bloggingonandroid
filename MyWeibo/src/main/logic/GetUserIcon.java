package main.logic;

import android.graphics.Bitmap;
import android.os.AsyncTask;

public class GetUserIcon extends AsyncTask<String , Void , Bitmap>{

	@Override
	protected Bitmap doInBackground(String... urls) {
		// TODO Auto-generated method stub
		int count = urls.length;
		Bitmap result=null;
        for (int i = 0; i < count; i++) {
        	result = DataHelper.returnBitMap(urls[i]);
        }
		return result;
	}
	
	

}
