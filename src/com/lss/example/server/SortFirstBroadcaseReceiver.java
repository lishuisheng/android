package com.lss.example.server;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class SortFirstBroadcaseReceiver extends BroadcastReceiver {

	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub

		String msg = intent.getStringExtra("msg");
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

		String msg2 = "����First�����ߴ�����󷢹����ĵ���Ϣ";
		Bundle bundle = new Bundle();
		bundle.putString("key", msg2);
		setResultExtras(bundle);
		// �жϹ㲥
		//abortBroadcast();

	}

}
