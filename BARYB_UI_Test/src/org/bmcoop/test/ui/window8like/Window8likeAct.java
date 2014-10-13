package org.bmcoop.test.ui.window8like;

import org.bmcoop.lib.base.util.Log;
import org.bmcoop.lib.base.view.PFragAct;
import org.bmcoop.test.ui.R;

import org.bmcoop.test.ui.window8like.CenterFragCon.CallBackInCotrol;
import org.bmcoop.test.ui.window8like.act.InnerAct1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class Window8likeAct extends PFragAct {

	private CenterFragCon mCenterFragCon = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.window_8_like_act);
		mCenterFragCon = new CenterFragCon(pCon);
		mCenterFragCon.setCallback(new CallBackInCotrol() {
			@Override
			public void onClick(int _CLICK) {
				Intent intent = new Intent(Window8likeAct.this, InnerAct1.class).putExtra("tvNumber", _CLICK);
				startActivityForResult(intent, 0);
				overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
			}
		});
		
	}

	@Override
	public void abFragCreate() {
		pFragAdd(R.id.frameCenter, mCenterFragCon.forFragCreate());
	}

	@Override
	public void abFragResume(int requestCode) {
	}

	@Override
	public Context abReturnThis() {
		return null;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.i("DUER", "ERERERERERER");
		mCenterFragCon.reSetImages();
	}
}