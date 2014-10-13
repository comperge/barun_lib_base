package org.bmcoop.lib.base.view;

import android.content.Context;

public abstract class PFragControl {
	public Context pCon;
	public PFragment pPFragment;
	
	public PFragControl(Context context) {
		pCon = context;
	}

	public abstract void abCallback();

	public PFragment pOnCreate(PFragment pFragment) {
		abCallback();
		return pFragment;
	}

	public PFragment pOnResume(PFragment pFragment) {
//		pFragment.pOnResume();
		abCallback();
		return pFragment;
	}
}