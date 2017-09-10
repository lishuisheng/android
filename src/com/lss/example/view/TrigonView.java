package com.lss.example.view;

import com.lss.example.lssdemo.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

public class TrigonView extends View {

	private String text = null;
	private int color;
	private int size;

	private Paint paint;
	private Rect rect;

	public TrigonView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public TrigonView(Context context) {
		this(context, null);
	}

	public TrigonView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		
		// 获得属性的数组
		TypedArray typeArray = context.getTheme().obtainStyledAttributes(attrs,
				R.styleable.TrigonView, defStyleAttr, 0);
		
		int sum = typeArray.getIndexCount();
		
		for (int i = 0; i < sum; i++) {
			
			int arrt = typeArray.getIndex(i);
			switch (arrt) {
			case R.styleable.TrigonView_lss_text:
				text = typeArray.getString(arrt);
				break;
			case R.styleable.TrigonView_lss_size:
				size = typeArray.getDimensionPixelSize(arrt, (int) TypedValue
						.applyDimension(TypedValue.COMPLEX_UNIT_SP, 18,
								getResources().getDisplayMetrics()));

				break;
			case R.styleable.TrigonView_lss_color:
				color = typeArray.getColor(arrt, Color.WHITE);
				break;

			default:
				break;
			}
		}
		typeArray.recycle();
		paint = new Paint();
		paint.setTextSize(size);
		rect = new Rect();
		paint.getTextBounds(text, 0, text.length(), rect);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);


		paint.setColor(Color.GREEN);
		canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), paint);

		paint.setColor(color);
		canvas.drawText(text, getWidth() / 2 - rect.width() / 2, getHeight()
				/ 2 + rect.height() / 2, paint);

	}

}
