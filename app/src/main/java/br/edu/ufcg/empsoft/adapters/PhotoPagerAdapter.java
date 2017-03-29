package br.edu.ufcg.empsoft.adapters;

import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import br.edu.ufcg.empsoft.R;
import br.edu.ufcg.empsoft.models.PhotoItem;

/**
 * Created by treinamento-20 on 29/03/17.
 */

public class PhotoPagerAdapter extends PagerAdapter implements PhotoAdapter {

    private List<View> mViews;
    private List<PhotoItem> mData;
    private float mBaseElevation;

    public PhotoPagerAdapter() {
        mData = new ArrayList<>();
        mViews = new ArrayList<>();
    }

    public void addPhoto(PhotoItem item) {
        mViews.add(null);
        mData.add(item);
    }

    @Override
    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public View getCardViewAt(int position) {
        return mViews.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.photo_adapter, container, false);
        container.addView(view);
        bind(mData.get(position), view);
//        CardView cardView = (CardView) view.findViewById(R.id.cardView);
//
//        if (mBaseElevation == 0) {
//            mBaseElevation = cardView.getCardElevation();
//        }
//
//        cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);
        mViews.set(position, view.findViewById(R.id.cardView));
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        mViews.set(position, null);
    }

    private void bind(PhotoItem item, View view) {
        ImageView imageView = (ImageView) view.findViewById(R.id.photo);
        Picasso.with(view.getContext()).load(item.getUrl()).into(imageView);
    }



}
