package org.bmcoop.lib.base.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.view.SurfaceView;
import android.view.View;

public class UFilter {
	public static int WIDTH_STROKE_BIG = 0;
	public static int WIDTH_STROKE_MID = 1;
	public static int WIDTH_STROKE_THIN = 2;

	private static UFilter UFILTER = null;
	private Context pCon;

	private UFilter(Context pCon) {
		this.pCon = pCon;
	}

	public static UFilter with(Context pCon) {
		if (UFILTER == null)
			return new UFilter(pCon);
		else
			return UFILTER;
	}

	private float getStrokeWidth(int originalValue, int widthStroke) {
		if (widthStroke == WIDTH_STROKE_BIG) {
			return originalValue / 10.0f;
		} else if (widthStroke == WIDTH_STROKE_MID) {
			return originalValue / 20.0f;
		} else if (widthStroke == WIDTH_STROKE_THIN) {
			return originalValue / 40.0f;
		}
		return originalValue / 40.0f;
	}

	/**
	 * @param bitmap
	 * @param UBitmap_Width
	 * @param color
	 *            Color.WHITE
	 * @return
	 */
	public Bitmap getCircleStroke(Bitmap bitmap, int UBitmap_Width, int color) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		int length = width;
		if (height < width) {
			length = height;
		}
		float strokeLength = getStrokeWidth(length, UBitmap_Width);
		length = length - (int) strokeLength;

		Rect rect = new Rect((int) (strokeLength / 2.0f), (int) (strokeLength / 2.0f), length, length);
		RectF rectF = new RectF(rect);
		Paint paintBit = new Paint();

		paintBit.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		canvas.drawOval(rectF, paintBit);

