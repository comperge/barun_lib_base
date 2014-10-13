package org.bmcoop.lib.base.util;

import android.os.Handler;

public class UTimer {

	private UTimer() {
	}

	/**
	 * @param tickSecond
	 *            이후에 한번 불려짐
	 * @return
	 */
	public static WTimerOnce withOnce(int tickSecond) {
		return new WTimerOnce(tickSecond * 1000);
	}

	/**
	 * @param tickMiliSecond
	 *            이후에 한번 불려짐
	 * @return
	 */
	public static WTimerOnce withOnce_milliSecond(int tickMiliSecond) {
		return new WTimerOnce(tickMiliSecond);
	}

	/**
	 * @param tickSecond
	 *            이후에 최초 불려짐
	 * @param repeatCount
	 *            번 반복됨
	 * @return
	 */
	public static WTimerTick withRepeat(int tickSecond, int repeatCount) {
		return new WTimerTick(tickSecond * 1000, repeatCount);
	}

	/**
	 * @param afterSecond
	 *            이후에 최초 불려짐
	 * @param repeatCount
	 *            번 반복됨
	 * @return
	 */
	public static WTimerTick withRepeat_milliSecond(int tickMiliSecond, int repeatCount) {
		return new WTimerTick(tickMiliSecond, repeatCount);
	}



	public static class WTimerOnce {
		private Callback mCallback;
		private int mMilliSecond;
		private Handler mHanTimer;
		private RunBack mRunBack;
		
		private WTimerOnce(int milliSecond) {
			mMilliSecond = milliSecond;
		}

		public WTimerOnce forStart_autoClose(Callback WTimerOnce_Callback) {
			mCallback = WTimerOnce_Callback;
			start();
			return this;
		}

		private void start() {
			forClose();
			mHanTimer = new Handler();
			mRunBack = new RunBack();
			mHanTimer.postDelayed(mRunBack, mMilliSecond);
		}

		public void forClose() {
			if (mHanTimer != null) {
				try {
					mHanTimer.removeCallbacks(mRunBack);
					mRunBack = null;
					mHanTimer = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		public class RunBack implements Runnable {
			public void run() {
				forClose();
				if (mCallback != null) {
					mCallback.onEnd();
				}
			}
		};

		public static interface Callback {
			void onEnd();
		}
	}

	public static class WTimerTick {
		private Callback mCallback;
		private int mCount_total;
		private int mCount_now;
		private int mMilliSecond;
		private Handler mHanTimer;
		private RunBack mRunBack;

		private WTimerTick(int milliSecond, int repeatCount) {
			mMilliSecond = milliSecond;
			mCount_total = repeatCount;
		}

		public WTimerTick forStart_autoClose(Callback WTimerTick_Callback) {
			mCallback = WTimerTick_Callback;
			start();
			return this;
		}

		private void start() {
			forClose();
			mCount_now = 1;
			mHanTimer = new Handler();
			mRunBack = new RunBack();
			mHanTimer.postDelayed(mRunBack, mMilliSecond);
		}

		public void forClose() {
			if (mHanTimer != null) {
				try {
					mHanTimer.removeCallbacks(mRunBack);
					mRunBack = null;
					mHanTimer = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		public class RunBack implements Runnable {
			public void run() {
				if (mCount_total > mCount_now) {
					if (mCallback != null) {
						mCallback.onTick(mCount_now);
					}
					mCount_now++;
					if (mHanTimer != null) {
						mHanTimer.postDelayed(mRunBack, mMilliSecond);
					}
				} else {
					forClose();
					if (mCallback != null) {
						mCallback.onEnd();
					}
				}
			}
		};

		public static interface Callback {
			/**
			 * @param count
			 *            1부터 시작함
			 */
			void onTick(int repeatCount);

			void onEnd();
		}
	}
	
	/**
	 * @param postSecond
	 *            이후에 시작됨
	 * @param tickSecond
	 *            이후에 최초 불려짐. 단, 이 시간은 postSecond 이후임
	 * @param repeatCount
	 *            번 반복됨
	 * @return
	 */
	public static WTimerPost withPostDelay(int postSecond, int tickSecond, int repeatCount) {
		return new WTimerPost(postSecond * 1000, tickSecond * 1000, repeatCount);
	}

	/**
	 * @param postSecond
	 *            이후에 시작됨
	 * @param afterSecond
	 *            이후에 최초 불려짐. 단, 이 시간은 postSecond 이후임
	 * @param repeatCount
	 *            번 반복됨
	 * @return
	 */
	public static WTimerPost withPostDelay_milliSecond(int postSecond, int tickMiliSecond, int repeatCount) {
		return new WTimerPost(postSecond, tickMiliSecond, repeatCount);
	}

	public static class WTimerPost {
		private Callback mCallback;
		private int mCount_total;
		private int mCount_now;
		private int mPostSecond;
		private int mMilliSecond;
		private Handler mHanTimer;
		private RunBack mRunBack;

		private WTimerPost(int postSecond, int milliSecond, int repeatCount) {
			mPostSecond = postSecond;
			mMilliSecond = milliSecond;
			mCount_total = repeatCount;
		}

		public WTimerPost forStart_autoClose(Callback WTimerPost_Callback) {
			mCallback = WTimerPost_Callback;
			start();
			return this;
		}

		private void start() {
			forClose();
			mCount_now = 0;
			mHanTimer = new Handler();
			mRunBack = new RunBack();
			mHanTimer.postDelayed(mRunBack, mPostSecond);
		}

		public void forClose() {
			if (mHanTimer != null) {
				try {
					mHanTimer.removeCallbacks(mRunBack);
					mRunBack = null;
					mHanTimer = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		public class RunBack implements Runnable {
			public void run() {
				if (mCount_total > mCount_now) {
					if (mCallback != null) {
						mCallback.onTick(mCount_now);
					}
					mCount_now++;
					if (mHanTimer != null) {
						mHanTimer.postDelayed(mRunBack, mMilliSecond);
					}
				} else {
					forClose();
					if (mCallback != null) {
						mCallback.onEnd();
					}
				}
			}
		};

		public static interface Callback {
			/**
			 * @param count
			 *            1부터 시작함
			 */
			void onTick(int repeatCount);

			void onEnd();
		}
	}
}