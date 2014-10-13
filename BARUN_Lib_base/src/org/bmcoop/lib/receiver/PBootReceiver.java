package org.bmcoop.lib.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public abstract class PBootReceiver extends BroadcastReceiver {

	public static String ACTION = "android.intent.action.BOOT_COMPLETED";

//	<uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />
//<receiver android:name="com.sbmp..receiver.BootReceiver" >
//    <intent-filter>
//        <action android:name="android.intent.action.BOOT_COMPLETED" />
//    </intent-filter>
//</receiver>

	public PBootReceiver() {
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