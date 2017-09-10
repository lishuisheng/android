package com.lss.example.activity;

import java.io.File;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.lss.example.app.MyApplication;
import com.lss.example.lssdemo.R;
import com.lss.example.util.BaseSelfActivity;
import com.lss.example.util.DownLoadTask;
import com.lss.example.util.FileDownLoadThread;

public class MultiThreadActivity extends BaseSelfActivity {

	private Button btn_downLoad = null;
	private ProgressBar pb = null;
	private TextView tv = null;
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
	
		if (keyCode == KeyEvent.KEYCODE_BACK) {
	
			intentActivity(MainActivity.class);
			return true;
		}
	
		return super.onKeyDown(keyCode, event);
	}



	private FileDownLoadThread fdl = new FileDownLoadThread();
	
	 private String downloadUrl = MyApplication.url1;
	/** 
     * ʹ��Handler����UI������Ϣ 
     */  
    Handler mHandler = new Handler() {  
        @Override  
        public void handleMessage(Message msg) {  
        	
        	switch (msg.what) {
			case 1:
				
				myToast(MultiThreadActivity.this, "�������!");
				
				break;

			default:
				break;
			}
  
        	/*
            pb.setProgress(msg.getData().getInt("size"));  
  
            float temp = (float) pb.getProgress()  
                    / (float) pb.getMax();  
  
            int progress = (int) (temp * 100);  
            if (progress == 100) {  
                myToast(MultiThreadActivity.this, "������ɣ�");
            }  
            tv.setText("���ؽ���:" + progress + " %");  
            */
  
        }  
    };  
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.multi_thread_download);
		
		initView();
		
	}
	
	public void initView(){
		
		btn_downLoad = (Button) findViewById(R.id.btn_multiLoad);
		pb = (ProgressBar) findViewById(R.id.pb);
		tv = (TextView) findViewById(R.id.tv_multi);
	}
	
	
	 /** 
     * ����׼����������ȡSD��·���������߳� 
     */  
    private void doDownload() {  
        // ��ȡSD��·��  
        String path = Environment.getExternalStorageDirectory()  
                + "/lss/";  
        File file = new File(path);  
        // ���SD��Ŀ¼�����ڴ���  
        if (!file.exists()) {  
            file.mkdir();  
        }  
        // ����progressBar��ʼ��  
        pb.setProgress(0);  
  
        String fileName = "lishuisheng.apk";  
        int threadNum = 5;  
        String filepath = path + fileName;  
        DownLoadTask task = new DownLoadTask(downloadUrl, threadNum, filepath);  
        task.start();  
        
        if(fdl.isCompleted() == true){
        	
        	Message msg = new Message();
        	msg.what = 1;
        	mHandler.sendMessage(msg);
        }
        //mHandler.sendMessage(DownLoadTask.msg);
    }  
	
	
	//ע��˴�����Ϊpublic
	public void click(View view){
		
		 doDownload(); 
	}
	

}
