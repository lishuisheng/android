package com.lss.example.activity;

import java.util.ArrayList;
import java.util.List;

import com.lss.example.adapter.TableAdapter;
import com.lss.example.data.LiShuiSheng;
import com.lss.example.lssdemo.R;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ListView;


public class ListViewHolderActivity extends BaseActivity{

	private ListView listView = null;
	private ViewGroup title = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_lishuisheng);
		
		initView();
		
		List<LiShuiSheng> lists = new ArrayList<LiShuiSheng>();
		
		for(int i=0; i<50; i++){
			lists.add(new LiShuiSheng("ÀîË®Ê¤", 18, 120));
		}
	
		
		
		TableAdapter adapter = new TableAdapter(ListViewHolderActivity.this, lists);
		listView.setAdapter(adapter);
	}
	
	private void initView(){
		title = getView(R.id.table_title);
		listView = getView(R.id.list_lss);
		title.setBackgroundColor(Color.rgb(255, 100, 10));
	}


}
