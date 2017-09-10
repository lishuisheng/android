package com.lss.example.server;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class BinderService extends Service {

	/**
	 * 进度条的最大值
	 */
	public static final int MAX_PROGRESS = 100;
	/**
	 * 进度条的进度值
	 */
	private int progress = 0;

	/**
	 * 更新进度的回调接口,此接口在同一个包下创建了。
	 */
	private OnProgressListener onProgressListener;

	/**
	 * 注册回调接口的方法，供外部调用
	 * 
	 * @param onProgressListener
	 */
	public void setOnProgressListener(OnProgressListener onProgressListener) {
		this.onProgressListener = onProgressListener;
	}

	/**
	 * 增加get()方法，供Activity调用
	 * 
	 * @return 下载进度
	 */
	public int getProgress() {
		return progress;
	}

	/**
	 * 模拟下载任务，每秒钟更新一次
	 */
	public void startDownLoad() {
		thread.start();
	}
	
	Thread thread = new Thread(new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (progress < MAX_PROGRESS) {
				progress += 10;

				// 进度发生变化通知调用方
				if (onProgressListener != null) {
					onProgressListener.onProgress(progress);
				}

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}
	});

	 /** 
     * 返回一个Binder对象 
     */  
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return new MyBinder();
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		thread.interrupt();
	}
	
	public class MyBinder extends Binder{
		 /** 
         * 获取当前Service的实例 
         * @return 
         */  
        public BinderService getService(){  
            return BinderService.this;  
        }  
	}

}
