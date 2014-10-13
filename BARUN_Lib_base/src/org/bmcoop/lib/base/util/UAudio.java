package org.bmcoop.lib.base.util;

import android.content.Context;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;

public class UAudio {
	private static UAudio SINGLE_U;
	private static WAudio SINGLE_W;

	private UAudio() {
	}

	public static WAudio with(Context pCon) {
		if (SINGLE_U == null) {
			SINGLE_U = new UAudio();
		}
		if (SINGLE_W == null) {
			SINGLE_W = SINGLE_U.get(pCon, null);
		}
		return SINGLE_W;
	}

	public static WAudio with(Context pCon, Uri ringPath) {
		if (SINGLE_U == null) {
			SINGLE_U = new UAudio();
		}
		if (SINGLE_W == null) {
			SINGLE_W = SINGLE_U.get(pCon, ringPath);
		}
		return SINGLE_W;
	}

	private WAudio get(Context pCon, Uri ringPath) {
		return new WAudio(pCon, ringPath);
	}

	public class WAudio {

		private AudioManager mAudioManager;
		private Vibrator mVibrator;
		private Ringtone mRingtone;

		private WAudio(Context pCon, Uri ringPath) {
			mAudioManager = (AudioManager) pCon.getSystemService(Context.AUDIO_SERVICE);
			mVibrator = (Vibrator) pCon.getSystemService(Context.VIBRATOR_SERVICE);
			if (ringPath == null) {
				ringPath = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
			}
			mRingtone = RingtoneManager.getRingtone(pCon, ringPath);
		}

		public void forPlay() {
			switch (mAudioManager.getRingerMode()) {
			case AudioManager.RINGER_MODE_VIBRATE:
				long[] vibratePattern = { 100, 100, 300, 100, 100, 300 };
				mVibrator.vibrate(2000);
				mVibrator.vibrate(vibratePattern, -1);
				break;

			case AudioManager.RINGER_MODE_NORMAL:
				mRingtone.play();
				break;
			}
		}

		public void forStop() {
			if (mAudioManager.getRingerMode() == AudioManager.RINGER_MODE_NORMAL && isPlay()) {
				mRingtone.stop();
			}
		}

		public boolean isPlay() {
			return mRingtone.isPlaying();
		}
	}
}
