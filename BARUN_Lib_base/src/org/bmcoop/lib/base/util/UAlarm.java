package org.bmcoop.lib.base.util;

import org.bmcoop.lib.base.util.UPeriod.WPeriod;
import org.bmcoop.lib.receiver.PAlarmReceiver;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;


public class UAlarm {
	private static UAlarm SINGLE_U;

	private UAlarm() {
	}

	public static WIntentAlarm with(Context pCon, Class<?> _class) {
		if (SINGLE_U == null) {
			SINGLE_U = new UAlarm();
		}
		return SINGLE_U.get(pCon, _class);
	}

	private WIntentAlarm get(Context pCon, Class<?> _class) {
		return new WIntentAlarm(pCon, _class);
	}

	public class WIntentAlarm {
		private Context mCon;
		private WPeriod mWPeriod;
		private AlarmManager mAlarmMan;
		private Intent mIntent;
		private PendingIntent mPendingIntent;

		private WIntentAlarm(Context pCon, Class<?> _class) {
			mCon = pCon;
			mAlarmMan = (AlarmManager) mCon.getSystemService(Context.ALARM_SERVICE);
			mWPeriod = UPeriod.with();
			mIntent = new Intent(mCon, _class);
			mIntent.setAction(PAlarmReceiver.ACTION);
		}

		/**
		 * @param intendId 기존에 있는 Id는 종료 시키고, 다시 반복해서 실행된다.
		 * @return
		 */
		public WIntentAlarm forStart_repeat(int intendId) {
			forClose(intendId);
//			pIntent.putExtra(UIntent.ID, intendId);
			mPendingIntent = PendingIntent.getBroadcast(mCon, intendId, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);
			String firstTime = mWPeriod.getTime_next();
			String nextTime = mWPeriod.getTime_next_next();
			int nextTimeInt = UCalendar.with(firstTime, nextTime).secondGap() * 1000;
			long alarmTime = UCalendar.with(firstTime).time_long();

			mAlarmMan.setRepeating(AlarmManager.RTC_WAKEUP, alarmTime, nextTimeInt, mPendingIntent);
			return this;
		}

		/**
		 * 
		 * @param intendId 한번만 실행, 기존의 Id는 신경쓰지 않는다
		 */
		public WIntentAlarm forStart_once(int intendId) {
			mPendingIntent = PendingIntent.getBroadcast(mCon, intendId, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);
			String firstTime = mWPeriod.getTime_next();
			long alarmTime = UCalendar.with(firstTime).time_long();
			mAlarmMan.set(AlarmManager.RTC_WAKEUP, alarmTime, mPendingIntent);
			return this;
		}

		public WIntentAlarm forClose(int intendId) {
			mPendingIntent = PendingIntent.getBroadcast(mCon, intendId, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);
			mAlarmMan.cancel(mPendingIntent);
			return this;
		}

		public Intent getIntent() {
			return mIntent;
		}

		public WIntentAlarm setCycleMonth(int cycleMonth, int dayOfMonth, int actHour, int actMinute) {
			mWPeriod.setCycleMonth(cycleMonth, dayOfMonth, actHour, actMinute);
			return this;
		}

		public WIntentAlarm setCycleWeek(int dayOfWeek, int actHour, int actMinute) {
			mWPeriod.setCycleWeek(dayOfWeek, actHour, actMinute);
			return this;
		}

		public WIntentAlarm setCycleDay(int cycleDay, int actHour, int actMinute) {
			mWPeriod.setCycleDay(cycleDay, actHour, actMinute);
			return this;
		}

		public WIntentAlarm setCycleHour(int cycleHour, int actMinute) {
			mWPeriod.setCycleHour(cycleHour, actMinute);
			return this;
		}

		public WIntentAlarm setCycleMinute(int cycleMinute) {
			mWPeriod.setCycleMinute(cycleMinute);
			return this;
		}
	}
}