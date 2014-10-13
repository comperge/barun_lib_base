package org.bmcoop.lib.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class UBroadcastReceiver {

	private UBroadcastReceiver() {
	}

	public static IReceiver with(Context pCon) {
		return new UBroadcastReceiver().getIReceiver(pCon);
	}

	public static IReceiverGoogleTalk withGooGleTalk(Context pCon) {
		return new UBroadcastReceiver().getIReceiverGoogleTalk(pCon);
	}

	public static IReceiverMode withMode(Context pCon) {
		return new UBroadcastReceiver().getIReceiverMode(pCon);
	}

	public static IReceiverPower withPower(Context pCon) {
		return new UBroadcastReceiver().getIReceiverPower(pCon);
	}

	public static IReceiverResources withResources(Context pCon) {
		return new UBroadcastReceiver().getIReceiverResources(pCon);
	}

	public static IReceiverSDcard withSDcard(Context pCon) {
		return new UBroadcastReceiver().getIReceiverSDcard(pCon);
	}

	public static IReceiverSMS withSMS(Context pCon) {
		return new UBroadcastReceiver().getIReceiverSMS(pCon);
	}
	public static IReceiverTime withTime(Context pCon) {
		return new UBroadcastReceiver().getIReceiverTime(pCon);
	}

	public static IReceiverTTS withTTS(Context pCon) {
		return new UBroadcastReceiver().getIReceiverTTS(pCon);
	}

	public static IReceiverVoiceMail withVoiceMail(Context pCon) {
		return new UBroadcastReceiver().getIReceiverVoiceMail(pCon);
	}

	public static IReceiverWifi withWifi(Context pCon) {
		return new UBroadcastReceiver().getIReceiverWifi(pCon);
	}

	public static IReceiverNetworkAccess withNetworkAccess(Context pCon) {
		return new UBroadcastReceiver().getIReceiverNetworkAccess(pCon);
	}

	public static IReceiverMemory withMemory(Context pCon) {
		return new UBroadcastReceiver().getIReceiverMemory(pCon);
	}

	public static IReceiverMedia withMedia(Context pCon) {
		return new UBroadcastReceiver().getIReceiverMedia(pCon);
	}

	public static IReceiverBattery withBattery(Context pCon) {
		return new UBroadcastReceiver().getIReceiverBattery(pCon);
	}

	public static IReceiverBluetooth withBluetooth(Context pCon) {
		return new UBroadcastReceiver().getIReceiverBluetooth(pCon);
	}

	public static IReceiverVideo withVideo(Context pCon) {
		return new UBroadcastReceiver().getIReceiverVideo(pCon);
	}

	public static IReceiverUser withUser(Context pCon) {
		return new UBroadcastReceiver().getIReceiverUser(pCon);
	}

	public static IReceiverPicture withPicture(Context pCon) {
		return new UBroadcastReceiver().getIReceiverPicture(pCon);
	}

	public static IReceiverAudio withAudio(Context pCon) {
		return new UBroadcastReceiver().getIReceiverAudio(pCon);
	}

	public static IReceiverCall withCall(Context pCon) {
		return new UBroadcastReceiver().getIReceiverCall(pCon);
	}

	public static IReceiverPackage withPackage(Context pCon) {
		return new UBroadcastReceiver().getIReceiverPackage(pCon);
	}

	public static IReceiverHeadset withHeadset(Context pCon) {
		return new UBroadcastReceiver().getIReceiverHeadset(pCon);
	}

	public static IReceiverDownload withDownload(Context pCon) {
		return new UBroadcastReceiver().getIReceiverDownload(pCon);
	}

	public static IReceiverInnerNFC withInnerNFC(Context pCon) {
		return new UBroadcastReceiver().getIReceiverInnerNFC(pCon);
	}

	private IReceiver getIReceiver(Context pCon) {
		return new IReceiver(pCon);
	}

	private IReceiverGoogleTalk getIReceiverGoogleTalk(Context pCon) {
		return new IReceiverGoogleTalk(pCon);
	}

	private IReceiverMode getIReceiverMode(Context pCon) {
		return new IReceiverMode(pCon);
	}

	private IReceiverPower getIReceiverPower(Context pCon) {
		return new IReceiverPower(pCon);
	}

	private IReceiverResources getIReceiverResources(Context pCon) {
		return new IReceiverResources(pCon);
	}

	private IReceiverSDcard getIReceiverSDcard(Context pCon) {
		return new IReceiverSDcard(pCon);
	}
	private IReceiverSMS getIReceiverSMS(Context pCon) {
		return new IReceiverSMS(pCon);
	}

	private IReceiverTime getIReceiverTime(Context pCon) {
		return new IReceiverTime(pCon);
	}

	private IReceiverTTS getIReceiverTTS(Context pCon) {
		return new IReceiverTTS(pCon);
	}

	private IReceiverVoiceMail getIReceiverVoiceMail(Context pCon) {
		return new IReceiverVoiceMail(pCon);
	}

	private IReceiverWifi getIReceiverWifi(Context pCon) {
		return new IReceiverWifi(pCon);
	}

	private IReceiverNetworkAccess getIReceiverNetworkAccess(Context pCon) {
		return new IReceiverNetworkAccess(pCon);
	}

	private IReceiverMemory getIReceiverMemory(Context pCon) {
		return new IReceiverMemory(pCon);
	}

	private IReceiverMedia getIReceiverMedia(Context pCon) {
		return new IReceiverMedia(pCon);
	}

	private IReceiverBattery getIReceiverBattery(Context pCon) {
		return new IReceiverBattery(pCon);
	}

	private IReceiverBluetooth getIReceiverBluetooth(Context pCon) {
		return new IReceiverBluetooth(pCon);
	}

	private IReceiverVideo getIReceiverVideo(Context pCon) {
		return new IReceiverVideo(pCon);
	}

	private IReceiverUser getIReceiverUser(Context pCon) {
		return new IReceiverUser(pCon);
	}

	private IReceiverPicture getIReceiverPicture(Context pCon) {
		return new IReceiverPicture(pCon);
	}

	private IReceiverAudio getIReceiverAudio(Context pCon) {
		return new IReceiverAudio(pCon);
	}

	private IReceiverCall getIReceiverCall(Context pCon) {
		return new IReceiverCall(pCon);
	}

	private IReceiverPackage getIReceiverPackage(Context pCon) {
		return new IReceiverPackage(pCon);
	}

	private IReceiverHeadset getIReceiverHeadset(Context pCon) {
		return new IReceiverHeadset(pCon);
	}

	private IReceiverDownload getIReceiverDownload(Context pCon) {
		return new IReceiverDownload(pCon);
	}

	private IReceiverInnerNFC getIReceiverInnerNFC(Context pCon) {
		return new IReceiverInnerNFC(pCon);
	}

	///////////GoogleTalk///////////
	public class IReceiverGoogleTalk {

		private WReceiverGoogleTalk receiver;
		private IReceiverGoogleTalk(Context pCon) {
			receiver = new WReceiverGoogleTalk(pCon);
		}
		public WReceiverGoogleTalk addIntentFilter_gtalkConnected() {
			return receiver.addIntentFilter("android.intent.action.GTALK_CONNECTED");
		}
		public WReceiverGoogleTalk addIntentFilter_gtalkDisConnected() {
			return receiver.addIntentFilter("android.intent.action.GTALK_DISCONNECTED");
		}
	}

	public class WReceiverGoogleTalk {
		private Context pCon;
		private Callback mCallback;
		private WReceiverGoogleTalk(Context pCon) {
			this.pCon = pCon;
		}

		private BroadcastReceiver bro = new BroadcastReceiver() {
			public void onReceive(Context context, Intent intent) {
				if (mCallback == null)
					return;
				mCallback.onReceive(intent);
			}
		};

		public WReceiverGoogleTalk addIntentFilter_gtalkConnected() {
			return addIntentFilter("android.intent.action.GTALK_CONNECTED");
		}
		public WReceiverGoogleTalk addIntentFilter_gtalkDisConnected() {
			return addIntentFilter("android.intent.action.GTALK_DISCONNECTED");
		}

		private WReceiverGoogleTalk addIntentFilter(String filtername) {
			IntentFilter filter = new IntentFilter();
			filter.addAction(filtername);
			pCon.registerReceiver(bro, filter);
			return this;
		}

		public WReceiverGoogleTalk forStart(Callback UReceiver_Callback) {
			mCallback = UReceiver_Callback;
			return this;
		}

		public WReceiverGoogleTalk forClose() {
			if (bro != null)
				pCon.unregisterReceiver(bro);
			return this;
		}
	}

	/////////////mode/////////////
	public class IReceiverMode {

		private WReceiverMode receiver;
		private IReceiverMode(Context pCon) {
			receiver = new WReceiverMode(pCon);
		}
		public WReceiverMode addIntentFilter_AirPlane() {
			return receiver.addIntentFilter("android.intent.action.AIRPLANE_MODE");
		}
		public WReceiverMode addIntentFilter_DreamingStarted() {
			return receiver.addIntentFilter("android.intent.action.DREAMING_STARTED");
		}
		public WReceiverMode addIntentFilter_DreamingStopped() {
			return receiver.addIntentFilter("android.intent.action.DREAMING_STOPPED");
		}
		public WReceiverMode addIntentFilter_RingerChanged() {
			return receiver.addIntentFilter("android.intent.media.RINGER_MODE_CHANGED");
		}
		public WReceiverMode addIntentFilter_ConfigurationChanged() {
			return receiver.addIntentFilter("android.intent.action.CONFIGURATION_CHANGED");
		}
		public WReceiverMode addIntentFilter_InputMethodchanged() {
			return receiver.addIntentFilter("android.intent.action.INPUT_METHOD_CHANGED");
		}
		public WReceiverMode addIntentFilter_LocaleChanged() {
			return receiver.addIntentFilter("android.intent.action.LOCALE_CHANGED");
		}
		public WReceiverMode addIntentFilter_Providerchanged() {
			return receiver.addIntentFilter("android.intent.action.PROVIDER_CHANGED");
		}
		public WReceiverMode addIntentFilter_DockEvent() {
			return receiver.addIntentFilter("android.intent.action.DOCK_EVENT");
		}
		public WReceiverMode addIntentFilter_QueryKeyboardLayouts() {
			return receiver.addIntentFilter("android.intent.action.QUERY_KEYBOARD_LAYOUTS");
		}
	}

	public class WReceiverMode {
		private Context pCon;
		private Callback mCallback;
		private WReceiverMode(Context pCon) {
			this.pCon = pCon;
		}

		private BroadcastReceiver bro = new BroadcastReceiver() {
			public void onReceive(Context context, Intent intent) {
				if (mCallback == null)
					return;
				mCallback.onReceive(intent);
			}
		};

		public WReceiverMode addIntentFilter_AirPlane() {
			return addIntentFilter("android.intent.action.AIRPLANE_MODE");
		}
		public WReceiverMode addIntentFilter_DreamingStarted() {
			return addIntentFilter("android.intent.action.DREAMING_STARTED");
		}
		public WReceiverMode addIntentFilter_DreamingStopped() {
			return addIntentFilter("android.intent.action.DREAMING_STOPPED");
		}
		public WReceiverMode addIntentFilter_RingerChanged() {
			return addIntentFilter("android.intent.media.RINGER_MODE_CHANGED");
		}
		public WReceiverMode addIntentFilter_ConfigurationChanged() {
			return addIntentFilter("android.intent.action.CONFIGURATION_CHANGED");
		}
		public WReceiverMode addIntentFilter_InputMethodchanged() {
			return addIntentFilter("android.intent.action.INPUT_METHOD_CHANGED");
		}
		public WReceiverMode addIntentFilter_LocaleChanged() {
			return addIntentFilter("android.intent.action.LOCALE_CHANGED");
		}
		public WReceiverMode addIntentFilter_Providerchanged() {
			return addIntentFilter("android.intent.action.PROVIDER_CHANGED");
		}
		public WReceiverMode addIntentFilter_DockEvent() {
			return addIntentFilter("android.intent.action.DOCK_EVENT");
		}
		public WReceiverMode addIntentFilter_QueryKeyboardLayouts() {
			return addIntentFilter("android.intent.action.QUERY_KEYBOARD_LAYOUTS");
		}

		private WReceiverMode addIntentFilter(String filtername) {
			IntentFilter filter = new IntentFilter();
			filter.addAction(filtername);
			pCon.registerReceiver(bro, filter);
			return this;
		}

		public WReceiverMode forStart(Callback UReceiver_Callback) {
			mCallback = UReceiver_Callback;
			return this;
		}

		public WReceiverMode forClose() {
			if (bro != null)
				pCon.unregisterReceiver(bro);
			return this;
		}
	}

	///////////Power///////////
	public class IReceiverPower {

		private WReceiverPower receiver;
		private IReceiverPower(Context pCon) {
			receiver = new WReceiverPower(pCon);
		}
		public WReceiverPower addIntentFilter_ScreenOff() {
			return receiver.addIntentFilter("android.intent.action.SCREEN_OFF");
		}
		
		public WReceiverPower addIntentFilter_ScreenOn() {
			return receiver.addIntentFilter("android.intent.action.SCREEN_ON");
		}
		
		public WReceiverPower addIntentFilter_ActionPowerConnected() {
			return receiver.addIntentFilter("android.intent.action.ACTION_POWER_CONNECTED");
		}
		
		public WReceiverPower addIntentFilter_ActionPowerDisconnected() {
			return receiver.addIntentFilter("android.intent.action.ACTION_POWER_DISCONNECTED");
		}
		
		public WReceiverPower addIntentFilter_Reboot() {
			return receiver.addIntentFilter("android.intent.action.REBOOT");
		}
		
		public WReceiverPower addIntentFilter_ActionShutdown() {
			return receiver.addIntentFilter("android.intent.action.ACTION_SHUTDOWN" );
		}
		
		public WReceiverPower addIntentFilter_BootCompleted() {
			return receiver.addIntentFilter("android.intent.action.BOOT_COMPLETED" );
		}
	}

	public class WReceiverPower {
		private Context pCon;
		private Callback mCallback;
		private WReceiverPower(Context pCon) {
			this.pCon = pCon;
		}

		private BroadcastReceiver bro = new BroadcastReceiver() {
			public void onReceive(Context context, Intent intent) {
				if (mCallback == null)
					return;
				mCallback.onReceive(intent);
			}
		};

		public WReceiverPower addIntentFilter_ScreenOff() {
			return addIntentFilter("android.intent.action.SCREEN_OFF");
		}
		public WReceiverPower addIntentFilter_ScreenOn() {
			return addIntentFilter("android.intent.action.SCREEN_ON");
		}
		public WReceiverPower addIntentFilter_ActionPowerConnected() {
			return addIntentFilter("android.intent.action.ACTION_POWER_CONNECTED");
		}
		public WReceiverPower addIntentFilter_ActionPowerDisconnected() {
			return addIntentFilter("android.intent.action.ACTION_POWER_DISCONNECTED");
		}
		public WReceiverPower addIntentFilter_Reboot() {
			return addIntentFilter("android.intent.action.REBOOT");
		}
		
		public WReceiverPower addIntentFilter_ActionShutdown() {
			return addIntentFilter("android.intent.action.ACTION_SHUTDOWN" );
		}
		
		public WReceiverPower addIntentFilter_BootCompleted() {
			return addIntentFilter("android.intent.action.BOOT_COMPLETED" );
		}

		private WReceiverPower addIntentFilter(String filtername) {
			IntentFilter filter = new IntentFilter();
			filter.addAction(filtername);
			pCon.registerReceiver(bro, filter);
			return this;
		}

		public WReceiverPower forStart(Callback UReceiver_Callback) {
			mCallback = UReceiver_Callback;
			return this;
		}

		public WReceiverPower forClose() {
			if (bro != null)
				pCon.unregisterReceiver(bro);
			return this;
		}
	}

	///////////Resource///////////
	public class IReceiverResources {

		private WReceiverResources receiver;
		private IReceiverResources(Context pCon) {
			receiver = new WReceiverResources(pCon);
		}
		public WReceiverResources addIntentFilter_ExternalApplicationsAvailable() {
			return receiver.addIntentFilter("android.intent.action.EXTERNAL_APPLICATIONS_AVAILABLE");
		}
		public WReceiverResources addIntentFilter_ExternalApplicationsUnAvailable() {
			return receiver.addIntentFilter("android.intent.action.EXTERNAL_APPLICATIONS_UNAVAILABLE");
		}
	}

	public class WReceiverResources {
		private Context pCon;
		private Callback mCallback;
		private WReceiverResources(Context pCon) {
			this.pCon = pCon;
		}

		private BroadcastReceiver bro = new BroadcastReceiver() {
			public void onReceive(Context context, Intent intent) {
				if (mCallback == null)
					return;
				mCallback.onReceive(intent);
			}
		};

		public WReceiverResources addIntentFilter_ExternalApplicationsAvailable() {
			return addIntentFilter("android.intent.action.EXTERNAL_APPLICATIONS_AVAILABLE");
		}
		public WReceiverResources addIntentFilter_ExternalApplicationsUnAvailable() {
			return addIntentFilter("android.intent.action.EXTERNAL_APPLICATIONS_UNAVAILABLE");
		}

		private WReceiverResources addIntentFilter(String filtername) {
			IntentFilter filter = new IntentFilter();
			filter.addAction(filtername);
			pCon.registerReceiver(bro, filter);
			return this;
		}

		public WReceiverResources forStart(Callback UReceiver_Callback) {
			mCallback = UReceiver_Callback;
			return this;
		}

		public WReceiverResources forClose() {
			if (bro != null)
				pCon.unregisterReceiver(bro);
			return this;
		}
	}

	///////////SDCARD///////////
	public class IReceiverSDcard {

		private WReceiverSDcard receiver;
		private IReceiverSDcard(Context pCon) {
			receiver = new WReceiverSDcard(pCon);
		}
		public WReceiverSDcard addIntentFilter_ExternalAppAvailable() {
			return receiver.addIntentFilter("android.intent.action.EXTERNAL_APPLICATIONS_AVAILABLE");
		}
		public WReceiverSDcard addIntentFilter_ExternalAppUnAvailable() {
			return receiver.addIntentFilter("android.intent.action.EXTERNAL_APPLICATIONS_UNAVAILABLE");
		}
	}

	public class WReceiverSDcard {
		private Context pCon;
		private Callback mCallback;
		private WReceiverSDcard(Context pCon) {
			this.pCon = pCon;
		}

		private BroadcastReceiver bro = new BroadcastReceiver() {
			public void onReceive(Context context, Intent intent) {
				if (mCallback == null)
					return;
				mCallback.onReceive(intent);
			}
		};

		public WReceiverSDcard addIntentFilter_ExternalAppAvailable() {
			return addIntentFilter("android.intent.action.EXTERNAL_APPLICATIONS_AVAILABLE");
		}
		public WReceiverSDcard addIntentFilter_ExternalAppUnAvailable() {
			return addIntentFilter("android.intent.action.EXTERNAL_APPLICATIONS_UNAVAILABLE");
		}

		private WReceiverSDcard addIntentFilter(String filtername) {
			IntentFilter filter = new IntentFilter();
			filter.addAction(filtername);
			pCon.registerReceiver(bro, filter);
			return this;
		}

		public WReceiverSDcard forStart(Callback UReceiver_Callback) {
			mCallback = UReceiver_Callback;
			return this;
		}

		public WReceiverSDcard forClose() {
			if (bro != null)
				pCon.unregisterReceiver(bro);
			return this;
		}
	}

	///////////SMS///////////
	public class IReceiverSMS {

		private WReceiverSMS receiver;

		private IReceiverSMS(Context pCon) {
			receiver = new WReceiverSMS(pCon);
		}
		public WReceiverSMS addIntentFilter_SMSReceived() {
			return receiver.addIntentFilter("android.provider.Telephony.SMS_RECEIVED");
		}
		public WReceiverSMS addIntentFilter_DataSMSReceived() {
			return receiver.addIntentFilter("android.intent.action.DATA_SMS_RECEIVED");
		}
		public WReceiverSMS addIntentFilter_CellBroadcastSMSReceived() {
			return receiver.addIntentFilter("android.provider.Telephony.SMS_CB_RECEIVED");
		}
		public WReceiverSMS addIntentFilter_EmeergenyCellBroadcastReceived() {
			return receiver.addIntentFilter("android.provider.Telephony.SMS_EMERGENCY_CB_RECEIVED");
		}
		public WReceiverSMS addIntentFilter_ServiceCategoryProgramDataSMSReceived() {
			return receiver.addIntentFilter("android.provider.Telephony.SMS_SERVICE_CATEGORY_PROGRAM_DATA_RECEIVED");
		}
		public WReceiverSMS addIntentFilter_SimMessageFull() {
			return receiver.addIntentFilter("android.provider.Telephony.SIM_FULL");
		}
		public WReceiverSMS addIntentFilter_SMSRejected() {
			return receiver.addIntentFilter("android.provider.Telephony.SMS_REJECTED");
		}
		public WReceiverSMS addIntentFilter_WapPushReceived() {
			return receiver.addIntentFilter("android.provider.Telephony.WAP_PUSH_RECEIVED");
		}
		public WReceiverSMS addIntentFilter_WapPushDeliver() {
			return receiver.addIntentFilter("android.provider.Telephony.WAP_PUSH_DELIVER");
		}
		public WReceiverSMS addIntentFilter_SMSDeliver() {
			return receiver.addIntentFilter("android.provider.Telephony.SMS_DELIVER");
		}
		public WReceiverSMS addIntentFilter_ConentChanged() {
			return receiver.addIntentFilter("android.intent.action.CONTENT_CHANGED");
		}
	}

	public class WReceiverSMS {
		private Context pCon;
		private Callback mCallback;
		private WReceiverSMS(Context pCon) {
			this.pCon = pCon;
		}

		private BroadcastReceiver bro = new BroadcastReceiver() {
			public void onReceive(Context context, Intent intent) {
				if (mCallback == null)
					return;
				mCallback.onReceive(intent);
			}
		};

		public WReceiverSMS addIntentFilter_SMSReceived() {
			return addIntentFilter("android.provider.Telephony.SMS_RECEIVED");
		}
		public WReceiverSMS addIntentFilter_DataSMSReceived() {
			return addIntentFilter("android.intent.action.DATA_SMS_RECEIVED");
		}
		public WReceiverSMS addIntentFilter_CellBroadcastSMSReceived() {
			return addIntentFilter("android.provider.Telephony.SMS_CB_RECEIVED");
		}
		public WReceiverSMS addIntentFilter_EmeergenyCellBroadcastReceived() {
			return addIntentFilter("android.provider.Telephony.SMS_EMERGENCY_CB_RECEIVED");
		}
		public WReceiverSMS addIntentFilter_ServiceCategoryProgramDataSMSReceived() {
			return addIntentFilter("android.provider.Telephony.SMS_SERVICE_CATEGORY_PROGRAM_DATA_RECEIVED");
		}
		public WReceiverSMS addIntentFilter_SimMessageFull() {
			return addIntentFilter("android.provider.Telephony.SIM_FULL");
		}
		public WReceiverSMS addIntentFilter_SMSRejected() {
			return addIntentFilter("android.provider.Telephony.SMS_REJECTED");
		}
		public WReceiverSMS addIntentFilter_WapPushReceived() {
			return addIntentFilter("android.provider.Telephony.WAP_PUSH_RECEIVED");
		}
		public WReceiverSMS addIntentFilter_WapPushDeliver() {
			return addIntentFilter("android.provider.Telephony.WAP_PUSH_DELIVER");
		}
		public WReceiverSMS addIntentFilter_SMSDeliver() {
			return addIntentFilter("android.provider.Telephony.SMS_DELIVER");
		}
		public WReceiverSMS addIntentFilter_ConentChanged() {
			return addIntentFilter("android.intent.action.CONTENT_CHANGED");
		}

		private WReceiverSMS addIntentFilter(String filtername) {
			IntentFilter filter = new IntentFilter();
			filter.addAction(filtername);
			pCon.registerReceiver(bro, filter);
			return this;
		}

		public WReceiverSMS forStart(Callback UReceiver_Callback) {
			mCallback = UReceiver_Callback;
			return this;
		}

		public WReceiverSMS forClose() {
			if (bro != null)
				pCon.unregisterReceiver(bro);
			return this;
		}
	}

	///////////TIME///////////
	public class IReceiverTime {

		private WReceiverTime receiver;

		private IReceiverTime(Context pCon) {
			receiver = new WReceiverTime(pCon);
		}
		public WReceiverTime addIntentFilter_DateChanged() {
			return receiver.addIntentFilter("android.intent.action.DATE_CHANGED");
		}
		public WReceiverTime addIntentFilter_TimezoneChanged() {
			return receiver.addIntentFilter("android.intent.action.TIMEZONE_CHANGED");
		}
		public WReceiverTime addIntentFilter_TimeSet() {
			return receiver.addIntentFilter("android.intent.action.TIME_SET");
		}
		public WReceiverTime addIntentFilter_TimeTick() {
			return receiver.addIntentFilter("android.intent.action.TIME_TICK");
		}
	}

	public class WReceiverTime {
		private Context pCon;
		private Callback mCallback;
		private WReceiverTime(Context pCon) {
			this.pCon = pCon;
		}

		private BroadcastReceiver bro = new BroadcastReceiver() {
			public void onReceive(Context context, Intent intent) {
				if (mCallback == null)
					return;
				mCallback.onReceive(intent);
			}
		};

		public WReceiverTime addIntentFilter_DateChanged() {
			return addIntentFilter("android.intent.action.DATE_CHANGED");
		}
		public WReceiverTime addIntentFilter_TimezoneChanged() {
			return addIntentFilter("android.intent.action.TIMEZONE_CHANGED");
		}
		public WReceiverTime addIntentFilter_TimeSet() {
			return addIntentFilter("android.intent.action.TIME_SET");
		}
		public WReceiverTime addIntentFilter_TimeTick() {
			return addIntentFilter("android.intent.action.TIME_TICK");
		}

		private WReceiverTime addIntentFilter(String filtername) {
			IntentFilter filter = new IntentFilter();
			filter.addAction(filtername);
			pCon.registerReceiver(bro, filter);
			return this;
		}

		public WReceiverTime forStart(Callback UReceiver_Callback) {
			mCallback = UReceiver_Callback;
			return this;
		}

		public WReceiverTime forClose() {
			if (bro != null)
				pCon.unregisterReceiver(bro);
			return this;
		}
	}

	///////////TTS///////////
	public class IReceiverTTS {

		private WReceiverTTS receiver;

		private IReceiverTTS(Context pCon) {
			receiver = new WReceiverTTS(pCon);
		}

		public WReceiverTTS addIntentFilter_TTSQueueProcessingCompleted() {
			return receiver.addIntentFilter("android.speech.tts.TTS_QUEUE_PROCESSING_COMPLETED");
		}

		public WReceiverTTS addIntentFilter_TTSDataInstalled() {
			return receiver.addIntentFilter("android.speech.tts.engine.TTS_DATA_INSTALLED");
		}
	}

	public class WReceiverTTS {
		private Context pCon;
		private Callback mCallback;
		private WReceiverTTS(Context pCon) {
			this.pCon = pCon;
		}

		private BroadcastReceiver bro = new BroadcastReceiver() {
			public void onReceive(Context context, Intent intent) {
				if (mCallback == null)
					return;
				mCallback.onReceive(intent);
			}
		};

		public WReceiverTTS addIntentFilter_TTSQueueProcessingCompleted() {
			return addIntentFilter("android.speech.tts.TTS_QUEUE_PROCESSING_COMPLETED");
		}

		public WReceiverTTS addIntentFilter_TTSDataInstalled() {
			return addIntentFilter("android.speech.tts.engine.TTS_DATA_INSTALLED");
		}

		private WReceiverTTS addIntentFilter(String filtername) {
			IntentFilter filter = new IntentFilter();
			filter.addAction(filtername);
			pCon.registerReceiver(bro, filter);
			return this;
		}

		public WReceiverTTS forStart(Callback UReceiver_Callback) {
			mCallback = UReceiver_Callback;
			return this;
		}

		public WReceiverTTS forClose() {
			if (bro != null)
				pCon.unregisterReceiver(bro);
			return this;
		}
	}

	///////////VoiceMail///////////
	public class IReceiverVoiceMail {

		private WReceiverVoiceMail receiver;

		private IReceiverVoiceMail(Context pCon) {
			receiver = new WReceiverVoiceMail(pCon);
		}

		public WReceiverVoiceMail addIntentFilter_FetchVoiceMail() {
			return receiver.addIntentFilter("android.intent.action.FETCH_VOICEMAIL");
		}

		public WReceiverVoiceMail addIntentFilter_NewVoiceMail() {
			return receiver.addIntentFilter("android.intent.action.NEW_VOICEMAIL");
		}
	}

	public class WReceiverVoiceMail {
		private Context pCon;
		private Callback mCallback;
		private WReceiverVoiceMail(Context pCon) {
			this.pCon = pCon;
		}

		private BroadcastReceiver bro = new BroadcastReceiver() {
			public void onReceive(Context context, Intent intent) {
				if (mCallback == null)
					return;
				mCallback.onReceive(intent);
			}
		};

		public WReceiverVoiceMail addIntentFilter_FetchVoiceMail() {
			return addIntentFilter("android.intent.action.FETCH_VOICEMAIL");
		}

		public WReceiverVoiceMail addIntentFilter_NewVoiceMail() {
			return addIntentFilter("android.intent.action.NEW_VOICEMAIL");
		}

		private WReceiverVoiceMail addIntentFilter(String filtername) {
			IntentFilter filter = new IntentFilter();
			filter.addAction(filtername);
			pCon.registerReceiver(bro, filter);
			return this;
		}

		public WReceiverVoiceMail forStart(Callback UReceiver_Callback) {
			mCallback = UReceiver_Callback;
			return this;
		}

		public WReceiverVoiceMail forClose() {
			if (bro != null)
				pCon.unregisterReceiver(bro);
			return this;
		}
	}

	///////////Wifi///////////
	public class IReceiverWifi {

		private WReceiverWifi receiver;

		private IReceiverWifi(Context pCon) {
			receiver = new WReceiverWifi(pCon);
		}

		public WReceiverWifi addIntentFilter_NetworkIdsChanged() {
			return receiver.addIntentFilter("android.net.wifi.NETWORK_IDS_CHANGED");
		}

		public WReceiverWifi addIntentFilter_RssiChanged() {
			return receiver.addIntentFilter("android.net.wifi.RSSI_CHANGED");
		}

		public WReceiverWifi addIntentFilter_StateChange() {
			return receiver.addIntentFilter("android.net.wifi.STATE_CHANGE");
		}

		public WReceiverWifi addIntentFilter_ConnectionChange() {
			return receiver.addIntentFilter("android.net.wifi.supplicant.CONNECTION_CHANGE");
		}

		public WReceiverWifi addIntentFilter_SupplicantStateChange() {
			return receiver.addIntentFilter("android.net.wifi.supplicant.STATE_CHANGE");
		}

		public WReceiverWifi addIntentFilter_WifiStateChanged() {
			return receiver.addIntentFilter("android.net.wifi.WIFI_STATE_CHANGED");
		}

		public WReceiverWifi addIntentFilter_ScanResults() {
			return receiver.addIntentFilter("android.net.wifi.SCAN_RESULTS");
		}

		public WReceiverWifi addIntentFilter_P2PConnectionStateChange() {
			return receiver.addIntentFilter("android.net.wifi.p2p.CONNECTION_STATE_CHANGE");
		}

		public WReceiverWifi addIntentFilter_P2PPeersChanged() {
			return receiver.addIntentFilter("android.net.wifi.p2p.PEERS_CHANGED");
		}

		public WReceiverWifi addIntentFilter_P2PStateChanged() {
			return receiver.addIntentFilter("android.net.wifi.p2p.STATE_CHANGED");
		}

		public WReceiverWifi addIntentFilter_P2PThisDeviceChanged() {
			return receiver.addIntentFilter("android.net.wifi.p2p.THIS_DEVICE_CHANGED");
		}

		public WReceiverWifi addIntentFilter_P2PDiscoveryStateChange() {
			return receiver.addIntentFilter("android.net.wifi.p2p.DISCOVERY_STATE_CHANGE");
		}
	}

	public class WReceiverWifi {
		private Context pCon;
		private Callback mCallback;
		private WReceiverWifi(Context pCon) {
			this.pCon = pCon;
		}

		private BroadcastReceiver bro = new BroadcastReceiver() {
			public void onReceive(Context context, Intent intent) {
				if (mCallback == null)
					return;
				mCallback.onReceive(intent);
			}
		};

		public WReceiverWifi addIntentFilter_NetworkIdsChanged() {
			return addIntentFilter("android.net.wifi.NETWORK_IDS_CHANGED");
		}

		public WReceiverWifi addIntentFilter_RssiChanged() {
			return addIntentFilter("android.net.wifi.RSSI_CHANGED");
		}

		public WReceiverWifi addIntentFilter_StateChange() {
			return addIntentFilter("android.net.wifi.STATE_CHANGE");
		}

		public WReceiverWifi addIntentFilter_ConnectionChange() {
			return addIntentFilter("android.net.wifi.supplicant.CONNECTION_CHANGE");
		}

		public WReceiverWifi addIntentFilter_SupplicantStateChange() {
			return addIntentFilter("android.net.wifi.supplicant.STATE_CHANGE");
		}

		public WReceiverWifi addIntentFilter_WifiStateChanged() {
			return addIntentFilter("android.net.wifi.WIFI_STATE_CHANGED");
		}

		public WReceiverWifi addIntentFilter_ScanResults() {
			return addIntentFilter("android.net.wifi.SCAN_RESULTS");
		}

		public WReceiverWifi addIntentFilter_P2PConnectionStateChange() {
			return addIntentFilter("android.net.wifi.p2p.CONNECTION_STATE_CHANGE");
		}

		public WReceiverWifi addIntentFilter_P2PPeersChanged() {
			return addIntentFilter("android.net.wifi.p2p.PEERS_CHANGED");
		}

		public WReceiverWifi addIntentFilter_P2PStateChanged() {
			return addIntentFilter("android.net.wifi.p2p.STATE_CHANGED");
		}

		public WReceiverWifi addIntentFilter_P2PThisDeviceChanged() {
			return addIntentFilter("android.net.wifi.p2p.THIS_DEVICE_CHANGED");
		}

		public WReceiverWifi addIntentFilter_P2PDiscoveryStateChange() {
			return addIntentFilter("android.net.wifi.p2p.DISCOVERY_STATE_CHANGE");
		}

		private WReceiverWifi addIntentFilter(String filtername) {
			IntentFilter filter = new IntentFilter();
			filter.addAction(filtername);
			pCon.registerReceiver(bro, filter);
			return this;
		}

		public WReceiverWifi forStart(Callback UReceiver_Callback) {
			mCallback = UReceiver_Callback;
			return this;
		}

		public WReceiverWifi forClose() {
			if (bro != null)
				pCon.unregisterReceiver(bro);
			return this;
		}
	}

	///////////NETWORK ACCESS///////////
	public class IReceiverNetworkAccess {

		private WReceiverNetworkAccess receiver;

		private IReceiverNetworkAccess(Context pCon) {
			receiver = new WReceiverNetworkAccess(pCon);
		}

		public WReceiverNetworkAccess addIntentFilter_ProxyChange() {
			return receiver.addIntentFilter("android.intent.action.PROXY_CHANGE");
		}

		public WReceiverNetworkAccess addIntentFilter_StateChanged() {
			return receiver.addIntentFilter("android.net.nsd.STATE_CHANGED");
		}

		public WReceiverNetworkAccess addIntentFilter_ConnectivityChange() {
			return receiver.addIntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
		}
	}

	public class WReceiverNetworkAccess {
		private Context pCon;
		private Callback mCallback;
		private WReceiverNetworkAccess(Context pCon) {
			this.pCon = pCon;
		}

		private BroadcastReceiver bro = new BroadcastReceiver() {
			public void onReceive(Context context, Intent intent) {
				if (mCallback == null)
					return;
				mCallback.onReceive(intent);
			}
		};

		public WReceiverNetworkAccess addIntentFilter_ProxyChange() {
			return addIntentFilter("android.intent.action.PROXY_CHANGE");
		}

		public WReceiverNetworkAccess addIntentFilter_StateChanged() {
			return addIntentFilter("android.net.nsd.STATE_CHANGED");
		}

		public WReceiverNetworkAccess addIntentFilter_ConnectivityChange() {
			return addIntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
		}

		private WReceiverNetworkAccess addIntentFilter(String filtername) {
			IntentFilter filter = new IntentFilter();
			filter.addAction(filtername);
			pCon.registerReceiver(bro, filter);
			return this;
		}

		public WReceiverNetworkAccess forStart(Callback UReceiver_Callback) {
			mCallback = UReceiver_Callback;
			return this;
		}

		public WReceiverNetworkAccess forClose() {
			if (bro != null)
				pCon.unregisterReceiver(bro);
			return this;
		}
	}

	///////////MEMORY///////////
	public class IReceiverMemory {

		private WReceiverMemory receiver;

		private IReceiverMemory(Context pCon) {
			receiver = new WReceiverMemory(pCon);
		}

		public WReceiverMemory addIntentFilter_DeviceStorageLow() {
			return receiver.addIntentFilter("android.intent.action.DEVICE_STORAGE_LOW");
		}

		public WReceiverMemory addIntentFilter_DeviceStorageOK() {
			return receiver.addIntentFilter("android.intent.action.DEVICE_STORAGE_OK");
		}
	}

	public class WReceiverMemory {
		private Context pCon;
		private Callback mCallback;
		private WReceiverMemory(Context pCon) {
			this.pCon = pCon;
		}

		private BroadcastReceiver bro = new BroadcastReceiver() {
			public void onReceive(Context context, Intent intent) {
				if (mCallback == null)
					return;
				mCallback.onReceive(intent);
			}
		};

		public WReceiverMemory addIntentFilter_DeviceStorageLow() {
			return addIntentFilter("android.intent.action.DEVICE_STORAGE_LOW");
		}

		public WReceiverMemory addIntentFilter_DeviceStorageOK() {
			return addIntentFilter("android.intent.action.DEVICE_STORAGE_OK");
		}

		private WReceiverMemory addIntentFilter(String filtername) {
			IntentFilter filter = new IntentFilter();
			filter.addAction(filtername);
			pCon.registerReceiver(bro, filter);
			return this;
		}

		public WReceiverMemory forStart(Callback UReceiver_Callback) {
			mCallback = UReceiver_Callback;
			return this;
		}

		public WReceiverMemory forClose() {
			if (bro != null)
				pCon.unregisterReceiver(bro);
			return this;
		}
	}

	///////////MEDIA///////////
	public class IReceiverMedia {

		private WReceiverMedia receiver;

		private IReceiverMedia(Context pCon) {
			receiver = new WReceiverMedia(pCon);
		}

		public WReceiverMedia addIntentFilter_MediaBadRemoval() {
			return receiver.addIntentFilter("android.intent.action.MEDIA_BAD_REMOVAL");
		}

		public WReceiverMedia addIntentFilter_MediaButton() {
			return receiver.addIntentFilter("android.intent.action.MEDIA_BUTTON");
		}

		public WReceiverMedia addIntentFilter_MediaChecking() {
			return receiver.addIntentFilter("android.intent.action.MEDIA_CHECKING");
		}

		public WReceiverMedia addIntentFilter_MediaEject() {
			return receiver.addIntentFilter("android.intent.action.MEDIA_EJECT");
		}

		public WReceiverMedia addIntentFilter_MediaMounted() {
			return receiver.addIntentFilter("android.intent.action.MEDIA_MOUNTED");
		}

		public WReceiverMedia addIntentFilter_MediaNOFS() {
			return receiver.addIntentFilter("android.intent.action.MEDIA_NOFS");
		}

		public WReceiverMedia addIntentFilter_MediaRemoved() {
			return receiver.addIntentFilter("android.intent.action.MEDIA_REMOVED");
		}

		public WReceiverMedia addIntentFilter_MediaScannerFinished() {
			return receiver.addIntentFilter("android.intent.action.MEDIA_SCANNER_FINISHED");
		}

		public WReceiverMedia addIntentFilter_MediaScannerScanFile() {
			return receiver.addIntentFilter("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
		}

		public WReceiverMedia addIntentFilter_MediaScannerStarted() {
			return receiver.addIntentFilter("android.intent.action.MEDIA_SCANNER_STARTED");
		}

		public WReceiverMedia addIntentFilter_MediaShared() {
			return receiver.addIntentFilter("android.intent.action.MEDIA_SHARED");
		}

		public WReceiverMedia addIntentFilter_MediaUnmountable() {
			return receiver.addIntentFilter("android.intent.action.MEDIA_UNMOUNTABLE");
		}

		public WReceiverMedia addIntentFilter_MediaUnmounted() {
			return receiver.addIntentFilter("android.intent.action.MEDIA_UNMOUNTED");
		}
	}

	public class WReceiverMedia {
		private Context pCon;
		private Callback mCallback;
		private WReceiverMedia(Context pCon) {
			this.pCon = pCon;
		}

		private BroadcastReceiver bro = new BroadcastReceiver() {
			public void onReceive(Context context, Intent intent) {
				if (mCallback == null)
					return;
				mCallback.onReceive(intent);
			}
		};

		public WReceiverMedia addIntentFilter_MediaBadRemoval() {
			return addIntentFilter("android.intent.action.MEDIA_BAD_REMOVAL");
		}

		public WReceiverMedia addIntentFilter_MediaButton() {
			return addIntentFilter("android.intent.action.MEDIA_BUTTON");
		}

		public WReceiverMedia addIntentFilter_MediaChecking() {
			return addIntentFilter("android.intent.action.MEDIA_CHECKING");
		}

		public WReceiverMedia addIntentFilter_MediaEject() {
			return addIntentFilter("android.intent.action.MEDIA_EJECT");
		}

		public WReceiverMedia addIntentFilter_MediaMounted() {
			return addIntentFilter("android.intent.action.MEDIA_MOUNTED");
		}

		public WReceiverMedia addIntentFilter_MediaNOFS() {
			return addIntentFilter("android.intent.action.MEDIA_NOFS");
		}

		public WReceiverMedia addIntentFilter_MediaRemoved() {
			return addIntentFilter("android.intent.action.MEDIA_REMOVED");
		}

		public WReceiverMedia addIntentFilter_MediaScannerFinished() {
			return addIntentFilter("android.intent.action.MEDIA_SCANNER_FINISHED");
		}

		public WReceiverMedia addIntentFilter_MediaScannerScanFile() {
			return addIntentFilter("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
		}

		public WReceiverMedia addIntentFilter_MediaScannerStarted() {
			return addIntentFilter("android.intent.action.MEDIA_SCANNER_STARTED");
		}

		public WReceiverMedia addIntentFilter_MediaShared() {
			return addIntentFilter("android.intent.action.MEDIA_SHARED");
		}

		public WReceiverMedia addIntentFilter_MediaUnmountable() {
			return addIntentFilter("android.intent.action.MEDIA_UNMOUNTABLE");
		}

		public WReceiverMedia addIntentFilter_MediaUnmounted() {
			return addIntentFilter("android.intent.action.MEDIA_UNMOUNTED");
		}

		private WReceiverMedia addIntentFilter(String filtername) {
			IntentFilter filter = new IntentFilter();
			filter.addAction(filtername);
			pCon.registerReceiver(bro, filter);
			return this;
		}

		public WReceiverMedia forStart(Callback UReceiver_Callback) {
			mCallback = UReceiver_Callback;
			return this;
		}

		public WReceiverMedia forClose() {
			if (bro != null)
				pCon.unregisterReceiver(bro);
			return this;
		}
	}

	///////////BATTERY///////////
	public class IReceiverBattery {

		private WReceiverBattery receiver;

		private IReceiverBattery(Context pCon) {
			receiver = new WReceiverBattery(pCon);
		}

		public WReceiverBattery addIntentFilter_BatteryChanged() {
			return receiver.addIntentFilter("android.intent.action.BATTERY_CHANGED");
		}

		public WReceiverBattery addIntentFilter_BatteryLow() {
			return receiver.addIntentFilter("android.intent.action.BATTERY_LOW");
		}

		public WReceiverBattery addIntentFilter_BatteryOkay() {
			return receiver.addIntentFilter("android.intent.action.BATTERY_OKAY");
		}
	}

	public class WReceiverBattery {
		private Context pCon;
		private Callback mCallback;
		private WReceiverBattery(Context pCon) {
			this.pCon = pCon;
		}

		private BroadcastReceiver bro = new BroadcastReceiver() {
			public void onReceive(Context context, Intent intent) {
				if (mCallback == null)
					return;
				mCallback.onReceive(intent);
			}
		};

		public WReceiverBattery addIntentFilter_BatteryChanged() {
			return addIntentFilter("android.intent.action.BATTERY_CHANGED");
		}

		public WReceiverBattery addIntentFilter_BatteryLow() {
			return addIntentFilter("android.intent.action.BATTERY_LOW");
		}

		public WReceiverBattery addIntentFilter_BatteryOkay() {
			return addIntentFilter("android.intent.action.BATTERY_OKAY");
		}

		private WReceiverBattery addIntentFilter(String filtername) {
			IntentFilter filter = new IntentFilter();
			filter.addAction(filtername);
			pCon.registerReceiver(bro, filter);
			return this;
		}

		public WReceiverBattery forStart(Callback UReceiver_Callback) {
			mCallback = UReceiver_Callback;
			return this;
		}

		public WReceiverBattery forClose() {
			if (bro != null)
				pCon.unregisterReceiver(bro);
			return this;
		}
	}

	///////////BLUETOOTH///////////
	public class IReceiverBluetooth {

		private WReceiverBluetooth receiver;

		private IReceiverBluetooth(Context pCon) {
			receiver = new WReceiverBluetooth(pCon);
		}

		public WReceiverBluetooth addIntentFilter_DiscoveryFinished() {
			return receiver.addIntentFilter("android.bluetooth.adapter.action.DISCOVERY_FINISHED");
		}

		public WReceiverBluetooth addIntentFilter_DiscoveryStarted() {
			return receiver.addIntentFilter("android.bluetooth.adapter.action.DISCOVERY_STARTED");
		}

		public WReceiverBluetooth addIntentFilter_LocalNameChanged() {
			return receiver.addIntentFilter("android.bluetooth.adapter.action.LOCAL_NAME_CHANGED");
		}

		public WReceiverBluetooth addIntentFilter_ScanModeChanged() {
			return receiver.addIntentFilter("android.bluetooth.adapter.action.SCAN_MODE_CHANGED");
		}

		public WReceiverBluetooth addIntentFilter_StateChanged() {
			return receiver.addIntentFilter("android.bluetooth.adapter.action.STATE_CHANGED");
		}

		public WReceiverBluetooth addIntentFilter_ACLConnected() {
			return receiver.addIntentFilter("android.bluetooth.device.action.ACL_CONNECTED");
		}

		public WReceiverBluetooth addIntentFilter_ACLDisconnected() {
			return receiver.addIntentFilter("android.bluetooth.device.action.ACL_DISCONNECTED");
		}

		public WReceiverBluetooth addIntentFilter_ACLDisconnectedRequested() {
			return receiver.addIntentFilter("android.bluetooth.device.action.ACL_DISCONNECT_REQUESTED");
		}

		public WReceiverBluetooth addIntentFilter_BondStateChanged() {
			return receiver.addIntentFilter("android.bluetooth.device.action.BOND_STATE_CHANGED");
		}

		public WReceiverBluetooth addIntentFilter_ClassChanged() {
			return receiver.addIntentFilter("android.bluetooth.device.action.CLASS_CHANGED");
		}

		public WReceiverBluetooth addIntentFilter_Found() {
			return receiver.addIntentFilter("android.bluetooth.device.action.FOUND");
		}

		public WReceiverBluetooth addIntentFilter_NameChanged() {
			return receiver.addIntentFilter("android.bluetooth.device.action.NAME_CHANGED");
		}

		public WReceiverBluetooth addIntentFilter_ActionScoAudioStateUpdated() {
			return receiver.addIntentFilter("android.media.ACTION_SCO_AUDIO_STATE_UPDATED");
		}

		public WReceiverBluetooth addIntentFilter_A2DPConnectionStateChanged() {
			return receiver.addIntentFilter("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED");
		}

		public WReceiverBluetooth addIntentFilter_A2DPPlayingStateChanged() {
			return receiver.addIntentFilter("android.bluetooth.a2dp.profile.action.PLAYING_STATE_CHANGED");
		}

		public WReceiverBluetooth addIntentFilter_ConnectionStateChanged() {
			return receiver.addIntentFilter("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED");
		}

		public WReceiverBluetooth addIntentFilter_VendorSpecificHeadsetEvent() {
			return receiver.addIntentFilter("android.bluetooth.headset.action.VENDOR_SPECIFIC_HEADSET_EVENT");
		}

		public WReceiverBluetooth addIntentFilter_A2DPAudioStateChanged() {
			return receiver.addIntentFilter("android.bluetooth.headset.profile.action.AUDIO_STATE_CHANGED");
		}

		public WReceiverBluetooth addIntentFilter_HeadsetConnectionStateChanged() {
			return receiver.addIntentFilter("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
		}

		public WReceiverBluetooth addIntentFilter_UUID() {
			return receiver.addIntentFilter("android.bluetooth.device.action.UUID");
		}

		public WReceiverBluetooth addIntentFilter_PairingRequest() {
			return receiver.addIntentFilter("android.bluetooth.device.action.PAIRING_REQUEST");
		}
	}

	public class WReceiverBluetooth {
		private Context pCon;
		private Callback mCallback;
		private WReceiverBluetooth(Context pCon) {
			this.pCon = pCon;
		}

		private BroadcastReceiver bro = new BroadcastReceiver() {
			public void onReceive(Context context, Intent intent) {
				if (mCallback == null)
					return;
				mCallback.onReceive(intent);
			}
		};

		public WReceiverBluetooth addIntentFilter_DiscoveryFinished() {
			return addIntentFilter("android.bluetooth.adapter.action.DISCOVERY_FINISHED");
		}

		public WReceiverBluetooth addIntentFilter_DiscoveryStarted() {
			return addIntentFilter("android.bluetooth.adapter.action.DISCOVERY_STARTED");
		}

		public WReceiverBluetooth addIntentFilter_LocalNameChanged() {
			return addIntentFilter("android.bluetooth.adapter.action.LOCAL_NAME_CHANGED");
		}

		public WReceiverBluetooth addIntentFilter_ScanModeChanged() {
			return addIntentFilter("android.bluetooth.adapter.action.SCAN_MODE_CHANGED");
		}

		public WReceiverBluetooth addIntentFilter_StateChanged() {
			return addIntentFilter("android.bluetooth.adapter.action.STATE_CHANGED");
		}

		public WReceiverBluetooth addIntentFilter_ACLConnected() {
			return addIntentFilter("android.bluetooth.device.action.ACL_CONNECTED");
		}

		public WReceiverBluetooth addIntentFilter_ACLDisconnected() {
			return addIntentFilter("android.bluetooth.device.action.ACL_DISCONNECTED");
		}

		public WReceiverBluetooth addIntentFilter_ACLDisconnectedRequested() {
			return addIntentFilter("android.bluetooth.device.action.ACL_DISCONNECT_REQUESTED");
		}

		public WReceiverBluetooth addIntentFilter_BondStateChanged() {
			return addIntentFilter("android.bluetooth.device.action.BOND_STATE_CHANGED");
		}

		public WReceiverBluetooth addIntentFilter_ClassChanged() {
			return addIntentFilter("android.bluetooth.device.action.CLASS_CHANGED");
		}

		public WReceiverBluetooth addIntentFilter_Found() {
			return addIntentFilter("android.bluetooth.device.action.FOUND");
		}

		public WReceiverBluetooth addIntentFilter_NameChanged() {
			return addIntentFilter("android.bluetooth.device.action.NAME_CHANGED");
		}

		public WReceiverBluetooth addIntentFilter_ActionScoAudioStateUpdated() {
			return addIntentFilter("android.media.ACTION_SCO_AUDIO_STATE_UPDATED");
		}

		public WReceiverBluetooth addIntentFilter_A2DPConnectionStateChanged() {
			return addIntentFilter("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED");
		}

		public WReceiverBluetooth addIntentFilter_A2DPPlayingStateChanged() {
			return addIntentFilter("android.bluetooth.a2dp.profile.action.PLAYING_STATE_CHANGED");
		}

		public WReceiverBluetooth addIntentFilter_ConnectionStateChanged() {
			return addIntentFilter("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED");
		}

		public WReceiverBluetooth addIntentFilter_VendorSpecificHeadsetEvent() {
			return addIntentFilter("android.bluetooth.headset.action.VENDOR_SPECIFIC_HEADSET_EVENT");
		}

		public WReceiverBluetooth addIntentFilter_A2DPAudioStateChanged() {
			return addIntentFilter("android.bluetooth.headset.profile.action.AUDIO_STATE_CHANGED");
		}

		public WReceiverBluetooth addIntentFilter_HeadsetConnectionStateChanged() {
			return addIntentFilter("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
		}

		public WReceiverBluetooth addIntentFilter_UUID() {
			return addIntentFilter("android.bluetooth.device.action.UUID");
		}

		public WReceiverBluetooth addIntentFilter_PairingRequest() {
			return addIntentFilter("android.bluetooth.device.action.PAIRING_REQUEST");
		}

		private WReceiverBluetooth addIntentFilter(String filtername) {
			IntentFilter filter = new IntentFilter();
			filter.addAction(filtername);
			pCon.registerReceiver(bro, filter);
			return this;
		}

		public WReceiverBluetooth forStart(Callback UReceiver_Callback) {
			mCallback = UReceiver_Callback;
			return this;
		}

		public WReceiverBluetooth forClose() {
			if (bro != null)
				pCon.unregisterReceiver(bro);
			return this;
		}
	}

	///////////VIDEO///////////
	public class IReceiverVideo {

		private WReceiverVideo receiver;

		private IReceiverVideo(Context pCon) {
			receiver = new WReceiverVideo(pCon);
		}

		public WReceiverVideo addIntentFilter_NewVideo() {
			return receiver.addIntentFilter("android.hardware.action.NEW_VIDEO");
		}
	}

	public class WReceiverVideo {
		private Context pCon;
		private Callback mCallback;
		private WReceiverVideo(Context pCon) {
			this.pCon = pCon;
		}

		private BroadcastReceiver bro = new BroadcastReceiver() {
			public void onReceive(Context context, Intent intent) {
				if (mCallback == null)
					return;
				mCallback.onReceive(intent);
			}
		};

		public WReceiverVideo addIntentFilter_NewVideo() {
			return addIntentFilter("android.hardware.action.NEW_VIDEO");
		}

		private WReceiverVideo addIntentFilter(String filtername) {
			IntentFilter filter = new IntentFilter();
			filter.addAction(filtername);
			pCon.registerReceiver(bro, filter);
			return this;
		}

		public WReceiverVideo forStart(Callback UReceiver_Callback) {
			mCallback = UReceiver_Callback;
			return this;
		}

		public WReceiverVideo forClose() {
			if (bro != null)
				pCon.unregisterReceiver(bro);
			return this;
		}
	}

	///////////USER///////////
	public class IReceiverUser {

		private WReceiverUser receiver;

		private IReceiverUser(Context pCon) {
			receiver = new WReceiverUser(pCon);
		}

		public WReceiverUser addIntentFilter_ActionPasswordChanged() {
			return receiver.addIntentFilter("android.app.action.ACTION_PASSWORD_CHANGED");
		}

		public WReceiverUser addIntentFilter_ActionPasswordFailed() {
			return receiver.addIntentFilter("android.app.action.ACTION_PASSWORD_FAILED");
		}

		public WReceiverUser addIntentFilter_ActionPasswordSucceeded() {
			return receiver.addIntentFilter("android.app.action.ACTION_PASSWORD_SUCCEEDED");
		}

		public WReceiverUser addIntentFilter_DeviceAdminDisabled() {
			return receiver.addIntentFilter("android.app.action.DEVICE_ADMIN_DISABLED");
		}

		public WReceiverUser addIntentFilter_DeviceAdminDisableRequested() {
			return receiver.addIntentFilter("android.app.action.DEVICE_ADMIN_DISABLE_REQUESTED");
		}

		public WReceiverUser addIntentFilter_DeviceAdminEnbled() {
			return receiver.addIntentFilter("android.app.action.DEVICE_ADMIN_ENABLED");
		}

		public WReceiverUser addIntentFilter_UIDRemoved() {
			return receiver.addIntentFilter("android.intent.action.UID_REMOVED");
		}

		public WReceiverUser addIntentFilter_UserPresent() {
			return receiver.addIntentFilter("android.intent.action.USER_PRESENT");
		}

		public WReceiverUser addIntentFilter_ActionPasswordExpiring() {
			return receiver.addIntentFilter("android.app.action.ACTION_PASSWORD_EXPIRING");
		}
	}

	public class WReceiverUser {
		private Context pCon;
		private Callback mCallback;
		private WReceiverUser(Context pCon) {
			this.pCon = pCon;
		}

		private BroadcastReceiver bro = new BroadcastReceiver() {
			public void onReceive(Context context, Intent intent) {
				if (mCallback == null)
					return;
				mCallback.onReceive(intent);
			}
		};

		public WReceiverUser addIntentFilter_ActionPasswordChanged() {
			return addIntentFilter("android.app.action.ACTION_PASSWORD_CHANGED");
		}

		public WReceiverUser addIntentFilter_ActionPasswordFailed() {
			return addIntentFilter("android.app.action.ACTION_PASSWORD_FAILED");
		}

		public WReceiverUser addIntentFilter_ActionPasswordSucceeded() {
			return addIntentFilter("android.app.action.ACTION_PASSWORD_SUCCEEDED");
		}

		public WReceiverUser addIntentFilter_DeviceAdminDisabled() {
			return addIntentFilter("android.app.action.DEVICE_ADMIN_DISABLED");
		}

		public WReceiverUser addIntentFilter_DeviceAdminDisableRequested() {
			return addIntentFilter("android.app.action.DEVICE_ADMIN_DISABLE_REQUESTED");
		}

		public WReceiverUser addIntentFilter_DeviceAdminEnbled() {
			return addIntentFilter("android.app.action.DEVICE_ADMIN_ENABLED");
		}

		public WReceiverUser addIntentFilter_UIDRemoved() {
			return addIntentFilter("android.intent.action.UID_REMOVED");
		}

		public WReceiverUser addIntentFilter_UserPresent() {
			return addIntentFilter("android.intent.action.USER_PRESENT");
		}

		public WReceiverUser addIntentFilter_ActionPasswordExpiring() {
			return addIntentFilter("android.app.action.ACTION_PASSWORD_EXPIRING");
		}

		private WReceiverUser addIntentFilter(String filtername) {
			IntentFilter filter = new IntentFilter();
			filter.addAction(filtername);
			pCon.registerReceiver(bro, filter);
			return this;
		}

		public WReceiverUser forStart(Callback UReceiver_Callback) {
			mCallback = UReceiver_Callback;
			return this;
		}

		public WReceiverUser forClose() {
			if (bro != null)
				pCon.unregisterReceiver(bro);
			return this;
		}
	}

	///////////PICTURE///////////
	public class IReceiverPicture {

		private WReceiverPicture receiver;

		private IReceiverPicture(Context pCon) {
			receiver = new WReceiverPicture(pCon);
		}

		public WReceiverPicture addIntentFilter_CameraButton() {
			return receiver.addIntentFilter("android.intent.action.CAMERA_BUTTON");
		}

		public WReceiverPicture addIntentFilter_NewPicture() {
			return receiver.addIntentFilter("android.hardware.action.NEW_PICTURE");
		}
	}

	public class WReceiverPicture {
		private Context pCon;
		private Callback mCallback;
		private WReceiverPicture(Context pCon) {
			this.pCon = pCon;
		}

		private BroadcastReceiver bro = new BroadcastReceiver() {
			public void onReceive(Context context, Intent intent) {
				if (mCallback == null)
					return;
				mCallback.onReceive(intent);
			}
		};

		public WReceiverPicture addIntentFilter_CameraButton() {
			return addIntentFilter("android.intent.action.CAMERA_BUTTON");
		}

		public WReceiverPicture addIntentFilter_NewPicture() {
			return addIntentFilter("android.hardware.action.NEW_PICTURE");
		}

		private WReceiverPicture addIntentFilter(String filtername) {
			IntentFilter filter = new IntentFilter();
			filter.addAction(filtername);
			pCon.registerReceiver(bro, filter);
			return this;
		}

		public WReceiverPicture forStart(Callback UReceiver_Callback) {
			mCallback = UReceiver_Callback;
			return this;
		}

		public WReceiverPicture forClose() {
			if (bro != null)
				pCon.unregisterReceiver(bro);
			return this;
		}
	}

	///////////AUDIO///////////
	public class IReceiverAudio {

		private WReceiverAudio receiver;

		private IReceiverAudio(Context pCon) {
			receiver = new WReceiverAudio(pCon);
		}

		public WReceiverAudio addIntentFilter_CloseAudioEffectControlSession() {
			return receiver.addIntentFilter("android.media.action.CLOSE_AUDIO_EFFECT_CONTROL_SESSION");
		}

		public WReceiverAudio addIntentFilter_OpenAudioEffectControlSession() {
			return receiver.addIntentFilter("android.media.action.OPEN_AUDIO_EFFECT_CONTROL_SESSION");
		}

		public WReceiverAudio addIntentFilter_AudioBecomingNoisy() {
			return receiver.addIntentFilter("android.media.AUDIO_BECOMING_NOISY");
		}
	}

	public class WReceiverAudio {
		private Context pCon;
		private Callback mCallback;
		private WReceiverAudio(Context pCon) {
			this.pCon = pCon;
		}

		private BroadcastReceiver bro = new BroadcastReceiver() {
			public void onReceive(Context context, Intent intent) {
				if (mCallback == null)
					return;
				mCallback.onReceive(intent);
			}
		};

		public WReceiverAudio addIntentFilter_CloseAudioEffectControlSession() {
			return addIntentFilter("android.media.action.CLOSE_AUDIO_EFFECT_CONTROL_SESSION");
		}

		public WReceiverAudio addIntentFilter_OpenAudioEffectControlSession() {
			return addIntentFilter("android.media.action.OPEN_AUDIO_EFFECT_CONTROL_SESSION");
		}

		public WReceiverAudio addIntentFilter_AudioBecomingNoisy() {
			return addIntentFilter("android.media.AUDIO_BECOMING_NOISY");
		}

		private WReceiverAudio addIntentFilter(String filtername) {
			IntentFilter filter = new IntentFilter();
			filter.addAction(filtername);
			pCon.registerReceiver(bro, filter);
			return this;
		}

		public WReceiverAudio forStart(Callback UReceiver_Callback) {
			mCallback = UReceiver_Callback;
			return this;
		}

		public WReceiverAudio forClose() {
			if (bro != null)
				pCon.unregisterReceiver(bro);
			return this;
		}
	}

	///////////CALL///////////
	public class IReceiverCall {

		private WReceiverCall receiver;

		private IReceiverCall(Context pCon) {
			receiver = new WReceiverCall(pCon);
		}

		public WReceiverCall addIntentFilter_NewOutgoingCall() {
			return receiver.addIntentFilter("android.intent.action.NEW_OUTGOING_CALL");
		}

		public WReceiverCall addIntentFilter_PhoneState() {
			return receiver.addIntentFilter("android.intent.action.PHONE_STATE");
		}
	}

	public class WReceiverCall {
		private Context pCon;
		private Callback mCallback;
		private WReceiverCall(Context pCon) {
			this.pCon = pCon;
		}

		private BroadcastReceiver bro = new BroadcastReceiver() {
			public void onReceive(Context context, Intent intent) {
				if (mCallback == null)
					return;
				mCallback.onReceive(intent);
			}
		};

		public WReceiverCall addIntentFilter_NewOutgoingCall() {
			return addIntentFilter("android.intent.action.NEW_OUTGOING_CALL");
		}

		public WReceiverCall addIntentFilter_PhoneState() {
			return addIntentFilter("android.intent.action.PHONE_STATE");
		}

		private WReceiverCall addIntentFilter(String filtername) {
			IntentFilter filter = new IntentFilter();
			filter.addAction(filtername);
			pCon.registerReceiver(bro, filter);
			return this;
		}

		public WReceiverCall forStart(Callback UReceiver_Callback) {
			mCallback = UReceiver_Callback;
			return this;
		}

		public WReceiverCall forClose() {
			if (bro != null)
				pCon.unregisterReceiver(bro);
			return this;
		}
	}

	///////////PACKAGE///////////
	
	public class IReceiverPackage {

		private WReceiverPackage receiver;

		private IReceiverPackage(Context pCon) {
			receiver = new WReceiverPackage(pCon);
		}

		public WReceiverPackage addIntentFilter_PackageAdded() {
			return receiver.addIntentFilter("android.intent.action.PACKAGE_ADDED");
		}

		public WReceiverPackage addIntentFilter_PackageChanged() {
			return receiver.addIntentFilter("android.intent.action.PACKAGE_CHANGED");
		}

		public WReceiverPackage addIntentFilter_PackageDataCleared() {
			return receiver.addIntentFilter("android.intent.action.PACKAGE_DATA_CLEARED");
		}

		public WReceiverPackage addIntentFilter_PackageRemoved() {
			return receiver.addIntentFilter("android.intent.action.PACKAGE_REMOVED");
		}

		public WReceiverPackage addIntentFilter_PackageReplaced() {
			return receiver.addIntentFilter("android.intent.action.PACKAGE_REPLACED");
		}

		public WReceiverPackage addIntentFilter_PackageFullyRemoved() {
			return receiver.addIntentFilter("android.intent.action.PACKAGE_FULLY_REMOVED");
		}

		public WReceiverPackage addIntentFilter_MyPackageReplaced() {
			return receiver.addIntentFilter("android.intent.action.MY_PACKAGE_REPLACED");
		}

		public WReceiverPackage addIntentFilter_ManagePackageStorage() {
			return receiver.addIntentFilter("android.intent.action.MANAGE_PACKAGE_STORAGE");
		}

		public WReceiverPackage addIntentFilter_PackageRestarted() {
			return receiver.addIntentFilter("android.intent.action.PACKAGE_RESTARTED");
		}

		public WReceiverPackage addIntentFilter_PackageFirstLaunch() {
			return receiver.addIntentFilter("android.intent.action.PACKAGE_FIRST_LAUNCH");
		}

		public WReceiverPackage addIntentFilter_PackageNeedsVerification() {
			return receiver.addIntentFilter("android.intent.action.PACKAGE_NEEDS_VERIFICATION");
		}

		public WReceiverPackage addIntentFilter_PackageVerified() {
			return receiver.addIntentFilter("android.intent.action.PACKAGE_VERIFIED");
		}
	}

	public class WReceiverPackage extends BroadcastReceiver{
		private Context pCon;
		private Callback mCallback;
		public WReceiverPackage(Context pCon) {
			this.pCon = pCon;
		}
		@Override
		public void onReceive(Context context, Intent intent) {
			if (mCallback == null)
				return;
			mCallback.onReceive(intent);
		}

		public WReceiverPackage addIntentFilter_PackageAdded() {
			return addIntentFilter("android.intent.action.PACKAGE_ADDED");
		}

		public WReceiverPackage addIntentFilter_PackageChanged() {
			return addIntentFilter("android.intent.action.PACKAGE_CHANGED");
		}

		public WReceiverPackage addIntentFilter_PackageDataCleared() {
			return addIntentFilter("android.intent.action.PACKAGE_DATA_CLEARED");
		}

		public WReceiverPackage addIntentFilter_PackageRemoved() {
			return addIntentFilter("android.intent.action.PACKAGE_REMOVED");
		}

		public WReceiverPackage addIntentFilter_PackageReplaced() {
			return addIntentFilter("android.intent.action.PACKAGE_REPLACED");
		}

		public WReceiverPackage addIntentFilter_PackageFullyRemoved() {
			return addIntentFilter("android.intent.action.PACKAGE_FULLY_REMOVED");
		}

		public WReceiverPackage addIntentFilter_MyPackageReplaced() {
			return addIntentFilter("android.intent.action.MY_PACKAGE_REPLACED");
		}

		public WReceiverPackage addIntentFilter_ManagePackageStorage() {
			return addIntentFilter("android.intent.action.MANAGE_PACKAGE_STORAGE");
		}

		public WReceiverPackage addIntentFilter_PackageRestarted() {
			return addIntentFilter("android.intent.action.PACKAGE_RESTARTED");
		}

		public WReceiverPackage addIntentFilter_PackageFirstLaunch() {
			return addIntentFilter("android.intent.action.PACKAGE_FIRST_LAUNCH");
		}

		public WReceiverPackage addIntentFilter_PackageNeedsVerification() {
			return addIntentFilter("android.intent.action.PACKAGE_NEEDS_VERIFICATION");
		}

		public WReceiverPackage addIntentFilter_PackageVerified() {
			return addIntentFilter("android.intent.action.PACKAGE_VERIFIED");
		}

		private WReceiverPackage addIntentFilter(String filtername) {
			IntentFilter filter = new IntentFilter();
			filter.addDataScheme("package");
			filter.addAction(filtername);
			pCon.registerReceiver(this, filter);
			return this;
		}

		public WReceiverPackage forStart(Callback UReceiver_Callback) {
			mCallback = UReceiver_Callback;
			return this;
		}

		public WReceiverPackage forClose() {
			if (this != null)
				pCon.unregisterReceiver(this);
			return this;
		}
	}

	///////////HEADSET///////////
	public class IReceiverHeadset {

		private WReceiverHeadset receiver;

		private IReceiverHeadset(Context pCon) {
			receiver = new WReceiverHeadset(pCon);
		}

		public WReceiverHeadset addIntentFilter_HeadsetPlug() {
			return receiver.addIntentFilter("android.intent.action.HEADSET_PLUG");
		}
	}

	public class WReceiverHeadset {
		private Context pCon;
		private Callback mCallback;
		private WReceiverHeadset(Context pCon) {
			this.pCon = pCon;
		}

		private BroadcastReceiver bro = new BroadcastReceiver() {
			public void onReceive(Context context, Intent intent) {
				if (mCallback == null)
					return;
				mCallback.onReceive(intent);
			}
		};

		public WReceiverHeadset addIntentFilter_HeadsetPlug() {
			return addIntentFilter("android.intent.action.HEADSET_PLUG");
		}

		private WReceiverHeadset addIntentFilter(String filtername) {
			IntentFilter filter = new IntentFilter();
			filter.addAction(filtername);
			pCon.registerReceiver(bro, filter);
			return this;
		}

		public WReceiverHeadset forStart(Callback UReceiver_Callback) {
			mCallback = UReceiver_Callback;
			return this;
		}

		public WReceiverHeadset forClose() {
			if (bro != null)
				pCon.unregisterReceiver(bro);
			return this;
		}
	}

	///////////DOWNLOAD///////////
	public class IReceiverDownload {

		private WReceiverDownload receiver;

		private IReceiverDownload(Context pCon) {
			receiver = new WReceiverDownload(pCon);
		}

		public WReceiverDownload addIntentFilter_DownloadComplete() {
			return receiver.addIntentFilter("android.intent.action.DOWNLOAD_COMPLETE");
		}

		public WReceiverDownload addIntentFilter_DownloadNotificationClicked() {
			return receiver.addIntentFilter("android.intent.action.DOWNLOAD_NOTIFICATION_CLICKED");
		}
	}

	public class WReceiverDownload {
		private Context pCon;
		private Callback mCallback;
		private WReceiverDownload(Context pCon) {
			this.pCon = pCon;
		}

		private BroadcastReceiver bro = new BroadcastReceiver() {
			public void onReceive(Context context, Intent intent) {
				if (mCallback == null)
					return;
				mCallback.onReceive(intent);
			}
		};

		public WReceiverDownload addIntentFilter_DownloadComplete() {
			return addIntentFilter("android.intent.action.DOWNLOAD_COMPLETE");
		}

		public WReceiverDownload addIntentFilter_DownloadNotificationClicked() {
			return addIntentFilter("android.intent.action.DOWNLOAD_NOTIFICATION_CLICKED");
		}

		private WReceiverDownload addIntentFilter(String filtername) {
			IntentFilter filter = new IntentFilter();
			filter.addAction(filtername);
			pCon.registerReceiver(bro, filter);
			return this;
		}

		public WReceiverDownload forStart(Callback UReceiver_Callback) {
			mCallback = UReceiver_Callback;
			return this;
		}

		public WReceiverDownload forClose() {
			if (bro != null)
				pCon.unregisterReceiver(bro);
			return this;
		}
	}

	///////////InnerNFC///////////
	public class IReceiverInnerNFC {

		private WReceiverInnerNFC receiver;

		private IReceiverInnerNFC(Context pCon) {
			receiver = new WReceiverInnerNFC(pCon);
		}

		public WReceiverInnerNFC addIntentFilter_HeadsetPlug() {
			return receiver.addIntentFilter("android.nfc.action.ADAPTER_STATE_CHANGED");
		}
	}

	public class WReceiverInnerNFC {
		private Context pCon;
		private Callback mCallback;
		private WReceiverInnerNFC(Context pCon) {
			this.pCon = pCon;
		}

		private BroadcastReceiver bro = new BroadcastReceiver() {
			public void onReceive(Context context, Intent intent) {
				if (mCallback == null)
					return;
				mCallback.onReceive(intent);
			}
		};

		public WReceiverInnerNFC addIntentFilter_HeadsetPlug() {
			return addIntentFilter("android.nfc.action.ADAPTER_STATE_CHANGED");
		}

		private WReceiverInnerNFC addIntentFilter(String filtername) {
			IntentFilter filter = new IntentFilter();
			filter.addAction(filtername);
			pCon.registerReceiver(bro, filter);
			return this;
		}

		public WReceiverInnerNFC forStart(Callback UReceiver_Callback) {
			mCallback = UReceiver_Callback;
			return this;
		}

		public WReceiverInnerNFC forClose() {
			if (bro != null)
				pCon.unregisterReceiver(bro);
			return this;
		}
	}

	public class IReceiver {

		private WReceiver mWReceiver;
		private IReceiver(Context pCon) {
			mWReceiver = new WReceiver(pCon);
		}
		public WReceiver addIntentFilter(String... filternames) {
			return mWReceiver.addIntentFilter(filternames);
		}
	}

	public class WReceiver {
		private Context pCon;
		private Callback mCallback;
		private WReceiver(Context pCon) {
			this.pCon = pCon;
		}

		private BroadcastReceiver bro = new BroadcastReceiver() {
			public void onReceive(Context context, Intent intent) {
				if (mCallback == null)
					return;
				mCallback.onReceive(intent);
			}
		};

		public WReceiver addIntentFilter(String... filternames) {
			for (String temp : filternames) {
				IntentFilter filter = new IntentFilter();
				filter.addAction(temp);
				pCon.registerReceiver(bro, filter);
			}
			return this;
		}

		public WReceiver forStart(Callback UReceiver_Callback) {
			mCallback = UReceiver_Callback;
			return this;
		}

		public WReceiver forClose() {
			if (bro != null)
				pCon.unregisterReceiver(bro);
			return this;
		}

	}

	public interface Callback {
		void onReceive(Intent intent);
	}
}
