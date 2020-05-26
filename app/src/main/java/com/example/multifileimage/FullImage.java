package com.example.multifileimage;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FullImage extends AppCompatActivity {
    private ImageSwitcher mImageSwitcher;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fullimageview);
        Intent intent = getIntent();
        mImageSwitcher = (ImageSwitcher)findViewById(R.id.imageSwitcher);
//        String position = intent.getExtras().getInt("id");
        String position = intent.getStringExtra("id");
//        ImageAdapter imageAdapter = new ImageAdapter(this,null);
        ImageView imageView = (ImageView)findViewById(R.id.FullImage);

//        imageView.setImageResource(imageAdapter.mThumbdId[position]);
        imageView.setImageURI(Uri.parse(position));

    }

    public void SwitchImage(View view) {                
        mImageSwitcher.showNext();
    }
}
