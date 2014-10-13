package org.bmcoop.lib.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.annotation.SuppressLint;
import android.text.format.DateFormat;

@SuppressLint("SimpleDateFormat")
public class UCalendar extends UCalendarUtil {
	private static UCalendar singleton;

	/**
	 * @param 2013-01-17 13:10:59
	 */
	public static ICalendar with() {
		if (singleton == null) {
			singleton = new UCalendar();
		}
		return singleton.getCalendar();
	}

	private ICalendar getCalendar() {
		return new ICalendar();
	}

	/**
	 * @param 2013-01-17 13:10:59
	 */
	public static ICalendar with(String date) {
		if (singleton == null) {
			singleton = new UCalendar();
		}
		return singleton.getCalendar(date);
	}

	private ICalendar getCalendar(String date) {
		return new ICalendar(date);
	}

	/**
	 * @param 초
	 *            단위로 입력
	 * @return
	 */
	public static ICalendar with(int second) {
		if (singleton == null) {
			singleton = new UCalendar();
		}
		return singleton.getCalendar(second);
	}

	private ICalendar getCalendar(int second) {
		return new ICalendar(second);
	}

	public static ICalendar with(GregorianCalendar gregorianCalendar) {
		if (singleton == null) {
			singleton = new UCalendar();
		}
		return singleton.getCalendar(gregorianCalendar);
	}

	private ICalendar getCalendar(GregorianCalendar gregorianCalendar) {
		return new ICalendar(gregorianCalendar);
	}

	public static ICalendar with(long time) {
		if (singleton == null) {
			singleton = new UCalendar();
		}
		return singleton.getCalendar(time);
	}

	private ICalendar getCalendar(long time) {
		return new ICalendar(time);
	}

