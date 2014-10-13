package org.bmcoop.lib.base.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class UDialog {

	private UDialog() {
	}

	public static WDialogBtnOne withOne(Context pCon, String message, String btnTextOk) {
		return new WDialogBtnOne(pCon, message, btnTextOk);
	}

	public static WDialogBtnOne withOne_yes(Context pCon, String message) {
		return new WDialogBtnOne(pCon, message, "예");
	}

	public static WDialogBtnOne withOne_ok(Context pCon, String message) {
		return new WDialogBtnOne(pCon, message, "확인");
	}

	public static class WDialogBtnOne {
		private Callback mCallback;
		private Context mCon;
		private String mTitle;
		private String mMessage;
		private String mBtnTextOk;

		private WDialogBtnOne(Context pCon, String message, String btnTextOk) {
			mCon = pCon;
			mTitle = "알림";
			mMessage = message;
			mBtnTextOk = btnTextOk;
		}

		public WDialogBtnOne forShow(Callback WDialogBtnOne_Callback) {
			mCallback = WDialogBtnOne_Callback;
			try {
				new AlertDialog.Builder(mCon).setTitle(mTitle).setMessage(mMessage).setPositiveButton(mBtnTextOk, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						if (mCallback != null) {
							mCallback.onClick();
						}
					}
				}).show();
			} catch (Exception e) {
			}
			return this;
		}

		public static interface Callback {
			void onClick();
		}
	}

	public static WDialogBtnTwo withTwo(Context pCon, String message, String btnTextOne, String btnTextTwo) {
		return new WDialogBtnTwo(pCon, message, btnTextOne, btnTextTwo);
	}

	public static WDialogBtnTwo withTwo_yesNo(Context pCon, String message) {
		return new WDialogBtnTwo(pCon, message, "예", "아니오");
	}

	public static WDialogBtnTwo withTwo_okCancel(Context pCon, String message) {
		return new WDialogBtnTwo(pCon, message, "확인", "취소");
	}

	public static class WDialogBtnTwo {
		private CallbackYes mCallbackYes;
		private CallbackAll mCallbackAll;
		private Context mCon;
		private String mTitle;
		private String mMessage;
		private String mBtnTextOne;
		private String mBtnTextTwo;

		private WDialogBtnTwo(Context context, String message, String btnTextOne, String btnTextTwo) {
			mCon = context;
			mTitle = "알림";
			mMessage = message;
			mBtnTextOne = btnTextOne;
			mBtnTextTwo = btnTextTwo;
		}

		public WDialogBtnTwo forShow_yes(CallbackYes WDialogBtnTwo_CallbackYes) {
			mCallbackYes = WDialogBtnTwo_CallbackYes;
			show();
			return this;
		}

		public WDialogBtnTwo forShow_all(CallbackAll WDialogBtnTwo_CallbackAll) {
			mCallbackAll = WDialogBtnTwo_CallbackAll;
			show();
			return this;
		}

		private void show() {
			try {
				new AlertDialog.Builder(mCon).setTitle(mTitle).setMessage(mMessage).setPositiveButton(mBtnTextOne, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						if (mCallbackYes != null) {
							mCallbackYes.onClick();
						}
						if (mCallbackAll != null) {
							mCallbackAll.onClick_yes();
						}
					}
				}).setNegativeButton(mBtnTextTwo, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						if (mCallbackAll != null) {
							mCallbackAll.onClick_no();
						}
					}
				}).show();
			} catch (Exception e) {
			}
		}

		public static interface CallbackYes {
			void onClick();
		}

		public static interface CallbackAll {
			void onClick_yes();

			void onClick_no();
		}
	}

	public static WDialogList withList_cancel(Context pCon, String[] textContArray) {
		return new WDialogList(pCon, textContArray, "취소");
	}

	public static class WDialogList {
		private Callback mCallback;
		private Context mCon;
		private String mTitle;
		private String[] mTextContArray;
		private String mBtnText;

		private WDialogList(Context pCon, String[] textContArray, String btnText) {
			mCon = pCon;
			mTitle = "알림";
			mTextContArray = textContArray;
			mBtnText = btnText;
		}

		public WDialogList forShow(Callback WDialogList_Callback) {
			mCallback = WDialogList_Callback;
			try {
				new AlertDialog.Builder(mCon).setTitle(mTitle).setItems(mTextContArray, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						if (mCallback != null) {
							mCallback.onClick(which);
						}
					}
				}).setNegativeButton(mBtnText, null).show();
			} catch (Exception e) {
			}
			return this;
		}

		public static interface Callback {
			void onClick(int index);
		}
	}

	public static WDialogRadio withRadio_ok(Context pCon, String[] textContArray, int indexSelected) {
		return new WDialogRadio(pCon, textContArray, indexSelected, "확인");
	}

	public static class WDialogRadio {
		private Callback mCallback;
		private Context mCon;
		private String mTitle;
		private String[] mTextContArray;
		private String mBtnText;
		private int mIndexSelected;

		private WDialogRadio(Context pCon, String[] textContArray, int indexSelected, String btnText) {
			mCon = pCon;
			mTitle = "알림";
			mTextContArray = textContArray;
			mBtnText = btnText;
			mIndexSelected = indexSelected;
		}

		public WDialogRadio forShow(Callback WDialogRadio_Callback) {
			mCallback = WDialogRadio_Callback;
			try {
				new AlertDialog.Builder(mCon).setTitle(mTitle).setSingleChoiceItems(mTextContArray, mIndexSelected, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						mIndexSelected = which;
					}
				}).setNegativeButton(mBtnText, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						if (mCallback != null) {
							mCallback.onClick(mIndexSelected);
						}
					}
				}).show();
			} catch (Exception e) {
			}
			return this;
		}

		public static interface Callback {
			void onClick(int index);
		}
	}
}