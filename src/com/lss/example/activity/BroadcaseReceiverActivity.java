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

	private Button button01 = null;//��̬ע����ͨ�㲥
	private Button button02 = null;//��̬ע����ͨ�㲥
	private Button button03 = null;//��̬ע������㲥
	private UnSortDynamicBroadcaseReceiver receiver = null;//����һ����̬ע���õ��Ľ����߶���
	private Context context = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.broasereceiver);

		initView();
	}

	/*
	 * ��ʼ���ؼ��Լ����ü���
	 */
	private void initView() {
		
		//ʵ����
		button01 = (Button) findViewById(R.id.btn_broadcase_one);
		button02 = (Button) findViewById(R.id.btn_broadcase_two);
		button03 = (Button) findViewById(R.id.btn_broadcase_three);
		
		//���ü���
		button01.setOnClickListener(new BroacaseListener());
		button02.setOnClickListener(new BroacaseListener());
		button03.setOnClickListener(new BroacaseListener());
		
		context = BroadcaseReceiverActivity.this;
	}

	/*
	 * ��ť�����ӿ���
	 */
	public class BroacaseListener implements OnClickListener {

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub

			switch (view.getId()) {
			case R.id.btn_broadcase_one:

				// ʹ�þ�̬�ķ�ʽע��㲥������ʹ����ʾ��ͼ���з��͹㲥
				Intent intent = new Intent("lishuisheng");
				intent.putExtra("msg", "��̬ע�᣺���Ǵӹ㲥���͹�������Ϣ");
				
				//��������㲥
				context.sendBroadcast(intent, null);
				break;

			case R.id.btn_broadcase_two:

				 //���͹㲥  
				Intent intent2 = new Intent("dynamic");
				intent2.putExtra("msg", "��̬ע�᣺���Ǵӹ㲥���͹�������Ϣ");
				sendBroadcast(intent2, null); 
				break;

			case R.id.btn_broadcase_three:
				
				//ʹ�þ�̬�ķ�ʽע��㲥������ʹ����ʾ��ͼ���з��͹㲥
				Intent intent3 = new Intent("sort");
				intent3.putExtra("msg", "��̬ע������㲥�����Ǵӹ㲥���͹�������Ϣ");
				
				//��������㲥
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
		//��̬ע��㲥  
        IntentFilter intentFilter = new IntentFilter("dynamic"); 
        receiver = new UnSortDynamicBroadcaseReceiver();  
        registerReceiver(receiver, intentFilter);  
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
		//ȡ���㲥
		unregisterReceiver(receiver);
	}

	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();

		showSlowToast("click back");
	}

}
