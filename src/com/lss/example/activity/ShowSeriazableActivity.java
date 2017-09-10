package com.lss.example.activity;

import com.lss.example.bean.Book;
import com.lss.example.lssdemo.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

public class ShowSeriazableActivity extends Activity {

	private TextView tv = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.seriazable_two);
	
		String str = getSeriazableString();
	
		tv = (TextView) findViewById(R.id.tv_seriazable);
		tv.setText(str);
	}
	
	private String getSeriazableString(){
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		Book book = (Book) bundle.getSerializable("seriazable");
		
		String str = "bookName:" + book.getName() + "  bookAthor:" + book.getAthor() + " bookPrice:" + book.getPrice();
		
		return str;
	}
	

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub

		if (keyCode == KeyEvent.KEYCODE_BACK) {

			Intent intent = new Intent(this, SeriazableActivity.class);
			this.startActivity(intent);
			finish();

			return true;
		}

		return super.onKeyDown(keyCode, event);
	}


}
