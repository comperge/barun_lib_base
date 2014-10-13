package org.bmcoop.lib.base.util;

import android.content.Context;

public class UTestSingleton {
	private static UTestSingleton SINGLE_U;
	private static WTestSingleton SINGLE_W;

	private UTestSingleton() {
	}

	public static WTestSingleton with(Context pCon) {
		if (SINGLE_U == null) {
			SINGLE_U = new UTestSingleton();
		}
		if (SINGLE_W == null) {
			SINGLE_W = SINGLE_U.get(pCon);
		}
		return SINGLE_W;
	}

	private WTestSingleton get(Context pCon) {
		return new WTestSingleton(pCon);
	}

	public class WTestSingleton {

		private WTestSingleton(Context pCon) {
		}
	}
}
