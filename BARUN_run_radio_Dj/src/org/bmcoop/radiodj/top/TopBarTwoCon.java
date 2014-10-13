package org.bmcoop.radiodj.top;

import org.bmcoop.lib.base.view.PFragControl;
import org.bmcoop.lib.base.view.PFragment;
import org.bmcoop.radiodj.R;

import android.content.Context;

public class TopBarTwoCon extends PFragControl {
	private TopFragBarTwo mTopFragBarTwo;

	public TopBarTwoCon(Context pCon) {
		super(pCon);
	}

	public PFragment forFragCreate() {
		reData();
		mTopFragBarTwo = TopFragBarTwo.with(R.drawable.btn_prev, 0);
		return pOnCreate(mTopFragBarTwo);
	}

	public PFragment forFragResume() {
		if (mTopFragBarTwo == null) {
			return forFragCreate();
		}
		reData();
		return pOnResume(mTopFragBarTwo.withView(R.drawable.btn_prev, -1));
	}

	private void reData() {
	}

	@Override
	public void abCallback() {
		mTopFragBarTwo.callBack(new TopFragBarTwo.CallBack() {

			public void onClick(int _CLICK) {
			}
		});
	}
}