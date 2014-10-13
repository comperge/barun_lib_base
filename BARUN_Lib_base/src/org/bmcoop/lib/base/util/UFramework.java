package org.bmcoop.lib.base.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.Notification;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;

public class UFramework {

	// <uses-permission android:name="android.permission.VIBRATE"/>
	public static void doVibrat(Context context) {
		Vibrator mVib;
		mVib = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
		mVib.vibrate(500);
	}

	// <uses-permission android:name="android.permission.VIBRATE"/>
	public static void doVibrate(Context context, int miliSec) {
		Vibrator mVib = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
		mVib.vibrate(miliSec);
	}

	/**
	 * @param phoneNumber
	 *            010123412324
	 * @return 010-1234-1234
	 */
	public static String getPhoneNumberFormat(String phoneNumber) {
		return PhoneNumberUtils.formatNumber(phoneNumber);
	}

	/**
	 * @param phoneNumber
	 *            010-1234-1234
	 * @return 010123412324
	 */
	public static String getPhoneNumberStrip(String phoneNumber) {
		return PhoneNumberUtils.stripSeparators(phoneNumber);
	}

	public static boolean isPhoneNumber(String phoneNumber) {
		return PhoneNumberUtils.isGlobalPhoneNumber(phoneNumber);
	}

	public static String getphoneNumber(Context context) {
		TelephonyManager mTelephonyMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		// String imsi = mTelephonyMgr.getSubscriberId();
		// String imei = mTelephonyMgr.getDeviceId();
		String phoneNumber = mTelephonyMgr.getLine1Number();
		phoneNumber = phoneNumber.replace("+82", "0");

		return phoneNumber;
		// <uses-permission
		// android:name="android.permission.READ_PHONE_STATE"></uses-permission>
	}

	public static int getAlarmSystem(Context context) {
		AudioManager audioMan = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		return audioMan.getRingerMode();

		// switch (SystemUtil.getAlarmSystem(context)) {
		// case AudioManager.RINGER_MODE_SILENT:
		// AppMan.PRE_MAN.setPrefer(PreferMan.CONF_ALARM, PreferMan.ALARM_NONE);
		// break;
		// case AudioManager.RINGER_MODE_VIBRATE:
		// AppMan.PRE_MAN.setPrefer(PreferMan.CONF_ALARM, PreferMan.ALARM_VIB);
		// break;
		// case AudioManager.RINGER_MODE_NORMAL:
		// AppMan.PRE_MAN.setPrefer(PreferMan.CONF_ALARM, PreferMan.ALARM_BELL);
		// break;
		// }
	}

	// public static void updateGcmRegistration(Context context, String sendId)
	// {
	// Intent intent = new Intent(GCMConstants.INTENT_TO_GCM_REGISTRATION);
	// intent.setPackage("com.google.android.gsf");
	// intent.putExtra(GCMConstants.EXTRA_APPLICATION_PENDING_INTENT,
	// PendingIntent.getBroadcast(context, 0, new Intent(), 0));
	// intent.putExtra(GCMConstants.EXTRA_SENDER, sendId);
	// context.startService(intent);
	// }

	public static Intent getIntentFileView(Context context, File file, String ext) {
		Intent intent = new Intent();
		intent.setAction(android.content.Intent.ACTION_VIEW);
		// 파일 확장자 별로 mime type 지정해 준다.
		if (ext.equals("mp3")) {
			intent.setDataAndType(Uri.fromFile(file), "audio/*");
		} else if (ext.equals("mp4")) {
			intent.setDataAndType(Uri.fromFile(file), "vidio/*");
		} else if (ext.equals("jpg") || ext.equals("jpeg") || ext.equals("JPG") || ext.equals("gif")
				|| ext.equals("png") || ext.equals("bmp")) {
			intent.setDataAndType(Uri.fromFile(file), "image/*");
		} else if (ext.equals("txt")) {
			intent.setDataAndType(Uri.fromFile(file), "text/*");
		} else if (ext.equals("doc") || ext.equals("docx")) {
			intent.setDataAndType(Uri.fromFile(file), "application/msword");
		} else if (ext.equals("xls") || ext.equals("xlsx")) {
			intent.setDataAndType(Uri.fromFile(file), "application/vnd.ms-excel");
		} else if (ext.equals("ppt") || ext.equals("pptx")) {
			intent.setDataAndType(Uri.fromFile(file), "application/vnd.ms-powerpoint");
		} else if (ext.equals("pdf")) {
			intent.setDataAndType(Uri.fromFile(file), "application/pdf");
		} else if (ext.equals("hwp")) {
			intent.setDataAndType(Uri.fromFile(file), "application/hwp");
		}

		return intent;

	}

