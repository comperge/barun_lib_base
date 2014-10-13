package org.bmcoop.lib.base.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class UOutputStream {
	private static UOutputStream SINGLE_U;

	private UOutputStream() {
	}

	public static WFileOutputStream with(String pathFull) {
		if (SINGLE_U == null) {
			SINGLE_U = new UOutputStream();
		}
		return SINGLE_U.getFile(null, pathFull);
	}

	public static WFileOutputStream with(File file) {
		if (SINGLE_U == null) {
			SINGLE_U = new UOutputStream();
		}
		return SINGLE_U.getFile(file, null);
	}

	private WFileOutputStream getFile(File file, String pathFull) {
		return new WFileOutputStream(file, pathFull);
	}

	public class WFileOutputStream {
		private File mFile;
		private String mPathFull;

		private WFileOutputStream(File file, String pathFull) {
			mFile = file;
			mPathFull = pathFull;
		}

		public boolean append(String content) {
			FileOutputStream fileOutputStream = null;
			try {
				if (mFile != null) {
					fileOutputStream = new FileOutputStream(mFile, true);
				}
				if (mPathFull != null) {
					fileOutputStream = new FileOutputStream(mPathFull, true);
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			
			byte[] buffer = content.getBytes();
			if (fileOutputStream != null) {
				try {
					fileOutputStream.write(buffer);
					fileOutputStream.write("\n".getBytes());
					fileOutputStream.close();
					return true;
				} catch (Exception e) {
					try {
						fileOutputStream.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					e.printStackTrace();
				}
			}
			return false;
		}

		public boolean write(String content) {
			FileOutputStream fileOutputStream = null;
			try {
				if (mFile != null) {
					fileOutputStream = new FileOutputStream(mFile, false);
				}
				if (mPathFull != null) {
					fileOutputStream = new FileOutputStream(mPathFull, false);
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			byte[] buffer = content.getBytes();
			if (fileOutputStream != null) {
				try {
					fileOutputStream.write(buffer);
					fileOutputStream.write("\n".getBytes());
					fileOutputStream.close();
					return true;
				} catch (Exception e) {
					try {
						fileOutputStream.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					e.printStackTrace();
				}
			}
			return false;
		}
	}
}