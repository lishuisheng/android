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
		
		//�����intent����������action���ѣ����߳��в������Ϣ�Լ�������
		intent = new Intent("broadcase");
		//ʵ����һ��Timer��ͨ��Timer�Ķ���ִ��TimerTask
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
			//�ڷ���˷��͹㲥
			sendBroadcast(intent);
			i++;
		}
		
	}

}
