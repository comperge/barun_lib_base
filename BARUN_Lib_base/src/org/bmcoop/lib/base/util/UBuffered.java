package org.bmcoop.lib.base.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class UBuffered {
	private static UBuffered SINGLE_U;

	private UBuffered() {
	}

	public static WBufferedReader withRead(InputStream inputStream) {
		if (SINGLE_U == null) {
			SINGLE_U = new UBuffered();
		}
		return SINGLE_U.getReader(inputStream);
	}

	private WBufferedReader getReader(InputStream inputStream) {
		return new WBufferedReader(inputStream);
	}

	public static WBufferedReader withRead_UTF8(File file) {
		if (SINGLE_U == null) {
			SINGLE_U = new UBuffered();
		}
		return SINGLE_U.getReader(file, "UTF-8");
	}

	public static WBufferedReader withRead(File file, String charsetName) {
		if (SINGLE_U == null) {
			SINGLE_U = new UBuffered();
		}
		return SINGLE_U.getReader(file, charsetName);
	}

	private WBufferedReader getReader(File file, String charsetName) {
		return new WBufferedReader(file, charsetName);
	}

	public class WBufferedReader {
		private StringBuilder mStringBuilder;
		private ArrayList<String> mStrList;

		private WBufferedReader(InputStream inputStream) {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			try {
				makeString(bufferedReader);
				bufferedReader.close();
				inputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
				try {
					inputStream.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}

		private WBufferedReader(File file, String charsetName) {
			String charset = "UTF-8";
			if (charsetName != null && charsetName.length() != 0) {
				charset = charsetName;
			}
			try {
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
				makeString(bufferedReader);
				bufferedReader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void makeString(BufferedReader bufferedReader) throws Exception {
			mStringBuilder = new StringBuilder();
			mStrList = new ArrayList<String>();
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				mStringBuilder.append(line + '\n');
				mStrList.add(line + '\n');
			}
		}

		public String getString() {
			if (mStringBuilder != null) {
				mStringBuilder.toString();
			}
			return null;
		}

		public ArrayList<String> getString_list() {
			return mStrList;
		}
	}

	public static WBufferedWriter withWrite_UTF8(File file) {
		if (SINGLE_U == null) {
			SINGLE_U = new UBuffered();
		}
		return SINGLE_U.getWrite(file, "UTF-8");
	}

	public static WBufferedWriter withWrite(File file, String charsetName) {
		if (SINGLE_U == null) {
			SINGLE_U = new UBuffered();
		}
		return SINGLE_U.getWrite(file, charsetName);
	}

	private WBufferedWriter getWrite(File file, String charsetName) {
		return new WBufferedWriter(file, "UTF-8");
	}

	public class WBufferedWriter {
		private BufferedWriter mBufferedWriter;

		private WBufferedWriter(File file, String charsetName) {
			String charset = "UTF-8";
			if (charsetName != null && charsetName.length() != 0) {
				charset = charsetName;
			}
			try {
				mBufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), charset));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public boolean write(String content) {
			try {
				mBufferedWriter.write(content);
				mBufferedWriter.close();
			} catch (Exception e) {
				e.printStackTrace();
				try {
					mBufferedWriter.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				return false;
			}
			return true;
		}
	}
}