package com.lss.example.activity;

import com.lss.example.bean.Book;
import com.lss.example.lssdemo.R;
import com.lss.example.server.Person;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SeriazableActivity extends BaseActivity implements OnClickListener{

	private Button mButton = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.seriable_one);
		
		initView();
	}
	
	private void initView(){
		mButton = (Button) findViewById(R.id.btn_seriazable);
		mButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
			
		if(arg0 == mButton){
			seriableMethor();
		}
		
	}
	
	private void seriableMethor(){
		Book book = new Book();
		book.setName("ÉñµñÏÀÂÂ");
		book.setPrice(100);
		book.setAthor("½ðÓ¹");
		
		Intent intent = new Intent(SeriazableActivity.this, ShowSeriazableActivity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("seriazable", book);
		intent.putExtras(bundle);
		
		startActivity(intent);
		finish();
	}
	



}
