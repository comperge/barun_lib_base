package org.bmcoop.lib.base.util;

import java.io.File;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

public class UFile {
	private static UFile SINGLE_U;

	private UFile() {
	}

	public static WFile withLocal(Context pCon, String dir1) throws Exception {
		if (SINGLE_U == null) {
			SINGLE_U = new UFile();
		}
		return SINGLE_U.getStorage(dir1, null, null, false, pCon);
	}

	public static WFile withLocal(Context pCon, String dir1, String dir2) throws Exception {
		if (SINGLE_U == null) {
			SINGLE_U = new UFile();
		}
		return SINGLE_U.getStorage(dir1, dir2, null, false, pCon);
	}

	public static WFile withLocal(Context pCon, String dir1, String dir2, String dir3) throws Exception {
		if (SINGLE_U == null) {
			SINGLE_U = new UFile();
		}
		return SINGLE_U.getStorage(dir1, dir2, dir3, false, pCon);
	}

	public static WFile withSdcard(String dir1) throws Exception {
		if (SINGLE_U == null) {
			SINGLE_U = new UFile();
		}
		return SINGLE_U.getStorage(dir1, null, null, true, null);
	}

	public static WFile withSdcard(String dir1, String dir2) throws Exception {
		if (SINGLE_U == null) {
			SINGLE_U = new UFile();
		}
		return SINGLE_U.getStorage(dir1, dir2, null, true, null);
	}

	public static WFile withSdcard(String dir1, String dir2, String dir3) throws Exception {
		if (SINGLE_U == null) {
			SINGLE_U = new UFile();
		}
		return SINGLE_U.getStorage(dir1, dir2, dir3, true, null);
	}

	private WFile getStorage(String dir1, String dir2, String dir3, boolean isSdcard, Context pCon) throws Exception {
		return new WFile(dir1, dir2, dir3, isSdcard, pCon);
	}

	public class WFile {
		private String mPathRoot;

		private WFile(String dir1, String dir2, String dir3, boolean isSdcard, Context pCon) throws Exception {
			if (isSdcard) {
				if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
					mPathRoot = Environment.getExternalStorageDirectory().getPath();
				} else {
					throw new Exception();
				}
			} else {
				mPathRoot = pCon.getFilesDir().getPath();
			}

			if (dir3 != null && dir2 != null && dir1 != null) {
				mPathRoot = mPathRoot + File.separator + dir1 + File.separator + dir2 + File.separator + dir3;
			} else if (dir2 != null && dir1 != null) {
				mPathRoot = mPathRoot + File.separator + dir1 + File.separator + dir2;
			} else if (dir1 != null) {
				mPathRoot = mPathRoot + File.separator + dir1;
			}
			if (!UFileUtil.withDir(mPathRoot).create()) {
				throw new Exception();
			}
		}

		//		public IStorage(Context pCon, String dir1, String dir2, String dir3){
		//			
		//			if (U.isValid(dir3) && U.isValid(dir2) && U.isValid(dir1)) {
		//				mPathRoot = mPathRoot + File.separator + dir1 + File.separator + dir2 + File.separator + dir3;
		//			} else if (U.isValid(dir2) && U.isValid(dir1)) {
		//				mPathRoot = mPathRoot + File.separator + dir1 + File.separator + dir2;
		//			} else if (U.isValid(dir1)) {
		//				mPathRoot = mPathRoot + File.separator + dir1;
		//			}
		//			UFileUtil__.buildDir(new File(mPathRoot));
		//		}

		public boolean create_noCreateIfExist(String fileName) {
			return UFileUtil.withFile(mPathRoot, fileName).create_noCreateIfExist();
		}

		public boolean create_deleteFirst(String fileName) {
			return UFileUtil.withFile(mPathRoot, fileName).create_deleteFirst();
		}

		public boolean rename(String fileName_Old, String fileName_New) {
			return UFileUtil.withFile(mPathRoot, fileName_Old).rename(fileName_New);
		}

		public boolean isExist(String fileName) {
			return UFileUtil.withFile(mPathRoot, fileName).isExist();
		}

		public boolean delete(String fileName) {
			return UFileUtil.withFile(mPathRoot, fileName).delete();
		}

		public File getFile(String fileName) {
			return UFileUtil.withFile(mPathRoot, fileName).get();
		}

		public String getPath_file(String fileName) {
			return UFileUtil.withFile(mPathRoot, fileName).getPath_file();
		}

		public File getRoot() {
			return new File(mPathRoot);
		}
		
		public String getPath_root() {
			return new File(mPathRoot).getPath();
		}

		public String read(String fileName) {
			return UFileUtil.withFile(mPathRoot, fileName).read();
		}

		public ArrayList<String> read_list(String fileName) {
			return UFileUtil.withFile(mPathRoot, fileName).read_list();
		}

		public boolean write(String fileName, String content) {
			return UFileUtil.withFile(mPathRoot, fileName).write(content);
		}

		public boolean append(String fileName, String content) {
			return UFileUtil.withFile(mPathRoot, fileName).append(content);
		}

		public boolean bitmap_save(String fileName, Bitmap bitmap) {
			return UImage.withBitmap(bitmap).writeBitmap(UFileUtil.withFile(mPathRoot, fileName).getPath_file());
		}

		public Bitmap bitmap_get(String fileName) {
			return UImage.withBitmap(UFileUtil.withFile(mPathRoot, fileName).getPath_file()).getBitmap();
		}
	}
	
	//캐시 위치 알아내기
//	public File getExternalCacheDir() {
//		if (Build.VERSION.SDK_INT >= 8) {
//			return mCon.getExternalCacheDir();
//		}
//
//		// Before Froyo we need to construct the external cache dir ourselves
//		final String cacheDir = "/Android/data/" + mCon.getPackageName() + "/cache/";
//		return new File(Environment.getExternalStorageDirectory().getPath() + cacheDir);
//	}
//
//	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
//	public boolean isExternalStorageRemovable() {
//		if (Build.VERSION.SDK_INT >= 9) {
//			return Environment.isExternalStorageRemovable();
//		}
//		return true;
//	}
//
//	public String getPath_sdcard() {
//		return Environment.getExternalStorageDirectory().toString();
//	}
//
//	public String getPath_cache() {
//		return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !isExternalStorageRemovable()
//				? getExternalCacheDir().getPath()
//				: mCon.getCacheDir().getPath();
//	}
}