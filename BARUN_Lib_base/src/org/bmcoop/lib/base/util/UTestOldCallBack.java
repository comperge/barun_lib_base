package org.bmcoop.lib.base.util;

import android.content.Context;

public class UTestOldCallBack {
	private CallBack mCallBack;

	private UTestOldCallBack() {
	}

	public static WTestCallBack with(Context pCon) {
		return new UTestOldCallBack().get(pCon);
	}

	private WTestCallBack get(Context pCon) {
		return new WTestCallBack(pCon);
	}

	public class WTestCallBack {
		private WTestCallBack(Context pCon) {
		}

		public void onClick() {
			if (mCallBack != null) {
				mCallBack.onClick();
			}
		}

		public WTestCallBack callBack(CallBack WTestSingleton_CallBack) {
			mCallBack = WTestSingleton_CallBack;
			return this;
		}
	}

	public interface CallBack {
		void onClick();
	}
}