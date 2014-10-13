package org.bmcoop.lib.base.util;

import java.util.List;

import android.content.Context;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;

public class UTelephone {
	private static UTelephone singleton;
	private Uevent mUevent;

	public static ITelephone with(Context pCon) {
		if (singleton == null) {
			singleton = new UTelephone();
		}
		return singleton.getTelephone(pCon);
	}

	private ITelephone getTelephone(Context pCon) {
		return new ITelephone(pCon);
	}

	public class ITelephone {
		private TelephonyManager mTelephonyManager;

		public ITelephone(Context pCon) {
			mTelephonyManager = (TelephonyManager) pCon.getSystemService(Context.TELEPHONY_SERVICE);
		}

		public List<NeighboringCellInfo> getNeighboringCellInfo_list() {
			//대부분 삼성폰은 지원하지 않는다고 함
			List<NeighboringCellInfo> neighboringCellInfos = mTelephonyManager.getNeighboringCellInfo();
			
			for (NeighboringCellInfo neighboringCellInfo : neighboringCellInfos) {
				neighboringCellInfo.getCid();
				neighboringCellInfo.getLac();
				neighboringCellInfo.getPsc();
				neighboringCellInfo.getNetworkType();
				neighboringCellInfo.getRssi();

				Log.d("cellp", neighboringCellInfo.toString());
			}
			return neighboringCellInfos;
		}

