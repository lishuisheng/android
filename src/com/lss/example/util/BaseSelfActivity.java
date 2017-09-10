package com.lss.example.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class BaseSelfActivity extends Activity implements OnClickListener {

	protected Activity self;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		init();
	}

	private void init() {
		self = this;
	}

	// 界面跳转方法
	public void intentActivity(Class activity) {
		Intent intent = new Intent(self, activity);
		startActivity(intent);
		finish();
	}

	// 初始化控件
	public void setOnclickListener(int... ids) {
		for (int id : ids) {
			findViewById(id).setOnClickListener(this);
		}
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	}

	public void myToast(Context context, String str) {

		final Toast toast = Toast.makeText(context, str, Toast.LENGTH_SHORT);
		toast.show();

		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			public void run() {
				toast.cancel();
			}
		}, 1);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub

		if (keyCode == KeyEvent.KEYCODE_BACK) {

			return true;
		}

		return super.onKeyDown(keyCode, event);
	}

}
