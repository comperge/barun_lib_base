package org.bmcoop.lib.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public abstract class PBatteryReceiver extends BroadcastReceiver {

	public static String ACTION = "android.intent.action.BATTERY_LOW";
	public static String ACTION2 = "android.intent.action.BATTERY_OKAY";
	public static String ACTION3 = "android.intent.action.ACTION_POWER_CONNECTED";
	public static String ACTION4 = "android.intent.action.ACTION_POWER_DISCONNECTED";

	// <receiver android:name="com.mcu..receiver.BatteryReceiver" >
	// <intent-filter>
	// <action android:name="android.intent.action.ACTION_POWER_CONNECTED" />
	// </intent-filter>
	// </receiver>

	public PBatteryReceiver() {
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