package com.lss.example.view;

import com.lss.example.lssdemo.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class FragmentB extends Fragment {

	private ImageView iv_b = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_b, container, false);
		iv_b = (ImageView) view.findViewById(R.id.iv_fragment_b);
		iv_b.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//Toast.makeText(getActivity(), "click b", 1).show();
			}
		});
		return view;
	}
}
