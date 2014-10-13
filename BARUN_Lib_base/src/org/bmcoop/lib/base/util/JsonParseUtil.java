package org.bmcoop.lib.base.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonParseUtil {
	public static final class JU extends JsonParseUtil {

	}

	public static String getString(String json, Object... paths) throws JSONException {
		Object j = getJson(json, paths);
		if (paths[paths.length - 1] instanceof String)
			return ((JSONObject) j).getString((String) paths[paths.length - 1]);
		if (paths[paths.length - 1] instanceof Integer)
			return ((JSONArray) j).getString((Integer) paths[paths.length - 1]);
		throw new NullPointerException("what the json");
	}
	public static int getInt(String json, Object... paths) throws JSONException {
		Object j = getJson(json, paths);
		if (paths[paths.length - 1] instanceof String)
			return ((JSONObject) j).getInt((String) paths[paths.length - 1]);
		if (paths[paths.length - 1] instanceof Integer)
			return ((JSONArray) j).getInt((Integer) paths[paths.length - 1]);
		throw new NullPointerException("what the json");
	}
	public static double getDouble(String json, Object... paths) throws JSONException {
		Object j = getJson(json, paths);
		if (paths[paths.length - 1] instanceof String)
			return ((JSONObject) j).getDouble((String) paths[paths.length - 1]);
		if (paths[paths.length - 1] instanceof Integer)
			return ((JSONArray) j).getDouble((Integer) paths[paths.length - 1]);
		throw new NullPointerException("what the json");
	}
	public static long getLong(String json, Object... paths) throws JSONException {
		Object j = getJson(json, paths);
		if (paths[paths.length - 1] instanceof String)
			return ((JSONObject) j).getLong((String) paths[paths.length - 1]);
		if (paths[paths.length - 1] instanceof Integer)
			return ((JSONArray) j).getLong((Integer) paths[paths.length - 1]);
		throw new NullPointerException("what the json");
	}
	public static boolean getBoolean(String json, Object... paths) throws JSONException {
		Object j = getJson(json, paths);
		if (paths[paths.length - 1] instanceof String)
			return ((JSONObject) j).getBoolean((String) paths[paths.length - 1]);
		if (paths[paths.length - 1] instanceof Integer)
			return ((JSONArray) j).getBoolean((Integer) paths[paths.length - 1]);
		throw new NullPointerException("what the json");
	}
	public static boolean isNull(String json, Object... paths) throws JSONException {
		Object j = getJson(json, paths);
		if (paths[paths.length - 1] instanceof String)
			return ((JSONObject) j).isNull((String) paths[paths.length - 1]);
		if (paths[paths.length - 1] instanceof Integer)
			return ((JSONArray) j).isNull((Integer) paths[paths.length - 1]);
		throw new NullPointerException("what the json");
	}

	private static Object getJson(String json, Object[] paths) throws JSONException {
		if (json.charAt(0) != '[' && json.charAt(0) != '{')
			throw new IllegalArgumentException("json must start '[' '{'");

		Object j = null;
		if (json.charAt(0) == '[')
			j = new JSONArray(json);

		if (json.charAt(0) == '{')
			j = new JSONObject(json);

		int count = paths.length;
		int i;
		for (i = 0; i < count - 1; i++) {
			if (paths[i] instanceof String && paths[i + 1] instanceof String)
				j = ((JSONObject) j).getJSONObject((String) paths[i]);
			if (paths[i] instanceof String && paths[i + 1] instanceof Integer)
				j = ((JSONObject) j).getJSONArray((String) paths[i]);
			if (paths[i] instanceof Integer && paths[i + 1] instanceof String)
				j = ((JSONArray) j).getJSONObject((Integer) paths[i]);
			if (paths[i] instanceof Integer && paths[i + 1] instanceof Integer)
				j = ((JSONArray) j).getJSONArray((Integer) paths[i]);
		}

		return j;
	}
}
