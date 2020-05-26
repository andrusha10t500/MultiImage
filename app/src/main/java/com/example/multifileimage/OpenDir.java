package com.example.multifileimage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.lang.reflect.Array;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;


public class OpenDir extends Activity {
    TextView txt;
    ListView list;
    ArrayAdapter<String> ArAd;
    String back;
    URL[] url;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opendir);
        txt = (TextView)findViewById(R.id.textPath);
        list = (ListView)findViewById(R.id.list_dir);
        String[] text = getIntent().getStringArrayExtra("txt");
//        ArrayList<String> text = getIntent().getStringArrayListExtra("txt");
        ArAd = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,text);
        list.setAdapter(ArAd);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                 Toast.makeText(getApplicationContext(),adapterView.getItemAtPosition(i).toString() +
//                        txt.getText(), Toast.LENGTH_SHORT).show();
                if(txt.getText().toString().indexOf("storage") != -1) {
                    DirectoriesPath(adapterView.getItemAtPosition(i).toString(),0);
                } else {
                    DirectoriesPath("/storage/" + adapterView.getItemAtPosition(i).toString(),0);
                }
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
            f = new File(InSet).listFiles();
        }
        StringList.clear();
        for (File f1 : f) {
                StringList.add(f1.getName().toString());
        }

        ArAd = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,StringList);
        list.setAdapter(ArAd);
    }

    public void onClickBack(View view) {
        back = txt.getText().toString().substring(0,txt.getText().toString().lastIndexOf("/"));
        Toast.makeText(getApplicationContext(),back,Toast.LENGTH_SHORT);
        DirectoriesPath(back,1);
    }

    public void onClickGo(View view) {
        ClickGo();

//        MultiTif mt  = new MultiTif();
//        MultiTif.TiffMultipage(new File(txt.getText().toString()));
    }
    public void ClickGo() {
        Toast.makeText(getApplicationContext(),txt.getText().toString(),Toast.LENGTH_SHORT).show();
        File[] images = new File(txt.getText().toString()).listFiles();
        String[] ArrayImages = new String[images.length];
        int i=0;
//        Intent intent = new Intent(getApplicationContext(),ImageAdapter.class);
        for (File image : images) {
            if(image.getName().substring(image.getName().length()-3,image.getName().length()).equals("jpg")){
//                ArrayImages[i++].add(image.getName().toString());
                ArrayImages[i++] = txt.getText().toString() + "/" + image.getName().toString();
            }
        }
//        GridView grid = (GridView)findViewById(R.id.GridView1);
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("ArrayString",ArrayImages);
        startActivity(intent);
//        MainActivity ma = new MainActivity();
//        GridView grid =(GridView)findViewById(R.id.GridView1);
//        ImageAdapter im = new ImageAdapter(this,ArrayImages);
////        im.ImageArrayForUrl = ArrayImages;
//        ImageAdapter ia = new ImageAdapter(this.getBaseContext(), ArrayImages);
//        grid.setAdapter(ia);

//        setContentView(R.layout.activity_main);
    }

}