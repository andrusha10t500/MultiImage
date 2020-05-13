package com.example.multifileimage;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class OpenDir extends Activity {
    TextView txt;
    ListView list;
    ArrayAdapter<String> ArAd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opendir);
        txt = (TextView)findViewById(R.id.textPath);
        list = (ListView)findViewById(R.id.list_dir);
        ArrayList<String> text = getIntent().getStringArrayListExtra("txt");
        ArAd = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,text);
        list.setAdapter(ArAd);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),"Выбрано " + adapterView.getItemAtPosition(i).toString() ,Toast.LENGTH_SHORT).show();
            }
        });
    }


}