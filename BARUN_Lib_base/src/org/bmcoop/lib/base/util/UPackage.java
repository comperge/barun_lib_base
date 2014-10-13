package org.bmcoop.lib.base.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import android.app.ActivityManager;
import android.app.ActivityManager.RecentTaskInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PermissionInfo;
import android.graphics.drawable.Drawable;

public class UPackage {

	public static String getAppLabel(Context context) {
		PackageManager pacMan = (PackageManager) context.getPackageManager();
		ApplicationInfo appInfo = null;
		try {
			appInfo = pacMan.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		if (appInfo != null) {
			return (String) pacMan.getApplicationLabel(appInfo);
		}
		return null;
	}

	public static String getAppLabel(Context context, String packageName) {
		PackageManager pacMan = (PackageManager) context.getPackageManager();
		ApplicationInfo appInfo = null;
		try {
			appInfo = pacMan.getApplicationInfo(packageName, PackageManager.GET_META_DATA);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		if (appInfo != null) {
			return (String) pacMan.getApplicationLabel(appInfo);
		}
		return null;
	}

	private static String[] getAppLabel(PackageManager pacMan, List<ActivityManager.RecentTaskInfo> infoList, int i) {
		ApplicationInfo appInfo = null;
		String[] reStr = new String[2];
		try {
			RecentTaskInfo packInfo = infoList.get(i);
			Intent intent = packInfo.baseIntent;
			reStr[0] = intent.getComponent().getPackageName();
			appInfo = pacMan.getApplicationInfo(reStr[0], 0);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		if (appInfo != null) {
			reStr[1] = (String) pacMan.getApplicationLabel(appInfo);
			return reStr;
		}
		return null;
	}

	public static ArrayList<HashMap<String, String>> getAppLabelRecent(Context context, int appRecentCount) {
		PackageManager pacMan = (PackageManager) context.getPackageManager();
		ActivityManager actMan = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RecentTaskInfo> infoList = actMan.getRecentTasks(appRecentCount,
				Intent.FLAG_ACTIVITY_NEW_TASK);
		ArrayList<HashMap<String, String>> appList = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < infoList.size(); i++) {
			String[] appLabel = getAppLabel(pacMan, infoList, i);
			if (appLabel != null) {
				HashMap<String, String> hash = new HashMap<String, String>();
				hash.put("packageName", appLabel[0]);
				hash.put("label", appLabel[1]);
				appList.add(hash);
			}
		}

		return appList;
	}

	public static String getPackage(Context context) {
		return context.getPackageName();
	}

	public static Drawable getPackageIcon(Context context, String packageName) {
		PackageManager packMan = (PackageManager) context.getPackageManager();
		try {
			return packMan.getApplicationIcon(packageName);
		} catch (NameNotFoundException e) {
			// e.printStackTrace();
			return null;
		}
	}

	public static String getPackageInstallDate(Context context, String packageName) {
		PackageManager packMan = (PackageManager) context.getPackageManager();
		ApplicationInfo appInfo = null;
		try {
			appInfo = packMan.getApplicationInfo(packageName, PackageManager.GET_META_DATA);
		} catch (NameNotFoundException e) {
			// e.printStackTrace();
			return "";
		}

		String appFile = appInfo.sourceDir;
		long installed = new File(appFile).lastModified(); // Epoch Time
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
		String dateString = formatter.format(new Date(installed));

		if (dateString == null) {
			dateString = "";
		}
		return dateString;
	}

	/**
	 * @param appInfo
	 * @return 2013-01-01
	 */
	public static String getPackageInstallDate(ApplicationInfo appInfo) {
		String appFile = appInfo.sourceDir;
		long installed = new File(appFile).lastModified(); // Epoch Time
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
		String dateString = formatter.format(new Date(installed));

		return dateString;
	}

	public static String getPackageInstallMemory(Context context, String packageName) {
		PackageManager packMan = (PackageManager) context.getPackageManager();
		ApplicationInfo appInfo = null;
		try {
			appInfo = packMan.getApplicationInfo(packageName, PackageManager.GET_META_DATA);
		} catch (NameNotFoundException e) {
			// e.printStackTrace();
			return "";
		}

		String reStr = "";
		try {
			String appFile = appInfo.sourceDir;
			long installed = new File(appFile).length();
			installed = installed + new File(appInfo.dataDir).length();
			double nCalcVal = Math.round(((double) installed / 1000000.0) * Math.pow(10, 1)) / Math.pow(10, 1);
			reStr = nCalcVal + "M";
		} catch (Exception e) {
			return "";
		}

		return reStr;
	}

	public static String getPackageInstallMemory(ApplicationInfo appInfo) {
		String reStr = "";
		try {
			String appFile = appInfo.sourceDir;
			long installed = new File(appFile).length();
			installed = installed + new File(appInfo.dataDir).length();
			double nCalcVal = Math.round(((double) installed / 1000000.0) * Math.pow(10, 1)) / Math.pow(10, 1);
			reStr = nCalcVal + "M";
		} catch (Exception e) {
			return "";
		}

		return reStr;
	}

	public static String getPackageRunningTop(Context context) {
		ActivityManager actMan = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		ComponentName localComponentName = ((ActivityManager.RunningTaskInfo) actMan.getRunningTasks(1).get(0)).topActivity;
		return localComponentName.getPackageName();
	}

	public static String[] getPermissionInfo(Context context, String permName) {
		PackageManager packMan = (PackageManager) context.getPackageManager();
		PermissionInfo permissionInfo;
		String[] reStr = { "", "" };
		try {
			permissionInfo = packMan.getPermissionInfo(permName, PackageManager.GET_META_DATA);
			reStr[0] = (String) permissionInfo.loadLabel(packMan);
			reStr[1] = (String) permissionInfo.loadDescription(packMan);
		} catch (NameNotFoundException e) {
			// e.printStackTrace();
			reStr = null;
		}
		return reStr;
	}

	public static boolean isPackageExist(Context context, String packageName) {
		PackageManager pacMan = (PackageManager) context.getPackageManager();
		ApplicationInfo appInfo;
		try {
			appInfo = pacMan.getApplicationInfo(packageName, PackageManager.GET_META_DATA);
		} catch (NameNotFoundException e) {
			// e.printStackTrace();
			return false;
		}
		if (appInfo != null && !appInfo.packageName.equals("")) {
			return true;
		}
		return false;
	}

	public static boolean isPackageGooglePlaySource(Context context) {
		PackageManager packMan = (PackageManager) context.getPackageManager();
		String packageName = getPackage(context);
		String unknown = null;
		try {
			unknown = packMan.getInstallerPackageName(packageName);
		} catch (Exception e) {
			unknown = null;
			return false;
		}
		if (unknown == null) {
			return false;
		}
		return true;
	}

	public static boolean isPackageGooglePlaySource(Context context, String packageName) {
		PackageManager packMan = (PackageManager) context.getPackageManager();
		String unknown = null;
		try {
			unknown = packMan.getInstallerPackageName(packageName);
		} catch (Exception e) {
			unknown = null;
			return false;
		}
		if (unknown == null) {
			return false;
		}
		return true;
	}

	public static boolean isPackageGooglePlaySource(PackageManager packMan, String packageName) {
		String unknown = null;
		try {
			unknown = packMan.getInstallerPackageName(packageName);
		} catch (Exception e) {
			return false;
		}
		if (unknown == null) {
			return false;
		}
		return true;
	}
	
	public static ArrayList<String> getPackageNameList(Context context, boolean havePreload){
		ArrayList<String> reData = new ArrayList<String>();
		PackageManager packMan = (PackageManager) context.getPackageManager();
		List<PackageInfo> packages = packMan.getInstalledPackages(PackageManager.GET_META_DATA);
		for (PackageInfo pack : packages) {
			if(havePreload){
				reData.add(pack.packageName);
			}else{
				if ((pack.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 1) {
					reData.add(pack.packageName);
				}
			}
		}
		return reData;
	}

	public static boolean isPackagePreload(Context context, String packageName) {
		PackageManager packMan = (PackageManager) context.getPackageManager();
		PackageInfo packages = null;
		try {
			packages = packMan.getPackageInfo(packageName, PackageManager.GET_PERMISSIONS);
		} catch (NameNotFoundException e) {
			return false;
		}

		if (packages != null && (packages.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 1) {
			return true;
		}
		return false;
	}

	public static boolean isPackagePreload(PackageInfo packageInfo) {
		if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 1) {
			return true;
		}
		return false;
	}

	public static boolean isPackageRunning(Context context, String packageName) {
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningAppProcessInfo> info = am.getRunningAppProcesses();

		for (Iterator iterator = info.iterator(); iterator.hasNext();) {
			RunningAppProcessInfo runningTaskInfo = (RunningAppProcessInfo) iterator.next();
			if (runningTaskInfo.processName.equals(packageName)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isServiceRunning(Context context, String packageName) {
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningServiceInfo> info = am.getRunningServices(200);

		for (Iterator iterator = info.iterator(); iterator.hasNext();) {
			RunningServiceInfo runningTaskInfo = (RunningServiceInfo) iterator.next();
			if (runningTaskInfo.service.getPackageName().equals(packageName)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param context
	 * @param packageName
	 *            com.mcu.lib
	 * @param className
	 *            .asset.ClassName
	 * @return
	 */
	public static boolean isServiceRunning(Context context, String packageName, String className) {
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningServiceInfo> info = am.getRunningServices(200);

		for (Iterator iterator = info.iterator(); iterator.hasNext();) {
			RunningServiceInfo runningTaskInfo = (RunningServiceInfo) iterator.next();
			if (runningTaskInfo.process.equals(packageName)
					&& runningTaskInfo.service.getClassName().equals(packageName + className)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * App을 직접 실행하기 위한. startActvity(getIntentLaunch())로 하면됨
	 * 
	 * @param context
	 * @param packageName
	 * @return
	 */
	public static Intent getIntentLaunch(Context context, String packageName) {
		PackageManager pacMan = (PackageManager) context.getPackageManager();
		return pacMan.getLaunchIntentForPackage(packageName);
	}
}
