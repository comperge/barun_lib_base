package org.bmcoop.test.ui.window8like.act;

import org.bmcoop.lib.base.util.Log;
import org.bmcoop.lib.base.view.PFragAct;
import org.bmcoop.test.ui.R;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class InnerAct1 extends PFragAct {

	private Inner1CenterFragCon mCenterFragCon = null;
	private int tvNumber = -1;
	private LinearLayout llInner1Act;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

//		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
//		getWindow().setFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND, WindowManager.LayoutParams.FLAG_DIM_BEHIND);
		
//		WindowManager.LayoutParams lp = getWindow().getAttributes();  
//		lp.dimAmount=0.0f;  
//		getWindow().setAttributes(lp);  
//		getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND); 
		
//		getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		
		
		setContentView(R.layout.inner1_act);

		tvNumber = getIntent().getIntExtra("tvNumber", 1);
//		Log.i("tvNumbe~~~~~~~~~~~~~~~~act", tvNumber);
		// getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
		// WindowManager.LayoutParams.FLAG_BLUR_BEHIND);

		mCenterFragCon = new Inner1CenterFragCon(pCon, tvNumber);
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
}