		paintBit.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paintBit);

		Paint paintStroke = new Paint();
		paintStroke.setColor(color);
		paintStroke.setStyle(Paint.Style.STROKE);
		paintStroke.setStrokeWidth(strokeLength);
		paintStroke.setAntiAlias(true);
		canvas.drawOval(rectF, paintStroke);

		return output;
	}

	public Bitmap getBlurFilter(Bitmap bitmap, int radius) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		canvas.drawColor(Color.WHITE);
		Paint paint = new Paint();
		paint.setAntiAlias(true);

		BlurMaskFilter blur = new BlurMaskFilter(bitmap.getWidth(), BlurMaskFilter.Blur.INNER);
		paint.setMaskFilter(blur);
		canvas.drawBitmap(bitmap, 0, 0, paint);
		return output;
	}

	public Bitmap getBlurFilter(View view, int radius) {
		Bitmap bitmap = screenShot(view);
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		// canvas.drawColor(Color.TRANSPARENT);
		Paint paint = new Paint();
		paint.setAntiAlias(true);

		BlurMaskFilter blur = new BlurMaskFilter(radius, BlurMaskFilter.Blur.NORMAL);
		paint.setMaskFilter(blur);
		canvas.drawBitmap(bitmap, 0, 0, paint);
		return output;
	}

	// public Bitmap screenShot(View view) {
	// Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),
	// Config.ARGB_8888);
	// Canvas canvas = new Canvas(bitmap);
	// view.draw(canvas);
	// return bitmap;
	// }

	public Bitmap screenShot(View view) {
		Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		if (view instanceof SurfaceView) {
			SurfaceView surfaceView = (SurfaceView) view;
			surfaceView.setZOrderOnTop(true);
			surfaceView.draw(canvas);
			surfaceView.setZOrderOnTop(false);
			return bitmap;
		} else {
			view.draw(canvas);
			return bitmap;
		}
	}

	public Bitmap getblur(View view, int radius) {
		return fastblur(screenShot(view), radius);

	}

	@SuppressLint("NewApi")
	public Bitmap fastblur(Bitmap sentBitmap, int radius) {

		if (VERSION.SDK_INT > 16) {
			Bitmap bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);

			final RenderScript rs = RenderScript.create(pCon);
			final Allocation input = Allocation.createFromBitmap(rs, sentBitmap, Allocation.MipmapControl.MIPMAP_NONE, Allocation.USAGE_SCRIPT);
			final Allocation output = Allocation.createTyped(rs, input.getType());
			final ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
			script.setRadius(radius /* e.g. 3.f */);
			script.setInput(input);
			script.forEach(output);
			output.copyTo(bitmap);
			return bitmap;
		}

		Bitmap bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);

		if (radius < 1) {
			return (null);
		}

		int w = bitmap.getWidth();
		int h = bitmap.getHeight();

		int[] pix = new int[w * h];
		Log.i("pix", w + " " + h + " " + pix.length);
		bitmap.getPixels(pix, 0, w, 0, 0, w, h);

		int wm = w - 1;
		int hm = h - 1;
		int wh = w * h;
		int div = radius + radius + 1;

		int r[] = new int[wh];
		int g[] = new int[wh];
		int b[] = new int[wh];
		int rsum, gsum, bsum, x, y, i, p, yp, yi, yw;
		int vmin[] = new int[Math.max(w, h)];

		int divsum = (div + 1) >> 1;
		divsum *= divsum;
		int dv[] = new int[256 * divsum];
		for (i = 0; i < 256 * divsum; i++) {
			dv[i] = (i / divsum);
		}

		yw = yi = 0;

		int[][] stack = new int[div][3];
		int stackpointer;
		int stackstart;
		int[] sir;
		int rbs;
		int r1 = radius + 1;
		int routsum, goutsum, boutsum;
		int rinsum, ginsum, binsum;

		for (y = 0; y < h; y++) {
			rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
			for (i = -radius; i <= radius; i++) {
				p = pix[yi + Math.min(wm, Math.max(i, 0))];
				sir = stack[i + radius];
				sir[0] = (p & 0xff0000) >> 16;
				sir[1] = (p & 0x00ff00) >> 8;
				sir[2] = (p & 0x0000ff);
				rbs = r1 - Math.abs(i);
				rsum += sir[0] * rbs;
				gsum += sir[1] * rbs;
				bsum += sir[2] * rbs;
				if (i > 0) {
					rinsum += sir[0];
					ginsum += sir[1];
					binsum += sir[2];
				} else {
					routsum += sir[0];
					goutsum += sir[1];
					boutsum += sir[2];
				}
			}
			stackpointer = radius;

			for (x = 0; x < w; x++) {

				r[yi] = dv[rsum];
				g[yi] = dv[gsum];
				b[yi] = dv[bsum];

				rsum -= routsum;
				gsum -= goutsum;
				bsum -= boutsum;

				stackstart = stackpointer - radius + div;
				sir = stack[stackstart % div];

				routsum -= sir[0];
				goutsum -= sir[1];
				boutsum -= sir[2];

				if (y == 0) {
					vmin[x] = Math.min(x + radius + 1, wm);
				}
				p = pix[yw + vmin[x]];

				sir[0] = (p & 0xff0000) >> 16;
				sir[1] = (p & 0x00ff00) >> 8;
				sir[2] = (p & 0x0000ff);

				rinsum += sir[0];
				ginsum += sir[1];
				binsum += sir[2];

				rsum += rinsum;
				gsum += ginsum;
				bsum += binsum;

				stackpointer = (stackpointer + 1) % div;
				sir = stack[(stackpointer) % div];

				routsum += sir[0];
				goutsum += sir[1];
				boutsum += sir[2];

				rinsum -= sir[0];
				ginsum -= sir[1];
				binsum -= sir[2];

				yi++;
			}
			yw += w;
		}
		for (x = 0; x < w; x++) {
			rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
			yp = -radius * w;
			for (i = -radius; i <= radius; i++) {
				yi = Math.max(0, yp) + x;

				sir = stack[i + radius];

				sir[0] = r[yi];
				sir[1] = g[yi];
				sir[2] = b[yi];

				rbs = r1 - Math.abs(i);

				rsum += r[yi] * rbs;
				gsum += g[yi] * rbs;
				bsum += b[yi] * rbs;

				if (i > 0) {
					rinsum += sir[0];
					ginsum += sir[1];
					binsum += sir[2];
				} else {
					routsum += sir[0];
					goutsum += sir[1];
					boutsum += sir[2];
				}

				if (i < hm) {
					yp += w;
				}
			}
			yi = x;
			stackpointer = radius;
			for (y = 0; y < h; y++) {
				pix[yi] = (0xff000000 & pix[yi]) | (dv[rsum] << 16) | (dv[gsum] << 8) | dv[bsum];

				rsum -= routsum;
				gsum -= goutsum;
				bsum -= boutsum;

				stackstart = stackpointer - radius + div;
				sir = stack[stackstart % div];

				routsum -= sir[0];
				goutsum -= sir[1];
				boutsum -= sir[2];

				if (x == 0) {
					vmin[y] = Math.min(y + r1, hm) * w;
				}
				p = x + vmin[y];

				sir[0] = r[p];
				sir[1] = g[p];
				sir[2] = b[p];

				rinsum += sir[0];
				ginsum += sir[1];
				binsum += sir[2];

				rsum += rinsum;
				gsum += ginsum;
				bsum += binsum;

				stackpointer = (stackpointer + 1) % div;
				sir = stack[stackpointer];

				routsum += sir[0];
				goutsum += sir[1];
				boutsum += sir[2];

				rinsum -= sir[0];
				ginsum -= sir[1];
				binsum -= sir[2];

				yi += w;
			}
		}

		Log.i("pix", w + " " + h + " " + pix.length);
		bitmap.setPixels(pix, 0, w, 0, 0, w, h);
		return (bitmap);
	}
}
