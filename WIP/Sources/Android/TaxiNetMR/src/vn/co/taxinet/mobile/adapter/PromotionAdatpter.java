package vn.co.taxinet.mobile.adapter;

import java.util.ArrayList;

import vn.co.taxinet.mobile.DTO.PromotionDTO;
import vn.co.taxinet.mobile.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PromotionAdatpter extends ArrayAdapter<PromotionDTO> {
	private Context context;
	private int textViewResourceId;
	private ArrayList<PromotionDTO> listPromotion;
	private TextView mPromotionContent;

	public PromotionAdatpter(Context context, int textViewResourId,
			ArrayList<PromotionDTO> listPromotion) {
		super(context, textViewResourId, listPromotion);
		this.context = context;
		this.textViewResourceId = textViewResourId;
		this.listPromotion = listPromotion;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(textViewResourceId, null);
		}
		PromotionDTO promotion = listPromotion.get(position);
		mPromotionContent.setText(promotion.getPromotion_content());
		return v;
	}

	public void initialize(View v) {
		mPromotionContent = (TextView) v.findViewById(R.id.promotion_content);
	}
}
