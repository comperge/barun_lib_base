package org.bmcoop.lib.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.annotation.SuppressLint;
import android.text.format.DateFormat;

@SuppressLint("SimpleDateFormat")
public class DatatimeUtil {
	public static final class DT extends DatatimeUtil {

	}

	public static final long TIMEGAP = +32400000L;
	public static final long DAY1 = 86400000L;

	public static long today() {
		return ST(System.currentTimeMillis());
	}

	public static String now_yyyymmdd() {
		return DatatimeUtil.yyyymmdd(System.currentTimeMillis());
	}

	public static String now_yyyymmddhhmmss() {
		return DatatimeUtil.yyyymmddhhmmss(System.currentTimeMillis());
	}

	public static String yyyymmdd(long milliseconds) {
		return DatatimeUtil.format(milliseconds, DatatimeUtil.yyyymmdd);
	}

	public static String yyyymmddhhmmss(long milliseconds) {
		return DatatimeUtil.format(milliseconds, DatatimeUtil.yyyymmddhhmmss);
	}

	public static String mmddhhmmss__Now() {
		return DatatimeUtil.format(System.currentTimeMillis(), DatatimeUtil.mmddhhmmss__);
	}

	public static long ST(long milliseconds) {
		return ((milliseconds + TIMEGAP) / DAY1 * DAY1) - TIMEGAP;
	}

	public static long stripTime(long milliseconds) {
		return ST(milliseconds);
	}

	public static long SD(long milliseconds) {
		milliseconds = ST(milliseconds);
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(milliseconds);
		c.set(Calendar.DATE, 1);
		return c.getTimeInMillis();
	}

	public static long stripDay(long milliseconds) {
		return SD(milliseconds);
	}

	public static long move(long milliseconds, int field, int distance) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(milliseconds);
		c.add(field, distance);
		return c.getTimeInMillis();
	}

	public static String format(long milliseconds, SimpleDateFormat to) {
		try {
			return to.format(new Date(milliseconds));
		} catch (Exception e) {
//			e.printStackTrace();
			return "";
		}
	}

	public static String format(String date, SimpleDateFormat from, SimpleDateFormat to) {
		try {
			return to.format(from.parse(date));
		} catch (ParseException e) {
//			e.printStackTrace();
		}
		return date;
	}

	public static CharSequence format(long datetime, CharSequence format_adayago, CharSequence format_today) {
		if (ST(datetime) == ST(System.currentTimeMillis()))
			return DateFormat.format(format_today, datetime);
		return DateFormat.format(format_adayago, datetime);
	}

	public static long parse(String date, SimpleDateFormat from) {
		try {
			return from.parse(date).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0L;
	}

	// 달의 몇번째 주?
	public static int weekofmonth(long milliseconds) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(milliseconds);
		return c.get(Calendar.DAY_OF_WEEK_IN_MONTH);
	}

	public static final SimpleDateFormat yyyymm = new SimpleDateFormat("yyyyMM");

	public static final SimpleDateFormat yyyy = new SimpleDateFormat("yyyy");
	public static final SimpleDateFormat yyyy__ = new SimpleDateFormat("yyyy년");

	public static final SimpleDateFormat weekofmonth = new SimpleDateFormat("W");

	public static final SimpleDateFormat mmdd__ = new SimpleDateFormat("MM월dd일");
	public static final SimpleDateFormat mmdd_2 = new SimpleDateFormat("MM.dd");

	public static final SimpleDateFormat mm = new SimpleDateFormat("MM");
	public static final SimpleDateFormat m__ = new SimpleDateFormat("M월");
	public static final SimpleDateFormat mm__ = new SimpleDateFormat("MM월");

	public static final SimpleDateFormat dd = new SimpleDateFormat("dd");
	public static final SimpleDateFormat dd__ = new SimpleDateFormat("dd일");

	public static final SimpleDateFormat emdhs = new SimpleDateFormat("(E)M/d HH:mm");
	public static final SimpleDateFormat mdehs = new SimpleDateFormat("M/d(E) HH:mm");

	public static final SimpleDateFormat mde = new SimpleDateFormat("M/d(E)");
	public static final SimpleDateFormat dayofweek = new SimpleDateFormat("E");
	public static final SimpleDateFormat dayofweek__ = new SimpleDateFormat("E요일");

	public static final SimpleDateFormat yyyy_mmdd = new SimpleDateFormat("yyyy\nMM.dd");
	public static final SimpleDateFormat yyyy__mmdd = new SimpleDateFormat("yyyy.\nMM.dd");

	public static final SimpleDateFormat yyyymmddE__ = new SimpleDateFormat("yyyy년 MM월 dd일 E요일");

	public static final SimpleDateFormat yyyymmdd = new SimpleDateFormat("yyyyMMdd");
	public static final SimpleDateFormat yyyymmdd_ = new SimpleDateFormat("yyyy/MM/dd");
	public static final SimpleDateFormat yyyymmdd__ = new SimpleDateFormat("yyyy년 MM월 dd일");
	public static final SimpleDateFormat yyyymmdd_1 = new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat yyyymmdd_2 = new SimpleDateFormat("yyyy.MM.dd");

	public static final SimpleDateFormat yyyymmddahmm_ = new SimpleDateFormat("yyyy/MM/dd a hh:mm");

	public static final SimpleDateFormat yyyymmddhhmm__ = new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm");

	public static final SimpleDateFormat yyyymmddhhmmss = new SimpleDateFormat("yyyyMMddHHmmss");
	public static final SimpleDateFormat yyyymmddhhmmss_ = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	public static final SimpleDateFormat yyyymmddhhmmss_1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat yyyymmddhhmmss_2 = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

	public static final SimpleDateFormat mmddhhmmss__ = new SimpleDateFormat("MM월dd일 HH:mm");

	public static final SimpleDateFormat ahhmm = new SimpleDateFormat("a hh:mm");
	public static final SimpleDateFormat hhmmss = new SimpleDateFormat("HHmmss");
	public static final SimpleDateFormat mmss = new SimpleDateFormat("mm:ss");
	public static final SimpleDateFormat ms = new SimpleDateFormat("m:s");

	public static int getDayOfWeek(long milliseconds) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(milliseconds);
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	public static int distanceBetween(long milliseconds_first, long milliseconds_end, int field) {
		if (milliseconds_end < milliseconds_first)
			throw new IllegalArgumentException("must milliseconds_first < milliseconds_end");

		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		int sy = start.get(Calendar.YEAR);
		int ey = end.get(Calendar.YEAR);
		int sm = start.get(Calendar.MONTH);
		int em = end.get(Calendar.MONTH);

		switch (field) {
			case Calendar.YEAR :
				return ey - sy + 1;
			case Calendar.MONTH :
				return (12 - sm) + (em) + ((ey > sy) ? ((ey - sy - 1) * 12) : 0);
			case Calendar.DAY_OF_WEEK :
				return (int) ((milliseconds_end - milliseconds_first) / (86400000L * 7L));
			case Calendar.DATE :
				return (int) ((milliseconds_end - milliseconds_first) / 86400000L);
			default :
				break;
		}

		return 0;
	}

	public static int distanceDay(long s, long e) {
		return (int) ((ST(e) - ST(s)) / 86400000L);

	}
}