	public static String getLocaleInfo(Context context) {
		Locale systemLocale = context.getResources().getConfiguration().locale;
		// String strDisplayCountry = systemLocale.getDisplayCountry();
		// String strCountry = systemLocale.getCountry();
		String strLanguage = systemLocale.getLanguage();
		return strLanguage;
	}

	public static int getNotificationDefaultSound(Context context) {
		AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		switch (audioManager.getRingerMode()) {
		case AudioManager.RINGER_MODE_SILENT:
			return Notification.DEFAULT_LIGHTS;
		case AudioManager.RINGER_MODE_VIBRATE:
			return Notification.DEFAULT_VIBRATE;
		case AudioManager.RINGER_MODE_NORMAL:
			return Notification.DEFAULT_SOUND;
		}
		return Notification.DEFAULT_VIBRATE;
	}

	public static void setRingerMode(Context context, int AudioManager_RINGER) {
		AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		audioManager.setRingerMode(AudioManager_RINGER);
	}

	public static boolean setBluetoothEnable(boolean setEnable) {
		BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		if (setEnable) {
			return bluetoothAdapter.enable();
		} else {
			return bluetoothAdapter.disable();
		}
	}

	public static boolean isKorea(Context context) {
		Locale systemLocale = context.getResources().getConfiguration().locale;
		String strLanguage = systemLocale.getLanguage();
		if (strLanguage.equals("ko")) {
			return true;
		}
		return false;
	}

	public static void exitApp(Activity ac) {
		ac.moveTaskToBack(true);
		ac.finish();
		android.os.Process.killProcess(android.os.Process.myPid());
	}

	public static int getVersion() {
//		Build.VERSION_CODES.GINGERBREAD
//		Build.VERSION_CODES.HONYCOMB
		return Build.VERSION.SDK_INT;
	}

	public static boolean isVersionJellyBean_Up() {
		if (getVersion() >= 16) {
			return true;
		}
		return false;
	}

	public static boolean isVersionIcream_Up() {
		if (getVersion() >= 14) {
			return true;
		}
		return false;
	}

	public static boolean isPhoneBrand(String brandName) {
		String brand = Build.BRAND;

		if (brand.toLowerCase().contains((brandName))) {
			return true;
		}
		return false;
	}

	public static boolean isPhoneModel(String modelName) {
		String model = Build.MODEL;

		if (model.equals(modelName)) {
			return true;
		}
		return false;
	}

	public static boolean hasFroyo() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO;
	}

	public static boolean hasGingerbread() {
		// Build.VERSION_CODES.GINGERBREAD
		return Build.VERSION.SDK_INT >= 9;
	}

	public static boolean hasHoneycomb() {
		// Build.VERSION_CODES.HONEYCOMB
		return Build.VERSION.SDK_INT >= 11;
	}

	public static boolean hasHoneycombMR1() {
		// Build.VERSION_CODES.HONEYCOMB_MR1
		return Build.VERSION.SDK_INT >= 12;
	}

	public static boolean hasJellyBean() {
		// Build.VERSION_CODES.JELLY_BEAN
		return Build.VERSION.SDK_INT >= 16;
	}

	public static boolean isRoaming(Context context) {
		TelephonyManager mTelephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		if (mTelephonyManager.isNetworkRoaming()) {
			return true;
		}
		return false;
	}

	public static String[] getAccountList(Context context) {
		String[] redata = null;
		AccountManager manager = (AccountManager) context.getSystemService(Context.ACCOUNT_SERVICE);
		Account[] list = manager.getAccounts();
		if (list != null && list.length > 0) {
			redata = new String[list.length];
			for (int i = 0; i < redata.length; i++) {
				redata[i] = list[i].name;
			}
		}
		return redata;
	}
	
	public static String[] getAccountList_mail(Context context) {
		ArrayList<String> redata = new ArrayList<String>();
		AccountManager manager = (AccountManager) context.getSystemService(Context.ACCOUNT_SERVICE);
		Account[] list = manager.getAccounts();
		if (list != null && list.length > 0) {
			for (int i = 0; i < list.length; i++) {
				if(list[i].name.contains("@")){
					redata.add(list[i].name);
				}
			}
		}
		return UString.withArrayList(redata).getArray();
	}
}
