package org.bmcoop.test.ui.window8like.act;

import org.bmcoop.lib.base.view.PFragControl;
import org.bmcoop.lib.base.view.PFragment;

import android.content.Context;

public class Inner1CenterFragCon extends PFragControl {
	private Inner1CenterFrag mCenterFrag;
	private int tvNumber = 1;
	public Inner1CenterFragCon(Context pCon, int tvNumber) {
		super(pCon);
		this.tvNumber = tvNumber;
	}

	public PFragment forFragCreate() {
		reData();
		mCenterFrag = Inner1CenterFrag.with(tvNumber);
		return pOnCreate(mCenterFrag);
	}

	public PFragment forFragResume() {
		if (mCenterFrag == null) {
			return forFragCreate();
		}
		reData();
		return pOnResume(mCenterFrag.withView(tvNumber));
	}

	private void reData() {
	}

	@Override
	public void abCallback() {
		mCenterFrag.callBack(new Inner1CenterFrag.CallBack() {
			@Override
			public void onClick(int _CLICK) {

			}
		});
	}
}