	public static ICalendar with_format(String dateNotFormat) {
		if (singleton == null) {
			singleton = new UCalendar();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date;
		Calendar gc = new GregorianCalendar();
		try {
			date = sdf.parse(dateNotFormat);
			gc.setTime(date);
		} catch (ParseException e) {
		}

		return singleton.getCalendar(gc.getTimeInMillis());
	}

	public class ICalendar {
		private String inputTime;
		private GregorianCalendar gregorianCalendar;

		private ICalendar() {
			gregorianCalendar = getCalendarNow();
			this.inputTime = getString(gregorianCalendar);
		}

		private ICalendar(String inputTime) {
			gregorianCalendar = getGregorianCalendar(inputTime);
			this.inputTime = inputTime;
		}

		private ICalendar(long time) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			inputTime = sdf.format(new Date(time));
			gregorianCalendar = getGregorianCalendar(inputTime);
		}

		private ICalendar(int second) {
			gregorianCalendar = getCalendarNow();
			gregorianCalendar.set(Calendar.DAY_OF_MONTH, 1);
			gregorianCalendar.set(Calendar.SECOND, 0);
			gregorianCalendar.set(Calendar.MINUTE, 0);
			gregorianCalendar.set(Calendar.HOUR_OF_DAY, 0);
			gregorianCalendar.add(Calendar.SECOND, second);
			inputTime = getString(gregorianCalendar);
		}

		private ICalendar(GregorianCalendar gregorianCalendar) {
			this.gregorianCalendar = gregorianCalendar;
			inputTime = getString(gregorianCalendar);
		}

		public GregorianCalendar gregorianCalendar() {
			return gregorianCalendar;
		}

		public String time_system() {
			long nowmills = System.currentTimeMillis();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.format(new Date(nowmills));
		}

		public String time() {
			return inputTime;
		}

		/**
		 * @param notDash
		 *            20140217133921
		 * @return
		 */
		public String time(String notDash) {
			return notDash.substring(0, 4) + "-" + notDash.substring(4, 6) + "-" + notDash.substring(6, 8) + " " + notDash.substring(8, 10) + ":" + notDash.substring(10, 12) + ":"
					+ notDash.substring(12, 14);
		}

		public String time_fileName() {
			return inputTime.replace("-", "").replace(" ", "_").replace(":", "");
		}

		public ArrayList<String> dayList(String dateEnd) {
			ArrayList<String> redata = new ArrayList<String>();
			for (int i = 0; i < 1000; i++) {
				redata.add(date());
				if (date().equals(dateEnd)) {
					break;
				}
				gregorianCalendar.add(Calendar.DAY_OF_MONTH, 1);
				inputTime = getString(gregorianCalendar);
			}
			return redata;
		}

		public String date() {
			if (U.isValid(inputTime)) {
				inputTime = inputTime.substring(0, 10);
			}
			return inputTime;
		}

		public String date_yesterday() {
			gregorianCalendar.add(Calendar.DAY_OF_MONTH, -1);
			return getString(gregorianCalendar).substring(0, 10);
		}

		public int year() {
			if (inputTime != null && inputTime.length() >= 4) {
				return Integer.parseInt(inputTime.substring(0, 4));
			}
			return -1;
		}

		public String year_string() {
			if (U.isValid(inputTime)) {
				inputTime = inputTime.substring(0, 4);
			}
			return inputTime;
		}

		public ArrayList<String> yearList(String dateEnd) {
			ArrayList<String> redata = new ArrayList<String>();
			for (int i = 0; i < 1000; i++) {
				redata.add(year_string());
				if (year_string().equals(dateEnd.substring(0, 4))) {
					break;
				}
				gregorianCalendar.add(Calendar.YEAR, 1);
				inputTime = getString(gregorianCalendar);
			}
			return redata;
		}

		public int month() {
			if (inputTime != null && inputTime.length() >= 7) {
				return Integer.parseInt(inputTime.substring(5, 7));
			}
			return -1;
		}

		public String month_string() {
			if (inputTime != null && inputTime.length() >= 7) {
				return inputTime.substring(5, 7);
			}
			return "";
		}

		public String yearMonth() {
			if (U.isValid(inputTime)) {
				inputTime = inputTime.substring(0, 7);
			}
			return inputTime;
		}

		public ArrayList<String> monthList(String dateEnd) {
			ArrayList<String> redata = new ArrayList<String>();
			for (int i = 0; i < 1000; i++) {
				redata.add(yearMonth());
				if (yearMonth().equals(dateEnd.substring(0, 7))) {
					break;
				}
				gregorianCalendar.add(Calendar.MONTH, 1);
				inputTime = getString(gregorianCalendar);
			}
			return redata;
		}

		public ArrayList<String> quarterList(String dateEnd) {
			ArrayList<String> redata = new ArrayList<String>();
			for (int i = 0; i < 1000; i++) {
				if (gregorianCalendar.get(Calendar.MONTH) == 2 || gregorianCalendar.get(Calendar.MONTH) == 5 || gregorianCalendar.get(Calendar.MONTH) == 8
						|| gregorianCalendar.get(Calendar.MONTH) == 11) {
					redata.add(yearMonth());
				}
				if (yearMonth().equals(dateEnd.substring(0, 7))) {
					break;
				}
				gregorianCalendar.add(Calendar.MONTH, 1);
				inputTime = getString(gregorianCalendar);
			}
			return redata;
		}

		public int day() {
			if (inputTime != null && inputTime.length() >= 10) {
				return Integer.parseInt(inputTime.substring(8, 10));
			}
			return -1;
		}

		public String day_string() {
			if (inputTime != null && inputTime.length() >= 10) {
				return inputTime.substring(8, 10);
			}
			return "";
		}

		public int[] dayHourMinute() {
			int[] reArray = new int[3];
			reArray[0] = gregorianCalendar.get(Calendar.DAY_OF_MONTH) - 1;
			reArray[1] = gregorianCalendar.get(Calendar.HOUR_OF_DAY);
			reArray[2] = gregorianCalendar.get(Calendar.MINUTE);
			return reArray;
		}

		public int[] dayHourMinuteSecond() {
			int[] reArray = new int[4];
			reArray[0] = gregorianCalendar.get(Calendar.DAY_OF_MONTH) - 1;
			reArray[1] = gregorianCalendar.get(Calendar.HOUR_OF_DAY);
			reArray[2] = gregorianCalendar.get(Calendar.MINUTE);
			reArray[3] = gregorianCalendar.get(Calendar.SECOND);
			return reArray;
		}

		public boolean isToday(String dateTarget) {
			if (dateTarget != null && dateTarget.length() >= 10) {
				int year = Integer.parseInt(dateTarget.subSequence(0, 4).toString());
				int month = Integer.parseInt(dateTarget.subSequence(5, 7).toString());
				int day = Integer.parseInt(dateTarget.subSequence(8, 10).toString());
				if (year == year() && month == month() && day == day()) {
					return true;
				}
			}
			return false;
		}

		public int dayOfWeek() {
			return gregorianCalendar.get(Calendar.DAY_OF_WEEK);
		}

		public int day_lastOfMonth() {
			return gregorianCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		}

		public int hour() {
			if (inputTime != null && inputTime.length() >= 13) {
				return Integer.parseInt(inputTime.substring(11, 13));
			}
			return -1;
		}

		/**
		 * @return 13:10
		 */
		public String hourMinute() {
			if (inputTime != null && inputTime.length() >= 16) {
				return inputTime.substring(11, 16);
			}
			return inputTime;
		}

		public int[] hourMinute_array() {
			int[] reData = new int[2];
			if (inputTime != null && inputTime.length() >= 16) {
				reData[0] = Integer.parseInt(inputTime.substring(11, 13));
				reData[1] = Integer.parseInt(inputTime.substring(14, 16));
			}
			return reData;
		}

		public String hourMinute_AmPm() {
			String delegate = "aaa hh:mm";
			return (String) DateFormat.format(delegate, gregorianCalendar.getTime());
		}

		/**
		 * @return 03:04:01
		 */
		public String hourMinuteSecond() {
			if (inputTime != null && inputTime.length() >= 19) {
				return inputTime.substring(11, 19);
			}
			return inputTime;
		}

		public int minute() {
			if (inputTime != null && inputTime.length() >= 16) {
				return Integer.parseInt(inputTime.substring(14, 16));
			}
			return -1;
		}

		public int[] minuteSecond_array() {
			int[] reData = new int[2];
			if (inputTime != null && inputTime.length() >= 19) {
				reData[0] = Integer.parseInt(inputTime.substring(14, 16));
				reData[1] = Integer.parseInt(inputTime.substring(17, 19));
			}
			return reData;
		}

		public int second() {
			if (inputTime != null && inputTime.length() >= 19) {
				return Integer.parseInt(inputTime.substring(17, 19));
			}
			return -1;
		}

		/**
		 * @return 01-17 13:10
		 */
		public String monthDayHourMinute() {
			if (inputTime != null && inputTime.length() >= 16) {
				return inputTime.substring(5, 16);
			}
			return inputTime;
		}

		/**
		 * @return 01-17
		 */
		public String monthDay() {
			if (inputTime != null && inputTime.length() >= 10) {
				return inputTime.substring(5, 10);
			}
			return inputTime;
		}

		/**
		 * @return 01-17 13:10:00
		 */
		public String monthDayHourMinuteSecond() {
			if (inputTime != null && inputTime.length() >= 16) {
				return inputTime.substring(5, 19);
			}
			return inputTime;
		}

		public long time_long() {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date;
			try {
				date = sdf.parse(inputTime);
			} catch (ParseException e) {
				return -1;
			}
			Calendar gc = getCalendarNow();
			gc.setTime(date);
			return gc.getTimeInMillis();
		}

		public int[] yearMonthDay() {
			int[] reArray = new int[3];
			reArray[0] = gregorianCalendar.get(Calendar.YEAR);
			reArray[1] = gregorianCalendar.get(Calendar.MONTH) + 1;
			reArray[2] = gregorianCalendar.get(Calendar.DAY_OF_MONTH);
			return reArray;
		}

		public int[] yearMonthYesterDay() {
			int[] reArray = new int[3];
			gregorianCalendar.add(Calendar.DAY_OF_MONTH, -1);
			reArray[0] = gregorianCalendar.get(Calendar.YEAR);
			reArray[1] = gregorianCalendar.get(Calendar.MONTH) + 1;
			reArray[2] = gregorianCalendar.get(Calendar.DAY_OF_MONTH);
			return reArray;
		}
	}

