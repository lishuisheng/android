package com.lss.example.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import com.lss.example.activity.ListViewButtonActivity;
import com.lss.example.data.MyData;
import com.lss.example.lssdemo.R;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.StaticLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ListViewButtonAdapter extends BaseAdapter {

	/*
	 * 构造函数，用以传递上下文
	 */
	public ListViewButtonAdapter(Context context) {
		this.inflater = LayoutInflater.from(context);
		this.context = context;
	}
	
	Context context;
	ArrayList<HashMap<String, Object>> list = MyData.getListBtnData();

	LayoutInflater inflater = null;

	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View contentView, ViewGroup parent) {
		// TODO Auto-generated method stub

		ViewHolder holder = null;

		if (null == contentView) {
			holder = new ViewHolder();
			contentView = inflater.inflate(R.layout.list_item_btn, null);

			holder.tv = (TextView) contentView.findViewById(R.id.tv_listview);
			holder.iv = (ImageView) contentView.findViewById(R.id.iv_listview);
			holder.btn = (Button) contentView.findViewById(R.id.btn_listview);

			contentView.setTag(holder);
		} else {
			holder = (ViewHolder) contentView.getTag();
		}

		String text = (String) list.get(position).get("text");
		holder.tv.setText(text);

		holder.iv.setImageResource((Integer) list.get(position).get("img"));

		holder.btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				showInfo();
			}
		});

		return contentView;
	}

	/**
	 * 当用户点击按钮时触发的事件，会弹出一个确认对话框
	 */
	public void showInfo() {

		new AlertDialog.Builder(context)

		.setTitle("我的listview")

		.setMessage("介绍...")

		.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {

			}

		})

		.show();

	}

	private static class ViewHolder {

		TextView tv;
		ImageView iv;
		Button btn;
	}

}
