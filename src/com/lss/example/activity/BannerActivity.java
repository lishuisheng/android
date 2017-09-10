package com.lss.example.activity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.lss.example.bean.SongBanner;
import com.lss.example.data.MyData;
import com.lss.example.lssdemo.R;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

public class BannerActivity extends BaseActivity{

	public static String IMAGE_CACHE_PATH = "imageloader/Cache"; // ͼƬ����·��  
	  
    private ViewPager adViewPager;  
    private List<ImageView> imageViews;// ������ͼƬ����  
  
    private List<View> dots; // ͼƬ�������ĵ���Щ��  
    private List<View> dotList;  
  
    private TextView tv_date;  
    private TextView tv_title;  
    private TextView tv_topic_from;  
    private TextView tv_topic;  
    private int currentItem = 0; // ��ǰͼƬ��������  
    // ��������ָʾ��  
    private View dot0;  
    private View dot1;  
    private View dot2;  
    private View dot3;  
    private View dot4;  
  
	
	//��ʱ����
	private ScheduledExecutorService scheduledExecutorService = null;
	
	//�첽����
	private ImageLoader mImageLoader = null;
	private DisplayImageOptions options = null;
	
	//�ֲ�������
	private List<SongBanner> list = null;
	
	
	 private Handler handler = new Handler() {  
	        public void handleMessage(android.os.Message msg) {  
	            adViewPager.setCurrentItem(currentItem);  
	        };  
	    };  
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.banner);
		
		 // ʹ��ImageLoader֮ǰ��ʼ��  
        initImageLoader();  
        
     // ��ȡͼƬ����ʵ��  
        mImageLoader = ImageLoader.getInstance();  
        options = new DisplayImageOptions.Builder()  
                .showStubImage(R.drawable.top_banner_android)  
                .showImageForEmptyUri(R.drawable.top_banner_android)  
                .showImageOnFail(R.drawable.top_banner_android)  
                .cacheInMemory(true).cacheOnDisc(true)  
                .bitmapConfig(Bitmap.Config.RGB_565)  
                .imageScaleType(ImageScaleType.EXACTLY).build();  
  
        initAdData();  
  
        startAd();  
		
	}
	
	
	/** 
     * ��ʼ��ImageLoader 
     */  
    private void initImageLoader() {  
        File cacheDir = com.nostra13.universalimageloader.utils.StorageUtils  
                .getOwnCacheDirectory(getApplicationContext(),  
                        IMAGE_CACHE_PATH);  
  
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()  
                .cacheInMemory(true).cacheOnDisc(true).build();  
  
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(  
                this).defaultDisplayImageOptions(defaultOptions)  
                .memoryCache(new LruMemoryCache(12 * 1024 * 1024))  
                .memoryCacheSize(12 * 1024 * 1024)  
                .discCacheSize(32 * 1024 * 1024).discCacheFileCount(100)  
                .threadPriority(Thread.NORM_PRIORITY - 2)  
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();  
  
        ImageLoader.getInstance().init(config);  
    }  

    /** 
     * ��ʼ��������� 
     */  
    private void initAdData() {  
        // �������  
        list = MyData.getBannerData();  
  
        imageViews = new ArrayList<ImageView>();  
  
        // ��  
        dots = new ArrayList<View>();  
        dotList = new ArrayList<View>();  
        dot0 = findViewById(R.id.v_dot0);  
        dot1 = findViewById(R.id.v_dot1);  
        dot2 = findViewById(R.id.v_dot2);  
        dot3 = findViewById(R.id.v_dot3);  
        dot4 = findViewById(R.id.v_dot4);  
        dots.add(dot0);  
        dots.add(dot1);  
        dots.add(dot2);  
        dots.add(dot3);  
        dots.add(dot4);  
          
        tv_date = (TextView) findViewById(R.id.tv_date);  
        tv_title = (TextView) findViewById(R.id.tv_title);  
        tv_topic_from = (TextView) findViewById(R.id.tv_topic_from);  
        tv_topic = (TextView) findViewById(R.id.tv_topic);  
  
        adViewPager = (ViewPager) findViewById(R.id.vp);  
        adViewPager.setAdapter(new BannerAdapter());// �������ViewPagerҳ���������  
        // ����һ������������ViewPager�е�ҳ��ı�ʱ����  
        adViewPager.setOnPageChangeListener(new MyPageChangeListener());  
        addDynamicView();  
    }  
  
    
    private void addDynamicView() {  
        // ��̬���ͼƬ������ָʾ��Բ��  
        // ��ʼ��ͼƬ��Դ  
        for (int i = 0; i < list.size(); i++) {  
            ImageView imageView = new ImageView(this);  
            // �첽����ͼƬ  
            mImageLoader.displayImage(list.get(i).getImgUrl(), imageView,  
                    options);  
            imageView.setScaleType(ScaleType.CENTER_CROP);  
            imageViews.add(imageView);  
            dots.get(i).setVisibility(View.VISIBLE);  
            dotList.add(dots.get(i));  
        }  
    }  
    
    private class MyPageChangeListener implements OnPageChangeListener {  
    	  
        private int oldPosition = 0;  
  
        @Override  
        public void onPageScrollStateChanged(int arg0) {  
              
        }  
  
        @Override  
        public void onPageScrolled(int arg0, float arg1, int arg2) {  
              
        }  
  
        @Override  
        public void onPageSelected(int position) {  
            currentItem = position;  
            SongBanner banner = list.get(position);  
            tv_title.setText(banner.getTitle()); // ���ñ���  
            tv_date.setText(banner.getDate());  
            tv_topic_from.setText(banner.getTopicFrom());  
            tv_topic.setText(banner.getTopic());  
            dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);  
            dots.get(position).setBackgroundResource(R.drawable.dot_focused);  
            oldPosition = position;  
        }  
    }  
    
    private void startAd() {  
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();  
        // ��Activity��ʾ������ÿ�����л�һ��ͼƬ��ʾ  
        scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 2,  
                TimeUnit.SECONDS);  
    }  
    
    private class ScrollTask implements Runnable {  
    	  
        @Override  
        public void run() {  
            synchronized (adViewPager) {  
                currentItem = (currentItem + 1) % imageViews.size();  
                handler.obtainMessage().sendToTarget();  
            }  
        }  
    }  
	
    @Override
    protected void onStop() {
    	// TODO Auto-generated method stub
    	super.onStop();
    	scheduledExecutorService.shutdown();
    }
    
    private class BannerAdapter extends PagerAdapter {  
    	  
        @Override  
        public int getCount() {  
            return list.size();  
        }  
  
        @Override  
        public Object instantiateItem(ViewGroup container, int position) {  
            ImageView iv = imageViews.get(position);  
            ((ViewPager) container).addView(iv);  
            final SongBanner banner = list.get(position);  
            // �����������������ͼƬ�ĵ���¼�  
            iv.setOnClickListener(new OnClickListener() {  
  
                @Override  
                public void onClick(View v) {  
                    // ������ת�߼�  
                	
                	showFastToast("click");
                }  
            });  
            return iv;  
        }  
  
        @Override  
        public void destroyItem(View arg0, int arg1, Object arg2) {  
            ((ViewPager) arg0).removeView((View) arg2);  
        }  
  
        @Override  
        public boolean isViewFromObject(View arg0, Object arg1) {  
            return arg0 == arg1;  
        }  
  
        @Override  
        public void restoreState(Parcelable arg0, ClassLoader arg1) {  
  
        }  
  
        @Override  
        public Parcelable saveState() {  
            return null;  
        }  
  
        @Override  
        public void startUpdate(View arg0) {  
  
        }  
  
        @Override  
        public void finishUpdate(View arg0) {  
  
        }  
  
    }  
  

}
