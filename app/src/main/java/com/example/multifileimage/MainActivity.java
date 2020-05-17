package com.example.multifileimage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//    ArrayList<String> ArStr = new ArrayList<String>();
    String [] ArStr;
    GridView gridview;
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
        String[] GetArray =  getIntent().getStringArrayExtra("ArrayString");
        gridview = (GridView) findViewById(R.id.GridView1);
        if (GetArray == null) {
            gridview.setAdapter(new ImageAdapter(this, ListDirectory()));
        } else {
            gridview.setAdapter(new ImageAdapter(this, GetArray));
        }

        gridview.setOnItemClickListener(gridviewSetOnClickListener);
    }

    private GridView.OnItemClickListener gridviewSetOnClickListener = new GridView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(getApplicationContext(),
                    FullImage.class);

//            intent.putExtra("id",i);
            Toast.makeText(getApplicationContext(),adapterView.getItemAtPosition(i).toString(),Toast.LENGTH_SHORT).show();
            intent.putExtra("id",adapterView.getItemAtPosition(i).toString());
            startActivity(intent);
        }
    };

    public String[] ListDirectory() {
        File[] f = new File("/storage/").listFiles();
        String[] ArStr = new String[f.length];
//        ArStr.clear();
        int i=0;
        for (File f1 : f) {
            ArStr[i++] = f1.getName();
//             ArStr.add(f1.toString());
        }
        return ArStr;
    }

    public void OpenDirectory(View view) {
        Intent intent  = new Intent(this, OpenDir.class);
        if(ListDirectory() != null) {
//            intent.putStringArrayListExtra("txt",ListDirectory());
            intent.putExtra("txt",ListDirectory());
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(),"no",Toast.LENGTH_LONG).show();
        }
    }
}