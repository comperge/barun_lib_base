package org.bmcoop.lib.base.util;

import java.io.Serializable;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class UActivity {
	public static final String KEY_Refresh = "resultRefresh";
	private static UActivity SINGLE_U;

	private UActivity() {
	}
	
	public static WActivity withSender(Context pCon, Class<?> _class) {
		if (SINGLE_U == null) {
			SINGLE_U = new UActivity();
		}
		return SINGLE_U.get(pCon, _class);
	}

	private WActivity get(Context pCon, Class<?> _class) {
		return new WActivity(pCon, _class);
	}

	public static WActivity withReceiver(Context pCon) {
		if (SINGLE_U == null) {
			SINGLE_U = new UActivity();
		}
		return SINGLE_U.get(pCon);
	}

	private WActivity get(Context pCon) {
		return new WActivity(pCon);
	}

	public static WActivity withResult(Intent pIntent) {
		if (SINGLE_U == null) {
			SINGLE_U = new UActivity();
		}
		return SINGLE_U.get(pIntent);
	}

	private WActivity get(Intent pIntent) {
		return new WActivity(pIntent);
	}

	public class WActivity {
		private Intent mIntent;
		private Activity mActivity;
		private final String Id = "Id";
		private final String Index = "Index";
		private final String Type = "Type";
		private final String Entity = "Entity";
		private final String Int1 = "Int1";
		private final String Int2 = "Int2";
		private final String Int3 = "Int3";
		private final String Int4 = "Int4";
		private final String Int5 = "Int5";
		private final String Int6 = "Int6";
		private final String String1 = "String1";
		private final String String2 = "String2";
		private final String String3 = "String3";
		private final String Long1 = "Long1";
		private final String Long2 = "Long2";
		private final String Long3 = "Long3";
		private final String Float1 = "Float1";
		private final String Float2 = "Float2";
		private final String Float3 = "Float3";
		private final String Double1 = "Double1";
		private final String Double2 = "Double2";
		private final String Double3 = "Double3";
		private final String Boolean1 = "Boolean1";
		private final String Boolean2 = "Boolean2";
		private final String Boolean3 = "Boolean3";

		private WActivity(Context pCon, Class<?> _class) {
			mIntent = new Intent(pCon, _class);
			mActivity = (Activity) pCon;
		}

		private WActivity(Context pCon) {
			mActivity = (Activity) pCon;
			mIntent = mActivity.getIntent();
		}

		private WActivity(Intent pIntent) {
			mIntent = pIntent;
			mIntent.putExtra(KEY_Refresh, false);
		}

		public WActivity forRefresh() {
			mIntent.putExtra(KEY_Refresh, true);
			mActivity.setResult(Activity.RESULT_OK, mIntent);
			return this;
		}

		public void forStartActivity() {
			mActivity.startActivity(mIntent);
		}

		public void forStartActivity(int requestCode) {
			mActivity.startActivityForResult(mIntent, requestCode);
		}

		public WActivity addId(int id) {
			mIntent.putExtra(Id, id);
			return this;
		}

		/**
		 * @return default -1
		 */
		public int getId() {
			return mIntent.getIntExtra(Id, -1);
		}

		public WActivity addIndex(int index) {
			mIntent.putExtra(Index, index);
			return this;
		}

		/**
		 * @return default -1
		 */
		public int getIndex() {
			return mIntent.getIntExtra(Index, -1);
		}

		public WActivity addType(int type) {
			mIntent.putExtra(Type, type);
			return this;
		}

		/**
		 * @return default -1
		 */
		public int getType() {
			return mIntent.getIntExtra(Type, -1);
		}

		public int getType(int defaultValue) {
			return mIntent.getIntExtra(Type, defaultValue);
		}
		
		public Object getSerial() {
			return mIntent.getSerializableExtra(Entity);
		}

		public WActivity addSerial(Serializable enty) {
			mIntent.putExtra(Entity, enty);
			return this;
		}

		public WActivity addInt1(int int1) {
			mIntent.putExtra(Int1, int1);
			return this;
		}

		public int getInt1() {
			return mIntent.getIntExtra(Int1, 0);
		}

		public int getInt1(int defaultValue) {
			return mIntent.getIntExtra(Int1, defaultValue);
		}

		public WActivity addInt2(int int2) {
			mIntent.putExtra(Int2, int2);
			return this;
		}

		public int getInt2() {
			return mIntent.getIntExtra(Int2, 0);
		}

		public int getInt2(int defaultValue) {
			return mIntent.getIntExtra(Int2, defaultValue);
		}

		public WActivity addInt3(int int3) {
			mIntent.putExtra(Int3, int3);
			return this;
		}

		public int getInt3() {
			return mIntent.getIntExtra(Int3, 0);
		}

		public int getInt3(int defaultValue) {
			return mIntent.getIntExtra(Int3, defaultValue);
		}

		public WActivity addInt4(int int4) {
			mIntent.putExtra(Int4, int4);
			return this;
		}

		public int getInt4() {
			return mIntent.getIntExtra(Int4, 0);
		}

		public int getInt4(int defaultValue) {
			return mIntent.getIntExtra(Int4, defaultValue);
		}

		public WActivity addInt5(int int5) {
			mIntent.putExtra(Int5, int5);
			return this;
		}

		public int getInt5() {
			return mIntent.getIntExtra(Int5, 0);
		}

		public int getInt5(int defaultValue) {
			return mIntent.getIntExtra(Int5, defaultValue);
		}

		public WActivity addInt6(int int6) {
			mIntent.putExtra(Int6, int6);
			return this;
		}

		public int getInt6() {
			return mIntent.getIntExtra(Int6, 0);
		}

		public int getInt6(int defaultValue) {
			return mIntent.getIntExtra(Int6, defaultValue);
		}

		public WActivity addString1(String string1) {
			mIntent.putExtra(String1, string1);
			return this;
		}

		public String getString1() {
			return mIntent.getStringExtra(String1);
		}

		public WActivity addString2(String string2) {
			mIntent.putExtra(String2, string2);
			return this;
		}

		public String getString2() {
			return mIntent.getStringExtra(String2);
		}

		public WActivity addString3(String string3) {
			mIntent.putExtra(String3, string3);
			return this;
		}

		public String getString3() {
			return mIntent.getStringExtra(String3);
		}

		public WActivity addLong1(String long1) {
			mIntent.putExtra(Long1, long1);
			return this;
		}

		public long getLong1() {
			return mIntent.getLongExtra(Long1, 0);
		}

		public WActivity addLong2(Long long2) {
			mIntent.putExtra(Long2, long2);
			return this;
		}

		public long getLong() {
			return mIntent.getLongExtra(Long2, 0);
		}

		public WActivity addLong3(Long long3) {
			mIntent.putExtra(Long3, long3);
			return this;
		}

		public long getLong3() {
			return mIntent.getLongExtra(Long3, 0);
		}

		public WActivity addFloat1(Float float1) {
			mIntent.putExtra(Float1, float1);
			return this;
		}

		public float getFloat1() {
			return mIntent.getFloatExtra(Float1, 0);
		}

		public WActivity addFloat2(Float float2) {
			mIntent.putExtra(Float2, float2);
			return this;
		}

		public float getFloat() {
			return mIntent.getFloatExtra(Float2, 0);
		}

		public WActivity addFloat3(Float float3) {
			mIntent.putExtra(Float3, float3);
			return this;
		}

		public float getFloat3() {
			return mIntent.getFloatExtra(Float3, 0);
		}

		public WActivity addDouble1(Double double1) {
			mIntent.putExtra(Double1, double1);
			return this;
		}

		public double getDouble1() {
			return mIntent.getDoubleExtra(Double1, 0);
		}

		public WActivity addDouble2(Double double2) {
			mIntent.putExtra(Double2, double2);
			return this;
		}

		public double getDouble() {
			return mIntent.getDoubleExtra(Double2, 0);
		}

		public WActivity addDouble3(Double double3) {
			mIntent.putExtra(Double3, double3);
			return this;
		}

		public double getDouble3() {
			return mIntent.getDoubleExtra(Double3, 0);
		}

		public WActivity addBoolean1(Boolean boolean1) {
			mIntent.putExtra(Boolean1, boolean1);
			return this;
		}

		public boolean getBoolean1() {
			return mIntent.getBooleanExtra(Boolean1, false);
		}

		public WActivity addBoolean2(Boolean boolean2) {
			mIntent.putExtra(Boolean2, boolean2);
			return this;
		}

		public boolean getBoolean() {
			return mIntent.getBooleanExtra(Boolean2, false);
		}

		public WActivity addBoolean3(Boolean boolean3) {
			mIntent.putExtra(Boolean3, boolean3);
			return this;
		}

		public boolean getBoolean3() {
			return mIntent.getBooleanExtra(Boolean3, false);
		}
	}
}
