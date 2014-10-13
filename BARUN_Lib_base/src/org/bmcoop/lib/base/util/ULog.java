package org.bmcoop.lib.base.util;

import org.bmcoop.lib.base.file.PPackageMan;

import android.content.Context;
import android.util.Log;

public class ULog {
	private static ULog SINGLE_U;

	private ULog() {
	}

	public static WLog with() {
		if (SINGLE_U == null) {
			SINGLE_U = new ULog();
		}
		return SINGLE_U.get();
	}

	private WLog get() {
		return new WLog();
	}

	public class WLog {
		private String mString = "";
		private final String TAG = " ==> ";
		private boolean isFirst = true;

		private WLog() {
		}

		public WLog addTag(String tag) {
			mString = tag + TAG;
			return this;
		}

		public WLog add(String key, String value) {
			return makeString(key, value);
		}

		public WLog add(String key, boolean value) {
			return makeString(key, String.valueOf(value));
		}

		public WLog add(String key, int value) {
			return makeString(key, String.valueOf(value));
		}

		public WLog add(String key, long value) {
			return makeString(key, String.valueOf(value));
		}

		public WLog add(String key, float value) {
			return makeString(key, String.valueOf(value));
		}

		public WLog add(String key, double value) {
			return makeString(key, String.valueOf(value));
		}

		public String getString() {
			return mString;
		}
		
		public void e() {
			Log.e("Error", mString);
		}

		public void d() {
			Log.d("Debug", mString);
		}
		
		public void i() {
			Log.i("Info", mString);
		}

		private WLog makeString(String key, String value) {
			if (isFirst) {
				isFirst = false;
				mString = mString + key + ": " + value;
			} else {
				mString = mString + ", " + key + ": " + value;
			}
			return this;
		}
	}

	/**
	 * Development 상태에서만 Log함, PackMan에서 변경 가능
	 * 
	 * @param pCon
	 * @param message
	 */
	public static void e(Context pCon, String message) {
		makeLog_e(pCon, String.valueOf(message));
	}

	private static void makeLog_e(Context pCon, String message) {
		PPackageMan packMan = new PPackageMan(pCon);
		if (packMan != null && packMan.isAppLogEnabled()) {
			Log.e(pCon.getClass().getName(), String.valueOf(message));
		}
	}

	/**
	 * Development 상태에서만 Log함, PackMan에서 변경 가능
	 * 
	 * @param pCon
	 * @param message
	 */
	public static void e(Context pCon, boolean message) {
		makeLog_e(pCon, String.valueOf(message));
	}

	/**
	 * Development 상태에서만 Log함, PackMan에서 변경 가능
	 * 
	 * @param pCon
	 * @param message
	 */
	public static void e(Context pCon, int message) {
		makeLog_e(pCon, String.valueOf(message));
	}

	/**
	 * Development 상태에서만 Log함, PackMan에서 변경 가능
	 * 
	 * @param pCon
	 * @param message
	 */
	public static void e(Context pCon, long message) {
		makeLog_e(pCon, String.valueOf(message));
	}

	/**
	 * Development 상태에서만 Log함, PackMan에서 변경 가능
	 * 
	 * @param pCon
	 * @param message
	 */
	public static void e(Context pCon, float message) {
		makeLog_e(pCon, String.valueOf(message));
	}

	/**
	 * Development 상태에서만 Log함, PackMan에서 변경 가능
	 * 
	 * @param pCon
	 * @param message
	 */
	public static void e(Context pCon, double message) {
		makeLog_e(pCon, String.valueOf(message));
	}

	/**
	 * Development 상태에서만 Log함, PackMan에서 변경 가능
	 * 
	 * @param pCon
	 * @param message
	 */
	public static void d(Context pCon, String message) {
		makeLog_d(pCon, String.valueOf(message));
	}

	private static void makeLog_d(Context pCon, String message) {
		PPackageMan packMan = new PPackageMan(pCon);
		if (packMan != null && packMan.isAppLogEnabled()) {
			Log.d(pCon.getClass().getName(), String.valueOf(message));
		}
	}

	/**
	 * Development 상태에서만 Log함, PackMan에서 변경 가능
	 * 
	 * @param pCon
	 * @param message
	 */
	public static void d(Context pCon, boolean message) {
		makeLog_d(pCon, String.valueOf(message));
	}

	/**
	 * Development 상태에서만 Log함, PackMan에서 변경 가능
	 * 
	 * @param pCon
	 * @param message
	 */
	public static void d(Context pCon, int message) {
		makeLog_d(pCon, String.valueOf(message));
	}

	/**
	 * Development 상태에서만 Log함, PackMan에서 변경 가능
	 * 
	 * @param pCon
	 * @param message
	 */
	public static void d(Context pCon, long message) {
		makeLog_d(pCon, String.valueOf(message));
	}

	/**
	 * Development 상태에서만 Log함, PackMan에서 변경 가능
	 * 
	 * @param pCon
	 * @param message
	 */
	public static void d(Context pCon, float message) {
		makeLog_d(pCon, String.valueOf(message));
	}

	/**
	 * Development 상태에서만 Log함, PackMan에서 변경 가능
	 * 
	 * @param pCon
	 * @param message
	 */
	public static void d(Context pCon, double message) {
		makeLog_d(pCon, String.valueOf(message));
	}

	/**
	 * Release 되어도 무조건 Log됨
	 * 
	 * @param pCon
	 * @param message
	 */
	public static void i(Context pCon, String message) {
		makeLog_i(pCon, String.valueOf(message));
	}

	public static void makeLog_i(Context pCon, String message) {
		Log.i(pCon.getClass().getName(), message);
	}

	/**
	 * Release 되어도 무조건 Log됨
	 * 
	 * @param pCon
	 * @param message
	 */
	public static void i(Context pCon, boolean message) {
		makeLog_i(pCon, String.valueOf(message));
	}

	/**
	 * Release 되어도 무조건 Log됨
	 * 
	 * @param pCon
	 * @param message
	 */
	public static void i(Context pCon, int message) {
		makeLog_i(pCon, String.valueOf(message));
	}

	/**
	 * Release 되어도 무조건 Log됨
	 * 
	 * @param pCon
	 * @param message
	 */
	public static void i(Context pCon, long message) {
		makeLog_i(pCon, String.valueOf(message));
	}

	/**
	 * Release 되어도 무조건 Log됨
	 * 
	 * @param pCon
	 * @param message
	 */
	public static void i(Context pCon, float message) {
		makeLog_i(pCon, String.valueOf(message));
	}

	/**
	 * Release 되어도 무조건 Log됨
	 * 
	 * @param pCon
	 * @param message
	 */
	public static void i(Context pCon, double message) {
		makeLog_i(pCon, String.valueOf(message));
	}
}
