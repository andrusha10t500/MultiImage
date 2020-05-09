package com.example.multifileimage;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

    private Context mContext;
    public ImageAdapter(Context c) {
        mContext = c;
    }

    @Override
    public int getCount() {
        return mThumbdId.length;
    }

    @Override
    public Object getItem(int i) {
        return mThumbdId[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageview;
        if(view == null) {
            imageview = new ImageView(mContext);
            imageview.setLayoutParams(new GridView.LayoutParams(100,100));
            imageview.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageview.setPadding(8,8,8,8);
        }
        else {
            imageview = (ImageView)view;
        }
        imageview.setImageResource(mThumbdId[i]);
        return imageview;
    }

    public int[] mThumbdId  = {R.drawable.image1, R.drawable.image2};
}
