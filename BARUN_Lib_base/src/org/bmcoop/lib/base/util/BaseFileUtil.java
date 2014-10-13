package org.bmcoop.lib.base.util;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.nio.channels.FileChannel;
import java.util.UUID;

import org.bmcoop.lib.base.BuildConfig;


import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Environment;


/**
 * @author djrain
 * 
 *         <pre>
 * 기능 : ssp관련 파일IO Util
 *  
 * file     => xxxxxxxxxx.xxx              filename and ext
 * filename => xxxxxxxxxxx                 has no ext
 * pathfile => /xxxx/xxxxx/xxxxxxxxx.xxx   absolute path + filename + ext
 * ext      => .xxx                        note : has dot(".")
 * 
 * &lt;uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
 * </pre>
 */
public class BaseFileUtil {
	public static final class FU extends BaseFileUtil {

	}
	private static File ROOT;
	/** 파일네임에 적합하지 않은것 */
	private static final String regularExpressionVFilename = "\\\\/|[&{}?=/\\\\: <>*|\\]\\[\\\"\\']";

	public static void CREATE(Context context) {
		ROOT = context.getExternalFilesDir(null);
	}
	public static void CREATE(String rootFolder) {
		ROOT = makePath(Environment.getExternalStorageDirectory() + "/" + rootFolder);
	}

	public static File getRoot() {
		if (BuildConfig.DEBUG && ROOT == null)
			throw new NullPointerException("yet created!");
		return ROOT;
	}
	protected static File makePath(final Object path) {
		File fileDir = toFile(path);
		if (!fileDir.isDirectory())
			fileDir.mkdirs();
		return fileDir;
	}
	protected static File makePath(final String path) {
		File fileDir = new File(path);
		if (!fileDir.isDirectory())
			fileDir.mkdirs();
		return fileDir;
	}
	public static String uName() {
		return UUID.randomUUID().toString();
	}
	public static String vName(String name) {
		return name.replaceAll(regularExpressionVFilename, "_");
	}

	public static String getExt(Object file) {
		String filename = toString(file);
		return filename.substring(filename.lastIndexOf(".") + ".".length());
	}
	public static File getPathfile(String filename) {
		return toFile(getRoot() + "/" + filename);
	}

	public static long length(Object pathfile) {
		try {
			return toFile(pathfile).length();
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return -1;
	}

	public static boolean exists(Object pathfile) {
		try {
			return toFile(pathfile).exists();
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return false;
	}

	public static boolean delete(Object pathfile) {
		try {
			return toFile(pathfile).delete();
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return false;
	}

	public static File toFile(Object file) {
		File f = null;
		if (file instanceof Uri) {
			f = new File(((Uri) file).getSchemeSpecificPart());
		} else if (file instanceof String) {
			f = new File((String) file);
		} else if (file instanceof File) {
			f = (File) file;
		} else {
			throw new UnsupportedOperationException("Must the file is Uri or String(pathfile) or File class");
		}
		return f;
	}
	public static String toString(Object file) {
		String filename = null;
		if (file instanceof Uri) {
			filename = ((Uri) file).getSchemeSpecificPart();
		} else if (file instanceof String) {
			filename = new File((String) file).getName();
		} else if (file instanceof File) {
			filename = ((File) file).getName();
		} else {
			throw new UnsupportedOperationException("Must the file is Uri or String(pathfile) or File class");
		}
		return filename;
	}
	public static Uri toUri(Object pathfile) {
		return Uri.fromFile(toFile(pathfile));
	}

	public static File getTempFile(String prefix, String suffix, File path) {
		try {
			return File.createTempFile(prefix, suffix, path);
		} catch (IOException e) {
//			e.printStackTrace();
		}
		return null;
	}
	public static File getTempFile(String prefix, String suffix) {
		return getTempFile(prefix, suffix, getRoot());
	}
//	public static File getTempTime(String prefix, String suffix) {
//		return getTempFile(prefix + "_" + DT.now_yyyymmddhhmmss() + "_", suffix, getRoot());
//	}

	public static File[] getfiles(final String ext) {
		return getRoot().listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				if (ext == null)
					return !pathname.isDirectory();
				else
					return !pathname.isDirectory() && pathname.getName().endsWith("." + ext);
			}
		});
	}

