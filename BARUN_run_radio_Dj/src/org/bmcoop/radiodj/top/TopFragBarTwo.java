package org.bmcoop.radiodj.top;

import org.bmcoop.lib.base.view.PFragment;
import org.bmcoop.radiodj.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TopFragBarTwo extends PFragment {
	private CallBack mCallBack;
	public static int CLICK_1 = 0;
	public static int CLICK_2 = 1;

	private LinearLayout ll1;
	private LinearLayout ll2;
	private Button btn1;
	private Button btn2;
	private TextView text1;

	private int resImg1;
	private int resImg2;

	public static TopFragBarTwo with(int resImg1, int resImg2) {
		TopFragBarTwo frag = new TopFragBarTwo();
		frag.setResImg1(resImg1);
		frag.setResImg2(resImg2);
		return frag;
	}

	public TopFragBarTwo withView(int resImg1, int resImg2) {
		this.resImg1 = resImg1;
		this.resImg2 = resImg2;
		return this;
	}

	@Override
	public View abCreateView(LayoutInflater inflater, ViewGroup container) {
		View root = pGetView_inflate(R.layout.frag_bar2, inflater, container);
		ll1 = (LinearLayout) root.findViewById(R.id.ll1);
		ll2 = (LinearLayout) root.findViewById(R.id.ll2);
		btn1 = (Button) root.findViewById(R.id.btn1);
		btn2 = (Button) root.findViewById(R.id.btn2);
		text1 = (TextView) root.findViewById(R.id.text1);

//		ll1.setOnClickListener(new View.OnClickListener() {
//			public void onClick(View view) {
//
//			}
//		});
//
//		ll1.setOnClickListener(new View.OnClickListener() {
//			public void onClick(View view) {
//
//			}
//		});

		btn1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				System.exit(0);
			}
		});
		btn2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {

			}
		});

		return root;
	}

	@Override
	public boolean abResume_isRefresh() {
		if (resImg1 != 0) {
			ll1.setVisibility(View.VISIBLE);
			btn1.setBackgroundResource(resImg1);
		}
		if (resImg2 != 0) {
			ll2.setVisibility(View.VISIBLE);
			btn2.setBackgroundResource(resImg2);
		}
		return false;
	}

	public interface CallBack {
		void onClick(int _CLICK);
	}

	public void callBack(CallBack new_CallBack) {
		mCallBack = new_CallBack;
	}

	public void setResImg1(int resImg1) {
		this.resImg1 = resImg1;
	}

	public void setResImg2(int resImg2) {
		this.resImg2 = resImg2;
	}

}