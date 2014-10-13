package org.bmcoop.lib.base.util;

import java.io.Serializable;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.bmcoop.lib.base.util.UTimer.WTimerTick;


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;


public class UWifi {

	private UWifi() {
	}

//    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
//	    <uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />
	/**
	 * @param pCon
	 *            3초마다 5회 실시함
	 * @return
	 */
	public static WWifi with(Context pCon) {
		return new WWifi(pCon, 3, 5);
	}

	public static WWifi with(Context pCon, int scanningSecond, int scanningRepeat) {
		return new WWifi(pCon, scanningSecond, scanningRepeat);
	}

	public static class WWifi {
		private Callback mCallback;
		private WifiManager mWifiMan;
		private WTimerTick mWTimerTick;
		private int mScanningSecond;
		private int mScanningRepeat;
		private boolean isWifiOn;

		public WWifi(Context pCon, int scanningSecond, int scanningRepeat) {
			mScanningSecond = scanningSecond;
			mScanningRepeat = scanningRepeat;
			mWifiMan = (WifiManager) pCon.getSystemService(Activity.WIFI_SERVICE);
		}

		@TargetApi(18)
		private boolean isScanAlways(WifiManager wifiManager) {
			boolean redata = false;
			try {
//				redata = wifiManager.isScanAlwaysAvailable();
			} catch (NoSuchMethodError e) {
//				e.printStackTrace();
			}
			return redata;
		}

		public WWifi forStart_scan(Callback WWifi_Callback) {
			mCallback = WWifi_Callback;
			isWifiOn = mWifiMan.isWifiEnabled();
			if (!isScanAlways(mWifiMan)) {
				mWifiMan.setWifiEnabled(true);	
			}

			mWTimerTick = UTimer.withRepeat(mScanningSecond, mScanningRepeat).forStart_autoClose(new WTimerTick.Callback() {

				@Override
				public void onTick(int repeatCount) {
					ULog.with().add("onTick", repeatCount).e();
					mWifiMan.startScan();
					ArrayList<WifiEnty> redata = getWifiList_scan();
					if (redata != null) {
						if (mCallback != null) {
							mCallback.onScan(redata);
						}
						closeWifi();
						mWTimerTick.forClose();
//						ULog.with().add("mWTimerTick", "forClose").e();
					}
				}

				@Override
				public void onEnd() {
					mWifiMan.startScan();
					if (mCallback != null) {
						mCallback.onScan(getWifiList_scan());
					}
					closeWifi();
//					ULog.with().add("onEnd", "close").e();
				}
			});
			return this;
		}

		private void closeWifi() {
			if (!isWifiOn) {
				if (!mWifiMan.setWifiEnabled(false)) {
					mWifiMan.setWifiEnabled(false);
				}
			}
		}

		public void forClose() {
			mCallback = null;
			mWTimerTick.forClose();
			closeWifi();
		}

		private ArrayList<WifiEnty> getWifiList_scan() {
			ArrayList<WifiEnty> redata = null;
			List<ScanResult> scanList = mWifiMan.getScanResults();
			if (scanList != null && scanList.size() != 0) {
				redata = new ArrayList<WifiEnty>();
//				ULog.with().add("result.SSID", scanList.get(0).SSID).e();
				for (int i = 0; i < scanList.size(); i++) {
					ScanResult result = scanList.get(i);
					redata.add(new WifiEnty(result.SSID, result.BSSID, result.level, result.capabilities, result.frequency));
				}
				redata = new WifiEnty().sortLevel(redata);
			}
			return redata;
		}

		public static interface Callback {
			void onScan(ArrayList<WifiEnty> wifiList);
		}
	}

	public static class WifiEnty implements Serializable {
		private static final long serialVersionUID = 3418862274992025805L;

		private String ssid;
		private String bssid;
		private String capabilities;
		private int level;
		private int frequency;

		public WifiEnty() {
		}

		public WifiEnty(String ssid, String bssid, int level, String capabilities, int frequency) {
			this.ssid = ssid;
			this.bssid = bssid;
			this.level = level;
			this.capabilities = capabilities;
			this.frequency = frequency;
		}

		private Comparator<WifiEnty> myComparator = new Comparator<WifiEnty>() {
			private final Collator collator = Collator.getInstance();

			@Override
			public int compare(WifiEnty object1, WifiEnty object2) {
				return collator.compare(String.valueOf(object1.getLevel()), String.valueOf(object2.getLevel()));
			}
		};

		public ArrayList<WifiEnty> sortLevel(ArrayList<WifiEnty> wifiList) {
			Collections.sort(wifiList, myComparator);
			return wifiList;
		}

		/**
		 * @return wifi 이름
		 */
		public String getSsid() {
			return ssid;
		}

		public void setSsid(String ssid) {
			this.ssid = ssid;
		}

		/**
		 * @return wifi Mac address
		 */
		public String getBssid() {
			return bssid;
		}

		public void setBssid(String bssid) {
			this.bssid = bssid;
		}

		/**
		 * @return 암호화 방식
		 */
		public String getCapabilities() {
			return capabilities;
		}

		public void setCapabilities(String capabilities) {
			this.capabilities = capabilities;
		}

		/**
		 * @return dBm
		 */
		public int getLevel() {
			return level;
		}

		public void setLevel(int level) {
			this.level = level;
		}

		/**
		 * @return wifi 주파수
		 */
		public int getFrequency() {
			return frequency;
		}

		public void setFrequency(int frequency) {
			this.frequency = frequency;
		}

	}

}