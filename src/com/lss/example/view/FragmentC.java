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

public class FragmentC extends Fragment {

	private ImageView iv_c = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		View view = inflater.inflate(R.layout.fragment_c, container, false);
		iv_c = (ImageView) view.findViewById(R.id.iv_fragment_c);

		iv_c.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//Toast.makeText(getActivity(), "click c", 1).show();
			}
		});
		return view;
	}
}
