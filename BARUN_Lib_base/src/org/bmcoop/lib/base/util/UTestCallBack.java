package org.bmcoop.lib.base.util;

import android.content.Context;

public class UTestCallBack {

	private UTestCallBack() {
	}

	public static WTestCallBack with(Context pCon) {
		return new WTestCallBack(pCon);
	}

	public static class WTestCallBack {
		private Callback mCallback;

		private WTestCallBack(Context pCon) {
		}

		public void onClick() {
			if (mCallback != null) {
				mCallback.onClick();
			}
		}

		public WTestCallBack callBack(Callback WTestCallBack_Callback) {
			mCallback = WTestCallBack_Callback;
			return this;
		}

		public static interface Callback {
			void onClick();
		}
	}
}