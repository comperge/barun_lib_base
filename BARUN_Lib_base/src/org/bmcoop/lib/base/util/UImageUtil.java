package org.bmcoop.lib.base.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.media.ExifInterface;

public class UImageUtil {

	private static int IMAGE_SIZE_WIDTH = 720;
	private static int IMAGE_SIZE_HEIGHT = 1280;
	public static final int ROTATION_0 = 0;
	public static final int ROTATION_90 = 90;
	public static final int ROTATION_180 = 180;
	public static final int ROTATION_270 = 270;

	public UImageUtil() {

	}

	public Bitmap pBitmap(String pathImage, int scale) {
		Bitmap bit = BitmapFactory.decodeFile(pathImage, getOption(pathImage, scale));
		return bit;
	}

	public Bitmap pBitmap(String pathImage) {
		Options options = getOptionEmpty(pathImage);
		int width = options.outWidth;
		int height = options.outHeight;
		if (width > height) {
			int temp = height;
			height = width;
			width = temp;
		}
		int scale = 1;

		while (true) {
			if (width / 2 < IMAGE_SIZE_WIDTH || height / 2 < IMAGE_SIZE_HEIGHT)
				break;
			width /= 2;
			height /= 2;
			scale *= 2;
		}

		Bitmap bit = BitmapFactory.decodeFile(pathImage, getOption(pathImage, scale));
		return bit;
	}