		public ITelephone setUevent(Uevent new_Uevent) {
			mUevent = new_Uevent;
			mTelephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_FORWARDING_INDICATOR);
			mTelephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
			mTelephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_CELL_LOCATION);
			mTelephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_DATA_ACTIVITY);
			mTelephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_DATA_CONNECTION_STATE);
			mTelephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_MESSAGE_WAITING_INDICATOR);
			mTelephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_SERVICE_STATE);
			mTelephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_SIGNAL_STRENGTH);
			mTelephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);

			return this;
		}

		public void close() {
			mTelephonyManager.listen(null, PhoneStateListener.LISTEN_NONE);
		}

		PhoneStateListener phoneStateListener = new PhoneStateListener() {

			public void onCallForwardingIndicatorChanged(boolean cfi) {
				if (mUevent != null) {
					mUevent.onEvent(ULog.with().addTag("onCallForwardingIndicatorChanged").add("cfi", cfi).getString());
				}
			}

			public void onCallStateChanged(int state, String incomingNumber) {
				if (mUevent != null) {
					mUevent.onEvent(ULog.with().addTag("onCallStateChanged").add("state", state)
							.add("incomingNumber", incomingNumber).getString());
				}
			}

			public void onDataActivity(int direction) {
				mUevent.onEvent(ULog.with().addTag("onDataActivity").add("direction", direction).getString());
			}

			public void onDataConnectionStateChanged(int state) {
				mUevent.onEvent(ULog.with().addTag("onDataConnectionStateChanged").add("state", state).getString());
			}

			public void onMessageWaitingIndicatorChanged(boolean mwi) {
				mUevent.onEvent(ULog.with().addTag("onMessageWaitingIndicatorChanged").add("mwi", mwi).getString());
			}

			public void onServiceStateChanged(ServiceState serviceState) {
				mUevent.onEvent(ULog.with().addTag("onServiceStateChanged")
						.add("serviceState", serviceState.getOperatorAlphaLong()).getString());
			}

			public void onSignalStrengthChanged(int asu) {
				mUevent.onEvent(ULog.with().addTag("onSignalStrengthChanged").add("asu", asu).getString());
			}

			public void onCellLocationChanged(CellLocation location) {
				GsmCellLocation gsmCellLocation = (GsmCellLocation) location;
				if (mUevent != null) {
					mUevent.onEvent(ULog.with().addTag("onCellLocationChanged").add("cid", gsmCellLocation.getCid())
							.add("lac", gsmCellLocation.getLac()).add("psc", gsmCellLocation.getPsc()).getString());
//					mUevent.onCellLocationChanged(gsmCellLocation.getCid(), gsmCellLocation.getLac(),
//							gsmCellLocation.getPsc());
				}
			}

			/**
			 * invoked when data connection state changes (only way to get the
			 * network type)
			 */
			public void onDataConnectionStateChanged(int state, int networkType) {
				if (mUevent != null) {
					mUevent.onEvent(ULog.with().addTag("onDataConnectionStateChanged").add("state", state)
							.add("networkType", networkType).getString());
//					mUevent.onDataConnectionStateChanged(state, networkType);
				}
			}

			/** Callback invoked when network signal strengths changes. */
			public void onSignalStrengthsChanged(SignalStrength signalStrength) {
				if (mUevent != null) {
					mUevent.onEvent(ULog.with().addTag("onSignalStrengthsChanged")
							.add("signalStrength", signalStrength.getGsmSignalStrength()).getString());
//					mUevent.onSignalStrengthsChanged(signalStrength.getGsmSignalStrength());
				}
			}
		};

		public CellLocation getCellLocation() {
			return mTelephonyManager.getCellLocation();

			// api 17
//			List<CellInfo> cellInfos = (List<CellInfo>) this.telephonyManager.getAllCellInfo();
			//
//					for(CellInfo cellInfo : cellInfos)
//					{
//					    CellInfoGsm cellInfoGsm = (CellInfoGsm) cellInfo;
			//
//					    CellIdentityGsm cellIdentity = cellInfoGsm.getCellIdentity();
//					    CellSignalStrengthGsm cellSignalStrengthGsm = cellInfoGsm.getCellSignalStrength();
			//
//					    Log.d("cell", "registered: "+cellInfoGsm.isRegistered());
//					    Log.d("cell", cellIdentity.toString());         
//					    Log.d("cell", cellSignalStrengthGsm.toString());
//					}
		}

		public int getCellLocation_cid() {
			GsmCellLocation gsmCellLocation = (GsmCellLocation) mTelephonyManager.getCellLocation();
			return gsmCellLocation.getCid();
		}

		public int getCellLocation_lac() {
			GsmCellLocation gsmCellLocation = (GsmCellLocation) mTelephonyManager.getCellLocation();
			return gsmCellLocation.getLac();
		}

		public int getCellLocation_psc() {
			GsmCellLocation gsmCellLocation = (GsmCellLocation) mTelephonyManager.getCellLocation();
			return gsmCellLocation.getPsc();
		}
	}

	public interface Uevent {
//		void onCellLocationChanged(int cid, int lac, int psc);
//
//		void onDataConnectionStateChanged(int state, int networkType);
//
//		void onSignalStrengthsChanged(int signalStrength);

		void onEvent(String result);
	}

	public static String getData_networkState(int networkState) {
		if (networkState == TelephonyManager.DATA_ACTIVITY_DORMANT) {
			return "DATA_ACTIVITY_DORMANT";
		} else if (networkState == TelephonyManager.DATA_ACTIVITY_IN) {
			return "DATA_ACTIVITY_IN";
		} else if (networkState == TelephonyManager.DATA_ACTIVITY_INOUT) {
			return "DATA_ACTIVITY_INOUT";
		} else if (networkState == TelephonyManager.DATA_ACTIVITY_NONE) {
			return "DATA_ACTIVITY_NONE";
		} else if (networkState == TelephonyManager.DATA_ACTIVITY_OUT) {
			return "DATA_ACTIVITY_OUT";
		} else if (networkState == TelephonyManager.DATA_CONNECTED) {
			return "DATA_CONNECTED";
		} else if (networkState == TelephonyManager.DATA_CONNECTING) {
			return "DATA_CONNECTING";
		} else if (networkState == TelephonyManager.DATA_DISCONNECTED) {
			return "DATA_DISCONNECTED";
		} else if (networkState == TelephonyManager.DATA_SUSPENDED) {
			return "DATA_SUSPENDED";
		}

		return "";
	}

	public static String getData_networkType(int networkType) {
		if (networkType == TelephonyManager.NETWORK_TYPE_1xRTT) {
			return "NETWORK_TYPE_1xRTT";
		} else if (networkType == TelephonyManager.NETWORK_TYPE_CDMA) {
			return "NETWORK_TYPE_CDMA";
		} else if (networkType == TelephonyManager.NETWORK_TYPE_EDGE) {
			return "NETWORK_TYPE_EDGE";
		} else if (networkType == TelephonyManager.NETWORK_TYPE_EHRPD) {
			return "NETWORK_TYPE_EHRPD";
		} else if (networkType == TelephonyManager.NETWORK_TYPE_EVDO_0) {
			return "NETWORK_TYPE_EVDO_0";
		} else if (networkType == TelephonyManager.NETWORK_TYPE_EVDO_A) {
			return "NETWORK_TYPE_EVDO_A";
		} else if (networkType == TelephonyManager.NETWORK_TYPE_EVDO_B) {
			return "NETWORK_TYPE_EVDO_B";
		} else if (networkType == TelephonyManager.NETWORK_TYPE_GPRS) {
			return "NETWORK_TYPE_GPRS";
		} else if (networkType == TelephonyManager.NETWORK_TYPE_HSDPA) {
			return "NETWORK_TYPE_HSDPA";
		} else if (networkType == TelephonyManager.NETWORK_TYPE_HSPA) {
			return "NETWORK_TYPE_HSPA";
		} else if (networkType == TelephonyManager.NETWORK_TYPE_HSPAP) {
			return "NETWORK_TYPE_HSPAP";
		} else if (networkType == TelephonyManager.NETWORK_TYPE_HSUPA) {
			return "NETWORK_TYPE_HSUPA";
		} else if (networkType == TelephonyManager.NETWORK_TYPE_IDEN) {
			return "NETWORK_TYPE_IDEN";
		} else if (networkType == TelephonyManager.NETWORK_TYPE_LTE) {
			return "NETWORK_TYPE_LTE";
		} else if (networkType == TelephonyManager.NETWORK_TYPE_UMTS) {
			return "NETWORK_TYPE_UMTS";
		} else if (networkType == TelephonyManager.NETWORK_TYPE_UNKNOWN) {
			return "NETWORK_TYPE_UNKNOWN";
		}
		return "";
	}
}