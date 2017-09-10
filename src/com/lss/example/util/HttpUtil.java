package com.lss.example.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.lss.example.app.MyApplication;

public class HttpUtil {

	private static String path = MyApplication.url3;
	
	
	public HttpUtil() {
		// TODO Auto-generated constructor stub
		
	}
	
	//获取输入流
	public static InputStream getInputStream(){
		
		InputStream inputStream = null;
		
		try {
			
			URL url = new URL(path);
			if(url != null){
				HttpURLConnection connect = (HttpURLConnection) url.openConnection();
				connect.setDoInput(true);
				connect.setConnectTimeout(5000);
				connect.setRequestMethod("GET");
				int responseCode = connect.getResponseCode();
				if(responseCode == 200){
					inputStream = connect.getInputStream();
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inputStream;
	}
	
	//将输入流转换为字节数组
	public static byte[] getByteArray(InputStream inputStream){
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		
		int len = 0;
		try {
			while((len = inputStream.read(buffer)) != -1){
				bos.write(buffer, 0, len);
			}
			bos.close();
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bos.toByteArray();
	}
	
}
