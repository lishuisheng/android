package com.lss.example.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class BaseActivity extends Activity implements OnClickListener {

	private Activity self;
	private String TAG = "---tag";
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		init();
	}
	
	/**
	 * 调试
	 * @param context
	 * @param str
	 */
	public void setTag(Context context, String str){
		Log.v( context + TAG, str);
	}
	private void init(){
		self = this;
	}
	
	public void mySystemOutPrint(String title, String msg){
		
		System.out.println(title + "------>" + msg);
		
	}

	//需要其子类去继承的属性与方法都不能是私有的权限
		public void changeActivity(Class myclass){
			
			Intent intent = new Intent(self, myclass);
			startActivity(intent);
			finish();
			
		}
		
		@SuppressWarnings("unchecked")
		public final <T extends View> T getView (int id) {
		    try {
		        return (T) findViewById(id);
		    } catch (ClassCastException ex) {
		        Log.e(TAG, "Could not cast View to concrete class.", ex);
		        throw ex;
		    }
		}
		
	public void showFastToast(String str) {

		final Toast toast = Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT);
		toast.show();

		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			public void run() {
				toast.cancel();
			}
		}, 1);
	}
	
	public void showSlowToast(String str) {

		final Toast toast = Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT);
		toast.show();

		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			public void run() {
				toast.cancel();
			}
		}, 2);
	}


	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub

		if (keyCode == KeyEvent.KEYCODE_BACK) {

			changeActivity(MainActivity.class);

			return true;
		}

		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}

}
