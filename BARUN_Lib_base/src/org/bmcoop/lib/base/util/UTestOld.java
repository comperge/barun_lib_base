package org.bmcoop.lib.base.util;

import android.content.Context;

public class UTestOld {
	private static UTestOld SINGLE_U;

	private UTestOld() {
	}

	public static WTest with(Context pCon) {
		if (SINGLE_U == null) {
			SINGLE_U = new UTestOld();
		}
		return SINGLE_U.get(pCon);
	}

	private WTest get(Context pCon) {
		return new WTest(pCon);
	}

	public class WTest {

		private WTest(Context pCon) {

		}
	}
}