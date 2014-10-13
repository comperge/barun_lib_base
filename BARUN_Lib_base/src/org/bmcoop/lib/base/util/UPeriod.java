package org.bmcoop.lib.base.util;

import android.content.ContentValues;

public class UPeriod {
	private static UPeriod SINGLE_U;

	private UPeriod() {
	}

	public static WPeriod with() {
		if (SINGLE_U == null) {
			SINGLE_U = new UPeriod();
		}
		return SINGLE_U.getString(null);
	}

	public static WPeriod with(String startTime) {
		if (SINGLE_U == null) {
			SINGLE_U = new UPeriod();
		}
		return SINGLE_U.getString(startTime);
	}

	private WPeriod getString(String targetTime) {
		return new WPeriod(targetTime);
	}

	public static WPeriod withJson(String jsonString) {
		if (SINGLE_U == null) {
			SINGLE_U = new UPeriod();
		}
		return SINGLE_U.getJson(null, jsonString);
	}

	public static WPeriod withJson(String targetTime, String jsonString) {
		if (SINGLE_U == null) {
			SINGLE_U = new UPeriod();
		}
		return SINGLE_U.getJson(targetTime, jsonString);
	}

	private WPeriod getJson(String targetTime, String jsonString) {
		return new WPeriod(targetTime, jsonString);
	}

	public class WPeriod {
		private static final int CYCLE_YEAR = 1;
		private static final int CYCLE_MONTH = 3;
		private static final int CYCLE_WEEK = 4;
		private static final int CYCLE_BIWEEK = 5;
		private static final int CYCLE_DAY = 6;
		private static final int CYCLE_HOUR = 7;
		private static final int CYCLE_MINUTE = 8;

		private int cycle;
		private int year;
		private int month;
		private int dayOfMonth;
		private int dayOfWeek;
		private int hour;
		private int minute;
		private int actHour;
		private int actMinute;
		private String targetTime;

		private WPeriod(String targetTime) {
			if (targetTime == null) {
				this.targetTime = UCalendar.with().time();
			} else {
				this.targetTime = targetTime;
			}
		}

		private WPeriod(String targetTime, String jsonString) {
			this(targetTime);
			ContentValues contentValues = UJsonUtil.withObject(jsonString).get_contentValues();

			if (contentValues != null) {
				this.cycle = Integer.parseInt((String) contentValues.get("cycle"));
				this.year = Integer.parseInt((String) contentValues.get("year"));
				this.month = Integer.parseInt((String) contentValues.get("month"));
				this.dayOfMonth = Integer.parseInt((String) contentValues.get("dayOfMonth"));
				this.dayOfWeek = Integer.parseInt((String) contentValues.get("dayOfWeek"));
				this.hour = Integer.parseInt((String) contentValues.get("hour"));
				this.minute = Integer.parseInt((String) contentValues.get("minute"));
				this.actHour = Integer.parseInt((String) contentValues.get("actHour"));
				this.actMinute = Integer.parseInt((String) contentValues.get("actMinute"));
			}
		}

		/**
		 * @return "2014-12-31 00:00:00"
		 */
		public String getTime_next() {
			if (cycle == CYCLE_MINUTE) {
				return UCalendar.withAfter(targetTime).minute(minute);
			} else if (cycle == CYCLE_HOUR) {
				return UCalendar.withAfter(targetTime).hour(hour, actMinute);
			} else if (cycle == CYCLE_DAY) {
				return UCalendar.withAfter(targetTime).day(dayOfMonth, actHour, actMinute);
			} else if (cycle == CYCLE_BIWEEK) {
				return UCalendar.withAfter(targetTime).week_biWeek(dayOfWeek, actHour, actMinute);
			} else if (cycle == CYCLE_WEEK) {
				return UCalendar.withAfter(targetTime).week(dayOfWeek, actHour, actMinute);
			} else if (cycle == CYCLE_MONTH) {
				return UCalendar.withAfter(targetTime).month(month, dayOfMonth, actHour, actMinute);
			} else if (cycle == CYCLE_YEAR) {
				return UCalendar.withAfter(targetTime).year(year, month, dayOfMonth, actHour, actMinute);
			}
			return null;
		}

		public String getTime_next_next() {
			return UPeriod.with(getTime_next()).setCycleAll(this).getTime_next();
		}

		public WPeriod getNext() {
			return UPeriod.with(getTime_next()).setCycleAll(this);
		}

