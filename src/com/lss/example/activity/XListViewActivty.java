package com.lss.example.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.maxwin.view.XListView;
import me.maxwin.view.XListView.IXListViewListener;

import com.lss.example.adapter.XListViewAdapter;
import com.lss.example.lssdemo.R;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;

public class XListViewActivty extends BaseActivity implements IXListViewListener{
	
	private static final Object Integer = null;
	private XListView listView = null;
	private XListViewAdapter mAdapter = null;
	private List<Map<String, Object>> items = null;
	private Handler mHandler;
	private int start = 0;
	private static int refreshCnt = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xlistview);
		
		initViews();
		geneItems();
	
		listView.setAdapter(mAdapter);
//		mListView.setPullLoadEnable(false);
//		mListView.setPullRefreshEnable(false);
		listView.setXListViewListener(this);
		mHandler = new Handler();
	}
	
	private void geneItems() {
		for (int i = 0; i != 6; ++i) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", "我的第" + (++start) + "任女朋友");
			map.put("num", i);
			items.add(map);
		}
	}
	
	private void onLoad() {
		listView.stopRefresh();
		listView.stopLoadMore();
		listView.setRefreshTime("更新时间");
	}
	
	
	
	private void initViews(){
		listView = getView(R.id.id_xlistView);
		listView.setPullLoadEnable(true);
		items = new ArrayList<Map<String,Object>>();
		mAdapter = new XListViewAdapter(XListViewActivty.this, items);
		
	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				start = ++refreshCnt;
				items.clear();
				geneItems();
				// mAdapter.notifyDataSetChanged();
				mAdapter = new XListViewAdapter(XListViewActivty.this, items);
				listView.setAdapter(mAdapter);
				onLoad();
			}
		}, 2000);
	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				geneItems();
				mAdapter.notifyDataSetChanged();
				onLoad();
			}
		}, 2000);
	}

}
