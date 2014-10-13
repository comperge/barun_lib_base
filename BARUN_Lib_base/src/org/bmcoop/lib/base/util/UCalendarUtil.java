package org.bmcoop.lib.base.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class UCalendarUtil {

	public String getString(GregorianCalendar gregorianCalendar) {
		return getFormat(gregorianCalendar.get(Calendar.YEAR), gregorianCalendar.get(Calendar.MONTH) + 1, gregorianCalendar.get(Calendar.DAY_OF_MONTH), gregorianCalendar.get(Calendar.HOUR_OF_DAY),
				gregorianCalendar.get(Calendar.MINUTE), gregorianCalendar.get(Calendar.SECOND));
	}

	public String getFormat(int year, int month, int day, int hour, int min, int sec) {
		String reStr = String.valueOf(year) + "-" + getZeroAdded(month) + "-" + getZeroAdded(day) + " " + getZeroAdded(hour) + ":" + getZeroAdded(min) + ":" + getZeroAdded(sec);
		return reStr;
	}

	public String getZeroAdded(int value) {
		if (value < 10) {
			return "0" + String.valueOf(value);
		} else {
			return String.valueOf(value);
		}
	}

	public GregorianCalendar getCalendarNow() {
		Calendar calendar = Calendar.getInstance();
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
		gregorianCalendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
		gregorianCalendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH));
		return gregorianCalendar;
	}

	public GregorianCalendar getGregorianCalendar(String dateString) {
		if (dateString != null && dateString.length() >= 19) {
			GregorianCalendar inCal = getCalendarNow();
			inCal.set(Integer.parseInt(dateString.subSequence(0, 4).toString()), Integer.parseInt(dateString.subSequence(5, 7).toString()) - 1,
					Integer.parseInt(dateString.subSequence(8, 10).toString()), Integer.parseInt(dateString.subSequence(11, 13).toString()),
					Integer.parseInt(dateString.subSequence(14, 16).toString()), Integer.parseInt(dateString.subSequence(17, 19).toString()));
			inCal.set(Calendar.MILLISECOND, 0);
			return inCal;
		} else if (dateString != null && dateString.length() >= 10) {
			GregorianCalendar inCal = getCalendarNow();
			inCal.set(Integer.parseInt(dateString.subSequence(0, 4).toString()), Integer.parseInt(dateString.subSequence(5, 7).toString()) - 1,
					Integer.parseInt(dateString.subSequence(8, 10).toString()));
			inCal.set(Calendar.MILLISECOND, 0);
			return inCal;
		} else if (dateString != null && dateString.length() >= 7) {
			GregorianCalendar inCal = getCalendarNow();
			inCal.set(Integer.parseInt(dateString.subSequence(0, 4).toString()), Integer.parseInt(dateString.subSequence(5, 7).toString()) - 1, 1);
			inCal.set(Calendar.MILLISECOND, 0);
			return inCal;
		} else if (dateString != null && dateString.length() >= 4) {
			GregorianCalendar inCal = getCalendarNow();
			inCal.set(Integer.parseInt(dateString.subSequence(0, 4).toString()), 0, 1);
			inCal.set(Calendar.MILLISECOND, 0);
			return inCal;
		}
		return null;
	}

	public ArrayList<String> getDayOfMonth(GregorianCalendar gregorianCalendar) {
		ArrayList<String> redata = new ArrayList<String>();
		gregorianCalendar.set(Calendar.DAY_OF_MONTH, gregorianCalendar.getActualMinimum(Calendar.DATE));
		int loop = gregorianCalendar.getActualMaximum(Calendar.DATE);
		for (int i = 0; i < loop; i++) {
			redata.add(getString(gregorianCalendar).substring(0, 10));
			gregorianCalendar.add(Calendar.DAY_OF_MONTH, 1);
		}
		return redata;
	}

	public String getDay_first(GregorianCalendar gregorianCalendar, int monthAdd) {
		gregorianCalendar.add(Calendar.MONTH, monthAdd);
		gregorianCalendar.set(Calendar.DAY_OF_MONTH, gregorianCalendar.getActualMinimum(Calendar.DATE));
		gregorianCalendar.set(Calendar.HOUR_OF_DAY, 0);
		gregorianCalendar.set(Calendar.MINUTE, 0);
		gregorianCalendar.set(Calendar.SECOND, 0);
		return getString(gregorianCalendar);
	}

	public String getDay_last(GregorianCalendar gregorianCalendar) {
		gregorianCalendar.set(Calendar.DAY_OF_MONTH, gregorianCalendar.getActualMaximum(Calendar.DATE));
		gregorianCalendar.set(Calendar.HOUR_OF_DAY, 23);
		gregorianCalendar.set(Calendar.MINUTE, 59);
		gregorianCalendar.set(Calendar.SECOND, 59);
		return getString(gregorianCalendar);
	}

	public int getWeekOfYear(GregorianCalendar gregorianCalendar) {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}
}