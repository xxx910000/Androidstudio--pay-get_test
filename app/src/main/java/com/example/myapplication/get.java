package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class get extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get);
        getCode();
    }

    public void getCode(){
        ImageView ivCode=(ImageView)findViewById(R.id.imageView);
        BarcodeEncoder encoder = new BarcodeEncoder();
        try{
            Bitmap bit = encoder.encodeBitmap(id.getText()
                    .toString(), BarcodeFormat.QR_CODE,250,250);
            ivCode.setImageBitmap(bit);
        }catch (WriterException e){
            e.printStackTrace();
        }
    }
}
