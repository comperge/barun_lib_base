package org.bmcoop.lib.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public abstract class PReServiceReceiver extends BroadcastReceiver {

	public static String ACTION = "com.sbmp.lib.ReServiceReceiver";

//<receiver
//    android:name="com.sbmp..receiver.ReServiceReceiverr"
//    android:exported="false" >
//    <intent-filter>
//        <action android:name="com.sbmp.lib.ReServiceReceiver" />
//    </intent-filter>
//</receiver>

	public PReServiceReceiver() {
		super();
	}

	public IntentFilter getIntentFilter() {
		IntentFilter intentFilter = new IntentFilter(ACTION);
		return intentFilter;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		abReceive(context, intent);
	}

	abstract public boolean abReceive(Context context, Intent intent);
}