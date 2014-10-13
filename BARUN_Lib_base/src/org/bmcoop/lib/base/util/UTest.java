package org.bmcoop.lib.base.util;

import android.content.Context;

public class UTest {
	private static UTest SINGLE_U;

	private UTest() {
	}

	public static WTest with(Context pCon) {
		if (SINGLE_U == null) {
			SINGLE_U = new UTest();
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