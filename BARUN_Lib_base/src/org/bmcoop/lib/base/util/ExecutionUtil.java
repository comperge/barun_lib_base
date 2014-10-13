package org.bmcoop.lib.base.util;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;

/**
 * Execution Util
 */
public class ExecutionUtil {
	public static final class EU extends ExecutionUtil {

	}

	public static void createShortCut(Context context, String shortcut_name, int drawable_id_shortcuticon, Intent intent) {
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.addFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);

		Intent shortcutintent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
		shortcutintent.putExtra("duplicate", false);
		shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_NAME, shortcut_name);
		shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, Intent.ShortcutIconResource.fromContext(context, drawable_id_shortcuticon));
		shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, intent);

		context.sendBroadcast(shortcutintent);
	}

	public static String getApplicationLable(Context context, String packageName) {
		try {
			PackageManager pm = context.getPackageManager();
			ApplicationInfo applicationInfo = pm.getApplicationInfo(packageName, 0);
			return pm.getApplicationLabel(applicationInfo).toString();
		} catch (NameNotFoundException e) {
		}
		return "";
	}
	public static Drawable getApplicationIcon(Context context, String packageName) {
		try {
			return context.getPackageManager().getApplicationIcon(packageName);
		} catch (NameNotFoundException e) {
		}
		return null;
	}

	public static String getApplicationIconUri(Context context, String packageName) {
		try {
			final PackageManager pm = context.getPackageManager();
			final ApplicationInfo appInfo = pm.getApplicationInfo(packageName, 0);
			final Resources res = pm.getResourcesForApplication(appInfo);
			return "android.resource://" + packageName + "/drawable/" + res.getResourceEntryName(appInfo.icon);
//			if (appInfo.icon == 0)
//				uri = "android.resource://" + context.getPackageName() + "/drawable/icon";
		} catch (NameNotFoundException e) {
		}
		return Uri.EMPTY.toString();
	}

	public static Intent getLaunchIntent(Context context, String packageName) {
		final PackageManager pm = context.getPackageManager();
		return pm.getLaunchIntentForPackage(packageName);
	}
	public static List<ApplicationInfo> getInstalledApplications(Context context) {
		return context.getPackageManager().getInstalledApplications(PackageManager.GET_META_DATA);
	}

	public static List<ResolveInfo> queryIntentActivities(Context context, Intent intent) {
		PackageManager pm = context.getPackageManager();
		intent.addCategory(Intent.CATEGORY_LAUNCHER);
		List<ResolveInfo> appinfo = pm.queryIntentActivities(intent, 0);
		return appinfo;
	}

	public static int getVersionCode(Context context, String packageName) {
		try {
			PackageManager pm = context.getPackageManager();
			PackageInfo info = pm.getPackageInfo(packageName, 0);
			return info.versionCode;
		} catch (NameNotFoundException e) {
		}
		return -1;
	}

	public static String getVersionName(Context context, String packageName) {
		String appVersion = "";
		try {
			PackageManager pm = context.getPackageManager();
			PackageInfo info = pm.getPackageInfo(packageName, 0);
			return info.versionName;
		} catch (NameNotFoundException e) {
		}
		return appVersion;
	}

//		try {
//			PackageManager pm = context.getPackageManager();
//			ApplicationInfo appInfo = pm.getApplicationInfo(strPackageName, 0);
//			Resources iconRes = pm.getResourcesForApplication(appInfo);
//			String strName = iconRes.getResourceName(appInfo.icon);
//			String strAppName = iconRes.getString(appInfo.labelRes);
	//
//			Intent intent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
//			intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, pm.getLaunchIntentForPackage(strPackageName));
//			intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, strAppName);
	//
//			ShortcutIconResource shortCutIconResource = new Intent.ShortcutIconResource();
//			shortCutIconResource.packageName = strPackageName;
//			shortCutIconResource.resourceName = strName;
//			intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, shortCutIconResource);
//			intent.putExtra("duplicate", false);
//			context.sendBroadcast(intent);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

}
