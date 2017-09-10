package com.lss.example.activity;

import com.lss.example.lssdemo.R;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.widget.TextView;

public class SwipeRefreshLayoutActivity extends BaseActivity implements OnRefreshListener{

	private TextView textView = null;
	private SwipeRefreshLayout swipeRefreshLayout = null;
	private static final int REFRESH_COMPLETE = 0X110;  
	
	private Handler handler = new Handler(){
		
		public void handleMessage(android.os.Message msg) {
			
			switch (msg.what) {
			case REFRESH_COMPLETE:
				
				textView.setText("完成刷新");
				
				swipeRefreshLayout.setRefreshing(false);
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
		setContentView(R.layout.swiperefresh_layout);
		
		initView();
	}

	
	private void initView(){
		textView = getView(R.id.tv_swiperefresh);
		swipeRefreshLayout = getView(R.id.swipte);
		swipeRefreshLayout.setOnRefreshListener(this);
		swipeRefreshLayout.setColorScheme(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
		
			
	}


	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		textView.setText("正在刷新");
		handler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 3000);
		
	}
}
