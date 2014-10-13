package org.bmcoop.lib.base.util;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.location.LocationManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.BatteryManager;
import android.os.PowerManager;
import android.provider.Browser;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;

public class UDeviceState {
	private static UDeviceState singleton;
	private static WUState mWUOnOffChecker;
	private Context pCon = null;

	public static int HARDWARE_ON_STATUS = 1;
	public static int HARDWARE_OFF_STATUS = 0;

	private UDeviceState(Context pCon) {
		this.pCon = pCon;
	}

	public static WUState with(Context pCon) {
		if (singleton == null) {
			singleton = new UDeviceState(pCon);
		}
		if (mWUOnOffChecker == null) {
			mWUOnOffChecker = singleton.getWUOnOffChecker();
		}
		return mWUOnOffChecker;
	}

	private WUState getWUOnOffChecker() {
		return new WUState();
	}

	public class WUState {
		private ConnectivityManager mConnectivityManager;
		private NetworkInfo mNetworkInfo;
		private PowerManager mPowerManager;
		private BluetoothAdapter mBluetoothAdapter;
		private int appCnt = 0;

		private WUState() {
		}

		public boolean isWiFiOn() {
			mConnectivityManager = (ConnectivityManager) pCon.getSystemService(Context.CONNECTIVITY_SERVICE);
			mNetworkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			return mNetworkInfo.isConnected();
		}

		/**
		 * @return 켜있으면 1, 꺼져있으면 0 반환
		 */
		public int getWifiStatus() {
			if (isWiFiOn())
				return HARDWARE_ON_STATUS;
			else
				return HARDWARE_OFF_STATUS;
		}

		public boolean isScreenOn() {
			mPowerManager = (PowerManager) pCon.getSystemService(Context.POWER_SERVICE);
			return mPowerManager.isScreenOn();
		}

		public int getScreenStatus() {
			if (isScreenOn())
				return HARDWARE_ON_STATUS;
			else
				return HARDWARE_OFF_STATUS;
		}

		public boolean isBluetoothOn() {
			mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
			return mBluetoothAdapter.isEnabled();
		}

		public int getBluetoothStatus() {
			if (isBluetoothOn())
				return HARDWARE_ON_STATUS;
			else
				return HARDWARE_OFF_STATUS;
		}

		public boolean isUSBConnected() {
			IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
			Intent batteryStatus = pCon.registerReceiver(null, ifilter);
			boolean isCharging = false;

			int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
			isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL;

			return isCharging;
		}

		public int getUSBConnectionStatus() {
			if (isUSBConnected())
				return HARDWARE_ON_STATUS;
			else
				return HARDWARE_OFF_STATUS;
		}

		public int getBatteryPercent() {
			IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
			Intent batteryStatus = pCon.registerReceiver(null, ifilter);

			int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, 100);
			int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
			return level * 100 / scale;
		}

		@SuppressWarnings("deprecation")
		public boolean isHeadSetOn() {
			AudioManager mAudioManager = (AudioManager) pCon.getSystemService(Context.AUDIO_SERVICE);
			return mAudioManager.isWiredHeadsetOn();
		}

		public int getHeadtStatus() {
			if (isHeadSetOn()) {
				return HARDWARE_ON_STATUS;
			} else {
				return HARDWARE_OFF_STATUS;
			}
		}

		public boolean isGPSOn() {
			LocationManager manager = (LocationManager) pCon.getSystemService(Context.LOCATION_SERVICE);
			return manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		}

		public int getGPSStatus() {
			if (isGPSOn())
				return HARDWARE_ON_STATUS;
			else
				return HARDWARE_OFF_STATUS;
		}

		public String getLastBrowserHistory() {
			Cursor cursor = Browser.getAllVisitedUrls(pCon.getContentResolver());
			if (cursor.getCount() > 0) {
				cursor.moveToFirst();
				return cursor.getString(cursor.getColumnIndex("url"));
			} else {
				return "";
			}
		}

		public String getListofTaskApp() {
			ActivityManager actvityManager = (ActivityManager) pCon.getSystemService(Context.ACTIVITY_SERVICE);
			List<RunningTaskInfo> procInfos2 = actvityManager.getRunningTasks(100);
			PackageManager pm = pCon.getPackageManager();
			ApplicationInfo appInfo = null;
			String result = "";
			appCnt = 0;
			try {
				for (RunningTaskInfo runningProInfo : procInfos2) {
					++appCnt;
					appInfo = pm.getApplicationInfo(runningProInfo.baseActivity.getPackageName(), PackageManager.GET_META_DATA);
					result += pm.getApplicationLabel(appInfo).toString() + " | ";
				}
			} catch (NameNotFoundException e) {
				e.printStackTrace();
			}
			return result;
		}

		public int getAppCnt() {
			getListofTaskApp();
			return appCnt;
		}

		public String getImageInfo() {
			String fileName = "NONE";
			String[] proj = {MediaStore.Images.Media._ID, MediaStore.Images.Media.DATE_ADDED, MediaStore.Images.Media.DISPLAY_NAME,
					MediaStore.Images.Media.SIZE};
			Cursor imageCursor = pCon.getContentResolver().query(Images.Media.EXTERNAL_CONTENT_URI, proj, null, null, null);
//			ULog.i(pCon, imageCursor.getCount());
			if (imageCursor != null && imageCursor.moveToLast()) {
				if (imageCursor.getCount() > 0) {
//					ULog.i(pCon, imageCursor.getString(imageCursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME)));
					fileName = imageCursor.getString(imageCursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
				}
			}
			imageCursor.close();

			return fileName;
		}
	}
}