		public WPeriod setCycleAll(WPeriod wPeriod) {
			cycle = wPeriod.cycle;
			year = wPeriod.year;
			month = wPeriod.month;
			dayOfMonth = wPeriod.dayOfMonth;
			dayOfWeek = wPeriod.dayOfWeek;
			hour = wPeriod.hour;
			minute = wPeriod.minute;
			actHour = wPeriod.actHour;
			actMinute = wPeriod.actMinute;
			return this;
		}

		public String getJson_string() {
			ContentValues contentValues = new ContentValues();
			contentValues.put("cycle", cycle);
			contentValues.put("year", year);
			contentValues.put("month", month);
			contentValues.put("dayOfMonth", dayOfMonth);
			contentValues.put("dayOfWeek", dayOfWeek);
			contentValues.put("hour", hour);
			contentValues.put("minute", minute);
			contentValues.put("actHour", actHour);
			contentValues.put("actMinute", actMinute);
			contentValues.put("targetTime", targetTime);
			return UJsonUtil.withContentValues(contentValues).getString();
		}

		/**
		 * 년 단위로 반복되는 주기를 설정하기 위함
		 * 
		 * @param cycleYear
		 *            반복되는 년, 예를 들어 3이면 3년 마다 반복됨
		 * @param actMonth
		 *            알림을 할 달, 예를 들어 3이면 3월에 알림
		 * @param dayOfMonth
		 *            알림을 할 일, 예를 들어 3이면 3월 3일에 알림
		 * @param actHour
		 *            알림을 할 시간, 예를 들어 3이면 오전 3시에 알림
		 * @param actMinute
		 *            알림을 할 분, 예를 들어 3이면 3분에 알림
		 * @param distMinute
		 *            분산을 위한 분, 예를 들어 10이면 10분 안에 랜던 분을 더하여, 그 이후에 알림. 서버에서 분산 목적으로
		 *            사용 됨
		 * @return 주기 설정이 되었는 지 여부, false가 되면 입력된 값이 잘못된 것임
		 */
		public boolean setCycleYear(int cycleYear, int actMonth, int dayOfMonth, int actHour, int actMinute) {
			if (UCalendar.withValid().isMonth(actMonth)) {
				if (UCalendar.withValid().isDayOfMonth(dayOfMonth)) {
					if (UCalendar.withValid().isHour(actHour)) {
						if (UCalendar.withValid().isMinute(actMinute)) {
							this.cycle = CYCLE_YEAR;
							this.year = cycleYear;
							this.month = actMonth - 1;
							this.dayOfMonth = dayOfMonth;
							this.actHour = actHour;
							this.actMinute = actMinute;
							return true;
						}
					}
				}
			}
			return false;
		}

		/**
		 * 월 단위로 반복되는 주기를 설정하기 위함
		 * 
		 * @param cycleMonth
		 *            반복되는 월, 예를 들어 3이면 3개월 마다 반복됨
		 * @param dayOfMonth
		 *            알림을 할 일, 예를 들어 3이면 3월 3일에 알림
		 * @param actHour
		 *            알림을 할 시간, 예를 들어 3이면 오전 3시에 알림
		 * @param actMinute
		 *            알림을 할 분, 예를 들어 3이면 3분에 알림
		 * @param distMinute
		 *            분산을 위한 분, 예를 들어 10이면 10분 안에 랜던 분을 더하여, 그 이후에 알림. 서버에서 분산 목적으로
		 *            사용 됨
		 * @return 주기 설정이 되었는 지 여부, false가 되면 입력된 값이 잘못된 것임
		 */
		public boolean setCycleMonth(int cycleMonth, int dayOfMonth, int actHour, int actMinute) {
			if (UCalendar.withValid().isMonth(cycleMonth)) {
				if (UCalendar.withValid().isDayOfMonth(dayOfMonth)) {
					if (UCalendar.withValid().isHour(actHour)) {
						if (UCalendar.withValid().isMinute(actMinute)) {
							this.month = cycleMonth;
							this.cycle = CYCLE_MONTH;
							this.dayOfMonth = dayOfMonth;
							this.actHour = actHour;
							this.actMinute = actMinute;
							return true;
						}
					}
				}
			}
			return false;
		}

