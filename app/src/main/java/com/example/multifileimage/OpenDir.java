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

import java.io.File;
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
//                txt.setText(adapterView.getItemAtPosition(i).toString());
                DirectoriesPath(adapterView.getItemAtPosition(i).toString(),0);
//                Toast.makeText(getApplicationContext(),"Выбрано " + adapterView.getItemAtPosition(i).toString() ,Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void DirectoriesPath(String InSet, int But) {
//      But=1 - признак кнопки
        File[] f;
        ArrayList<String> StringList = new ArrayList<String>();
        list = (ListView)findViewById(R.id.list_dir);
        if (But==1) {
            txt.setText(InSet);
        } else {
            txt.setText(txt.getText().toString() + "/" + InSet);
        }
        f = new File(InSet).listFiles();

        if (f == null)
        {
            f = new File(txt.getText().toString()).listFiles();
        } else {
            f = new File(  InSet).listFiles();
        }
        StringList.clear();
        for (File f1 : f) {
                StringList.add(f1.getName().toString());
        }

        ArAd = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,StringList);
        list.setAdapter(ArAd);
    }

    public void onClickBack(View view) {
        String str1 = txt.getText().toString().substring(0,txt.getText().toString().lastIndexOf("/")),
                back = txt.getText().toString().substring(0,txt.getText().toString().lastIndexOf("/"));
        Toast.makeText(getApplicationContext(),back,Toast.LENGTH_SHORT);
//        txt.setText(back);
        DirectoriesPath(back,1);
    }
}