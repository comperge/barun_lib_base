package org.bmcoop.test.ui.window8like;

import org.bmcoop.lib.base.util.Log;
import org.bmcoop.lib.base.util.UFilter;
import org.bmcoop.lib.base.view.PFragment;
import org.bmcoop.test.ui.R;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CenterFrag extends PFragment {
	private CallBack mCallBack;
	private View root;
	private ImageView mBlurImage;
	private TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8;
	private int gapAnitStart = 30;
	private RelativeLayout mother_ll;
	private AnimationSet setAni;
	private int tvNumber = -1;

	// private LinearLayout ll1, ll2, ll3, ll4;

	public static CenterFrag with() {
		CenterFrag frag = new CenterFrag();
		return frag;
	}

	public CenterFrag withView() {
		return this;
	}

	@Override
	public View abCreateView(LayoutInflater inflater, ViewGroup container) {
		root = pGetView_inflate(R.layout.center_frag, inflater, container);
		tv1 = (TextView) root.findViewById(R.id.tv1);
		tv2 = (TextView) root.findViewById(R.id.tv2);
		tv3 = (TextView) root.findViewById(R.id.tv3);
		tv4 = (TextView) root.findViewById(R.id.tv4);
		tv5 = (TextView) root.findViewById(R.id.tv5);
		tv6 = (TextView) root.findViewById(R.id.tv6);
		tv7 = (TextView) root.findViewById(R.id.tv7);
		tv8 = (TextView) root.findViewById(R.id.tv8);
		mBlurImage = (ImageView) root.findViewById(R.id.blurView);
		// ll1 = (LinearLayout) root.findViewById(R.id.ll1);
		// ll2 = (LinearLayout) root.findViewById(R.id.ll2);
		// ll3 = (LinearLayout) root.findViewById(R.id.ll3);
		// ll4 = (LinearLayout) root.findViewById(R.id.ll4);
		mother_ll = (RelativeLayout) root.findViewById(R.id.mother_ll);

		tv8.setAlpha(11.8f);
		Animation mAnima = AnimationUtils.loadAnimation(pCon, R.anim.alpha_showing);
		mother_ll.startAnimation(mAnima);

		applyRotation(tv1, 90, 90, 1 * gapAnitStart);
		applyRotation(tv2, 90, 90, 2 * gapAnitStart);
		applyRotation(tv3, 90, 90, 3 * gapAnitStart);
		applyRotation(tv4, 90, 90, 4 * gapAnitStart);
		applyRotation(tv5, 90, 90, 5 * gapAnitStart);
		applyRotation(tv6, 90, 90, 6 * gapAnitStart);
		applyRotation(tv7, 90, 90, 7 * gapAnitStart);
		applyRotation(tv8, 90, 90, 8 * gapAnitStart);

		// applyRotation(ll1, 90, 90, 1 * gapAnitStart);
		// applyRotation(ll2, 90, 90, 2 * gapAnitStart);
		// applyRotation(ll3, 90, 90, 3 * gapAnitStart);
		// applyRotation(ll4, 90, 90, 4 * gapAnitStart);

		// Animation anima = AnimationUtils.loadAnimation(pCon, R.anim.showin);
		// tv1.startAnimation(anima);
		// tv2.startAnimation(anima);
		// tv3.startAnimation(anima);
		// tv4.startAnimation(anima);

		tv1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				tvNumber = 1;
				movetoCenterAnimation(v);
				tv1.setVisibility(View.INVISIBLE);
			}
		});
		tv2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				tvNumber = 2;
				movetoCenterAnimation(v);
			}
		});
		tv3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				tvNumber = 3;
				movetoCenterAnimation(v);
			}
		});
		tv4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				tvNumber = 4;
				movetoCenterAnimation(v);
			}
		});
		tv5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				tvNumber = 5;
				movetoCenterAnimation(v);
			}
		});
		tv6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				tvNumber = 6;
				movetoCenterAnimation(v);
			}
		});
		tv7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				tvNumber = 7;
				movetoCenterAnimation(v);
			}
		});
		tv8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				tvNumber = 8;
				movetoCenterAnimation(v);
			}
		});

		mBlurImage.setImageBitmap(UFilter.with(pCon).getblur(root, 200));
		return root;
	}

	public void reSetImages() {
		setAni.setFillAfter(false);
	}

	private void applyRotation(View view, float start, float end, long startOffset) {
		// Find the center of the container
		final float centerX = view.getWidth() / 2.0f;
		final float centerY = view.getHeight() / 2.0f;

		// Create a new 3D rotation with the supplied parameter
		// The animation listener is used to trigger the next animation
		final Rotate3dAnimation rotation = new Rotate3dAnimation(start, end, centerX, centerY, 310.0f, true);
		rotation.setStartOffset(startOffset);
		rotation.setDuration(50);
		rotation.setFillAfter(true);
		rotation.setInterpolator(new AccelerateInterpolator());
		rotation.setAnimationListener(new DisplayNextView(view));

		view.startAnimation(rotation);
	}

	private final class DisplayNextView implements Animation.AnimationListener {

		private View view;

		private DisplayNextView(View view) {
			this.view = view;
		}

		public void onAnimationStart(Animation animation) {
		}

		public void onAnimationEnd(Animation animation) {
			view.post(new SwapViews(view));
		}

		public void onAnimationRepeat(Animation animation) {
		}
	}

	/**
	 * This class is responsible for swapping the views and start the second
	 * half of the animation.
	 */
	private final class SwapViews implements Runnable {

		private View view;

		public SwapViews(View view) {
			this.view = view;
		}

		public void run() {
			final float centerX = view.getWidth() / 2.0f;
			final float centerY = view.getHeight() / 2.0f;
			Rotate3dAnimation rotation;

			// if (mPosition > -1) {
			// mPhotosList.setVisibility(View.GONE);
			view.setVisibility(View.VISIBLE);
			view.requestFocus();

			rotation = new Rotate3dAnimation(90, 180, centerX, centerY, 310.0f, false);
			// } else {
			// mImageView.setVisibility(View.GONE);
			// mPhotosList.setVisibility(View.VISIBLE);
			// mPhotosList.requestFocus();
			// rotation = new Rotate3dAnimation(90, 0, centerX, centerY, 310.0f,
			// false);
			// }

			rotation.setDuration(200);
			rotation.setFillAfter(true);
			rotation.setInterpolator(new DecelerateInterpolator());

			view.startAnimation(rotation);
		}
	}

	@Override
	public boolean abResume_isRefresh() {
		Log.i("DUER", "abResume_isRefresh");
		return true;
	}

	public interface CallBack {
		void onClick(int _CLICK);
	}

	public void setCallBack(CallBack new_CallBack) {
		mCallBack = new_CallBack;
	}

	// View.getLocatioinOnScreen(int[]);
	// 이러면 뷰가 스크린상 위치하는 좌표를 알 수 있다. TouchEvent에서 들어온 x,y 좌표들과 동일한 체계가 된다.
	// 그래서 예를 들어 TouchEvent 해당 View안에서 일어났는지 알기위해서는 이런코드면된다.
	public boolean chkTouchInside(View view, int x, int y) {
		int[] location = new int[2];
		view.getLocationOnScreen(location);
		if (x >= location[0]) {
			if (x <= location[0] + view.getWidth()) {
				if (y >= location[1]) {
					if (y <= location[1] + view.getHeight()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private synchronized void movetoCenterAnimation(View v) {

		setCenter(v);
		v.bringToFront();
		setAni = new AnimationSet(true);

		TranslateAnimation ani = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.ABSOLUTE, moveX, Animation.RELATIVE_TO_SELF, 0,
				Animation.ABSOLUTE, moveY);
		ani.setDuration(500); // 지속시간
		setAni.addAnimation(ani);
		// ///////////////////////////////////////////////////////////////////////
		Animation mAnima = AnimationUtils.loadAnimation(pCon, R.anim.scale_menu);
		setAni.addAnimation(mAnima);
		// ////////////////////////////////////////////////////////////////
		final float centerX = v.getRight() - v.getLeft();
		final float centerY = v.getBottom() - v.getTop();
		// final float centerX = v.getWidth() / 2.0f;
		// final float centerY = v.getHeight() / 2.0f;

		Log.i("v.getLeft() " + v.getLeft());
		Log.i("v.getRight() " + v.getRight());
		Log.i("v.getTop() " + v.getTop());
		Log.i("v.getBottom() " + v.getBottom());
		Log.i("centerX " + centerX);
		Log.i("centerY " + centerY);

		// Create a new 3D rotation with the supplied parameter
		final Rotate3dAnimation rotation = new Rotate3dAnimation(0, 360, 0, 0, 0, false);
		rotation.setDuration(500);
		rotation.setInterpolator(new AccelerateInterpolator());
		setAni.addAnimation(rotation);
		// //////////////////////////////////////////////////////////////
		setAni.setFillAfter(true);
		setAni.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {

				executorHandler.sendEmptyMessage(0);
//				mCallBack.onClick(tvNumber);

			}
		});
		v.startAnimation(setAni);

	}

	@SuppressLint("HandlerLeak")
	private Handler executorHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			try {
				mBlurImage.setAlpha(0.9f);
				// mBlurImage.setImageBitmap(UFilter.with(pCon).getBlurFilter(mother_ll,500));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};

	private int moveX = 0;
	private int moveY = 0;
	private int motherXCenter = 0;
	private int motherYCenter = 0;

	private void setCenter(View v) {
		Log.i("mother_ll.getLeft() " + mother_ll.getLeft());
		Log.i("mother_ll.getRight() " + mother_ll.getRight());
		Log.i("mother_ll.getTop() " + mother_ll.getTop());
		Log.i("mother_ll.getBottom() " + mother_ll.getBottom());
		Log.i("v.getLeft() " + v.getLeft());
		Log.i("v.getRight() " + v.getRight());
		Log.i("v.getTop() " + v.getTop());
		Log.i("v.getBottom() " + v.getBottom());

		int vXCenter = (v.getLeft() + v.getRight()) / 2;
		int vYCenter = (v.getTop() + v.getBottom()) / 2;
		motherXCenter = (mother_ll.getLeft() + mother_ll.getRight()) / 2;
		motherYCenter = (mother_ll.getTop() + mother_ll.getBottom()) / 2;

		moveX = (motherXCenter - vXCenter) / 3;
		moveY = (motherYCenter - vYCenter) / 3;
	}
}