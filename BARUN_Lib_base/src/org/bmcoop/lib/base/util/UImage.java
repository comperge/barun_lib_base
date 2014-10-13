package org.bmcoop.lib.base.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public final class UImage extends UImageUtil {
	private static UImage singleton;

	public static IBitmap withBitmap(String pathImage) {
		if (singleton == null) {
			singleton = new UImage();
		}
		return singleton.getBitmap(pathImage);
	}

	private IBitmap getBitmap(String pathImage) {
		return new IBitmap(pathImage);
	}

	public static IBitmap withBitmap(byte[] byteArray) {
		if (singleton == null) {
			singleton = new UImage();
		}
		return singleton.getBitmap(byteArray);
	}

	private IBitmap getBitmap(byte[] byteArray) {
		return new IBitmap(byteArray);
	}

	public static IBitmap withBitmap(InputStream inputStream) {
		if (singleton == null) {
			singleton = new UImage();
		}
		return singleton.getBitmap(inputStream);
	}

	private IBitmap getBitmap(InputStream inputStream) {
		return new IBitmap(inputStream);
	}

	public static IBitmap withBitmap(BitmapDrawable drawable) {
		if (singleton == null) {
			singleton = new UImage();
		}
		return singleton.getBitmap(drawable);
	}

	private IBitmap getBitmap(BitmapDrawable drawable) {
		return new IBitmap(drawable);
	}

	public static IBitmap withBitmap(Bitmap bitmap) {
		if (singleton == null) {
			singleton = new UImage();
		}
		return singleton.getBitmap(bitmap);
	}

	private IBitmap getBitmap(Bitmap bitmap) {
		return new IBitmap(bitmap);
	}

	public static IBitmap withBitmap(Context context, int resId) {
		if (singleton == null) {
			singleton = new UImage();
		}
		return singleton.getBitmap(context, resId);
	}

	private IBitmap getBitmap(Context context, int resId) {
		return new IBitmap(context, resId);
	}

	public static IBitmap withBitmap(int width, int height, int[] pixels) {
		if (singleton == null) {
			singleton = new UImage();
		}
		return singleton.getBitmap(width, height, pixels);
	}

	private IBitmap getBitmap(int width, int height, int[] pixels) {
		return new IBitmap(width, height, pixels);
	}

	public class IBitmap {
		private String mPathImage;
		private Bitmap mBitmap;

		public IBitmap(String pathImage) {
			this.mPathImage = pathImage;
			mBitmap = pBitmap(pathImage);
		}

		public IBitmap(byte[] byteArray) {
			mBitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
		}

		public IBitmap(InputStream inputStream) {
			mBitmap = BitmapFactory.decodeStream(inputStream);
		}

		public IBitmap(BitmapDrawable drawable) {
			mBitmap = drawable.getBitmap();
		}

		public IBitmap(Bitmap bitmap) {
			mBitmap = bitmap;
		}

		public IBitmap(Context context, int resId) {
			mBitmap = ((BitmapDrawable) context.getResources().getDrawable(resId)).getBitmap();
		}

		public IBitmap(int width, int height, int[] pixels) {
			mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
			mBitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		}

		public Bitmap getBitmap() {
			return mBitmap;
		}

		public Bitmap getBitmap_rotate(int ImageUtil_Rotation) {
			return getRotate(mBitmap, ImageUtil_Rotation);
		}

		public Bitmap getBitmap_resize(int width, int height) {
			return Bitmap.createScaledBitmap(mBitmap, width, height, false);
		}

		public Bitmap getBitmap_thumb() {
			return getCropCenter_scale(mBitmap, 200);
		}

		public Bitmap getBimap_cropCenter_scale(int sizePixel) {
			return getCropCenter_scale(mBitmap, sizePixel);
		}

		public Bitmap getBitmap_scale_width(int widthNew) {
			return getScale_width(mBitmap, widthNew);
		}

		public Bitmap getBitmap_Option(InputStream inputStream, int width, int height) {
			if (inputStream == null) {
				return null;
			}
			int scale = 0;
			if (mBitmap.getWidth() > mBitmap.getHeight()) {
				scale = mBitmap.getWidth() / width;
			} else {
				scale = mBitmap.getHeight() / height;
			}

			BitmapFactory.Options options = new BitmapFactory.Options();
			// 2.2에서는 RGB_565만 지원
			// 2.3에서는 ARGB_8888 지원
			options.inSampleSize = scale;
			options.inJustDecodeBounds = false;
			options.inPurgeable = true; // 메모리를 줄여주는 옵션
			options.inDither = true; // 이미지를 깔끔하게 처리해서 보여주는 옵션
			options.inPreferredConfig = Bitmap.Config.ARGB_8888;
			Bitmap bitmap = null;
			try {
				BitmapFactory.decodeStream(inputStream, null, options);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return bitmap;
		}

		public Bitmap getBitmap_scale_height(int heightNew) {
			return getScale_height(mBitmap, heightNew);
		}

		public Drawable getBitmap_resize_drawable(int width, int height) {
			return new BitmapDrawable(Bitmap.createScaledBitmap(mBitmap, width, height, false));
		}

		public Drawable getDrawable() {
			return new BitmapDrawable(mBitmap);
		}

		public byte[] getJPEG(int compressRate) {
			ByteArrayOutputStream blob = new ByteArrayOutputStream();
			mBitmap.compress(Bitmap.CompressFormat.JPEG, compressRate, blob);
			return blob.toByteArray();
		}

		public byte[] getPNG(int compressRate) {
			ByteArrayOutputStream bitmapStream = new ByteArrayOutputStream();
			mBitmap.compress(Bitmap.CompressFormat.PNG, compressRate, bitmapStream);
			return bitmapStream.toByteArray();
		}

		public boolean writeBitmap(String pathImage) {
			return pWriteBitmap(mBitmap, pathImage, 100);
		}

		public boolean writeBitmap(String pathImage, int qualityPersent) {
			return pWriteBitmap(mBitmap, pathImage, qualityPersent);
		}
	}
}
