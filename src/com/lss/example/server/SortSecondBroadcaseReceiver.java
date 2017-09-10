package com.lss.example.server;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class SortSecondBroadcaseReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		
		Bundle bundle = getResultExtras(true);
		String msg2 = bundle.getString("key");
		if(bundle == null){
			Toast.makeText(context, "接收信息为空", Toast.LENGTH_SHORT).show(); 
		}else {
			Toast.makeText(context, msg2, Toast.LENGTH_SHORT).show(); 
		}
		
	}

}
