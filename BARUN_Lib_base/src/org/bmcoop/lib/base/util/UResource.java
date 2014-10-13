package org.bmcoop.lib.base.util;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

public class UResource {
	private static UResource singleton;

	public static WResource with(Context pCon) {
		if (singleton == null) {
			singleton = new UResource();
		}
		return singleton.getResource(pCon);
	}

	private WResource getResource(Context pCon) {
		return new WResource(pCon);
	}

	public class WResource {
		private Resources mResources;

		public WResource(Context pCon) {
			mResources = pCon.getResources();
		}

		public String[] getArray(int R_array) {
			return mResources.getStringArray(R_array);
		}

		public ArrayList<String> getArray(int R_array, ArrayList<String> strlist) {
			String[] strArray = mResources.getStringArray(R_array);
			for (int i = 0; i < strArray.length; i++) {
				strlist.add(strArray[i]);
			}
			return strlist;
		}

		public boolean isTablet() {
			boolean isTablet = false;
			int xLargebit = 4;
			Configuration config = mResources.getConfiguration();
			isTablet = (config.screenLayout & xLargebit) == xLargebit;
			if (config.smallestScreenWidthDp == 800 || config.smallestScreenWidthDp == 600) {
				isTablet = true;
			}
			return isTablet;
		}
	}
}
