package org.bmcoop.radiodj.schadule;

import java.util.ArrayList;

import org.bmcoop.lib.base.util.Log;
import org.bmcoop.lib.base.view.PFragControl;
import org.bmcoop.lib.base.view.PFragment;

import android.content.Context;

public class CenterFragCon extends PFragControl {
	private CenterFrag mCenterFrag;
	private ArrayList<SchaduleBean> al = new ArrayList<SchaduleBean>();

	public CenterFragCon(Context pCon) {
		super(pCon);
	}

	public PFragment forFragCreate() {
		Log.i("DUER", "forFragCreate");
		reData();
		mCenterFrag = CenterFrag.with(al);
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
		return pOnResume(mCenterFrag.withView(al));
	}

	private void reData() {
		al = new DummyDataCreator().getData();
	}

	@Override
	public void abCallback() {
		mCenterFrag.callBack(new CenterFrag.CallBack() {

			@Override
			public void onClickRow(int _CLICK) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onClickSubmit(int _CLICK) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onClickImg(int _CLICK) {
				// TODO Auto-generated method stub
				
			}

		});
	}
}