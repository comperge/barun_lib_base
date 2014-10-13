package org.bmcoop.lib.base.util;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.Toast;

public class UToast {
	private static UToast SINGLE_U;

	private UToast() {
	}

	public static WToast with(Context pCon, String message) {
		if (SINGLE_U == null) {
			SINGLE_U = new UToast();
		}
		return SINGLE_U.get(pCon, message);
	}

	private WToast get(Context pCon, String message) {
		return new WToast(pCon, message);
	}

	public static WToast with(Context pCon, int resId) {
		if (SINGLE_U == null) {
			SINGLE_U = new UToast();
		}
		return SINGLE_U.get(pCon, resId);
	}

	private WToast get(Context pCon, int resId) {
		return new WToast(pCon, resId);
	}

	public class WToast {
		private Context mCon;
		private Activity mActivity;
		private String mMessage;

		private WToast(Context pCon, String message) {
			mCon = pCon;
			if (mCon instanceof Activity) {
				mActivity = (Activity) mCon;
			}
			mMessage = message;
		}

		private WToast(Context pCon, int resId) {
			mCon = pCon;
			if (mCon instanceof Activity) {
				mActivity = (Activity) mCon;
			}
			mMessage = mCon.getResources().getString(resId);
		}

		public void forShow() {
			try {
				if (mActivity != null) {
					mActivity.runOnUiThread(new Runnable() {
						public void run() {
							Toast.makeText(mCon, mMessage, Toast.LENGTH_SHORT).show();
						}
					});
				} else {
					Toast.makeText(mCon, mMessage, Toast.LENGTH_SHORT).show();
				}
			} catch (Exception e) {
			}
		}

		public void forShow_longTime() {
			try {
				if (mActivity != null) {
					mActivity.runOnUiThread(new Runnable() {
						public void run() {
							Toast.makeText(mCon, mMessage, Toast.LENGTH_LONG).show();
						}
					});
				} else {
					Toast.makeText(mCon, mMessage, Toast.LENGTH_LONG).show();
				}
			} catch (Exception e) {
			}
		}

		public boolean forShow_ifFalse(boolean isTrue) {
			if (!isTrue) {
				forShow();
			}
			return isTrue;
		}
	}

	public static WToastIf withIf(Context pCon, String messageTrue, String messageFlase) {
		if (SINGLE_U == null) {
			SINGLE_U = new UToast();
		}
		return SINGLE_U.getIf(pCon, messageTrue, messageFlase);
	}

	private WToastIf getIf(Context pCon, String messageTrue, String messageFlase) {
		return new WToastIf(pCon, messageTrue, messageFlase);
	}

	public static WToastIf withIf(Context pCon, int resIdTrue, int resIdFlase) {
		if (SINGLE_U == null) {
			SINGLE_U = new UToast();
		}
		return SINGLE_U.getIf(pCon, resIdTrue, resIdFlase);
	}

	private WToastIf getIf(Context pCon, int resIdTrue, int resIdFlase) {
		return new WToastIf(pCon, resIdTrue, resIdFlase);
	}

	public class WToastIf {
		private Context mCon;
		private Activity mActivity;
		private String mMessageTrue;
		private String mMessageFalse;

		private WToastIf(Context pCon, String messageTrue, String messageFlase) {
			mCon = pCon;
			if (mCon instanceof Activity) {
				mActivity = (Activity) mCon;
			}
			mMessageTrue = messageTrue;
			mMessageFalse = messageFlase;
		}

		private WToastIf(Context pCon, int resIdTrue, int resIdFlase) {
			mCon = pCon;
			if (mCon instanceof Activity) {
				mActivity = (Activity) mCon;
			}
			mMessageTrue = mCon.getResources().getString(resIdTrue);
			mMessageFalse = mCon.getResources().getString(resIdFlase);
		}

		public boolean forShow(boolean isTrue) {
			if (isTrue) {
				try {
					if (mActivity != null) {
						mActivity.runOnUiThread(new Runnable() {
							public void run() {
								Toast.makeText(mCon, mMessageTrue, Toast.LENGTH_SHORT).show();
							}
						});
					} else {
						Toast.makeText(mCon, mMessageTrue, Toast.LENGTH_SHORT).show();
					}
				} catch (Exception e) {
				}
			} else {
				try {
					if (mActivity != null) {
						mActivity.runOnUiThread(new Runnable() {
							public void run() {
								Toast.makeText(mCon, mMessageFalse, Toast.LENGTH_SHORT).show();
							}
						});
					} else {
						Toast.makeText(mCon, mMessageFalse, Toast.LENGTH_SHORT).show();
					}
				} catch (Exception e) {
				}
			}
			return isTrue;
		}
	}

	public static WToastImage withImage(Context pCon, String message, int resIdImage) {
		if (SINGLE_U == null) {
			SINGLE_U = new UToast();
		}
		return SINGLE_U.getImage(pCon, message, resIdImage);
	}

	private WToastImage getImage(Context pCon, String message, int resIdImage) {
		return new WToastImage(pCon, message, resIdImage);
	}

	public class WToastImage {
		private Context mCon;
		private Activity mActivity;
		private int mResIdImage;
		private String mMessage;

		private WToastImage(Context pCon, String message, int resIdImage) {
			mCon = pCon;
			if (mCon instanceof Activity) {
				mActivity = (Activity) mCon;
			}
			mMessage = message;
			mResIdImage = resIdImage;
		}

		public void forShow() {
			try {
				if (mActivity != null) {
					mActivity.runOnUiThread(new Runnable() {
						public void run() {
							LinearLayout linear = new LinearLayout(mCon);
							linear.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
							linear.setBackgroundResource(mResIdImage);
							linear.setGravity(Gravity.CENTER);
							Toast toast = Toast.makeText(mCon, mMessage, Toast.LENGTH_SHORT);
							toast.setView(linear);
							toast.show();
						}
					});
				} else {
					LinearLayout linear = new LinearLayout(mCon);
					linear.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
					linear.setBackgroundResource(mResIdImage);
					linear.setGravity(Gravity.CENTER);
					Toast toast = Toast.makeText(mCon, mMessage, Toast.LENGTH_SHORT);
					toast.setView(linear);
					toast.show();
				}
			} catch (Exception e) {
			}
		}
	}
}