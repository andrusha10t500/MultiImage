package com.example.multifileimage;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

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
//        ImageView imageview;
        View mView;
        if(view == null) {
//            --------------------------------------------
            view = new View(mContext);
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = inflater.inflate(R.layout.cellgrid,viewGroup,false);

//            --------------------------------------------
//            imageview = new ImageView(mContext);
//
//            imageview.setLayoutParams(new GridView.LayoutParams(100,100));
//            imageview.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageview.setPadding(8,8,8,8);
//            --------------------------------------------
        }
        else {
//            imageview = (ImageView)view;
            mView = (View)view;
        }
        ImageView imageView = (ImageView)mView.findViewById(R.id.imagepart);

        imageView.setImageURI(Uri.parse(ImageArrayForUrl[i]));
        mContext.getCacheDir().delete();

        return mView;
//        --------------------------------------------
//        imageview.setImageResource(mThumbdId[i]);
//        imageview.setImageURI(Uri.parse(ImageArrayForUrl[i]));
//        return imageview;
    }

//    public String[] ImageArrayForUrl = { "storage/8B6D-99CF/DCIM/Camera/IMG_20190625_140057.jpg",
//                                         "storage/8B6D-99CF/DCIM/Camera/IMG_20190625_140134.jpg"};
//    public int[] mThumbdId; //{R.drawable.image1, R.drawable.image2};

}