	public Options getOption(String pathImage, int scale) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		// 2.2에서는 RGB_565만 지원
		// 2.3에서는 ARGB_8888 지원
		options.inSampleSize = scale;
		options.inJustDecodeBounds = false;
		options.inPurgeable = true; // 메모리를 줄여주는 옵션
		options.inDither = true; // 이미지를 깔끔하게 처리해서 보여주는 옵션
		options.inPreferredConfig = Bitmap.Config.ARGB_8888;
		BitmapFactory.decodeFile(pathImage, options);
		return options;
	}
	
	public Options getOption(InputStream inputStream, int scale) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		// 2.2에서는 RGB_565만 지원
		// 2.3에서는 ARGB_8888 지원
		options.inSampleSize = scale;
		options.inJustDecodeBounds = false;
		options.inPurgeable = true; // 메모리를 줄여주는 옵션
		options.inDither = true; // 이미지를 깔끔하게 처리해서 보여주는 옵션
		options.inPreferredConfig = Bitmap.Config.ARGB_8888;
		BitmapFactory.decodeStream (inputStream, null, options);
		return options;
	}

	private Options getOptionEmpty(String pathImage) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inPreferredConfig = Bitmap.Config.ARGB_8888;
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(pathImage, options);
		return options;
	}

	public boolean pWriteBitmap(Bitmap bitmap, String pathIamge, int qualityPersent) {
		return makeFile(bitmap, pathIamge, qualityPersent, CompressFormat.PNG);
	}

	public boolean pWriteBitmap_jpeg(Bitmap bitmap, String pathIamge, int qualityPersent) {
		return makeFile(bitmap, pathIamge, qualityPersent, CompressFormat.JPEG);
	}

	private boolean makeFile(Bitmap bitmap, String pathIamge, int qualityPersent, CompressFormat format) {
		boolean reBool = true;
		FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream(new File(pathIamge));
			if (fileOut != null) {
				bitmap.compress(format, qualityPersent, fileOut);
				fileOut.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
			reBool = false;
		} finally {
			try {
				fileOut.close();
			} catch (Exception e1) {
				reBool = false;
			}
		}
		return reBool;
	}

	// decodes image and scales it to reduce memory consumption
	public Bitmap decodeFile(File f) {
		try {
			// decode image size
			BitmapFactory.Options o = new BitmapFactory.Options();
			o.inJustDecodeBounds = true;
			BitmapFactory.decodeStream(new FileInputStream(f), null, o);

			// Find the correct scale value. It should be the power of 2.
			final int REQUIRED_SIZE = 70;
			int width_tmp = o.outWidth, height_tmp = o.outHeight;
			int scale = 1;
			while (true) {
				if (width_tmp / 2 < REQUIRED_SIZE || height_tmp / 2 < REQUIRED_SIZE)
					break;
				width_tmp /= 2;
				height_tmp /= 2;
				scale *= 2;
			}

			// decode with inSampleSize
			BitmapFactory.Options o2 = new BitmapFactory.Options();
			o2.inSampleSize = 1; // scale;
			return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
		} catch (FileNotFoundException e) {
		}
		return null;
	}

	/**
	 * @param path
	 * @param cropSize
	 *            주어진 사이즈로 사진을 자름
	 * @param orientation
	 * @return
	 */

	public Bitmap getBitmapFromFileScaleCrop(String path, int cropSize, int orientation) {
		BitmapFactory.Options options = getOption(path, 1);

		float scale = getOptionScale(cropSize, cropSize, orientation, options, true);

		// Bitmap bitOpt = getOptionBit(path, options, scale);
		Bitmap bitOpt = null;

		Bitmap bitScaled = getScaledBit(cropSize, bitOpt);

		int bitWidth = bitScaled.getWidth();
		int bitHeight = bitScaled.getHeight();

		int startWidth = 0;
		int startHeight = 0;
		int size = 0;
		if (bitWidth > bitHeight) {
			startWidth = (bitWidth - bitHeight) / 2;
			size = bitHeight;
		} else {
			startHeight = (bitHeight - bitWidth) / 2;
			size = bitWidth;
		}

		return getRotatedBit(orientation, bitScaled, startWidth, startHeight, size);
	}

	public Bitmap getRotate(Bitmap bitmap, int UImageUtil_Rotation) {
		if (UImageUtil_Rotation > 0) {
			Matrix matrix = new Matrix();
			matrix.setRotate(UImageUtil_Rotation, (float) bitmap.getWidth() / 2f, (float) bitmap.getHeight() / 2f);
			return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
		}
		return bitmap;
	}

	/**
	 * 사진을 자르지 않고, 가로 세로 비율을 유지함
	 * 
	 * @param path
	 * @param width
	 * @param height
	 * @param orientation
	 * @return
	 */
	public Bitmap getBitmapFromFileScaleFixed(String path, int width, int height, int orientation) {
		// BitmapFactory.Options options = getSizeOnlyImage(path);
		//
		// float scale = getOptionScale(width, height, orientation, options,
		// true);
		//
		// Bitmap bitOpt = getOptionBit(path, options, scale);
		//
		// float scaleFac = getOptionScale(width, height, orientation, options,
		// false);
		//
		// Bitmap bitScaled = getScaledBit(scaleFac, bitOpt);
		//
		// return getRotatedBit(orientation, bitScaled, 0, 0, width, height);
		// }

		// public static Bitmap getBitmap(String url) {
		// File f = new
		// File(android.os.Environment.getExternalStorageDirectory(),
		// "TempImages");
		//
		// // from web
		// try {
		// Bitmap bitmap = null;
		// URL imageUrl = new URL(url);
		// HttpURLConnection conn = (HttpURLConnection)
		// imageUrl.openConnection();
		// conn.setConnectTimeout(30000);
		// conn.setReadTimeout(30000);
		// conn.setInstanceFollowRedirects(true);
		// InputStream is = conn.getInputStream();
		// OutputStream os = new FileOutputStream(f);
		// CopyStream.copyStream(is, os);
		// os.close();
		// bitmap = decodeFile(f);
		// return bitmap;
		// } catch (Exception ex) {
		// ex.printStackTrace();
		return null;
		// }
	}

	private float getOptionScale(int width, int height, int orientation, BitmapFactory.Options options,
			boolean optionScale) {
		boolean oriFlag = isOrientated(orientation);

		int tWidth = options.outWidth;
		int tHeight = options.outHeight;
		int wWidth = width;
		int wHeight = height;
		boolean wFlag = false;

		if (tWidth < wWidth && tHeight < wHeight) {
			return (float) 1;
		} else if (!oriFlag) {
			if (((float) tWidth / (float) tHeight) < ((float) wWidth / (float) wHeight)) {
				wFlag = true;
			}
		} else {
			if (((float) tHeight / (float) tWidth) < ((float) wWidth / (float) wHeight)) {
				wFlag = true;
			}
		}

		if (!optionScale) {
			wFlag = !wFlag;
		}

		if (wFlag) {
			return (float) tWidth / (float) wWidth;
		} else {
			return (float) tHeight / (float) wHeight;
		}
	}

	private Bitmap getRotatedBit(int orientation, Bitmap bitScaled, int startWidth, int startHeight, int size) {
		if (orientation > 0) {
			Matrix mat = new Matrix();
			mat.setRotate(orientation, (float) bitScaled.getWidth() / 2, (float) bitScaled.getHeight() / 2);
			return Bitmap.createBitmap(bitScaled, startWidth, startHeight, size, size, mat, true);
		} else {
			return Bitmap.createBitmap(bitScaled, startWidth, startHeight, size, size);
		}
	}

	private Bitmap getScaledBit(int cropSize, Bitmap bitOpt) {
		int bitWidth = bitOpt.getWidth();
		int bitHeight = bitOpt.getHeight();

		float scaleFac;
		if (bitWidth > bitHeight) {
			scaleFac = (float) bitHeight / (float) cropSize;
		} else {
			scaleFac = (float) bitWidth / (float) cropSize;
		}

		return Bitmap.createScaledBitmap(bitOpt, (int) ((float) bitWidth / scaleFac),
				(int) ((float) bitHeight / scaleFac), true);
	}

	private boolean isOrientated(int orientation) {
		boolean oriFlag = false;
		if ((orientation > 45 && orientation < 135) || (orientation > 225 && orientation < 315)) {
			oriFlag = true;
		}
		return oriFlag;
	}

	public Bitmap getCropCenter(Bitmap bitmap) {
		if (bitmap.getWidth() >= bitmap.getHeight()) {
			return Bitmap.createBitmap(bitmap, bitmap.getWidth() / 2 - bitmap.getHeight() / 2, 0, bitmap.getHeight(),
					bitmap.getHeight());
		} else {
			return Bitmap.createBitmap(bitmap, 0, bitmap.getHeight() / 2 - bitmap.getWidth() / 2, bitmap.getWidth(),
					bitmap.getWidth());
		}
	}

	public Bitmap getCropCenter_scale(Bitmap bitmap, int sizePixel) {
		return Bitmap.createScaledBitmap(getCropCenter(bitmap), sizePixel, sizePixel, true);
	}

	public Bitmap getScale_width(Bitmap bitmap, int widthNew) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		float scale = (float) widthNew / (float) width;

		return Bitmap.createScaledBitmap(bitmap, (int) ((float) width * scale), (int) ((float) height * scale), true);
	}

	public Bitmap getScale_height(Bitmap bitmap, int heightNew) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		float scale = (float) heightNew / (float) height;

		return Bitmap.createScaledBitmap(bitmap, (int) ((float) width * scale), (int) ((float) height * scale), true);
	}

	public static Bitmap getScale(Bitmap bitmap, float scale) {
		return Bitmap.createScaledBitmap(bitmap, (int) ((float) bitmap.getWidth() / scale),
				(int) ((float) bitmap.getHeight() / scale), true);
	}

	public static float getScale(int after, int before) {
		return (float) after / (float) before;
	}

	public static float getScale_fit(String pathImage, float targetWidth, float targetHeight) {
		int width = UImageUtil.getWidth_option(pathImage);
		int height = UImageUtil.getHeight_option(pathImage);
		return getScale_fit(width, height, targetWidth, targetHeight);
	}

	public static float getScale_fit(int width, int height, float targetWidth, float targetHeight) {
		float input = 0;
		float target = 0;
		if (width > height) {
			input = width;
			target = targetWidth;
		} else {
			input = height;
			target = targetHeight;
		}
		return target / input;
	}

	public static Bitmap getPolyToPoly(Bitmap bitmap, float[] src) {
		Matrix matrix = new Matrix();
		// if (src.length == 8) {
		// float x1 = src[0];
		// float y1 = src[1];
		// float x2 = src[2];
		// float y2 = src[3];
		// float x3 = src[4];
		// float y3 = src[5];
		// float x4 = src[6];
		// float y4 = src[7];
		//
		// float xCrop = 0;
		// float yCrop = 0;
		// if ((x2 - x1) > (x3 - x4)) {
		// xCrop = (x3 - x4);
		// } else {
		// xCrop = (x2 - x1);
		// }
		// if ((y4 - y1) > (y3 - y2)) {
		// yCrop = (y3 - y2);
		// } else {
		// yCrop = (y4 - y1);
		// }
		// float xScale = (float) 2 - xCrop / (float) bitmap.getWidth();
		// float yScale = (float) 2 - yCrop / (float) bitmap.getHeight();
		//
		// Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, (int)
		// ((float) bitmap.getWidth() * xScale),
		// (int) ((float) bitmap.getHeight() * yScale), true);
		// x1 = x1 * xScale;
		// x2 = x2 * xScale;
		// x3 = x3 * xScale;
		// x4 = x4 * xScale;
		// y1 = y1 * yScale;
		// y2 = y2 * yScale;
		// y3 = y3 * yScale;
		// y4 = y4 * yScale;
		// float[] scrScaled = new float[] { x1, y1, x2, y2, x3, y3, x4, y4 };
		// float[] dst = new float[] { 0, 0, scaledBitmap.getWidth(), 0,
		// scaledBitmap.getWidth(),
		// scaledBitmap.getHeight(), 0, scaledBitmap.getHeight() };
		// matrix.setPolyToPoly(scrScaled, 0, dst, 0, scrScaled.length >> 1);
		// int xStart = 0;
		// int yStart = 0;
		// if (x1 > x4) {
		// xStart = (int) x1;
		// } else {
		// xStart = (int) x4;
		// }
		// if (y1 > y2) {
		// yStart = (int) y2;
		// } else {
		// yStart = (int) y1;
		// }
		// return Bitmap.createBitmap(scaledBitmap, xStart, yStart,
		// bitmap.getWidth(), bitmap.getHeight(), matrix,
		// true);
		// }
		// return null;
		float[] dst = new float[] { 0, 0, bitmap.getWidth(), 0, bitmap.getWidth(), bitmap.getHeight(), 0,
				bitmap.getHeight() };
		matrix.setPolyToPoly(src, 0, dst, 0, src.length >> 1);
		return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
	}

	public static int getRotation(String pathImage) {
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

	public static int getRotation_four(boolean isLeft, int rotation) {
		if (isLeft) {
			rotation = ROTATION_270;
		} else {
			rotation = ROTATION_90;
		}
		return rotation;
	}

	public static int getWidth_option(String pathImage) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inPreferredConfig = Bitmap.Config.ARGB_8888;
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(pathImage, options);
		return options.outWidth;
	}

	public static int getHeight_option(String pathImage) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inPreferredConfig = Bitmap.Config.ARGB_8888;
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(pathImage, options);
		return options.outHeight;
	}
}
