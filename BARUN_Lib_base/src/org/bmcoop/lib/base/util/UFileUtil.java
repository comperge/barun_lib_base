package org.bmcoop.lib.base.util;

import java.io.File;
import java.util.ArrayList;

public class UFileUtil {
	private static UFileUtil SINGLE_U;

	private UFileUtil() {
	}

	public static WFileUtil withFile(String pathRootAbs, String fileName) {
		if (SINGLE_U == null) {
			SINGLE_U = new UFileUtil();
		}
		return SINGLE_U.get(pathRootAbs, fileName);
	}

	private WFileUtil get(String pathRootAbs, String fileName) {
		return new WFileUtil(pathRootAbs, fileName);
	}

	public static WFileUtil withFile(File file) {
		if (SINGLE_U == null) {
			SINGLE_U = new UFileUtil();
		}
		return SINGLE_U.get(file);
	}

	private WFileUtil get(File file) {
		return new WFileUtil(file);
	}

	public static WDirUtil withDir(String pathRootAbs, String dirName) {
		if (SINGLE_U == null) {
			SINGLE_U = new UFileUtil();
		}
		return SINGLE_U.getDir(pathRootAbs, dirName);
	}

	private WDirUtil getDir(String pathRootAbs, String dirName) {
		return new WDirUtil(pathRootAbs, dirName);
	}

	public static WDirUtil withDir(String pathDirFull) {
		if (SINGLE_U == null) {
			SINGLE_U = new UFileUtil();
		}
		return SINGLE_U.getDir(pathDirFull);
	}

	private WDirUtil getDir(String pathDirFull) {
		return new WDirUtil(pathDirFull);
	}

	public class WDirUtil {
		private String mPathRootAbs;
		private String mPathFullAbs;
		private String mDirName;
		private File mDir;

		private WDirUtil(String pathRootAbs, String dirName) {
			mPathRootAbs = pathRootAbs;
			mDirName = dirName;
			if (mPathRootAbs != null && mDirName != null) {
				mDir = new File(mPathRootAbs + File.separator + mDirName);
				mPathFullAbs = mDir.getPath();
			}
		}

		private WDirUtil(String pathDirFull) {
			mDir = new File(pathDirFull);
			mPathFullAbs = pathDirFull;
		}

		public boolean create() {
			if (!mDir.exists()) {
				return mDir.mkdirs();
			}
			return true;
		}

		public String getPath_dir() {
			return mPathFullAbs;
		}

		public String getPath_root() {
			return mPathRootAbs;
		}

	}

	public class WFileUtil {
		private String mPathRootAbs;
		private String mPathFullAbs;
		private String mFileName;
		private File mFile;

		private WFileUtil(File file) {
			mFile = file;
			mPathFullAbs = mFile.getPath();
		}

		private WFileUtil(String pathRootAbs, String fileName) {
			mPathRootAbs = pathRootAbs;
			mFileName = fileName;
			if (mPathRootAbs != null && mFileName != null) {
				mFile = new File(mPathRootAbs + File.separator + mFileName);
				mPathFullAbs = mFile.getPath();
			}
		}

		public boolean create_noCreateIfExist() {
			try {
				if (mFile != null && mFile.exists()) {
					return true;
				} else {
					return mFile.createNewFile();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}

		public boolean create_deleteFirst() {
			try {
				if (mFile != null && mFile.exists()) {
					if (!mFile.delete()) {
						return false;
					}
				}
				return mFile.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}

		public boolean delete() {
			if (mFile != null) {
				if (mFile.exists()) {
					try {
						return mFile.delete();
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					return true;
				}
			}
			return false;
		}

		public boolean isReadable() {
			return mFile.exists() && mFile.isFile() && mFile.canRead();
		}

		public boolean isExist() {
			return mFile.exists();
		}

		public boolean copy(String fileName_new) {
			if (mFile != null && mFile.exists()) {
				return UInputStream.with(mFile).write(new File(mPathRootAbs + File.separator + fileName_new));
			}
			return false;
		}

		public boolean copy_path(String pathFull_new) {
			if (mFile != null && mFile.exists()) {
				return UInputStream.with(mFile).write(pathFull_new);
			}
			return false;
		}

		public boolean copy(File file) {
			if (mFile != null && mFile.exists()) {
				return UInputStream.with(mFile).write(file);
			}
			return false;
		}

		public boolean rename(String fileName_new) {
			return mFile.renameTo(new File(mPathRootAbs + File.separator + fileName_new));
		}

		public boolean rename_path(String pathFull) {
			return mFile.renameTo(new File(pathFull));
		}

		public boolean rename(File file) {
			return mFile.renameTo(file);
		}

		public File get() {
			return mFile;
		}

		public String getPath_file() {
			return mPathFullAbs;
		}

		public String getPath_root() {
			return mPathRootAbs;
		}

		public String read() {
			if (mFile == null) {
				create_noCreateIfExist();
			}
			return UBuffered.withRead_UTF8(mFile).getString();
		}

		public ArrayList<String> read_list() {
			if (mFile == null) {
				create_noCreateIfExist();
			}
			return UBuffered.withRead_UTF8(mFile).getString_list();
		}

		public boolean write(String content) {
			if (mFile == null) {
				create_noCreateIfExist();
			}
			return UBuffered.withWrite_UTF8(mFile).write(content);
		}

		public boolean append(String content) {
			if (mFile == null) {
				create_noCreateIfExist();
			}
			return UOutputStream.with(mFile).append(content);
		}
	}
}