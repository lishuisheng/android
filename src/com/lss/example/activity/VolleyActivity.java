package com.lss.example.activity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lss.example.lssdemo.R;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class VolleyActivity extends BaseActivity{

	private Button button = null;
	private TextView textView = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.volley);
		initView();
	}
	
	private void initView(){
		button = getView(R.id.btn_volley);
		textView = getView(R.id.tv_volleyShow);
		button.setOnClickListener(this);
	}
	
	private void work(){
	
		RequestQueue queue = Volley.newRequestQueue(VolleyActivity.this);
		
		StringRequest request = new StringRequest("http://www.baidu.com", 
				new Response.Listener<String>() {

					@Override
					public void onResponse(String result) {
						// TODO Auto-generated method stub
						
						if(result != null){
							textView.setText(result);
						}else {
							showSlowToast("llllll");
						}
						
					}
				}, 
				new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub
					
						showSlowToast("eeee" + error.getMessage());
					}
				});
		
		queue.add(request);
	}
	
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		super.onClick(view);
		
		switch (view.getId()) {
		case R.id.btn_volley:
			
			work();
			break;

		default:
			break;
		}
	}

	
}
