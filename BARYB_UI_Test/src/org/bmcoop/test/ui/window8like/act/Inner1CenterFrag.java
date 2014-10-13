package org.bmcoop.test.ui.window8like.act;

import org.bmcoop.lib.base.util.Log;
import org.bmcoop.lib.base.view.PFragment;
import org.bmcoop.test.ui.R;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Inner1CenterFrag extends PFragment {

	private CallBack mCallBack;
	private View root;
	private TextView tvIconBig;
	private int tvNumber = 1;
	private RelativeLayout rlInner1;

	// private LinearLayout ll1, ll2, ll3, ll4;

	public static Inner1CenterFrag with(int tvNumber) {
		Inner1CenterFrag frag = new Inner1CenterFrag();
		frag.setTvNumber(tvNumber);
		return frag;
	}

	public Inner1CenterFrag withView(int tvNumber) {
		this.tvNumber = tvNumber;
		return this;
	}

	private void setTvNumber(int tvNumber) {
		this.tvNumber = tvNumber;
	}

	@Override
	public View abCreateView(LayoutInflater inflater, ViewGroup container) {
		root = pGetView_inflate(R.layout.inner1_frag, inflater, container);
		tvIconBig = (TextView) root.findViewById(R.id.tvIconBig);
		rlInner1 = (RelativeLayout) root.findViewById(R.id.rlInner1);

		
//		if (tvNumber == 1) {
//			rlInner1.setBackgroundColor(Color.rgb(1, 138, 0));
//		} else if (tvNumber == 2) {
//			rlInner1.setBackgroundColor(Color.rgb(14, 178, 239));
//		} else if (tvNumber == 3) {
//			rlInner1.setBackgroundColor(Color.rgb(142, 0, 149));
//		} else if (tvNumber == 4) {
//			rlInner1.setBackgroundColor(Color.rgb(210, 71, 38));
//		} else if (tvNumber == 5) {
//			rlInner1.setBackgroundColor(Color.rgb(1, 138, 0));
//		} else if (tvNumber == 6) {
//			rlInner1.setBackgroundColor(Color.rgb(1, 138, 0));
//		} else if (tvNumber == 7) {
//			rlInner1.setBackgroundColor(Color.rgb(210, 71, 38));
//		} else {
//			rlInner1.setBackgroundColor(Color.rgb(188, 56, 132));
//		}

		return root;
	}

	@Override
	public boolean abResume_isRefresh() {
		return true;
	}

	public interface CallBack {
		void onClick(int _CLICK);
	}

	public void callBack(CallBack new_CallBack) {
		mCallBack = new_CallBack;
	}
}