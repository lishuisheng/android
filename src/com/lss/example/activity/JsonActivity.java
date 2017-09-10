package com.lss.example.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.lss.example.lssdemo.R;
import com.lss.example.server.JsonServer;
import com.lss.example.server.Person;
import com.lss.example.util.JsonTools;

public class JsonActivity extends BaseActivity {

	private Button button = null;
	private TextView tv1 = null;
	private TextView tv2 = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.json_text);
		
		button = (Button) findViewById(R.id.btn_json);
		tv1 = (TextView) findViewById(R.id.tv_json);
		tv2 = (TextView) findViewById(R.id.tv_json2);
		
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				JsonServer server = new JsonServer();
				Person person = server.getPerson();
				String json = JsonTools.createJsonString("person", person);
				String json1 = JsonTools.createJsonString("name", "ÀîË®Ê¤");
				String json2 = JsonTools.createJsonString("person", server.getListPerson());
				tv1.setText(json2);
				
				List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
				try {
					//JSONObject jsonObject = new JSONObject(json2);
					//String str = jsonObject.optString("person");
					JSONArray jsonArray = new JSONArray(json2);
					JSONObject jsonObject = jsonArray.optJSONObject(0);
					String name = jsonObject.optString("name");
					tv2.setText("name:" + name);
					
					/*
					for(int i=0; i<jsonArray.length(); i++){
						
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("id", jsonArray.optInt(i));
						map.put("name", jsonArray.optString(i));
						map.put("address", jsonArray.optString(i));
						
						list.add(map);
					}*/
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
	}
	

}
