package org.bmcoop.lib.base.view;

import android.view.View;

public class FragEnty {
	private String name;
	private int resId;
	private View viewParent;
	private View viewChild;

	public FragEnty(String name) {
		this.name = name;
	}

	public FragEnty(String name, int resId) {
		this.name = name;
		this.resId = resId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getResId() {
		return resId;
	}

	public void setResId(int resId) {
		this.resId = resId;
	}

	public View getViewParent() {
		return viewParent;
	}

	public void setViewParent(View viewParent) {
		this.viewParent = viewParent;
	}

	public View getViewChild() {
		return viewChild;
	}

	public void setViewChild(View viewChild) {
		this.viewChild = viewChild;
	}
}