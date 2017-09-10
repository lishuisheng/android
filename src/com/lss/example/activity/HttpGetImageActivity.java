package com.lss.example.activity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.lss.example.lssdemo.R;
import com.lss.example.util.HttpUtil;

public class HttpGetImageActivity extends BaseActivity {

	private ImageView imageView = null;
	private Button button = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.http_get_image);

		imageView = (ImageView) findViewById(R.id.iv_http);
		button = (Button) findViewById(R.id.btn_http);

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				new Thread(runnable).start();
			}
		});

	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			Bundle bundle = msg.getData();
			byte[] b = bundle.getByteArray("buteArray");
			
			Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
			imageView.setImageBitmap(bitmap);
		};
	};

	Runnable runnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub

			InputStream is = HttpUtil.getInputStream();
			byte[] buffer = HttpUtil.getByteArray(is);
			
			Message msg = new Message();
			Bundle bundle = new Bundle();
			bundle.putByteArray("buteArray", buffer);
			msg.setData(bundle);
			handler.sendMessage(msg);
		}
	};

}
