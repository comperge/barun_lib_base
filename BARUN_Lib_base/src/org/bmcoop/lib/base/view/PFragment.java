package org.bmcoop.lib.base.view;

import java.util.ArrayList;

import org.bmcoop.lib.base.util.Log;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public abstract class PFragment extends Fragment {
	public LayoutInflater pInflater;
	public Activity pActivity;
	public Context pCon;
	public ArrayList<FragEnty> pFragList;
	public int mIndexFragNow;

	@Override
	public void onAttach(Activity activity) {
		// Activity에 처음 부착될 때 호출됨. activity는 부모를 의미하고, getActivity()로 호출할 수 있다.
		pActivity = activity;
		pCon = activity;
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// Activity도 아직 생성되기 전이다. menu를 자체 적으로 만들 겠다. 이런 것이 가능하다
		setHasOptionsMenu(true);
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Activity는 아직 생성되기 전이다. layout에 관련된 사항만 정의한다. getView()로 자체의 뷰를 부를 수
		// 있다.
		return abCreateView(inflater, container);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// Activity가 초기화 되고 난 이후에 생성된다. 실제 운영은 여기서 시작된다.
		Log.i("DUER", "onActivityCreated");
		pOnActivityCreated(savedInstanceState);
		super.onActivityCreated(savedInstanceState);
		pOnResume();
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.i("DUER", "onPause");
		if (pActivity.isFinishing()) {
			pOnPausing();
		}
	}

	//	UViewGroup.withFrame(root, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).setGravity_bottomCenter();

	public View pGetView_inflate(int resId, LayoutInflater inflater, ViewGroup container) {
		return inflater.inflate(resId, container, false);
	}

	// getActivity().getSupportFragmentManager().beginTransaction().remove(FragCamera.this).commit();
	public void pOnActivityCreated(Bundle savedInstanceState) {
	}

	public void pOnPausing() {
	}

	@SuppressLint("HandlerLeak") 
	protected Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			Log.i("DUER~", "handleMessage_mHandler");
			try {
				abResume_isRefresh();
				if(abResume_isRefresh()){
					pFragList_refreshVisible();	
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			super.handleMessage(msg);
		}
	};

	public void pOnResume() {
		Log.i("DUER~", "pOnResume");
		Message msg = Message.obtain();
		mHandler.sendMessageDelayed(msg, 50);
	}

	public void pSetFragList(ArrayList<FragEnty> fragList) {
		pFragList = fragList;
	}

	public void pFragList_addCallback(View viewParent, View viewChild) {
		if (pFragList != null && pFragList.size() > mIndexFragNow) {
			pFragList.get(mIndexFragNow).setViewParent(viewParent);
			pFragList.get(mIndexFragNow).setViewChild(viewChild);
			makeClickListener(viewParent, viewChild, mIndexFragNow);
			makeToast(viewParent, viewChild, mIndexFragNow);
			mIndexFragNow++;
		}
	}

	private void makeClickListener(View parent, View child, final int index) {
		if (parent != null) {
			parent.setOnClickListener(new View.OnClickListener() {
				public void onClick(View view) {
					pOnClick_callback(index);
				}
			});
			parent.setVisibility(View.VISIBLE);
		}

		child.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				pOnClick_callback(index);
			}
		});
	}

	private void makeToast(View parent, View child, final int index) {
		pSetView_toast(parent, child, pFragList.get(index).getName());
	}
	
	public void pSetView_toast(View parent, View child, final String str){
		if (parent != null) {
			parent.setOnLongClickListener(new View.OnLongClickListener() {
				@Override
				public boolean onLongClick(View v) {
					Toast.makeText(pCon, str, Toast.LENGTH_SHORT).show();
					return true;
				}
			});
		}

		child.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				Toast.makeText(pCon, str, Toast.LENGTH_SHORT).show();
				return true;
			}
		});
	}

	private void pFragList_refreshVisible() {
		if (pFragList != null) {
			for (int i = 0; i < pFragList.size(); i++) {
				if (pFragList.get(i).getResId() != 0) {
					if (pFragList.get(i).getViewParent() != null) {
						pFragList.get(i).getViewParent().setVisibility(View.VISIBLE);
					}
					if (pFragList.get(i).getViewChild() != null) {
						pFragList.get(i).getViewChild().setBackgroundResource(pFragList.get(i).getResId());
						pFragList.get(i).getViewChild().setVisibility(View.VISIBLE);
					}
				} else {
					if (pFragList.get(i).getViewParent() != null) {
						pFragList.get(i).getViewParent().setVisibility(View.GONE);
					}
					if (pFragList.get(i).getViewChild() != null) {
						pFragList.get(i).getViewChild().setVisibility(View.GONE);
					}
				}
			}
		}
	}

	public void pOnClick_callback(final int index) {
	}

	public abstract View abCreateView(LayoutInflater inflater, ViewGroup container);

	public abstract boolean abResume_isRefresh();
}