package org.bmcoop.lib.base.view;

import org.bmcoop.lib.base.util.Log;
import org.bmcoop.lib.base.util.UActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;


public abstract class PFragAct extends FragmentActivity {
	public FragmentManager mFragMan;
	public Context pCon;
	public Intent pIntent;
	private boolean isCreate = true;
	private boolean isResume;
	private int mRequestCode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		mFragMan = getSupportFragmentManager();

		pCon = getContext();
		pOnResume();
	}

	public abstract void abFragCreate();

	public abstract void abFragResume(int requestCode);

	@SuppressLint("HandlerLeak")
	protected Handler pHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (isCreate) {
				isCreate = false;
				isResume = false;
				abFragCreate();
				pAfterFragCreate();
			} else {
				if (isResume) {
					isResume = false;
					abFragResume(mRequestCode);
				}
			}

			super.handleMessage(msg);
		}
	};

	public void pAfterFragCreate(){
	}
	
	public void pOnResume() {
		Log.i("DUER", "Activity pOnResume " + isCreate);
		isResume = true;
		Message msg = Message.obtain();
		pHandler.sendMessageDelayed(msg, 50);
	}
	
	public Context getContext() {
		return abReturnThis();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode == Activity.RESULT_OK){
			setRequestCode(requestCode);
			if(data != null){
				if(data.getBooleanExtra(UActivity.KEY_Refresh, false)){
					pIntent = data;
					isResume = true;
					pOnResume();
				}
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	public int pGetRequestCode() {
		return mRequestCode;
	}

	private void setRequestCode(int requestCode) {
		this.mRequestCode = requestCode;
	}

	public abstract Context abReturnThis();

	public void pFragAdd(Fragment frag) {
		if (frag != null) {
			final FragmentTransaction fragTrans = mFragMan.beginTransaction();
			fragTrans.add(android.R.id.content, frag, "");
			fragTrans.commit();
		}
	}

	public void pFragAdd(int resId, Fragment frag) {
		if (frag != null) {
			final FragmentTransaction fragTrans = mFragMan.beginTransaction();
			fragTrans.add(resId, frag, "");
			fragTrans.commit();
		}
	}

	public void pFragAdd(int resId, Fragment frag, int FragmentTransaction_TRANSIT_) {
		if (frag != null) {
			final FragmentTransaction fragTrans = mFragMan.beginTransaction();
			fragTrans.add(resId, frag, "");
			fragTrans.addToBackStack(null);
			fragTrans.setTransition(FragmentTransaction_TRANSIT_);
			fragTrans.commit();
		}
	}

	public void pFragRemove(Fragment frag) {
		if (frag != null) {
			final FragmentTransaction fragTrans = mFragMan.beginTransaction();
			fragTrans.remove(frag);
			fragTrans.commit();
		}
	}

	public void pFragReplace(int resId, Fragment frag) {
		if (frag != null) {
			final FragmentTransaction fragTrans = mFragMan.beginTransaction();
			fragTrans.replace(resId, frag);
			fragTrans.commit();
		}
	}
	
	public void pFragReplace(int resId, Fragment frag, int FragmentTransaction_TRANSIT_) {
		if (frag != null) {
			final FragmentTransaction fragTrans = mFragMan.beginTransaction();
			fragTrans.replace(resId, frag, "");
			fragTrans.addToBackStack(null);
			fragTrans.setTransition(FragmentTransaction_TRANSIT_);
			fragTrans.commit();
		}
	}

	public void pFragShow(Fragment frag) {
		final FragmentTransaction fragTrans = mFragMan.beginTransaction();
		fragTrans.show(frag);
		fragTrans.commit();
	}

	public void pFragHide(Fragment frag) {
		final FragmentTransaction fragTrans = mFragMan.beginTransaction();
		fragTrans.hide(frag);
		fragTrans.commit();
	}
}