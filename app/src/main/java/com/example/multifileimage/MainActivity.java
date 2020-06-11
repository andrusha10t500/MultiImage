package com.example.multifileimage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ImageDecoder;
import android.graphics.ImageFormat;
import android.graphics.pdf.PdfDocument;
import android.graphics.pdf.PdfRenderer;
import android.media.ImageReader;
import android.media.ImageWriter;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.lowagie.text.BadElementException;
import com.lowagie.text.DocWriter;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.ElementListener;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfDocument.PdfInfo;
import com.lowagie.text.pdf.PdfPage;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.codec.Base64;
import com.lowagie.text.pdf.codec.PngImage;
import com.lowagie.text.pdf.codec.TiffImage;
import com.sun.pdfview.PDFPage;
import com.sun.pdfview.PDFRenderer;

import org.arakhne.afc.inputoutput.filefilter.MultiFileFilter;
import org.arakhne.afc.inputoutput.filefilter.PNGFileFilter;
import org.jfree.ui.Layer;
import org.jfree.util.Rotation;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URI;
import java.util.ArrayList;

//import com.itextpdf.text.Image;
//import com.itextpdf.text.pdf.codec.PngImage;
//import com.itextpdf.text.pdf.codec.PngWriter;

public class MainActivity extends AppCompatActivity {
//    ArrayList<String> ArStr = new ArrayList<String>();

    String [] ArStr;
    GridView gridview;
//    Gallery gridview;
    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        if (data == null) {
            return;
        }
        String url = data.getStringExtra("url");
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] GetArray =  getIntent().getStringArrayExtra("ArrayString");
//        gridview = (Gallery) findViewById(R.id.GridView1);
        gridview = (GridView) findViewById(R.id.GridView1);

        if (GetArray == null) {
            gridview.setAdapter(new ImageAdapter(this, ListDirectory()));
        } else {
            gridview.setAdapter(new ImageAdapter(this, GetArray));
        }

        gridview.setOnItemClickListener(gridviewSetOnClickListener);
        getApplicationContext().getCacheDir().delete();
    }

    private Gallery.OnItemClickListener gridviewSetOnClickListener = new GridView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(getApplicationContext(),
                FullImage.class);

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
            if (f1.isDirectory())
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
        getApplicationContext().getCacheDir().delete();
        finish();
    }

    public void Refresh(View view) throws IOException, DocumentException {
        String result=gridview.getAdapter().getItem(1).toString().substring(0,gridview.getAdapter().getItem(1).toString().lastIndexOf("/"));
        Toast.makeText(getApplicationContext(),result,
                Toast.LENGTH_SHORT).show();

        getApplicationContext().getCacheDir().delete();
        finish();
//        MultyImage.TiffMultipage(new File(result));
//        MultiTif.TiffMultipage(new File(result));
// -----------------------------------Начало пиздеца-----------------------------------
        File dir = new File(result);
        File[] dirAr = dir.listFiles();
        File out = new File(Environment.getExternalStorageDirectory() + "/" + dir.getName() + ".pdf");
        //Выходной поток fos
        FileOutputStream fos = new FileOutputStream(out);

        //Создаем документ
        Document doc = new Document();
        //узнаем размеры стандартные
        Rectangle pageSize = doc.getPageSize();
        float width = pageSize.getWidth();
        float height = pageSize.getHeight();
        //Создаём массив картинок
        Bitmap[] images = new Bitmap[dirAr.length];
        //Проходим по всем картинкам, записываем в поток

        //Создаём Writer
        PdfWriter.getInstance(doc,fos);
        doc.open();
        for(int i =0; i<=dirAr.length-1; i++) {
            //Создаём промежуточный поток
//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            try {
                Image image = Image.getInstance(dirAr[i].getAbsolutePath());
                //Для ориентации книжная|альбомная
                if (image.getHeight() < image.getWidth()) {
                    doc.setPageSize(PageSize.A4.rotate());
                    image.scaleAbsolute(height, width);
                } else {
                    doc.setPageSize(PageSize.A4);
                    image.scaleAbsolute(width,height);
                }
                //Обязательно создать новую страницу
                doc.newPage();
                doc.add(image);

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),"Error: " + e.toString(),Toast.LENGTH_LONG).show();
            }
        }
        doc.close();
// -----------------------------------Конец пиздеца------------------------------------
    }

}