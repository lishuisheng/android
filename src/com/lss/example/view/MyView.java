package com.lss.example.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {

	private Paint paint = new Paint();
	
	public MyView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}
	
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		 setMeasuredDimension(600 , 400) ;  
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		paint.setColor(Color.RED);
		canvas.drawColor(Color.BLUE);//设置画布背景颜色
		canvas.drawCircle(100, 100, 50, paint);//画圆
		
		paint.setColor(Color.YELLOW);
		canvas.drawRect(100, 100, 200, 200, paint);
		paint.setColor(Color.GREEN);
		canvas.drawText("自定义View", 10, 20, paint);
		
	}

}
