package com.lss.example.server;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class ServiceBroadcase extends Service {

	
	private int i = 0;
	private Intent intent = null;
	private Timer myTimer = null;
	private String TAG = "service";
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		Log.v(TAG, "service created");
		
		//这里的intent仅仅设置了action而已，在线程中才添加信息以及发生。
		intent = new Intent("broadcase");
		//实例化一个Timer，通过Timer的对象执行TimerTask
		myTimer = new Timer();
		myTimer.schedule(new MyTimerTask(), 0, 1000);
		
	}
	
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		Log.v(TAG, "service destroyed");
		
		MyTimerTask task = new MyTimerTask();
		task.cancel();
		myTimer.cancel();
		
		stopSelf();
	}

	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		
		Log.v(TAG, "service started");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		
		Log.v(TAG, "service unbind");
		return super.onUnbind(intent);
		
		
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		
		Log.v(TAG, "service bind");
		return null;
	}
	
	class MyTimerTask extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			if(i == 100){
				i = 0;
			}
			
			intent.putExtra("currentNum", i);
			//在服务端发送广播
			sendBroadcast(intent);
			i++;
		}
		
	}

}
