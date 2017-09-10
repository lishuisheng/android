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

	private String downloadUrl;// �������ӵ�ַ
	private int threadNum;// �������߳���
	private String filePath;// �����ļ�·����ַ
	private int blockSize;// ÿһ���̵߳�������
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
             // ��ȡ�����ļ��ܴ�С  
             int fileSize = conn.getContentLength();  
             if (fileSize <= 0) {  
                 System.out.println("��ȡ�ļ�ʧ��");  
                 return;  
             }  
             // ����ProgressBar���ĳ���Ϊ�ļ�Size  
             //mProgressbar.setMax(fileSize);  

             // ����ÿ���߳����ص����ݳ���  
             blockSize = (fileSize % threadNum) == 0 ? fileSize / threadNum  
                     : fileSize / threadNum + 1;  


             File file = new File(filePath);  
             for (int i = 0; i < threads.length; i++) {  
                 // �����̣߳��ֱ�����ÿ���߳���Ҫ���صĲ���  
                 threads[i] = new FileDownLoadThread(url, file, blockSize,  
                         (i + 1));  
                 threads[i].setName("Thread:" + i);  
                 threads[i].start();  
             }  

             boolean isfinished = false;  
             int downloadedAllSize = 0;  
             while (!isfinished) {  
                 isfinished = true;  
                 // ��ǰ�����߳���������  
                 downloadedAllSize = 0;  
                 for (int i = 0; i < threads.length; i++) {  
                     downloadedAllSize += threads[i].getDownloadLength();  
                     if (!threads[i].isCompleted()) {  
                         isfinished = false;  
                     }  
                 }  
                 // ֪ͨhandlerȥ������ͼ���  
                 msg = new Message();  
                 msg.getData().putInt("size", downloadedAllSize);  
                 // Log.d(TAG, "current downloadSize:" + downloadedAllSize);  
                 Thread.sleep(1000);// ��Ϣ1����ٶ�ȡ���ؽ���  
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
