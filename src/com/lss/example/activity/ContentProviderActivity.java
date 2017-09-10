package com.lss.example.activity;

import com.lss.example.lssdemo.R;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ContentProviderActivity extends BaseActivity{

	private TextView textView = null;
	private Button button = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contentprovider);
		
		initView();
		
	}
	
	private void initView(){
		textView = getView(R.id.tv_contentprovider);
		button = getView(R.id.btn_getContentProvider);
		button.setOnClickListener(this);
	}
	
	private void insert(){
		ContentResolver resolver = getContentResolver();
		Uri uri = Uri.parse("content://com.example.lsscontentprovider.MyContentProvider/insert");
		ContentValues values = new ContentValues();
		values.put("name", "李水胜");
		values.put("address", "广东");
		resolver.insert(uri, values);
	}
	
	private void query(){
		ContentResolver resolver = getContentResolver();
		Uri uri = Uri.parse("content://com.example.lsscontentprovider.MyContentProvider/query");
		Cursor cursor = resolver.query(uri, null, null, null, null);
		String name = null;
		String address = null;
		if(cursor != null){
			while(cursor.moveToNext()){
				name = cursor.getString(cursor.getColumnIndex("name"));
				address = cursor.getString(cursor.getColumnIndex("address"));
			}
			
			textView.setText("name: " + name + "  address: " + address);
		}else {
			showFastToast("cursor is null");
		}
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		super.onClick(arg0);
		
		insert();
		query();
		
		
	}
	
	
}
