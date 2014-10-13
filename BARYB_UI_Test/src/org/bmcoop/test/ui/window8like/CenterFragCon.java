package org.bmcoop.test.ui.window8like;

import org.bmcoop.lib.base.util.Log;
import org.bmcoop.lib.base.view.PFragControl;
import org.bmcoop.lib.base.view.PFragment;

import android.content.Context;

public class CenterFragCon extends PFragControl {
	private CenterFrag mCenterFrag;
	private CallBackInCotrol mCallBackInCotrol;

	public CenterFragCon(Context pCon) {
		super(pCon);
	}

	public PFragment forFragCreate() {
		Log.i("DUER", "forFragCreate");
		reData();
		mCenterFrag = CenterFrag.with();
		return pOnCreate(mCenterFrag);
	}

	public PFragment forFragResume() {
		Log.i("DUER", "forFragResume");
		if (mCenterFrag == null) {
			Log.i("DUER", "mCenterFrag is null");
			return forFragCreate();
		}
		reData();
		Log.i("DUER", "mCenterFrag is not null");
		return pOnResume(mCenterFrag.withView());
	}

	private void reData() {
	}

	public void reSetImages() {
//		mCenterFrag.reSetImages();
		forFragResume();
;	}

	@Override
	public void abCallback() {
		mCenterFrag.setCallBack(new CenterFrag.CallBack() {

			@Override
			public void onClick(int _CLICK) {
				mCallBackInCotrol.onClick(_CLICK);
			}
		});
	}
	public void setCallback(CallBackInCotrol mCallBackInCotrol){
		this.mCallBackInCotrol = mCallBackInCotrol;
		
	}
	public interface CallBackInCotrol {
		void onClick(int _CLICK);
	}
}