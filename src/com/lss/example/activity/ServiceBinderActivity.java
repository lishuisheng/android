package com.lss.example.activity;

import com.lss.example.lssdemo.R;
import com.lss.example.server.BinderService;
import com.lss.example.server.OnProgressListener;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ServiceBinderActivity extends BaseActivity implements
		OnClickListener {

	private ProgressBar pb = null;
	private TextView tv = null;
	private Button btn_bind = null;
	private Button btn_unbind = null;
	private MyConn conn = null;
	private BinderService myService = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.service_binder_activity);

		initViews();
	}

	private void initViews() {

		pb = (ProgressBar) findViewById(R.id.pb_service);
		tv = (TextView) findViewById(R.id.tv_binderservice);
		btn_bind = (Button) findViewById(R.id.btn_bindBinderService);
		btn_unbind = (Button) findViewById(R.id.btn_unbindBinderService);
		btn_bind.setOnClickListener(this);
		btn_unbind.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
		case R.id.btn_bindBinderService:
			if(conn == null){
				conn = new MyConn();
			}
			
			Intent intent = new Intent("bindservice");
			bindService(intent, conn, Context.BIND_AUTO_CREATE);
			
			if(myService != null){
				
				//ͨ��Serviceʵ��������������ط���
				myService.startDownLoad();
			}
			

			break;

		case R.id.btn_unbindBinderService:
			
			if(conn != null){
				try {
					unbindService(conn);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
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
			
			//��ȡһ��Serviceʵ��
			myService = ((BinderService.MyBinder)binder).getService();
			
			//�˷�����BindService���Զ���ģ��ӿ�Ҳ���Զ���ģ�ʵ�ָýӿڣ���дonProgress������
			myService.setOnProgressListener(new OnProgressListener() {
				
				//�˷����������Ǵ���һ��progress����
				@Override
				public void onProgress(int progress) {
					// TODO Auto-generated method stub
					pb.setProgress(progress);
					//�漰�߳�UI���£�ֻ�н��������������߳��и���UI
					//tv.setText("��ǰ���ȣ�" + progress + "%");
				}
			});
		}

		@Override
		public void onServiceDisconnected(ComponentName arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if(conn != null){
			//unbindService(conn);
		}
		
	}
}