	public static WCalendarChart withChart(String dateClick) {
		if (singleton == null) {
			singleton = new UCalendar();
		}
		return singleton.getCalendarChart(dateClick);
	}

	private WCalendarChart getCalendarChart(String dateClick) {
		return new WCalendarChart(dateClick);
	}

	public class WCalendarChart {
		private String mInputTime;
		private GregorianCalendar mGregorianCalendar;

		public WCalendarChart(String dateClick) {
			mGregorianCalendar = getGregorianCalendar(dateClick);
			mInputTime = getString(mGregorianCalendar);
		}

		public String getDate() {
			return mInputTime;
		}

		public ArrayList<String> getList_weekStart() {
			return makeList_week(false);
		}

		public ArrayList<String> getList_weekEnd() {
			return makeList_week(true);
		}

		public ArrayList<String> makeList_week(boolean isLast) {
			int dayGap = mGregorianCalendar.get(Calendar.DAY_OF_WEEK) - 1;
			mGregorianCalendar.add(Calendar.DAY_OF_MONTH, -dayGap);
			if (isLast) {
				setTime_last(mGregorianCalendar);
			} else {
				setTime_zero(mGregorianCalendar);
			}

			ArrayList<String> redata = new ArrayList<String>();
			for (int i = 0; i < 6; i++) {
				redata.add(getString(mGregorianCalendar));
				mGregorianCalendar.add(Calendar.DAY_OF_MONTH, 1);
			}
			redata.add(getString(mGregorianCalendar));
			return redata;
		}

