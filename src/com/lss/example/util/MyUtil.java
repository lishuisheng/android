package com.lss.example.util;


import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

public class MyUtil {

	private Context context;
	private String str;
	
	public MyUtil(){
		
	}
	
	public MyUtil(Context context, String str) {

		this.context = context;
		this.str = str;
	}
	
	public void myToast(){
		
		final Toast toast = Toast.makeText(context, str, Toast.LENGTH_SHORT);
		toast.show();

		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			public void run() {
				toast.cancel();
			}
		}, 1);
	}

}
