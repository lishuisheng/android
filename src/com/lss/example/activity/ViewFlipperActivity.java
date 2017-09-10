package com.lss.example.activity;

import com.lss.example.lssdemo.R;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

public class ViewFlipperActivity extends BaseActivity implements OnGestureListener{

	
	private GestureDetector detector;  
    private ViewFlipper flipper;  
    private final int HELPFILP_RESULT = 106;  
    Intent getMainActivity = null;  
    int count = 1; 
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.viewflipper);

		//设置背景颜色
		drawBackground();  
		
		//创造出每一页界面
        LayoutInflater inflater = LayoutInflater.from(this);  
        final View layout = inflater.inflate(R.layout.viewflipper, null);  
        
        setContentView(layout);  
        flipper = (ViewFlipper) findViewById(R.id.view_flipper);  
        detector = new GestureDetector(this);  
	}
	
	
	public void drawBackground()  
    {  
        GradientDrawable grad = new GradientDrawable(   
                   Orientation.TL_BR,  
                   new int[] {Color.rgb(0, 0, 127),  
                           Color.rgb(0, 0, 255),  
                           Color.rgb(127, 0, 255),  
                           Color.rgb(127, 127, 255),  
                           Color.rgb(127, 255, 255),  
                           Color.rgb(255, 255, 255)}   
        );   
  
        this.getWindow().setBackgroundDrawable(grad);  
    }  

	@Override
	public boolean onDown(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float arg1,
			float arg2) {
		// TODO Auto-generated method stub
		 if (e1.getX() - e2.getX() > 5) {  
	            this.flipper.setInAnimation(AnimationUtils.loadAnimation(this,  
	                    R.anim.push_left_in));  
	            this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this,  
	                    R.anim.push_left_out));  
	            if (count < 3) {  
	                this.flipper.showNext();  
	                count++;  
	            }  
	  
	            return true;  
	        } else if (e1.getX() - e2.getX() < -5) {  
	            this.flipper.setInAnimation(AnimationUtils.loadAnimation(this,  
	                    R.anim.push_right_in));  
	            this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this,  
	                    R.anim.push_right_out));  
	            if (count > 1) {  
	                this.flipper.showPrevious();  
	                count--;  
	            }  
	            return true;  
	        }  
	        return true;  
	}

	@Override
	public void onLongPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return this.detector.onTouchEvent(event);
	}

}
