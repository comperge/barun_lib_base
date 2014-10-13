package org.bmcoop.lib.base.util;

import java.io.IOException;
import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.media.ExifInterface;

public class UBitmapUtil {
	private static UBitmapUtil SINGLE_U;

	private UBitmapUtil() {
	}

	public static WBitmapOptions withOptions() {
		if (SINGLE_U == null) {
			SINGLE_U = new UBitmapUtil();
		}
		return SINGLE_U.getOptions();
	}

	private WBitmapOptions getOptions() {
		return new WBitmapOptions();
	}

	public class WBitmapOptions {

		private WBitmapOptions() {
		}

		public Options getOptions_empty() {
			BitmapFactory.Options redata = new BitmapFactory.Options();
			redata.inPreferredConfig = Bitmap.Config.ARGB_8888;
			redata.inJustDecodeBounds = true;
			return redata;
		}

		public Options getOptions_empty_closeInputStream(InputStream inputStream) {
			BitmapFactory.Options redata = getOptions_empty();
			BitmapFactory.decodeStream(inputStream, null, redata);
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return redata;
		}

		public Options getOptions(int scale) {
			if (scale < 1) {
				scale = 1;
			}
			BitmapFactory.Options options = new BitmapFactory.Options();
			// 2.2에서는 RGB_565만 지원
			// 2.3에서는 ARGB_8888 지원
			options.inSampleSize = scale;
			options.inJustDecodeBounds = false;
			options.inPurgeable = true; // 메모리를 줄여주는 옵션
			options.inDither = true; // 이미지를 깔끔하게 처리해서 보여주는 옵션
			options.inPreferredConfig = Bitmap.Config.ARGB_8888;
			return options;
		}
	}

	public static WBitmapInputStream withInputStream() {
		if (SINGLE_U == null) {
			SINGLE_U = new UBitmapUtil();
		}
		return SINGLE_U.getInputStream();
	}

	private WBitmapInputStream getInputStream() {
		return new WBitmapInputStream();
	}

	public class WBitmapInputStream {

		private WBitmapInputStream() {
		}

		public Bitmap getBitmap_closeInputStream(InputStream inputStream, BitmapFactory.Options options) {
			if (inputStream == null) {
				return null;
			}
			Bitmap redata = null;
			try {
				redata = BitmapFactory.decodeStream(inputStream, null, options);
				inputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return redata;
		}
	}

	public static final int ROTATION_0 = 0;
	public static final int ROTATION_90 = 90;
	public static final int ROTATION_180 = 180;
	public static final int ROTATION_270 = 270;

	public static WBitmapRotation withRotation() {
		if (SINGLE_U == null) {
			SINGLE_U = new UBitmapUtil();
		}
		return SINGLE_U.getRotation();
	}

	private WBitmapRotation getRotation() {
		return new WBitmapRotation();
	}

	public class WBitmapRotation {

		private WBitmapRotation() {
		}

		public int getRotation(String pathImage) {
			String orientString = null;
			try {
				ExifInterface exif = new ExifInterface(pathImage);
				orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
			} catch (IOException e) {
				e.printStackTrace();
			}
			int orientation = orientString != null ? Integer.parseInt(orientString) : ExifInterface.ORIENTATION_NORMAL;
			if (orientation == ExifInterface.ORIENTATION_ROTATE_90) {
				return ROTATION_90;
			} else if (orientation == ExifInterface.ORIENTATION_ROTATE_180) {
				return ROTATION_180;
			} else if (orientation == ExifInterface.ORIENTATION_ROTATE_270) {
				return ROTATION_270;
			} else {
				return ROTATION_0;
			}
		}

		public int getRotation_four(boolean isLeft, int rotation) {
			if (isLeft) {
				rotation = ROTATION_270;
			} else {
				rotation = ROTATION_90;
			}
			return rotation;
		}

	}
}