package com.lss.example.activity;

import java.util.ArrayList;
import java.util.HashMap;

import com.lss.example.data.MyData;
import com.lss.example.lssdemo.R;
import com.lss.example.util.BaseSelfActivity;
import com.lss.example.util.MyUtil;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

	private ListView listView = null;
	private SimpleAdapter adapter = null;
	private Context context = this;
	private MyData data = new MyData();
	private MyUtil util = null;
	private long exitTime = 0;
	private ArrayList<HashMap<String, Object>> listItem = null;
	
	//界面数组
	private Class[] classes = {MyAfinalActivity.class, MultiThreadActivity.class
			, ShowMyViewActivity.class, HttpGetImageActivity.class, JsonActivity.class
			, SeriazableActivity.class, ImageLoaderActivity.class, BroadcaseReceiverActivity.class
			, ListViewButtonActivity.class, FragmentStatic.class, FragmentDynamic.class
			, ServiceBroadcaseActivity.class, ServiceMessengerActivity.class, ServiceBinderActivity.class
			, DataBaseActivity.class, ListViewHolderActivity.class, BannerActivity.class, SaxParseActivity.class
			, AsynTaskActivity.class, XListViewActivty.class, ContentProviderActivity.class, VolleyActivity.class
			, SwipeRefreshLayoutActivity.class, TrigonViewActivity.class, ViewFlipperActivity.class}; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();
		fillListView();
	}

	public void initView() {

		listView = (ListView) findViewById(R.id.main_list);

	}

	public void fillListView() {

		listItem = data.getListData();
		adapter = new SimpleAdapter(context, listItem, R.layout.main_list_item,
				new String[] { "str" }, new int[] { R.id.main_list_item_tv });
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub

				int lenth = classes.length;
				if(position <lenth){
					changeActivity(classes[position]);
				}else {
					showSlowToast("nothing" + position);
				}
			}
		});
	}


	// 按返回键是否退出程序的提示
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			if ((System.currentTimeMillis() - exitTime) > 2000) {
				
				showSlowToast("再按一次退出程序");
				exitTime = System.currentTimeMillis();
				
			} else {
				finish();
				System.exit(0);
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);

	}

}
