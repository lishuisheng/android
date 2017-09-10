package com.lss.example.activity;

import com.lss.example.lssdemo.R;
import com.lss.example.util.MyUtil;
import com.lss.example.view.MyView;
import com.lss.example.view.SlipButton;
import com.lss.example.view.SlipButton.OnChangedListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ShowMyViewActivity extends BaseActivity implements OnChangedListener {

	private MyView view = null;
	private SlipButton slipButton = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myview);
		
		findView();
		
		
		view = (MyView) findViewById(R.id.view);
		view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "已经点击自定义控件", 1).show();
			}
		});
	}
	
	private void findView() {
		slipButton = (SlipButton) findViewById(R.id.btn_slip);
		slipButton.setCheck(true);
	}


	@Override
	public void OnChanged(boolean CheckState) {
		// TODO Auto-generated method stub
		
	}



}
