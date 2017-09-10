package com.lss.example.view;

import com.lss.example.lssdemo.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;

public class RoundImageView extends ImageView {

	
	/** 
     * ͼƬ�����ͣ�Բ��orԲ�� 
     */  
    private int type;  
    private static final int TYPE_CIRCLE = 0;  
    private static final int TYPE_ROUND = 1;  
  
    /** 
     * Բ�Ǵ�С��Ĭ��ֵ 
     */  
    private static final int BODER_RADIUS_DEFAULT = 10;  
    /** 
     * Բ�ǵĴ�С 
     */  
    private int mBorderRadius;  
  
    /** 
     * ��ͼ��Paint 
     */  
    private Paint mBitmapPaint;  
    /** 
     * Բ�ǵİ뾶 
     */  
    private int mRadius;  
    /** 
     * 3x3 ������Ҫ������С�Ŵ� 
     */  
    private Matrix mMatrix;  
    /** 
     * ��Ⱦͼ��ʹ��ͼ��Ϊ����ͼ����ɫ 
     */  
    private BitmapShader mBitmapShader;  
    /** 
     * view�Ŀ�� 
     */  
    private int mWidth;  
    private RectF mRoundRect;  
	
    public RoundImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		
		mMatrix = new Matrix();  
        mBitmapPaint = new Paint();  
        mBitmapPaint.setAntiAlias(true);  
  
        TypedArray a = context.obtainStyledAttributes(attrs,  
                R.styleable.RoundImageView);  
  
        mBorderRadius = a.getDimensionPixelSize(  
                R.styleable.RoundImageView_borderRadius, (int) TypedValue  
                        .applyDimension(TypedValue.COMPLEX_UNIT_DIP,  
                                BODER_RADIUS_DEFAULT, getResources()  
                                        .getDisplayMetrics()));// Ĭ��Ϊ10dp  
        type = a.getInt(R.styleable.RoundImageView_type, TYPE_CIRCLE);// Ĭ��ΪCircle  
  
        a.recycle();  
	}
    
    @Override  
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)  
    {  
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);  
  
        /** 
         * ���������Բ�Σ���ǿ�Ƹı�view�Ŀ��һ�£���СֵΪ׼ 
         */  
        if (type == TYPE_CIRCLE)  
        {  
            mWidth = Math.min(getMeasuredWidth(), getMeasuredHeight());  
            mRadius = mWidth / 2;  
            setMeasuredDimension(mWidth, mWidth);  
        }  
  
    }  
    
   /**
    * ��ʼ��Bitmap
    */
   private void setUpShader()  
   {  
       Drawable drawable = getDrawable();  
       if (drawable == null)  
       {  
           return;  
       }  
 
       Bitmap bmp = drawableToBitamp(drawable);  
       // ��bmp��Ϊ��ɫ����������ָ�������ڻ���bmp  
       mBitmapShader = new BitmapShader(bmp, TileMode.CLAMP, TileMode.CLAMP);  
       float scale = 1.0f;  
       if (type == TYPE_CIRCLE)  
       {  
           // �õ�bitmap���ߵ�Сֵ  
           int bSize = Math.min(bmp.getWidth(), bmp.getHeight());  
           scale = mWidth * 1.0f / bSize;  
 
       } else if (type == TYPE_ROUND)  
       {  
           // ���ͼƬ�Ŀ���߸���view�Ŀ�߲�ƥ�䣬�������Ҫ���ŵı��������ź��ͼƬ�Ŀ�ߣ�һ��Ҫ��������view�Ŀ�ߣ�������������ȡ��ֵ��  
           scale = Math.max(getWidth() * 1.0f / bmp.getWidth(), getHeight()  
                   * 1.0f / bmp.getHeight());  
       }  
       // shader�ı任��������������Ҫ���ڷŴ������С  
       mMatrix.setScale(scale, scale);  
       // ���ñ任����  
       mBitmapShader.setLocalMatrix(mMatrix);  
       // ����shader  
       mBitmapPaint.setShader(mBitmapShader);  
   }  
    
    /** 
     * drawableתbitmap 
     *  
     * @param drawable 
     * @return 
     */  
    private Bitmap drawableToBitamp(Drawable drawable)  
    {  
        if (drawable instanceof BitmapDrawable)  
        {  
            BitmapDrawable bd = (BitmapDrawable) drawable;  
            return bd.getBitmap();  
        }  
        int w = drawable.getIntrinsicWidth();  
        int h = drawable.getIntrinsicHeight();  
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);  
        Canvas canvas = new Canvas(bitmap);  
        drawable.setBounds(0, 0, w, h);  
        drawable.draw(canvas);  
        return bitmap;  
    }  
    
    @Override  
    protected void onDraw(Canvas canvas)  
    {  
        if (getDrawable() == null)  
        {  
            return;  
        }  
        setUpShader();  
  
        if (type == TYPE_ROUND)  
        {  
            canvas.drawRoundRect(mRoundRect, mBorderRadius, mBorderRadius,  
                    mBitmapPaint);  
        } else  
        {  
            canvas.drawCircle(mRadius, mRadius, mRadius, mBitmapPaint);  
            // drawSomeThing(canvas);  
        }  
    }  
      
    @Override  
    protected void onSizeChanged(int w, int h, int oldw, int oldh)  
    {  
        super.onSizeChanged(w, h, oldw, oldh);  
        // Բ��ͼƬ�ķ�Χ  
        if (type == TYPE_ROUND)  
            mRoundRect = new RectF(0, 0, getWidth(), getHeight());  
    }  

	

}
