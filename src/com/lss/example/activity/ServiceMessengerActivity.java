package com.lss.example.activity;

import com.lss.example.lssdemo.R;
import com.lss.example.server.ServiceMessenger;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ServiceMessengerActivity extends BaseActivity implements
		OnClickListener {

	private TextView textView = null;
	private Button btn_bind = null;
	private Button btn_unbind = null;
	private ServiceConnection conn = null;
	private Messenger rMessenger = null;
	private Messenger mMessenger = null;
	private Handler handler = new MyHandler();
	private boolean isBinder = false;
	private String TAG = "service_messenger_activity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.service_messenger_activity);

		Log.v(TAG, "create");

		initViews();
	}

	private void initViews() {
		textView = (TextView) findViewById(R.id.tv_service_messenger);
		btn_bind = (Button) findViewById(R.id.btn_bindService);
		btn_unbind = (Button) findViewById(R.id.btn_unbindervice);
		btn_bind.setOnClickListener(this);
		btn_unbind.setOnClickListener(this);

	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub

		switch (view.getId()) {
		case R.id.btn_bindService:

			if(conn == null){
				
				conn = new MyConn();
				
				//参数为action,这个参数跟注册service时的action要一致
				Intent intent = new Intent("com.lss.example.server.ServiceMessenger");
					
				
				//如果连接从成功，则返回true
				isBinder = bindService(intent, conn, BIND_AUTO_CREATE);
			}
			

			break;

		case R.id.btn_unbindervice:

			if(conn != null && isBinder){
				conn = new MyConn();
				unbindService(conn);
				conn = null;
				isBinder = false;
			}

			break;

		default:
			break;
		}
	}
	
	class MyConn implements ServiceConnection{

		@Override
		public void onServiceConnected(ComponentName arg0, IBinder binder) {
			// TODO Auto-generated method stub
			
			Log.v(TAG, "onServiceConnected");
			rMessenger = new Messenger(binder);
			mMessenger = new Messenger(handler);
			sendMessage();
			
		}

		//解除绑定的时候并不会调用此方法，只有连接出现意外时才会调用
		@Override
		public void onServiceDisconnected(ComponentName arg0) {
			// TODO Auto-generated method stub
			Log.v(TAG, "onServiceDisconnected");
			rMessenger = null;
		}
		
	}


	private void sendMessage() {
		Message msg = Message.obtain(null, 1);
		msg.replyTo = mMessenger;
		try {
			rMessenger.send(msg);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	class MyHandler extends Handler {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);

			switch (msg.what) {
			case 1:

				int i = msg.arg1;
				textView.setText("当前进度：" + i + "%");

				break;

			default:
				break;
			}

		}
	}

}
