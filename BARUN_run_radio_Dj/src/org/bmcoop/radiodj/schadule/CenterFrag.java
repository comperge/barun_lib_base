package org.bmcoop.radiodj.schadule;

import java.util.ArrayList;

import org.bmcoop.lib.base.util.Log;
import org.bmcoop.lib.base.view.PFragment;
import org.bmcoop.radiodj.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class CenterFrag extends PFragment {
	private CallBack mCallBack;
	private ArrayList<SchaduleBean> beans;
	private ListView lv;
	private SchaduleListAdapter listAdapter;

	public static CenterFrag with(ArrayList<SchaduleBean> beans) {
		CenterFrag frag = new CenterFrag();
		frag.setData(beans);
		return frag;
	}

	public CenterFrag withView(ArrayList<SchaduleBean> beans) {
		this.beans = beans;
		return this;
	}

	@Override
	public View abCreateView(LayoutInflater inflater, ViewGroup container) {
		View root = pGetView_inflate(R.layout.center_frag, inflater, container);
		lv = (ListView) root.findViewById(R.id.lvSschadule);
		return root;
	}

	@Override
	public boolean abResume_isRefresh() {
		Log.i("DUER", "abResume_isRefresh");
		listAdapter = new SchaduleListAdapter();
		lv.setAdapter(listAdapter);
		if (beans == null || beans.size() == 0) {
			lv.setVisibility(View.GONE);
		}
		return true;
	}

	public interface CallBack {
		void onClickRow(int _CLICK);

		void onClickSubmit(int _CLICK);

		void onClickImg(int _CLICK);
	}

	public void callBack(CallBack new_CallBack) {
		mCallBack = new_CallBack;
	}

	public void setData(ArrayList<SchaduleBean> beans) {
		this.beans = beans;
	}

	private class SchaduleListAdapter extends BaseAdapter {
		private ViewHolder mViewHolder = null;

		@Override
		public int getCount() {
			if (beans != null)
				return beans.size();
			else
				return 0;
		}

		@Override
		public Object getItem(int position) {
			if (beans != null)
				return beans.get(position);
			else
				return null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			if (beans == null || beans.get(position) == null)
				return null;

			if (convertView == null) {
				mViewHolder = new ViewHolder();
				convertView = View.inflate(pCon, R.layout.center_frag_item, null);

				mViewHolder.tvDate = (TextView) convertView.findViewById(R.id.tvDate);
				mViewHolder.tvModifyDate = (TextView) convertView.findViewById(R.id.tvModifyDate);
				mViewHolder.tvState = (TextView) convertView.findViewById(R.id.tvState);
				mViewHolder.tvStateImg = (TextView) convertView.findViewById(R.id.tvStateImg);
				mViewHolder.tvSubmit = (TextView) convertView.findViewById(R.id.tvSubmit);
				convertView.setTag(mViewHolder);
			} else {
				mViewHolder = (ViewHolder) convertView.getTag();
			}
			if (beans.get(position).getDate() != null)
				mViewHolder.tvDate.setText(beans.get(position).getDate());
			if (beans.get(position).getModifyDate() != null)
				mViewHolder.tvModifyDate.setText(beans.get(position).getModifyDate());

			if (beans.get(position).getState() == SchaduleBean.PROGRAM_EMPTY) {
				mViewHolder.tvState.setText("해당 프로그램이 작성되지 않았습니다");
				mViewHolder.tvStateImg.setBackgroundResource(R.drawable.bg_empty);
				mViewHolder.tvSubmit.setVisibility(View.GONE);
			} else if (beans.get(position).getState() == SchaduleBean.PROGRAM_PROCESSING) {
				mViewHolder.tvState.setText("해당 프로그램이 작성중입니다");
				mViewHolder.tvStateImg.setBackgroundResource(R.drawable.bg_processing);
				mViewHolder.tvSubmit.setVisibility(View.VISIBLE);
			} else if (beans.get(position).getState() == SchaduleBean.PROGRAM_OCCUPIED) {
				mViewHolder.tvState.setText("해당 프로그램이 작성완료되었습니다");
				mViewHolder.tvStateImg.setBackgroundResource(R.drawable.bg_occupied);
				mViewHolder.tvSubmit.setVisibility(View.VISIBLE);
			} else {
				mViewHolder.tvState.setText("해당 프로그램이 서버등록 되었습니다");
				mViewHolder.tvStateImg.setBackgroundResource(R.drawable.bg_submit);
				mViewHolder.tvSubmit.setVisibility(View.VISIBLE);
			}

			mViewHolder.tvStateImg.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					Log.i("DUER", "mViewHolder.tvStateImg ");
				}
			});

			mViewHolder.tvSubmit.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					Log.i("DUER", "mViewHolder.tvSubmit ");
				}
			});
			return convertView;
		}
	}

	private class ViewHolder {
		public TextView tvSubmit;
		public TextView tvStateImg;
		public TextView tvDate;
		public TextView tvState;
		public TextView tvModifyDate;
	}

}