		public ArrayList<String> getLabel_month() {
			mGregorianCalendar.set(Calendar.DAY_OF_MONTH, mGregorianCalendar.getActualMaximum(Calendar.DATE));
			setTime_last(mGregorianCalendar);
			String dateEnd = getString(mGregorianCalendar);
			mGregorianCalendar.set(Calendar.DAY_OF_MONTH, mGregorianCalendar.getActualMinimum(Calendar.DATE));

			ArrayList<String> redata = new ArrayList<String>();
			for (int i = 0; i < 31; i++) {
				if (getString(mGregorianCalendar).equals(dateEnd)) {
					redata.add(getString(mGregorianCalendar));
					return redata;
				} else {
					redata.add(getString(mGregorianCalendar));
					mGregorianCalendar.add(Calendar.DAY_OF_MONTH, 1);
					mInputTime = getString(mGregorianCalendar);
				}
			}
			return redata;
		}

		public ArrayList<String> getLabel_quarter() {
			mGregorianCalendar.set(Calendar.DAY_OF_MONTH, mGregorianCalendar.getActualMaximum(Calendar.DATE));
			setTime_last(mGregorianCalendar);

			int count = 0;
			ArrayList<String> redata = new ArrayList<String>();
			for (int i = 0; i < 12; i++) {
				if (count >= 4) {
					return redata;
				}
				if (mGregorianCalendar.get(Calendar.MONTH) == 2 || mGregorianCalendar.get(Calendar.MONTH) == 5 || mGregorianCalendar.get(Calendar.MONTH) == 8
						|| mGregorianCalendar.get(Calendar.MONTH) == 11) {
					count++;
					redata.add(getString(mGregorianCalendar));
				}
				mGregorianCalendar.add(Calendar.MONTH, 1);
			}
			return redata;
		}
	}

	public static IStringTwo with(String inTime, String outTime) {
		if (singleton == null) {
			singleton = new UCalendar();
		}
		return singleton.getStringTwo(inTime, outTime);
	}

	private IStringTwo getStringTwo(String inTime, String outTime) {
		return new IStringTwo(inTime, outTime);
	}

	public class IStringTwo {
		private String inputTime;
		private String outputTime;
		private GregorianCalendar gregorianInput;
		private GregorianCalendar gregorianOutput;

		private IStringTwo(String inTime, String outTime) {
			this.inputTime = inTime;
			this.outputTime = outTime;
			gregorianInput = getGregorianCalendar(inputTime);
			gregorianOutput = getGregorianCalendar(outputTime);
		}

		/**
		 * 두 시간의 간격을 초로 리턴함.
		 * 
		 * @return 음수는 없고, 절대값을 리턴함
		 */
		public int secondGap() {
			long millis = gregorianInput.getTimeInMillis() - gregorianOutput.getTimeInMillis();
			return Math.abs((int) (millis / 1000.0));
		}

		public boolean isTimeAfter() {
			long millis = gregorianOutput.getTimeInMillis() - gregorianInput.getTimeInMillis();
			if (millis > 0) {
				return true;
			}
			return false;
		}

		public boolean isTimeAfter_secondIgnore() {
			gregorianOutput.set(Calendar.MILLISECOND, 0);
			gregorianInput.set(Calendar.MILLISECOND, 0);
			return isTimeAfter();
		}

