package com.lss.example.adapter;

import java.util.List;

import com.lss.example.data.LiShuiSheng;
import com.lss.example.lssdemo.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TableAdapter extends BaseAdapter {
	
	private List<LiShuiSheng> lists = null;
	private LayoutInflater inflater = null;
	
	public TableAdapter(Context context, List<LiShuiSheng> lists){
		this.lists = lists;
		inflater = LayoutInflater.from(context);
	}
	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return lists.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return lists.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup arg2) {
		// TODO Auto-generated method stub
		
		LiShuiSheng lss = (LiShuiSheng) this.getItem(position);
		//LiShuiSheng lss = lists.get(position);
		ViewHolder viewHolder = null;
		if(view == null){
			viewHolder = new ViewHolder();
			view = inflater.inflate(R.layout.list_lishuisheng_item, null);
			viewHolder.mTextName = (TextView) view.findViewById(R.id.tv_lss_name);
			viewHolder.mTextAge = (TextView) view.findViewById(R.id.tv_lss_age);
			viewHolder.mTextWeigh = (TextView) view.findViewById(R.id.tv_lss_weigh);
			
			view.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder) view.getTag();
		}
		
		viewHolder.mTextName.setText(lss.getName());
		viewHolder.mTextAge.setText(lss.getAge()+"Ëê");
		viewHolder.mTextWeigh.setText(lss.getWeigh() + "g");
		
		return view;
	}
	
	private static class ViewHolder{
		
		public TextView mTextName = null;
		public TextView mTextAge = null;
		public TextView mTextWeigh = null;
	}

}