		/**
		 * 주 단위로 반복되는 주기를 설정하기 위함
		 * 
		 * @param dayOfWeek
		 *            반복되는 요일, 예를 들어 DAY_SUN 이면 일요일 마다 반복됨
		 * @param actHour
		 *            알림을 할 시간, 예를 들어 3이면 오전 3시에 알림
		 * @param actMinute
		 *            알림을 할 분, 예를 들어 3이면 3분에 알림
		 * @param distMinute
		 *            분산을 위한 분, 예를 들어 10이면 10분 안에 랜던 분을 더하여, 그 이후에 알림. 서버에서 분산 목적으로
		 *            사용 됨
		 * @return 주기 설정이 되었는 지 여부, false가 되면 입력된 값이 잘못된 것임
		 */
		public boolean setCycleWeek(int dayOfWeek, int actHour, int actMinute) {
			if (UCalendar.withValid().isDayOfWeek(dayOfWeek)) {
				if (UCalendar.withValid().isHour(actHour)) {
					if (UCalendar.withValid().isMinute(actMinute)) {
						this.cycle = CYCLE_WEEK;
						this.dayOfWeek = dayOfWeek;
						this.actHour = actHour;
						this.actMinute = actMinute;
						return true;
					}
				}
			}
			return false;
		}

		/**
		 * 격주 단위로 반복되는 주기를 설정하기 위함
		 * 
		 * @param dayOfWeek
		 *            반복되는 요일, 예를 들어 DAY_SUN 이면 격주 일요일 마다 반복됨
		 * @param actHour
		 *            알림을 할 시간, 예를 들어 3이면 오전 3시에 알림
		 * @param actMinute
		 *            알림을 할 분, 예를 들어 3이면 3분에 알림
		 * @param distMinute
		 *            분산을 위한 분, 예를 들어 10이면 10분 안에 랜던 분을 더하여, 그 이후에 알림. 서버에서 분산 목적으로
		 *            사용 됨
		 * @return 주기 설정이 되었는 지 여부, false가 되면 입력된 값이 잘못된 것임
		 */
		public boolean setCycleBiweek(int dayOfWeek, int actHour, int actMinute) {
			if (UCalendar.withValid().isDayOfWeek(dayOfWeek)) {
				if (UCalendar.withValid().isHour(actHour)) {
					if (UCalendar.withValid().isMinute(actMinute)) {
						this.cycle = CYCLE_BIWEEK;
						this.dayOfWeek = dayOfWeek;
						this.actHour = actHour;
						this.actMinute = actMinute;
						return true;
					}
				}
			}
			return false;
		}

		/**
		 * 일 단위로 반복되는 주기를 설정하기 위함
		 * 
		 * @param cycleDay
		 *            반복되는 일, 예를 들어 3이면 3일마다 반복됨
		 * @param actHour
		 *            알림을 할 시간, 예를 들어 3이면 오전 3시에 알림
		 * @param actMinute
		 *            알림을 할 분, 예를 들어 3이면 3분에 알림
		 * @param distMinute
		 *            분산을 위한 분, 예를 들어 10이면 10분 안에 랜던 분을 더하여, 그 이후에 알림. 서버에서 분산 목적으로
		 *            사용 됨
		 * @return 주기 설정이 되었는 지 여부, false가 되면 입력된 값이 잘못된 것임
		 */
		public boolean setCycleDay(int cycleDay, int actHour, int actMinute) {
			if (UCalendar.withValid().isDayOfMonth(cycleDay)) {
				if (UCalendar.withValid().isHour(actHour)) {
					if (UCalendar.withValid().isMinute(actMinute)) {
						this.cycle = CYCLE_DAY;
						this.dayOfMonth = cycleDay;
						this.actHour = actHour;
						this.actMinute = actMinute;
//						this.distMinute = distMinute;
						return true;
					}
				}
			}
			return false;
		}

		/**
		 * 시간 단위로 반복되는 주기를 설정하기 위함
		 * 
		 * @param cycleHour
		 *            반복되는 시간, 예를 들어 3이면 3시간마다 반복됨
		 * @param actMinute
		 *            알림을 할 분, 예를 들어 3이면 3분에 알림
		 * @return 주기 설정이 되었는 지 여부, false가 되면 입력된 값이 잘못된 것임
		 */
		public boolean setCycleHour(int cycleHour, int actMinute) {
			if (UCalendar.withValid().isHour(cycleHour)) {
				if (UCalendar.withValid().isMinute(actMinute)) {
					this.cycle = CYCLE_HOUR;
					this.hour = cycleHour;
					this.actMinute = actMinute;
					return true;
				}
			}
			return false;
		}

		/**
		 * 분 단위로 반복되는 주기를 설정하기 위함
		 * 
		 * @param cycleMinute
		 *            반복되는 분, 예를 들어 3이면 3분마다 반복됨
		 * @return 주기 설정이 되었는 지 여부, false가 되면 입력된 값이 잘못된 것임
		 */
		public boolean setCycleMinute(int cycleMinute) {
			if (UCalendar.withValid().isMinute(actMinute)) {
				this.cycle = CYCLE_MINUTE;
				this.minute = cycleMinute;
				return true;
			}
			return false;
		}
	}
}