		/**
		 * 두 날짜의 간격을 리턴함. 2013-01-01, 2013-01-03
		 * 
		 * @return 절대값 2 리턴
		 */
		public int dayGap() {
			if (inputTime.length() <= 10) {
				inputTime = inputTime + " 00:00:00";
			} else {
				inputTime = inputTime.substring(0, 10) + " 00:00:00";
			}
			if (outputTime.length() <= 10) {
				outputTime = outputTime + " 00:00:00";
			} else {
				outputTime = outputTime.substring(0, 10) + " 00:00:00";
			}
			GregorianCalendar inCal = getGregorianCalendar(inputTime);
			GregorianCalendar outCal = getGregorianCalendar(outputTime);
			long millis = (long) inCal.getTimeInMillis() - (long) outCal.getTimeInMillis();
			return Math.abs((int) (millis / 1000) / (24 * 60 * 60));
		}
	}

	/**
	 * @param 2013-01-17 13:10:59
	 */
	public static IStringAfter withAfter() {
		if (singleton == null) {
			singleton = new UCalendar();
		}
		return singleton.getStringAfter();
	}

	private IStringAfter getStringAfter() {
		return new IStringAfter();
	}

	/**
	 * @param 2013-01-17 13:10:59
	 */
	public static IStringAfter withAfter(String date) {
		if (singleton == null) {
			singleton = new UCalendar();
		}
		return singleton.getStringAfter(date);
	}

	private IStringAfter getStringAfter(String date) {
		return new IStringAfter(date);
	}

	public class IStringAfter {
		private String inputTime;
		private GregorianCalendar gregorianCalendar;

		private IStringAfter() {
			gregorianCalendar = getCalendarNow();
			this.inputTime = getString(gregorianCalendar);
		}

		private IStringAfter(String inputTime) {
			gregorianCalendar = getGregorianCalendar(inputTime);
			this.inputTime = inputTime;
		}

		public String year(int year, int month, int dayOfMonth, int actHour, int actMinute) {
			gregorianCalendar.set(Calendar.HOUR_OF_DAY, actHour);
			gregorianCalendar.set(Calendar.MINUTE, actMinute);
			gregorianCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
			gregorianCalendar.set(Calendar.MONTH, month);

			if (!UCalendar.with(inputTime, getString(gregorianCalendar)).isTimeAfter_secondIgnore()) {
				gregorianCalendar.add(Calendar.YEAR, year);
			}

			return getString(gregorianCalendar);
		}

		public String month(int month, int dayOfMonth, int actHour, int actMinute) {
			gregorianCalendar.set(Calendar.HOUR_OF_DAY, actHour);
			gregorianCalendar.set(Calendar.MINUTE, actMinute);
			gregorianCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

			if (!UCalendar.with(inputTime, getString(gregorianCalendar)).isTimeAfter_secondIgnore()) {
				gregorianCalendar.add(Calendar.MONTH, month);
			}

			return getString(gregorianCalendar);
		}

		public String month(int afterMonth) {
			gregorianCalendar.add(Calendar.MONTH, afterMonth);
			return getString(gregorianCalendar);
		}

		public String week(int dayOfWeek, int actHour, int actMinute) {
			int addDay = 0;
			gregorianCalendar.set(Calendar.HOUR_OF_DAY, actHour);
			gregorianCalendar.set(Calendar.MINUTE, actMinute);
			gregorianCalendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);
			if (!UCalendar.with(inputTime, getString(gregorianCalendar)).isTimeAfter_secondIgnore()) {
				gregorianCalendar.add(Calendar.DAY_OF_MONTH, 7 + addDay);
			}
			return getString(gregorianCalendar);
		}

