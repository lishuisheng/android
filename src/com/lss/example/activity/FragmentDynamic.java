package com.lss.example.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.lss.example.lssdemo.R;
import com.lss.example.view.FragmentA;
import com.lss.example.view.FragmentB;
import com.lss.example.view.FragmentC;

public class FragmentDynamic extends BaseActivity {

	private Button button_a = null;
	private Button button_b = null;
	private Button button_c = null;

	private FragmentA fragment_a = null;
	private FragmentB fragment_b = null;
	private FragmentC fragment_c = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//Òþ²Øµ¼º½¼üÅÌ
		hideMenu();
		setContentView(R.layout.fragment_dynamic);

		
		initView();
		if(savedInstanceState == null){
			setDefaultFragment();
		}
	
		setButtonColor(true, false, false);

	}
	
	private void hideMenu(){
		View rootView = getWindow().getDecorView();
		rootView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
	}

	private void setDefaultFragment() {

		FragmentManager fm = getFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();

		fragment_a = new FragmentA();
		transaction.replace(R.id.framelayout, fragment_a);
		transaction.commit();

	}

	private void initView() {
		button_a = (Button) findViewById(R.id.btn_tab_a);
		button_b = (Button) findViewById(R.id.btn_tab_b);
		button_c = (Button) findViewById(R.id.btn_tab_c);

		button_a.setOnClickListener(new MyListener());
		button_b.setOnClickListener(new MyListener());
		button_c.setOnClickListener(new MyListener());
	}

	public class MyListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub

			FragmentManager fm = getFragmentManager();
			FragmentTransaction transaction = fm.beginTransaction();
			switch (arg0.getId()) {
			case R.id.btn_tab_a:

				setButtonColor(true, false, false);
				
				if (null == fragment_a) {
					fragment_a = new FragmentA();
				}
				if (null != fragment_a) {
					showFastToast("aaaa");
					transaction.replace(R.id.framelayout, fragment_a);
					transaction.show(fragment_a);
					transaction.commit();
					if (null != fragment_b) {
						transaction.hide(fragment_b);
					}

					if (null != fragment_c) {
						transaction.hide(fragment_c);
					}
				}

				break;

			case R.id.btn_tab_b:

				setButtonColor(false, true, false);
				
				if (null == fragment_b) {
					fragment_b = new FragmentB();
				}
				if (null != fragment_b) {

					showFastToast("bbbb");
					transaction.replace(R.id.framelayout, fragment_b);
					transaction.show(fragment_b);
					transaction.commit();
					if (null != fragment_a) {
						transaction.hide(fragment_a);
					}

					if (null != fragment_c) {
						transaction.hide(fragment_c);
					}
				}

				break;

			case R.id.btn_tab_c:

				setButtonColor(false, false, true);
				if (null == fragment_c) {
					fragment_c = new FragmentC();
				}
				if (null != fragment_c) {
					showFastToast("cccc");
					transaction.replace(R.id.framelayout, fragment_c);
					transaction.show(fragment_c);
					transaction.commit();
					if (null != fragment_b) {
						transaction.hide(fragment_b);
					}

					if (null != fragment_a) {
						transaction.hide(fragment_a);
					}
				}

				break;
			default:
				break;
			}
		}

	}
	
	private void setButtonColor(boolean a, boolean b, boolean c){
		
		if(a){
			button_a.setBackgroundColor(Color.YELLOW);
			button_b.setBackgroundColor(Color.GRAY);
			button_c.setBackgroundColor(Color.GRAY);
		}else if(b){
			button_b.setBackgroundColor(Color.YELLOW);
			button_a.setBackgroundColor(Color.GRAY);
			button_c.setBackgroundColor(Color.GRAY);
		}else if(c){
			button_c.setBackgroundColor(Color.YELLOW);
			button_a.setBackgroundColor(Color.GRAY);
			button_b.setBackgroundColor(Color.GRAY);
		}else {
			
		}
	}
}
