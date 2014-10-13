package org.bmcoop.lib.base.util;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public final class UDatabase {

	public static boolean delete(SQLiteDatabase database, String tableName, String where, String[] whereClause) {
		int reInt = database.delete(tableName, where, whereClause);
		if (reInt > 0) {
			return true;
		}
		return false;
	}

	public static boolean deleteBulk(SQLiteDatabase database, String tableName) {
		try {
			database.beginTransaction();
			database.delete(tableName, null, null);
			database.setTransactionSuccessful();
		} catch (Exception e) {
			database.endTransaction();
			return false;
		}
		database.endTransaction();
		return true;
	}

	public static Cursor getCursorContentResoler(Context context, Uri uri, String[] project, String where,
			String[] whereClase, String order) {
		ContentResolver contentResolver = context.getContentResolver();
		Cursor cursor = contentResolver.query(uri, project, where, whereClase, order);

		return cursor;
	}

	public static ArrayList<HashMap<String, String>> getCursorHashList(String[] project, Cursor cursor) {
		ArrayList<HashMap<String, String>> reHashList = new ArrayList<HashMap<String, String>>();
		while (cursor.moveToNext()) {
			HashMap<String, String> hashMap = new HashMap<String, String>();
			for (int i = 0; i < project.length; i++) {
				String str = UDatabase.getKeyValue_String(cursor, project[i]);
				if (str != null && str.length() != 0) {
					hashMap.put(project[i], str);
				}
			}
			if (!hashMap.isEmpty()) {
				reHashList.add(hashMap);
			}
		}

		cursor.close();
		return reHashList;
	}

	public static double getKeyValue_Double(Cursor cursor, String key) {
		int indexId = -1;
		indexId = cursor.getColumnIndex(key);
		if (indexId >= 0) {
			return cursor.getDouble(cursor.getColumnIndex(key));
		}
		return 0.0;
	}
	
	public static float getKeyValue_Float(Cursor cursor, String key) {
		int indexId = -1;
		indexId = cursor.getColumnIndex(key);
		if (indexId >= 0) {
			return cursor.getFloat(cursor.getColumnIndex(key));
		}
		return 0F;
	}


	public static byte[] getKeyValue_Blob(Cursor cursor, String key) {
		int indexId = -1;
		indexId = cursor.getColumnIndex(key);
		if (indexId >= 0) {
			return cursor.getBlob(cursor.getColumnIndex(key));
		}
		return null;
	}

	public static int getKeyValue_int(Cursor cursor, String key) {
		int indexId = -1;
		indexId = cursor.getColumnIndex(key);
		if (indexId >= 0) {
			return cursor.getInt(cursor.getColumnIndex(key));
		}
		return 0;
	}

	public static long getKeyValue_long(Cursor cursor, String key) {
		int indexId = -1;
		indexId = cursor.getColumnIndex(key);
		if (indexId >= 0) {
			return cursor.getLong(cursor.getColumnIndex(key));
		}
		return 0;
	}

	public static String getKeyValue_String(Cursor cursor, String key) {
		int indexId = -1;
		indexId = cursor.getColumnIndex(key);
		if (indexId >= 0) {
			return cursor.getString(cursor.getColumnIndex(key));
		}
		return "";
	}

	public static String getNameColumn(Cursor cursor, String key) {
		int indexId = -1;
		indexId = cursor.getColumnIndex(key);
		if (indexId >= 0) {
			return cursor.getColumnName(indexId);
		}
		return "";
	}

	public static boolean insert(SQLiteDatabase database, String tableName, ContentValues conVal) {
		long reLong = database.insert(tableName, null, conVal);
		if (reLong != -1) {
			return true;
		}
		return false;
	}

	public static long insert_long(SQLiteDatabase database, String tableName, ContentValues conVal) {
		return database.insert(tableName, null, conVal);
	}

	public static boolean insertBulk(SQLiteDatabase database, String tableName, ArrayList<ContentValues> contList) {
		try {
			database.beginTransaction();
			for (int i = 0; i < contList.size(); i++) {
				database.insert(tableName, null, contList.get(i));
			}
			database.setTransactionSuccessful();
		} catch (Exception e) {
			e.printStackTrace();
			database.endTransaction();
			return false;
		}
		database.endTransaction();
		return true;
	}

	public static ArrayList<Long> insertBulk_long(SQLiteDatabase database, String tableName,
			ArrayList<ContentValues> contList) {
		ArrayList<Long> reData = new ArrayList<Long>();
		try {
			database.beginTransaction();
			for (int i = 0; i < contList.size(); i++) {
				reData.add(database.insert(tableName, null, contList.get(i)));
			}
			database.setTransactionSuccessful();
		} catch (Exception e) {
			e.printStackTrace();
			database.endTransaction();
			return reData;
		}
		database.endTransaction();
		return reData;
	}

	public static void logCursorData(Cursor cursor, int count) {
		int number = 0;
		while (cursor.moveToNext()) {
			if(number >= count){
				break;
			}
			number++;
			String col = "";
			for (int j = 0; j < cursor.getColumnCount(); j++) {
				try {
					col = col + cursor.getColumnName(j) + ": " + cursor.getString(j) + "\n";
				} catch (Exception e) {
				}
			}
			Log.e("DatabaseUtil", col);
		}
	}


	public static void logCursorData(Cursor cursor) {
		while (cursor.moveToNext()) {
			String col = "";
			for (int j = 0; j < cursor.getColumnCount(); j++) {
				try {
					col = col + cursor.getColumnName(j) + ": " + cursor.getString(j) + "\n";
				} catch (Exception e) {
				}
			}
			Log.e("DatabaseUtil", col);
		}
	}

	public static boolean update(SQLiteDatabase database, String tableName, ContentValues conVal, String where,
			String[] whereClause) {
		int reInt = database.update(tableName, conVal, where, whereClause);
		if (reInt > 0) {
			return true;
		}
		return false;
	}

	public static boolean updateBulk(SQLiteDatabase database, String tableName, ArrayList<ContentValues> contList,
			ArrayList<String> whereList, ArrayList<String[]> whereClauseList) {
		try {
			database.beginTransaction();
			for (int i = 0; i < contList.size(); i++) {
				if (whereClauseList == null) {
					database.update(tableName, contList.get(i), whereList.get(i), null);
				} else {
					database.update(tableName, contList.get(i), whereList.get(i), whereClauseList.get(i));
				}
			}
			database.setTransactionSuccessful();
		} catch (Exception e) {
			e.printStackTrace();
			database.endTransaction();
			return false;
		}
		database.endTransaction();
		return true;
	}
}
