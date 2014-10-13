package org.bmcoop.lib.base.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class UInputStream {
	private static UInputStream SINGLE_U;

	private UInputStream() {
	}

	public static WFileInputStream with(String pathFull) {
		if (SINGLE_U == null) {
			SINGLE_U = new UInputStream();
		}
		return SINGLE_U.getFile(null, pathFull);
	}

	public static WFileInputStream with(File file) {
		if (SINGLE_U == null) {
			SINGLE_U = new UInputStream();
		}
		return SINGLE_U.getFile(file, null);
	}

	private WFileInputStream getFile(File file, String pathFull) {
		return new WFileInputStream(file, pathFull);
	}

	public class WFileInputStream {
		private FileInputStream mFileInputStream;

		private WFileInputStream(File file, String pathFull) {
			try {
				if (file != null) {
					mFileInputStream = new FileInputStream(file);
				}
				if (pathFull != null) {
					mFileInputStream = new FileInputStream(pathFull);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public boolean write(File file) {
			FileOutputStream fileOutputStream = null;
			try {
				fileOutputStream = new FileOutputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return false;
			}
			return makeWrite(fileOutputStream);
		}
		
		public boolean write(String pathFull) {
			FileOutputStream fileOutputStream = null;
			try {
				fileOutputStream = new FileOutputStream(pathFull);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return false;
			}
			return makeWrite(fileOutputStream);
		}

		public boolean makeWrite(FileOutputStream fileOutputStream) {
			try {
				int readcount = 0;
				byte[] buffer = new byte[1024];
				while ((readcount = mFileInputStream.read(buffer, 0, 1024)) != -1) {
					fileOutputStream.write(buffer, 0, readcount);
				}
				mFileInputStream.close();
				fileOutputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
				try {
					fileOutputStream.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				return false;
			}
			return true;
		}

	}
}