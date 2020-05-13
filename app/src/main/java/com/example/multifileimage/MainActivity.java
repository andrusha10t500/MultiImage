package com.example.multifileimage;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> ArStr = new ArrayList<String>();
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (data == null) {return;}
        String url = data.getStringExtra("url");
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridview = (GridView) findViewById(R.id.GridView1);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(gridviewSetOnClickListener);
    }

    private GridView.OnItemClickListener gridviewSetOnClickListener = new GridView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(getApplicationContext(),
                    FullImage.class);
            intent.putExtra("id",i);
            startActivity(intent);
        }
    };

    public ArrayList ListDirectory() {
        File[] f = new File("/storage/").listFiles();
        ArStr.clear();
        for (File f1 : f) {
             ArStr.add(f1.toString());
        }
        return ArStr;
    }

    public void OpenDirectory(View view) {
        Intent intent  = new Intent(this, OpenDir.class);
        if(!ListDirectory().isEmpty()) {
            intent.putStringArrayListExtra("txt",ListDirectory());
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(),"no",Toast.LENGTH_LONG).show();
        }

    }
}