package org.bmcoop.lib.base.file;

import android.content.Context;

public class PPackageMan extends PPreference {
	private String Key_App_Log = "Key_App_Log";

	public PPackageMan(Context context) {
		super(context);
	}

	public void setAppLog(boolean enable) {
		write(Key_App_Log, enable);
	}

	public boolean isAppLogEnabled() {
		return read(Key_App_Log, true);
	}
}
