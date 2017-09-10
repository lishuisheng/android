package com.lss.example.activity;

import com.lss.example.adapter.ListViewButtonAdapter;
import com.lss.example.lssdemo.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class ListViewButtonActivity extends BaseActivity {

	private ListView listView = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview_btn);
		
		init();
	}
	
	private void init(){
		listView = (ListView) findViewById(R.id.lv_listview);
		ListViewButtonAdapter adapter = new ListViewButtonAdapter(ListViewButtonActivity.this);
		listView.setAdapter(adapter);
	}
	
	

}
