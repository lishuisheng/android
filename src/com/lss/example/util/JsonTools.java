package com.lss.example.util;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonTools {
	
	public JsonTools(){
		
	}
	
	public static String createJsonString(String key, Object value){
		
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put(key, value);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonObject.toString();
		
	}

}
