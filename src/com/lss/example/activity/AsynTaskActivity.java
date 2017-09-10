package com.lss.example.activity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.lss.example.app.MyApplication;
import com.lss.example.lssdemo.R;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class AsynTaskActivity extends BaseActivity{

	private Button button = null;
	private ImageView imageView = null;
	private ProgressDialog progressDialog = null;
	private String url = MyApplication.url3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.asyn_task);
		
		initView();
		initProgress();
		
	}
	
	private void initView(){
		button = getView(R.id.btn_asyntask);
		button.setOnClickListener(this);
		imageView = getView(R.id.iv_asyntask_image);
	}
	
	private void initProgress(){
		progressDialog = new ProgressDialog(this);
		progressDialog.setTitle("提示信息");
		progressDialog.setMessage("正在下载，请稍后...");
		progressDialog.setCancelable(false);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		
	}
	
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		super.onClick(view);
		
		switch (view.getId()) {
		case R.id.btn_asyntask:
			
			MyAsynTask task = new MyAsynTask();
			task.execute(url);
			
			break;

		default:
			break;
		}
	}
	
	class MyAsynTask extends AsyncTask<String, Integer, byte[]>{

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			progressDialog.show();
		}
		
		@Override
		protected byte[] doInBackground(String... params) {
			// TODO Auto-generated method stub
			
			byte[] image = new byte[]{};
			HttpClient client = new DefaultHttpClient();
			HttpGet get =new HttpGet(params[0]);
			try {
				HttpResponse response = client.execute(get);
				HttpEntity entity = response.getEntity();
				InputStream is = null;
				ByteArrayOutputStream byte_out = new ByteArrayOutputStream();
				
				if(entity!=null && response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
					
					//获取文件的总长度
					long file_lenth = entity.getContentLength();
					//每次读取后累加的长度
					long total_lenth = 0;
					
					int lenth = 0;
					
					byte[] data = new byte[1024];
					//每次读取1024个字节
					is = entity.getContent();
					while((lenth = is.read(data)) != -1){
						
						mySystemOutPrint("lenth", "" + lenth);
						//mySystemOutPrint("total", "" + file_lenth);
						
						total_lenth += lenth;
						//循环写入到输出字节流中
						byte_out.write(data, 0, lenth);
						
						int progress = (int) (total_lenth/(float)file_lenth)*100;
						
						//把进度发送到主线程进行UI更新
						publishProgress(progress);
					}
					
				}
				
				
				image = byte_out.toByteArray();
				is.close();
				byte_out.close();
				
				
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				client.getConnectionManager().shutdown();
			}
			
			return image;
		}
		
		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			progressDialog.setProgress(values[0]);
		}
		
		@Override
		protected void onPostExecute(byte[] result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			Bitmap bitmap = BitmapFactory.decodeByteArray(result, 0, result.length);
			imageView.setImageBitmap(bitmap);
			
			progressDialog.dismiss();
		}
		
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		progressDialog.setCancelable(true);
		progressDialog.dismiss();
	}

	
}
