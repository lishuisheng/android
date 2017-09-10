package com.lss.example.server;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

public class ServiceMessenger extends Service {

	private String TAG = "ServiceMessenger";
	private Timer timer = null;
	private int i = 0;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.v(TAG, "onCreate");
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		
		Log.v(TAG, "onBind");
		return messenger.getBinder();
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		Log.v(TAG, "onDestroy");
		if(timer != null){
			timer.cancel();
			timer = null;
		}
		
	}

	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		Log.v(TAG, "onUnbind");
		
		
		return super.onUnbind(intent);
	}

	class MyTimerTask extends TimerTask {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			if (i == 100) {
				i = 0;
			}
			try {
				// send the message to the client
				Message message = Message.obtain(null, 1, i, 0);
				messenger.send(message);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			i++;
		}

	}

	private Handler handler = new Handler() {

		public void handleMessage(android.os.Message msg) {

			switch (msg.what) {
			case 1:

				messenger = msg.replyTo;
				timer = new Timer();
				timer.schedule(new MyTimerTask(), 0, 1000);
				break;

			default:
				break;
			}

		};
	};

	private Messenger messenger = new Messenger(handler);
}
