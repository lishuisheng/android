package com.lss.example.server;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class BinderService extends Service {

	/**
	 * �����������ֵ
	 */
	public static final int MAX_PROGRESS = 100;
	/**
	 * �������Ľ���ֵ
	 */
	private int progress = 0;

	/**
	 * ���½��ȵĻص��ӿ�,�˽ӿ���ͬһ�����´����ˡ�
	 */
	private OnProgressListener onProgressListener;

	/**
	 * ע��ص��ӿڵķ��������ⲿ����
	 * 
	 * @param onProgressListener
	 */
	public void setOnProgressListener(OnProgressListener onProgressListener) {
		this.onProgressListener = onProgressListener;
	}

	/**
	 * ����get()��������Activity����
	 * 
	 * @return ���ؽ���
	 */
	public int getProgress() {
		return progress;
	}

	/**
	 * ģ����������ÿ���Ӹ���һ��
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

				// ���ȷ����仯֪ͨ���÷�
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
     * ����һ��Binder���� 
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
         * ��ȡ��ǰService��ʵ�� 
         * @return 
         */  
        public BinderService getService(){  
            return BinderService.this;  
        }  
	}

}
