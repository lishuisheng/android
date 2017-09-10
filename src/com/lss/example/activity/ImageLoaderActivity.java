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
		
		//ʵ�����ؼ�
		initView();
		
		//����ѡ������Ĭ�ϲ���
		DisplayImageOptions options = setConfOptions();
		
		//����ͼƬ
		loadImage(options);
		
		//loadImageTwo(options);
	
	
	}

	private void initView(){
		textView = (TextView) findViewById(R.id.tv_image_loader);
		imageView = (ImageView) findViewById(R.id.image);

	}
	
	private DisplayImageOptions setConfOptions(){
		
		//����Ĭ�ϵ�ImageLoader���ò���
		//ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)  
        //.memoryCache(new WeakMemoryCache())  ����������û������
        //.build();  
		ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(getApplicationContext());
		//���ImageLoaderʵ��������ģʽ
		ImageLoader.getInstance().init(configuration);
		
		//ѡ������
		DisplayImageOptions options = new DisplayImageOptions.Builder()
		.showStubImage(R.drawable.ic_stub)			// ����ͼƬ�����ڼ���ʾ��ͼƬ
		.showImageForEmptyUri(R.drawable.ic_empty)	// ����ͼƬUriΪ�ջ��Ǵ����ʱ����ʾ��ͼƬ
		.showImageOnFail(R.drawable.ic_error)		// ����ͼƬ���ػ��������з���������ʾ��ͼƬ	
		.cacheInMemory(true)						// �������ص�ͼƬ�Ƿ񻺴����ڴ���
		.cacheOnDisk(true)							// �������ص�ͼƬ�Ƿ񻺴���SD����
		.displayer(new RoundedBitmapDisplayer(20))	// ���ó�Բ��ͼƬ
		.build();									// �������ù���DisplayImageOption����
		
		
		
		return options;
	}
	
	private void loadImage(DisplayImageOptions options){
		
		/*
		 * �÷����Զ����ͼƬ��С���д����������ÿ��
		 * ���ĸ������ڵ���������������õ�
		 */
		ImageLoader.getInstance().displayImage(url, imageView, options, new SimpleImageLoadingListener(), new ImageLoadingProgressListener() {
			
			@Override
			public void onProgressUpdate(String url, View imageView, int current, int total) {
				// TODO Auto-generated method stub
				
				String text = "���ؽ��ȣ� " + (current/total)*100 + "%";
				textView.setText(text);
			}
		});
	}
	
	private void loadImageTwo(DisplayImageOptions options){
		
		
		//����ͼƬ�Ŀ��
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
