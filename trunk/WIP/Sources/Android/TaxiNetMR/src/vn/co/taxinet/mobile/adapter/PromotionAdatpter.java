package vn.co.taxinet.mobile.adapter;

import java.util.List;

import vn.co.taxinet.mobile.R;
import vn.co.taxinet.mobile.DTO.PromotionDTO;
import vn.co.taxinet.mobile.app.AppController;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

public class PromotionAdatpter extends BaseAdapter {

	private Activity activity;
	private LayoutInflater inflater;
	private List<PromotionDTO> promotionItems;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();

	public PromotionAdatpter(Activity activity,
			List<PromotionDTO> promotionItems) {
		this.activity = activity;
		this.promotionItems = promotionItems;
	}

	@Override
	public int getCount() {
		return promotionItems.size();
	}

	@Override
	public Object getItem(int location) {
		return promotionItems.get(location);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (inflater == null)
			inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null)
			convertView = inflater.inflate(R.layout.item_rider_promotion,
					null);

		if (imageLoader == null)
			imageLoader = AppController.getInstance().getImageLoader();
		NetworkImageView thumbNail = (NetworkImageView) convertView
				.findViewById(R.id.thumbnail);
		TextView mPromotionContent = (TextView) convertView
				.findViewById(R.id.tv_promotion_content);

		// getting movie data for the row
		PromotionDTO promotionDTO = promotionItems.get(position);

		// thumbnail image
		thumbNail.setImageUrl(promotionDTO.getImageUri(), imageLoader);

		// title
		mPromotionContent.setText(promotionDTO.getPromotion_content());

		return convertView;
	}
}
