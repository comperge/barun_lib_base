package org.bmcoop.radiodj;

import org.bmcoop.lib.base.util.Log;
import org.bmcoop.lib.base.view.PFragAct;
import org.bmcoop.radiodj.schadule.CenterFragCon;
import org.bmcoop.radiodj.top.TopBarTwoCon;

import android.content.Context;
import android.os.Bundle;

public class MainActivity extends PFragAct {

	private TopBarTwoCon mTopBarTwoCon = null;
	private CenterFragCon mCenterFragCon = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_act);
		mTopBarTwoCon = new TopBarTwoCon(pCon);
		mCenterFragCon = new CenterFragCon(pCon);
		
//		int width = getWindowManager().getDefaultDisplay().getgetWidth();
//		int height = getWindowManager().getDefaultDisplay().getHeight();
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void abFragCreate() {
		pFragAdd(R.id.frameTop, mTopBarTwoCon.forFragCreate());
		pFragAdd(R.id.frameCenter, mCenterFragCon.forFragCreate());
	}

	@Override
	public void abFragResume(int requestCode) {
	}

	@Override
	public Context abReturnThis() {
		return this;
	}
}