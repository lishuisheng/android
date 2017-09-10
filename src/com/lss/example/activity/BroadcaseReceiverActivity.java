package com.lss.example.activity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.lss.example.lssdemo.R;
import com.lss.example.server.UnSortDynamicBroadcaseReceiver;

public class BroadcaseReceiverActivity extends BaseActivity {

	private Button button01 = null;//静态注册普通广播
	private Button button02 = null;//动态注册普通广播
	private Button button03 = null;//静态注册有序广播
	private UnSortDynamicBroadcaseReceiver receiver = null;//定义一个动态注册用到的接受者对象
	private Context context = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.broasereceiver);

		initView();
	}

	/*
	 * 初始化控件以及设置监听
	 */
	private void initView() {
		
		//实例化
		button01 = (Button) findViewById(R.id.btn_broadcase_one);
		button02 = (Button) findViewById(R.id.btn_broadcase_two);
		button03 = (Button) findViewById(R.id.btn_broadcase_three);
		
		//设置监听
		button01.setOnClickListener(new BroacaseListener());
		button02.setOnClickListener(new BroacaseListener());
		button03.setOnClickListener(new BroacaseListener());
		
		context = BroadcaseReceiverActivity.this;
	}

	/*
	 * 按钮监听接口类
	 */
	public class BroacaseListener implements OnClickListener {

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub

			switch (view.getId()) {
			case R.id.btn_broadcase_one:

				// 使用静态的方式注册广播，可以使用显示意图进行发送广播
				Intent intent = new Intent("lishuisheng");
				intent.putExtra("msg", "静态注册：这是从广播发送过来的信息");
				
				//发送无序广播
				context.sendBroadcast(intent, null);
				break;

			case R.id.btn_broadcase_two:

				 //发送广播  
				Intent intent2 = new Intent("dynamic");
				intent2.putExtra("msg", "动态注册：这是从广播发送过来的信息");
				sendBroadcast(intent2, null); 
				break;

			case R.id.btn_broadcase_three:
				
				//使用静态的方式注册广播，可以使用显示意图进行发送广播
				Intent intent3 = new Intent("sort");
				intent3.putExtra("msg", "静态注册有序广播：这是从广播发送过来的信息");
				
				//发送有序广播
				sendOrderedBroadcast(intent3, null);

				break;

			default:
				break;
			}
		}

	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//动态注册广播  
        IntentFilter intentFilter = new IntentFilter("dynamic"); 
        receiver = new UnSortDynamicBroadcaseReceiver();  
        registerReceiver(receiver, intentFilter);  
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
		//取消广播
		unregisterReceiver(receiver);
	}

	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();

		showSlowToast("click back");
	}

}
