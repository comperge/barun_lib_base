package org.bmcoop.lib.base.util;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;

public final class UJson {
	private static UJson singleton;

	public static IJsonResult with(String strResult) {
		if (singleton == null) {
			singleton = new UJson();
		}
		return singleton.getJsonResult(strResult);
	}

	private IJsonResult getJsonResult(String strResult) {
		return new IJsonResult(strResult);
	}

	public class IJsonResult {
		private String mStrInput;
		private JSONObject mJsonObject;
		private JSONArray mJsonArray;

		public IJsonResult(String strResult) {
			mStrInput = strResult;
			try {
				mJsonObject = new JSONObject(strResult);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		public IJsonResult forInto(String key) {
			try {
				mJsonObject = (JSONObject) mJsonObject.get(key);
			} catch (Exception e) {
				//				e.printStackTrace();
			}
			try {
				mJsonArray = (JSONArray) mJsonObject.get(key);
			} catch (Exception e) {
				//				e.printStackTrace();
			}

			return this;
		}

		public String getString() {
			if (mJsonObject != null) {
				return mJsonObject.toString();
			}
			return null;
		}

		public ArrayList<ContentValues> getContentList() {
			if (mJsonArray != null) {
				try {
					return getList(mJsonArray);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return null;
		}
	}
	
	public ArrayList<ContentValues> getList(JSONArray jsonArray) throws JSONException {
		ArrayList<ContentValues> reData = new ArrayList<ContentValues>();
		for (int i = 0; i < jsonArray.length(); i++) {
			reData.add(getContextValues(jsonArray.getJSONObject(i)));
		}
		return reData;
	}

	public ContentValues getContextValues(JSONObject jsonObject) throws JSONException {
		ContentValues contValue = new ContentValues();
		Iterator iter = jsonObject.keys();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			String value;
			value = jsonObject.getString(key);
			contValue.put(key, value);
		}
		return contValue;
	}
}