	@SuppressWarnings("resource")
	public static void copy(File source, File target) throws IOException {
		FileChannel inChannel = new FileInputStream(source).getChannel();
		FileChannel outChannel = new FileOutputStream(target, false).getChannel();
		try {
			inChannel.transferTo(0, inChannel.size(), outChannel);
		} finally {
			if (inChannel != null)
				inChannel.close();
			if (outChannel != null)
				outChannel.close();
		}
	}

	public static void copy(String source, String target) throws IOException {
		copy(new File(source), new File(target));
	}

	public static void copyDB(SQLiteDatabase source, File target) throws IOException {
		copy(new File(source.getPath()), target);
	}

	/**
	 * @param pathfile
	 * @param obj
	 * <br>
	 *            not used just type<br>
	 *            must null
	 * @return T obj
	 * @throws IOException
	 * @throws StreamCorruptedException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings({"unchecked", "resource"})
	public static <T extends Serializable> T load(String pathfile, T obj) throws StreamCorruptedException, IOException, ClassNotFoundException {
		if (obj != null)
			throw new IllegalArgumentException("obj must null");

//		Log.l(pathfile, obj);
		FileInputStream fis = new FileInputStream(pathfile);
		ObjectInputStream ois = new ObjectInputStream(fis);
		return (T) ois.readObject();
	}
	public static <T extends Serializable> boolean save(String pathfile, T obj) throws IOException {
//		Log.l(obj, pathfile);

		FileOutputStream fos = new FileOutputStream(pathfile);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(obj);
		oos.flush();
		fos.close();
		return true;
	}
	public static boolean save(String pathfile, String str) throws IOException {
//		Log.l(obj, pathfile);

		FileOutputStream fos = new FileOutputStream(pathfile);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.write(str.getBytes());
		oos.flush();
		fos.close();
		return true;
	}

	public static void writeFile(Context context, String filename, String value) {
		try {
			File dir = context.getExternalFilesDir(null);
			File pathfile = new File(dir, "temp_" + filename + ".txt");
			FileOutputStream fos = new FileOutputStream(pathfile);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeUTF(value);
			oos.flush();
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static String readFile(Context context, String filename) {
		try {
			File dir = context.getExternalFilesDir(null);
			File pathfile = new File(dir, "temp_" + filename + ".txt");
			FileInputStream fos = new FileInputStream(pathfile);
			ObjectInputStream oos = new ObjectInputStream(fos);
			String value = oos.readUTF();
			oos.close();
			fos.close();
			return value;
		} catch (IOException e) {
		}
		return "";
	}

	public static void copyAssets(Context context, String parent, boolean overwrite) {

		AssetManager assetManager = context.getAssets();
		try {
			File root = new File(getRoot(), parent);
			if (root != null && !exists(root)) {
				Log.l("makePath", root);
				makePath(root);
			}

			byte[] buffer = new byte[(int) (Runtime.getRuntime().maxMemory() / 4)];;
			String[] files = assetManager.list(parent);
			for (String filename : files) {
				final String source = parent + (parent.length() > 0 ? "/" : "") + filename;
				final String target = getRoot().getAbsolutePath() + "/" + source;

				if (!overwrite && exists(target)) {
					Log.l("pass", source);
					continue;
				}

				try {
					assetManager.open(source);
				} catch (FileNotFoundException e) {
					Log.l("source is folder", source);
					buffer = null;
					copyAssets(context, source, overwrite);
					continue;
				}

				try {
					Log.l(source + "\n==>" + target);
					InputStream in = assetManager.open(source);
					OutputStream out = new FileOutputStream(target);
					int read;
					while ((read = in.read(buffer)) != -1) {
						out.write(buffer, 0, read);
					}
					in.close();
					out.close();
				} catch (IOException e) {
					Log.l("Failed to copy asset file: " + filename, e);
//					Log.e("tag", "Failed to copy asset file: " + filename, e);
				}
			}
			buffer = null;
		} catch (IOException e) {
			Log.l("Failed to get asset file list.");
//			Log.e("tag", "Failed to get asset file list.", e);
		}
	}
}
