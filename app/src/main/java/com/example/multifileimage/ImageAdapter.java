package com.example.multifileimage;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter  {
    private Context mContext;
    public String[] ImageArrayForUrl;
    public ImageAdapter(Context c, String[] mImageArrayForUrl) {
        mContext = c;
        ImageArrayForUrl = mImageArrayForUrl;
    }

    @Override
    public int getCount() {
        if(ImageArrayForUrl == null) {
            return 0;
        } else {
            return ImageArrayForUrl.length;
        }
    }

    @Override
    public Object getItem(int i) {
        return ImageArrayForUrl[i];
        //mThumbdId[i];
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
//        imageview.setImageResource(mThumbdId[i]);
        imageview.setImageURI(Uri.parse(ImageArrayForUrl[i]));
        return imageview;
    }

    public int[] TransformArray(ArrayList<String> ArStr) {
        int i=0;
        int[] ArrayImagesInt = new int[ArStr.toArray().length];
        for (String Image : ArStr) {
            ArrayImagesInt[i++] = Image.hashCode();
        }
        return ArrayImagesInt;
    }
//    public String[] ImageArrayForUrl = { "storage/8B6D-99CF/DCIM/Camera/IMG_20190625_140057.jpg",
//                                         "storage/8B6D-99CF/DCIM/Camera/IMG_20190625_140134.jpg"};
//    public int[] mThumbdId; //{R.drawable.image1, R.drawable.image2};

}
