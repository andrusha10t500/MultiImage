package com.example.multifileimage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridview = (GridView)findViewById(R.id.GridView1);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(gridviewSetOnClickListener);
    }

    private GridView.OnItemClickListener gridviewSetOnClickListener = new GridView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(getApplicationContext(),
                    FullImage.class);
            intent.putExtra("id",i);
            22startActivity(intent);
        }
    };
}


