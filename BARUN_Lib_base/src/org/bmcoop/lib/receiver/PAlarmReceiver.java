package org.bmcoop.lib.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public abstract class PAlarmReceiver extends BroadcastReceiver {

	public static String ACTION = "com.mcu.lib.AlarmReceiver";

	//<receiver
//  android:name="com.mcu..asset.receiver.AlarmReceiver"
//  android:exported="false" >
//  <intent-filter>
//      <action android:name="com.mcu.lib.AlarmReceiver" />
//  </intent-filter>
//</receiver>

	public PAlarmReceiver() {
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

	abstract public void abReceive(Context context, Intent intent);
}