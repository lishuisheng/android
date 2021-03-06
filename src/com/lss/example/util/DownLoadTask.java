package com.lss.example.util;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;

public class DownLoadTask extends Thread {

	private String downloadUrl;// 下载链接地址
	private int threadNum;// 开启的线程数
	private String filePath;// 保存文件路径地址
	private int blockSize;// 每一个线程的下载量
	public static Message msg = null;

	public DownLoadTask() {

	}

	public DownLoadTask(String downloadUrl, int threadNum, String fileptah) {

		this.downloadUrl = downloadUrl;
		this.threadNum = threadNum;
		this.filePath = fileptah;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		 FileDownLoadThread[] threads = new FileDownLoadThread[threadNum];  
         try {  
             URL url = new URL(downloadUrl);  
             //Log.d(TAG, "download file http path:" + downloadUrl);  
             URLConnection conn = url.openConnection();  
             // 读取下载文件总大小  
             int fileSize = conn.getContentLength();  
             if (fileSize <= 0) {  
                 System.out.println("读取文件失败");  
                 return;  
             }  
             // 设置ProgressBar最大的长度为文件Size  
             //mProgressbar.setMax(fileSize);  

             // 计算每条线程下载的数据长度  
             blockSize = (fileSize % threadNum) == 0 ? fileSize / threadNum  
                     : fileSize / threadNum + 1;  


             File file = new File(filePath);  
             for (int i = 0; i < threads.length; i++) {  
                 // 启动线程，分别下载每个线程需要下载的部分  
                 threads[i] = new FileDownLoadThread(url, file, blockSize,  
                         (i + 1));  
                 threads[i].setName("Thread:" + i);  
                 threads[i].start();  
             }  

             boolean isfinished = false;  
             int downloadedAllSize = 0;  
             while (!isfinished) {  
                 isfinished = true;  
                 // 当前所有线程下载总量  
                 downloadedAllSize = 0;  
                 for (int i = 0; i < threads.length; i++) {  
                     downloadedAllSize += threads[i].getDownloadLength();  
                     if (!threads[i].isCompleted()) {  
                         isfinished = false;  
                     }  
                 }  
                 // 通知handler去更新视图组件  
                 msg = new Message();  
                 msg.getData().putInt("size", downloadedAllSize);  
                 // Log.d(TAG, "current downloadSize:" + downloadedAllSize);  
                 Thread.sleep(1000);// 休息1秒后再读取下载进度  
             }  

         } catch (MalformedURLException e) {  
             e.printStackTrace();  
         } catch (IOException e) {  
             e.printStackTrace();  
         } catch (InterruptedException e) {  
             e.printStackTrace();  
         }  
	}  

}
