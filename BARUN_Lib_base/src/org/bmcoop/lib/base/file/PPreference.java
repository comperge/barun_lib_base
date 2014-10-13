package org.bmcoop.lib.base.file;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PPreference {
	public Context pCon;
	public SharedPreferences pSharedPref;

	public PPreference(Context context) {
		pCon = context;
		pSharedPref = PreferenceManager.getDefaultSharedPreferences(context);
	}

	public String read(String key) {
		return pSharedPref.getString(key, "");
	}

	public void write(String key, String value) {
		SharedPreferences.Editor sharedEditor = pSharedPref.edit();
		sharedEditor.putString(key, value);
		sharedEditor.commit();
	}

	public int read(String key, int initInt) {
		return pSharedPref.getInt(key, initInt);
	}

	public void write(String key, int value) {
		SharedPreferences.Editor sharedEditor = pSharedPref.edit();
		sharedEditor.putInt(key, value);
		sharedEditor.commit();
	}

	public boolean read(String key, boolean flag) {
		return pSharedPref.getBoolean(key, flag);
	}

	public void write(String key, boolean flag) {
		SharedPreferences.Editor sharedEditor = pSharedPref.edit();
		sharedEditor.putBoolean(key, flag);
		sharedEditor.commit();
	}

	public float read(String key, float value) {
		return pSharedPref.getFloat(key, value);
	}

	public void write(String key, float value) {
		SharedPreferences.Editor sharedEditor = pSharedPref.edit();
		sharedEditor.putFloat(key, value);
		sharedEditor.commit();
	}

	public long read(String key, long value) {
		return pSharedPref.getLong(key, value);
	}

	public void write(String key, long value) {
		SharedPreferences.Editor sharedEditor = pSharedPref.edit();
		sharedEditor.putLong(key, value);
		sharedEditor.commit();
	}

	public void reverse(String key, boolean defaultBool) {
		if (read(key, defaultBool)) {
			write(key, false);
		} else {
			write(key, true);
		}
	}
}
