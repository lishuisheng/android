package com.lss.example.activity;

import java.io.File;

import com.lss.example.app.MyApplication;
import com.lss.example.lssdemo.R;
import com.lss.example.util.MyUtil;
import com.lss.example.view.HorizontalProgressBarWithNumber;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.FinalBitmap;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.annotation.view.ViewInject;
import net.tsz.afinal.http.AjaxCallBack;

public class MyAfinalActivity extends FinalActivity {

	@ViewInject(id = R.id.btn_final_showMsg)
	Button button = null;//下载网络图片按钮
	
	@ViewInject(id = R.id.btn_final_downLoadMsg)
	Button button_downLoad = null;//下载网络文件按钮

	@ViewInject(id = R.id.tv_final)
	TextView textView = null;//显示下载状态
	
	@ViewInject(id = R.id.iv_final)
	ImageView imageView = null;//显示图片控件

	private Context context = this;
	private MyUtil util = null;
	private String url1 = MyApplication.url1;//要下载的文件路径
	private String url = MyApplication.url;//要下载的图片路径
	private String obPath = MyApplication.savePath;//要保存到本地的路径
	private AjaxCallBack<File> callback = null;
	private FinalBitmap fb = null;
	private Animation mAnimation = null;
	
	private HorizontalProgressBarWithNumber mProgressBar;  
    private static final int MSG_PROGRESS_UPDATE = 0x110;  
    
    private Handler mHandler = new Handler() {  
        public void handleMessage(android.os.Message msg) {  
            int progress = mProgressBar.getProgress();  
            mProgressBar.setProgress(++progress);  
            if (progress >= 100) {  
                mHandler.removeMessages(MSG_PROGRESS_UPDATE);  
                  
            }  
            mHandler.sendEmptyMessageDelayed(MSG_PROGRESS_UPDATE, 100);  
        };  
    };  

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.final_activity);
		
		initView();
	}
	
	private void initView(){
		
		 mProgressBar = (HorizontalProgressBarWithNumber) findViewById(R.id.id_progressbar01);  
		mAnimation = AnimationUtils.loadAnimation(context, R.anim.my_rotate);
	}

	public void click(View view) {

		switch (view.getId()) {
		
		case R.id.iv_final:
			
			imageView.startAnimation(mAnimation);
			
			util = new MyUtil(context, "动画效果");
			util.myToast();
			
			break;
			
		case R.id.btn_final_showMsg:

			button.setText("换一张");
			textView.setText("下载图片成功");
			fb = FinalBitmap.create(context);
			fb.display(imageView, url);
			

			break;

		case R.id.btn_final_downLoadMsg:

			FinalHttp fh = new FinalHttp();
			callback = new AjaxCallBack<File>() {

				@Override
				public void onStart() {
					// TODO Auto-generated method stub
					super.onStart();

					util = new MyUtil(context, "开始下载...");
					util.myToast();
				}

				@Override
				public void onLoading(long count, long current) {
					// TODO Auto-generated method stub
					super.onLoading(count, current);

					int progress = 0;
					if (current != count && current != 0) {
						progress = (int) (current / (float) count * 100);
						
						textView.setText("下载进度：" + progress + "%");
						
						mProgressBar.setProgress(progress);
						// mHandler.sendEmptyMessage(MSG_PROGRESS_UPDATE);  
						
					} else {
						progress = 100;
						mProgressBar.setProgress(progress);
						textView.setText("下载文件成功");
					}
				
					
				}

				@Override
				public void onSuccess(File t) {
					// TODO Auto-generated method stub
					super.onSuccess(t);

					util = new MyUtil(context, "下载成功");
					util.myToast();

					textView.setText("下载成功");
				}

				@Override
				public void onFailure(Throwable t, int errorNo, String strMsg) {
					// TODO Auto-generated method stub
					super.onFailure(t, errorNo, strMsg);

					util = new MyUtil(context, "下载失败");
					util.myToast();

					textView.setText(strMsg);

				}
			};

			File file = new File(obPath);
			if(!file.exists()){
				file.mkdir();
			}

			String path = obPath + "zydp.apk";
			
			fh.download(url1, path, callback);

			break;

		default:
			break;
		}

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub

		if (keyCode == KeyEvent.KEYCODE_BACK) {

			Intent intent = new Intent(context, MainActivity.class);
			context.startActivity(intent);
			finish();

			return true;
		}

		return super.onKeyDown(keyCode, event);
	}

}
