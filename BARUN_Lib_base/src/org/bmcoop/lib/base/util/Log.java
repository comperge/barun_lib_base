package org.bmcoop.lib.base.util;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

import org.bmcoop.lib.base.BuildConfig;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;


/**
 * @author djrain
 * 
 */
public class Log {

	private static SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault());
	private static String func = new String();
	private static String locator = new String();
	private static String PREFIX = ">>";

	public static void l2(Object... args) {
		if (!BuildConfig.DEBUG)
			return;
		Exception e = new Exception();
		location2(e);
		android.util.Log.e(func, PREFIX + "f :" + _MESSAGE(args) + locator);
	}

	public static void ln(int n, Object... args) {
		if (!BuildConfig.DEBUG)
			return;
		Exception e = new Exception();
		locationN(n, e);
		android.util.Log.e(func, PREFIX + "f :" + _MESSAGE(args) + locator);
	}

	public static void e(Object... args) {
		if (!BuildConfig.DEBUG)
			return;
		Exception e = new Exception();
		location1(e);
		android.util.Log.e(func, PREFIX + "!!:" + _MESSAGE(args) + locator);
		e.printStackTrace();
	}

	public static void w(Object... args) {
		if (!BuildConfig.DEBUG)
			return;
		Exception e = new Exception();
		location1(e);
		android.util.Log.w(func, PREFIX + "! :" + _MESSAGE(args) + locator);
		e.printStackTrace();
	}

	public static void l() {
		if (!BuildConfig.DEBUG)
			return;
		Exception e = new Exception();
		location1(e);
		android.util.Log.e(func, PREFIX + "f :" + locator);
	}

	public static void f(Object... args) {
		Exception e = new Exception();
		location1(e);
		android.util.Log.e(func, PREFIX + "f :" + _MESSAGE(args) + locator);
	}

	public static void l(Object... args) {
		if (!BuildConfig.DEBUG)
			return;
		Exception e = new Exception();
		location1(e);
		android.util.Log.e(func, PREFIX + "f :" + _MESSAGE(args) + locator);
	}

	public static void ll(Object... args) {
		if (!BuildConfig.DEBUG)
			return;

		Exception e = new Exception();
		location1(e);

		String log = _MESSAGE(args);
		android.util.Log.e(func, PREFIX + "ll : >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + locator);
		int a = 200;
		int i = 0;
		for (i = 0; i < log.length(); i += a)
			android.util.Log.e(func, log.substring(i, Math.min(log.length(), i + a)));

		android.util.Log.e(func, PREFIX + "ll : <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<" + locator);
	}

	public static void ii(Object... args) {
		if (!BuildConfig.DEBUG)
			return;

		Exception e = new Exception();
		location1(e);

		String log = _MESSAGE(args);
		android.util.Log.i(func, PREFIX + "ll : >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + locator);
		int a = 200;
		int i = 0;
		for (i = 0; i < log.length(); i += a)
			android.util.Log.i(func, log.substring(i, Math.min(log.length(), i + a)));

		android.util.Log.i(func, PREFIX + "ll : <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<" + locator);
	}

	private static boolean DT = false;

	public static void dt(Object... args) {
		if (!BuildConfig.DEBUG)
			return;

		Exception e = new Exception();
		location1(e);
		DT = true;
		String log = _MESSAGE(args);
		DT = false;
		android.util.Log.e(func, PREFIX + "dt :" + log + locator);
	}

	public static void c(Object... args) {
		if (!BuildConfig.DEBUG)
			return;
		Exception e = new Exception();
		location1(e);
		android.util.Log.e(func, PREFIX + "? :" + _MESSAGE(args) + locator);
		e.printStackTrace();
	}

	public static void d(Object... args) {
		if (!BuildConfig.DEBUG)
			return;
		Exception e = new Exception();
		location1(e);
		android.util.Log.e(func, PREFIX + "d :" + _MESSAGE(args) + locator);
	}

	public static void v(Object... args) {
		if (!BuildConfig.DEBUG)
			return;
		Exception e = new Exception();
		location1(e);
		android.util.Log.e(func, PREFIX + "v :" + _MESSAGE(args) + locator);
	}

	public static void i(Object... args) {
		if (!BuildConfig.DEBUG)
			return;
		Exception e = new Exception();
		location1(e);
		android.util.Log.i(func, PREFIX + "i :" + _MESSAGE(args) + locator);
	}

	public static String _MESSAGE(Object... args) {
		if (args == null)
			return "null[]";

		StringBuffer sb = new StringBuffer();
		for (Object object : args) {
			sb.append(",");

			String string = "";
			if (object == null)
				string = "null";
			else if (object instanceof Class)
				string = _DUMP((Class<?>) object);
			else if (object instanceof Cursor)
				string = _DUMP((Cursor) object);
			else if (object instanceof Intent)
				string = _DUMP((Intent) object);
			else if (object instanceof Bundle)
				string = _DUMP((Bundle) object);
			else if (object instanceof Uri)
				string = _DUMP((Uri) object);
			else if (DT && object instanceof Long)
				string = _DUMP((Long) object);
			else if (object instanceof Exception)
				string = _DUMP((Exception) object);
			else
				string = object.toString();

			sb.append(string);
		}
		return sb.toString();
	}

	private static String _DUMP(Cursor c) {
		if (c == null)
			return null;

		StringBuilder sb = new StringBuilder();
		int count = c.getCount();
		sb.append("<" + count + ">");

		try {
			String[] columns = c.getColumnNames();
			sb.append(Arrays.toString(columns));
			sb.append("\n");
		} catch (Exception e) {
		}

		boolean isBeforeFirst = c.isBeforeFirst();
		int countColumns = c.getColumnCount();
		while (c.moveToNext()) {
			for (int i = 0; i < countColumns; i++) {
				try {
					sb.append(c.getString(i) + ",");
				} catch (Exception e) {
					sb.append("BLOB,");
				}
			}
			sb.append("\n");
			if (isBeforeFirst)
				break;
		}
		return sb.toString();
	}

	public static void cursor(Cursor c) {
		if (c == null)
			return;

		StringBuilder sb = new StringBuilder();
		int count = c.getCount();
		sb.append("<" + count + ">");

		try {
			String[] columns = c.getColumnNames();
			sb.append(Arrays.toString(columns));
			Log.l2(sb);
			sb.setLength(0);
		} catch (Exception e) {
		}

		boolean isBeforeFirst = c.isBeforeFirst();
		int countColumns = c.getColumnCount();
		while (c.moveToNext()) {
			for (int i = 0; i < countColumns; i++) {
				try {
					sb.append(c.getString(i) + ",");
				} catch (Exception e) {
					sb.append("BLOB,");
				}
			}
			Log.l2(sb);
			sb.setLength(0);
			if (!isBeforeFirst)
				break;
		}
	}

	public static void cursor(Context context, Uri uri) {
		Cursor c = context.getContentResolver().query(uri, null, null, null, null);
		if (c == null)
			return;
		cursor(c);
		c.close();
	}

	private static String _DUMP(Exception e) {
		e.printStackTrace();
		return e.getMessage();
	}

	private static String _DUMP(Bundle bundle) {
		StringBuffer sb = new StringBuffer();

		final Set<String> keys = bundle.keySet();
		String type = null;
		String value = null;
		for (String key : keys) {
			final Object o = bundle.get(key);
			if (o == null) {
				type = "null";
				value = "null";
			} else {
				type = o.getClass().getSimpleName();
				value = o.toString();
			}
			sb.append("\r\n");
			sb.append(key + "," + type + "," + value);
		}

		return sb.toString();
	}

	private static String _DUMP(Class<?> cls) {
		if (cls == null)
			return "";
		return cls.getSimpleName() + ((cls.getSuperclass() != null) ? (">>" + cls.getSuperclass().getSimpleName()) : "");
	}

	private static String _DUMP(Uri uri) {
		return uri.toString();
		// StringBuffer sb = new StringBuffer();
		// sb.append("\r\n Uri                       ");
		// sb.append(uri.toString());
		// sb.append("\r\n Scheme                    ");
		// sb.append(uri.getScheme() != null ? uri.getScheme().toString() :
		// "null");
		// sb.append("\r\n Host                      ");
		// sb.append(uri.getHost() != null ? uri.getHost().toString() : "null");
		// sb.append("\r\n Port                      ");
		// sb.append(uri.getPort());
		// sb.append("\r\n Path                      ");
		// sb.append(uri.getPath() != null ? uri.getPath().toString() : "null");
		// sb.append("\r\n Query                     ");
		// sb.append(uri.getQuery() != null ? uri.getQuery().toString() :
		// "null");
		// sb.append("\r\n");
		// sb.append("\r\n Fragment                  ");
		// sb.append(uri.getFragment() != null ? uri.getFragment().toString() :
		// "null");
		// sb.append("\r\n LastPathSegment           ");
		// sb.append(uri.getLastPathSegment() != null ?
		// uri.getLastPathSegment().toString() : "null");
		// sb.append("\r\n SchemeSpecificPart        ");
		// sb.append(uri.getSchemeSpecificPart() != null ?
		// uri.getSchemeSpecificPart().toString() : "null");
		// sb.append("\r\n UserInfo                  ");
		// sb.append(uri.getUserInfo() != null ? uri.getUserInfo().toString() :
		// "null");
		// sb.append("\r\n PathSegments              ");
		// sb.append(uri.getPathSegments() != null ?
		// uri.getPathSegments().toString() : "null");
		// sb.append("\r\n Authority                 ");
		// sb.append(uri.getAuthority() != null ? uri.getAuthority().toString()
		// : "null");
		// sb.append("\r\n");
		// sb.append("\r\n EncodedAuthority          ");
		// sb.append(uri.getEncodedAuthority() != null ?
		// uri.getEncodedAuthority().toString() : "null");
		// sb.append("\r\n EncodedPath               ");
		// sb.append(uri.getEncodedPath() != null ?
		// uri.getEncodedPath().toString() : "null");
		// sb.append("\r\n EncodedQuery              ");
		// sb.append(uri.getEncodedQuery() != null ?
		// uri.getEncodedQuery().toString() : "null");
		// sb.append("\r\n EncodedFragment           ");
		// sb.append(uri.getEncodedFragment() != null ?
		// uri.getEncodedFragment().toString() : "null");
		// sb.append("\r\n EncodedSchemeSpecificPart ");
		// sb.append(uri.getEncodedSchemeSpecificPart() != null ?
		// uri.getEncodedSchemeSpecificPart().toString() : "null");
		// sb.append("\r\n EncodedUserInfo           ");
		// sb.append(uri.getEncodedUserInfo() != null ?
		// uri.getEncodedUserInfo().toString() : "null");
		// sb.append("\r\n");
		// return sb.toString();
	}

	private static String _DUMP(long milliseconds) {
		return "<" + milliseconds + "," + sf.format(new Date(milliseconds)) + ">";
	}

	private static String _DUMP(Intent intent) {
		StringBuffer sb = new StringBuffer();

		sb.append("\r\n Action     ");
		sb.append(intent.getAction() != null ? intent.getAction().toString() : "null");
		sb.append("\r\n Data       ");
		sb.append(intent.getData() != null ? intent.getData().toString() : "null");
		sb.append("\r\n Categories ");
		sb.append(intent.getCategories() != null ? intent.getCategories().toString() : "null");
		sb.append("\r\n Type       ");
		sb.append(intent.getType() != null ? intent.getType().toString() : "null");
		sb.append("\r\n Scheme     ");
		sb.append(intent.getScheme() != null ? intent.getScheme().toString() : "null");
		sb.append("\r\n Package    ");
		sb.append(intent.getPackage() != null ? intent.getPackage().toString() : "null");
		sb.append("\r\n Component  ");
		sb.append(intent.getComponent() != null ? intent.getComponent().toString() : "null");

		if (intent.getExtras() != null)
			sb.append(_DUMP(intent.getExtras()));

		sb.append("\r\n");
		return sb.toString();
	}

	public static String location() {
		return locationN(1, new Exception());
	}

	private static String location1(final Exception e) {
		return locationN(1, e);
	}

	private static String location2(final Exception e) {
		return locationN(2, e);
	}

	public static String locationN(int n, final Exception e) {
		String funcStack = e.getStackTrace()[n].toString();

		int posJava = funcStack.lastIndexOf("(");
		int posFunc = funcStack.lastIndexOf(".", posJava - 1);
		int posClass = funcStack.lastIndexOf(".", posFunc - 1);
		func = funcStack.substring(posClass + 1, posJava);// classfuncName
		locator = ":at " + funcStack.substring(posJava);// javaName
		return locator;
	}

	public static void t(Context context, Object... args) {
		Log.l(args);
		Toast.makeText(context, _MESSAGE(args), Toast.LENGTH_SHORT).show();
	}

	public static void h(byte[] bytearray) {
		try {
			Log.l2("<" + bytearray.length + ">", h2s(bytearray));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	private static final char[] HEX_CHARS = "0123456789abcdef".toCharArray();

	public static String h2s(byte[] bytearray) throws UnsupportedEncodingException {
		if (bytearray == null)
			throw new NullPointerException();

		char[] chars = new char[2 * bytearray.length];
		for (int i = 0; i < bytearray.length; ++i) {
			chars[2 * i] = HEX_CHARS[(bytearray[i] & 0xF0) >>> 4];
			chars[2 * i + 1] = HEX_CHARS[bytearray[i] & 0x0F];
		}
		return new String(chars);
	}

	public static byte[] s2h(String string_hex_format) {
		byte[] bytes = new byte[string_hex_format.length() / 2];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) Integer.parseInt(string_hex_format.substring(2 * i, 2 * i + 2), 16);
		}
		return bytes;
	}

	// LAST_ERROR_500
	private static String LAST_ERROR_500 = "";

	public static void SL(String error) {
		LAST_ERROR_500 = error + LAST_ERROR_500.substring(0, 500);
	}

	public static String GL() {
		return LAST_ERROR_500;
	}

	public static void CL() {
		LAST_ERROR_500 = "";
	}

	static long s = 0;
	private static long e;

	public static void _s() {
		s = System.currentTimeMillis();
		e = s;
		Log.l2(String.format("%20d%20d%20d", s, e, e - s));
	}

	public static void _tic() {
		e = System.currentTimeMillis();
		Log.l2(String.format("%20d%20d%20d", s, e, e - s));
		s = e;
	}

	public static void _tic(String log) {
		e = System.currentTimeMillis();
		Log.l2(log, String.format("%20d%20d%20d", s, e, e - s));
		s = e;
	}

	public static long _length() {
		e = System.currentTimeMillis();
		Log.l2(String.format("%20d%20d%20d", s, e, e - s));
		long _tic_length = e - s;
		s = e;
		return _tic_length;
	}
}
