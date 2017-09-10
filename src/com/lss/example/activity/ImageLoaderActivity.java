package com.lss.example.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lss.example.app.MyApplication;
import com.lss.example.lssdemo.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

public class ImageLoaderActivity extends BaseActivity {

	private ImageView imageView = null;
	private TextView textView = null;
	String url = MyApplication.url;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image_loader);
		
		//实例化控件
		initView();
		
		//设置选项配置默认参数
		DisplayImageOptions options = setConfOptions();
		
		//下载图片
		loadImage(options);
		
		//loadImageTwo(options);
	
	
	}

	private void initView(){
		textView = (TextView) findViewById(R.id.tv_image_loader);
		imageView = (ImageView) findViewById(R.id.image);

	}
	
	private DisplayImageOptions setConfOptions(){
		
		//创建默认的ImageLoader配置参数
		//ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)  
        //.memoryCache(new WeakMemoryCache())  添加了软引用缓存策略
        //.build();  
		ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(getApplicationContext());
		//获得ImageLoader实例，单例模式
		ImageLoader.getInstance().init(configuration);
		
		//选项配置
		DisplayImageOptions options = new DisplayImageOptions.Builder()
		.showStubImage(R.drawable.ic_stub)			// 设置图片下载期间显示的图片
		.showImageForEmptyUri(R.drawable.ic_empty)	// 设置图片Uri为空或是错误的时候显示的图片
		.showImageOnFail(R.drawable.ic_error)		// 设置图片加载或解码过程中发生错误显示的图片	
		.cacheInMemory(true)						// 设置下载的图片是否缓存在内存中
		.cacheOnDisk(true)							// 设置下载的图片是否缓存在SD卡中
		.displayer(new RoundedBitmapDisplayer(20))	// 设置成圆角图片
		.build();									// 创建配置过得DisplayImageOption对象
		
		
		
		return options;
	}
	
	private void loadImage(DisplayImageOptions options){
		
		/*
		 * 该方法自动会对图片大小进行处理，无需设置宽高
		 * 第四个参数在第五个参数方法中用到
		 */
		ImageLoader.getInstance().displayImage(url, imageView, options, new SimpleImageLoadingListener(), new ImageLoadingProgressListener() {
			
			@Override
			public void onProgressUpdate(String url, View imageView, int current, int total) {
				// TODO Auto-generated method stub
				
				String text = "下载进度： " + (current/total)*100 + "%";
				textView.setText(text);
			}
		});
	}
	
	private void loadImageTwo(DisplayImageOptions options){
		
		
		//设置图片的宽高
		ImageSize size = new ImageSize(400, 400);
		ImageLoader.getInstance().loadImage(url, size, options, new SimpleImageLoadingListener(){
			
			@Override
			public void onLoadingComplete(String imageUri, View view,
					Bitmap loadedImage) {
				// TODO Auto-generated method stub
				super.onLoadingComplete(imageUri, view, loadedImage);
				
				imageView.setImageBitmap(loadedImage);
			}
		});
		
	}

}