		public String week_biWeek(int dayOfWeek, int actHour, int actMinute) {
			int addDay = 7;
			gregorianCalendar.set(Calendar.HOUR_OF_DAY, actHour);
			gregorianCalendar.set(Calendar.MINUTE, actMinute);
			gregorianCalendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);
			if (!UCalendar.with(inputTime, getString(gregorianCalendar)).isTimeAfter_secondIgnore()) {
				gregorianCalendar.add(Calendar.DAY_OF_MONTH, 7 + addDay);
			}
			return getString(gregorianCalendar);
		}

		public String day(int day, int actHour, int actMinute) {
			gregorianCalendar.set(Calendar.HOUR_OF_DAY, actHour);
			gregorianCalendar.set(Calendar.MINUTE, actMinute);

			if (!UCalendar.with(inputTime, getString(gregorianCalendar)).isTimeAfter_secondIgnore()) {
				gregorianCalendar.add(Calendar.DAY_OF_MONTH, day);
			}

			return getString(gregorianCalendar);
		}

		public String day(int day) {
			if (inputTime.length() <= 10) {
				inputTime = inputTime + " 00:00:00";
			} else {
				inputTime = inputTime.substring(0, 10) + " 00:00:00";
			}
			gregorianCalendar = getGregorianCalendar(inputTime);
			gregorianCalendar.add(Calendar.DAY_OF_MONTH, day);
			return getString(gregorianCalendar);
		}

		public String day_string(int day) {
			return day(day).substring(0, 10);
		}

		public String hour(int hour, int actMinute) {
			gregorianCalendar.set(Calendar.MINUTE, actMinute);
			gregorianCalendar.add(Calendar.HOUR_OF_DAY, hour);
			return getString(gregorianCalendar);
		}

		public String hour(int hour) {
			gregorianCalendar.add(Calendar.HOUR_OF_DAY, hour);
			return getString(gregorianCalendar);
		}

		public String hour_string(int hour) {
			gregorianCalendar.add(Calendar.HOUR_OF_DAY, hour);
			return getString(gregorianCalendar).substring(11, 16);
		}

		public String minute(int afterMinute) {
			gregorianCalendar.add(Calendar.MINUTE, afterMinute);
			return getString(gregorianCalendar);
		}

		public String second(int afterSecond) {
			gregorianCalendar.add(Calendar.SECOND, afterSecond);
			return getString(gregorianCalendar);
		}
	}

	/**
	 * @param 2013-01-17 13:10:59
	 */
	public static IStringValid withValid() {
		if (singleton == null) {
			singleton = new UCalendar();
		}
		return singleton.getStringValid();
	}

	private IStringValid getStringValid() {
		return new IStringValid();
	}

	public class IStringValid {
		public IStringValid() {
		}

		public boolean isMonth(int month) {
			if (month >= 1 && month <= 12) {
				return true;
			}
			return false;
		}

		public boolean isDayOfMonth(int dayOfMonth) {
			if (dayOfMonth >= 1 && dayOfMonth <= 31) {
				return true;
			}
			return false;
		}

		public boolean isDayOfWeek(int dayOfWeek) {
			if (dayOfWeek >= 1 && dayOfWeek <= 7) {
				return true;
			}
			return false;
		}

		public boolean isHour(int hour) {
			if (hour >= 0 && hour <= 23) {
				return true;
			}
			return false;
		}

		public boolean isMinute(int minute) {
			if (minute >= 0 && minute <= 59) {
				return true;
			}
			return false;
		}
	}

	/**
	 * @param hour
	 * @param minite
	 * @return 13:03
	 */
	public static String getHourMin(int hour, int minite) {
		return new UCalendarUtil().getZeroAdded(hour) + ":" + new UCalendarUtil().getZeroAdded(minite);
	}

	public static String[] getMonthDateInYear(String year) {
		String[] reData = new String[13];
		reData[0] = year + "-01-01";
		reData[1] = year + "-02-01";
		reData[2] = year + "-03-01";
		reData[3] = year + "-04-01";
		reData[4] = year + "-05-01";
		reData[5] = year + "-06-01";
		reData[6] = year + "-07-01";
		reData[7] = year + "-08-01";
		reData[8] = year + "-09-01";
		reData[9] = year + "-10-01";
		reData[10] = year + "-11-01";
		reData[11] = year + "-12-01";
		reData[12] = year + "-13-01";

		return reData;
	}

	public void setTime_zero(GregorianCalendar gregorianCalendar) {
		gregorianCalendar.set(Calendar.HOUR_OF_DAY, 0);
		gregorianCalendar.set(Calendar.MINUTE, 0);
		gregorianCalendar.set(Calendar.SECOND, 0);
		gregorianCalendar.set(Calendar.MILLISECOND, 0);
	}

	public void setTime_last(GregorianCalendar gregorianCalendar) {
		gregorianCalendar.set(Calendar.HOUR_OF_DAY, 23);
		gregorianCalendar.set(Calendar.MINUTE, 59);
		gregorianCalendar.set(Calendar.SECOND, 59);
		gregorianCalendar.set(Calendar.MILLISECOND, 999);
	}
}