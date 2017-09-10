package com.lss.example.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

import com.lss.example.lssdemo.R;
import com.lss.example.view.RoundImageView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class XListViewAdapter extends BaseAdapter {

	private Context context = null;
	private List<Map<String, Object>> lists = null;
	private LayoutInflater inflater = null;
	
	
	public XListViewAdapter(Context context, List<Map<String, Object>> lists) {
		// TODO Auto-generated constructor stub
	
		this.context = context;
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
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		ViewHolder holder = null;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map = lists.get(position);
		String name = null;
		int num = 0;
		int nums[] = {R.drawable.girl, R.drawable.girl1, R.drawable.girl2, R.drawable.girl3, R.drawable.girl4, R.drawable.girl5};
		if(map != null){
			name = (String) map.get("name");
			num =(Integer) map.get("num");
		}else{
			name = "null";
		}
		
		
		if(view == null){
			holder = new ViewHolder();
			view = inflater.inflate(R.layout.xlistvie_item, null);
			holder.imageView = (RoundImageView) view.findViewById(R.id.iv_roudn_img);
			holder.textView = (TextView) view.findViewById(R.id.tv_xlist_name);
			view.setTag(holder);
		}else {
			holder = (ViewHolder) view.getTag();
		}
		
		
		holder.imageView.setImageResource(nums[num]);
		holder.textView.setText(name);
		
		return view;
	}
	
	public static class ViewHolder{
		
		private RoundImageView imageView = null;
		private TextView textView = null;
	}

}


