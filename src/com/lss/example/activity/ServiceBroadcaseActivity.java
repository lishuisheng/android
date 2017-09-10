package com.lss.example.activity;

import com.lss.example.lssdemo.R;
import com.lss.example.server.ServiceBroadcase;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ServiceBroadcaseActivity extends BaseActivity implements
		OnClickListener {

	private TextView textView = null;
	private String TAG = "this";
	private Button btn_start = null;
	private Button btn_stop = null;
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			
			switch (msg.what) {
			case 1:
				
				Bundle bundle = msg.getData();
				int i = bundle.getInt("num");
				
				textView.setText("当前进度：" + i + "%");
				break;

			default:
				break;
			}
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.service_broadcase_activity);
		
		
		Log.v(TAG, "activity create");
		initView();
	}
	
	private void initView(){
		textView = (TextView) findViewById(R.id.tv_service_activity);
		btn_start = (Button) findViewById(R.id.btn_startService);
		btn_stop = (Button) findViewById(R.id.btn_stopService);
		btn_start.setOnClickListener(this);
		btn_stop.setOnClickListener(this);
	}
	

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub

		switch (view.getId()) {
		case R.id.btn_startService:

			Log.v(TAG, "btn_start");
			
			//在Activity端开启服务
			Intent intent = new Intent(ServiceBroadcaseActivity.this, ServiceBroadcase.class);
			startService(intent);
			
			break;
		case R.id.btn_stopService:
			
			Log.v(TAG, "btn_stop");
			Intent intent2 = new Intent(ServiceBroadcaseActivity.this, ServiceBroadcase.class);
			stopService(intent2);

			break;
		
		default:
			break;
		}

	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(receiver);
		Log.v(TAG, "activity destroy");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		//动态地注册广播。
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction("broadcase");
		registerReceiver(receiver, intentFilter);
		
		Log.v(TAG, "activity resume");
	}

	BroadcastReceiver receiver = new BroadcastReceiver(){

		@Override
		public void onReceive(Context arg0, Intent intent) {
			// TODO Auto-generated method stub
			
			//如果没有收到currentNum，则返回-1
			int i = intent.getIntExtra("currentNum", -1);
			
			Bundle bundle = new Bundle();
			bundle.putInt("num", i);
			
			Message msg = new Message();
			msg.what = 1;
			msg.setData(bundle);
			handler.sendMessage(msg);
			
			Log.v(TAG, "activity receiver");
		}
		
	};

}
