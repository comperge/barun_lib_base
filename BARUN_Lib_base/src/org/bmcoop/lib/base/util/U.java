package org.bmcoop.lib.base.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;

public class U {

	public static String res(int R, Context pCon, String str) {
		if (R == 0) {
			return str;
		} else {
			return pCon.getResources().getString(R);
		}
	}

	public static boolean isValid(String text) {
		if (text != null && text.length() != 0) {
			return true;
		}
		return false;
	}

	public static boolean isValid_not(String text) {
		if (text != null && text.length() != 0) {
			return false;
		}
		return true;
	}

	public static boolean isValid(ArrayList<?> arrayList, int index) {
		if (arrayList != null && arrayList.size() > 0) {
			if (arrayList.size() > index) {
				return true;
			}
		}
		return false;
	}

	public static boolean isValid_email(String email) {
		Pattern p = Pattern.compile("^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$");
		Matcher m = p.matcher(email);
		return m.matches();
	}

	public static boolean isNull(String text) {
		if (text == null) {
			return true;
		}
		return false;
	}

	public static boolean isNull_not(String text) {
		if (text != null) {
			return true;
		}
		return false;
	}

	/**
	 * 현재 단말내에서 실행중인 서비스의 실행 여부 반환
	 * 
	 * ex) isServiceAlive(context, "com.android.app.common.ExService");
	 * 
	 * @param pCon
	 * @param serviceClassName 서비스 클래스 명을 패키지를 포함하여 작성
	 * @return
	 */
	public static boolean isServiceAlive(Context pCon, String serviceClassName) {
		
		if (serviceClassName == null || "".equals(serviceClassName))
			return false;
		
		ActivityManager am = (ActivityManager) pCon.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningServiceInfo> rs = am.getRunningServices(100);
		
		for(RunningServiceInfo service : rs)
		{
			if(service.service.getClassName().equals(serviceClassName))
				return true;
		}

		return false;
	}
}
