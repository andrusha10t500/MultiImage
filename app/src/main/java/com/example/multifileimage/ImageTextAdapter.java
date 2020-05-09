package com.example.multifileimage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageTextAdapter extends BaseAdapter {

    private Context mContext;
    public ImageTextAdapter(Context c) {
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
        View grid;
        if(view==null) {
            grid = new View(mContext);
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            grid = inflater.inflate(R.layout.cellgrid, viewGroup, false);
        }
        else {
            grid = (View)view;
        }
        ImageView imageview = (ImageView)grid.findViewById(R.id.imagepart);
        imageview.setImageResource(mThumbdId[i]);
        return grid;
    }

    public int[] mThumbdId  = {R.drawable.image1, R.drawable.image2};
}
