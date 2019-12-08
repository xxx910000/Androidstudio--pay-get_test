package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;

public class pay extends AppCompatActivity {

    SurfaceView surfaceView;
    TextView textView;
    CameraSource cameraSource;
    BarcodeDetector barcodeDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        surfaceView=(SurfaceView)findViewById(R.id.sfv);
        textView=(TextView)findViewById(R.id.sid);


        Button paymoney=(Button)findViewById(R.id.paymoney);
        paymoney.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                int c = getSharedPreferences("count",MODE_PRIVATE)
                        .getInt("count",0);

                EditText money=(EditText)findViewById(R.id.money);
                String money_ = money.getText().toString();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference paymoney = database.getReference("paymoney");

                String getid = getSharedPreferences("saveid",MODE_PRIVATE)
                        .getString("ID","");

                String pay = new String(getid+"付給"+textView.getText().toString()+"  "+money_+"元");
                paymoney.child(String.valueOf(c)).setValue(pay);
                c++;
                SharedPreferences count = getSharedPreferences("count",MODE_PRIVATE);
                count.edit()
                        .putInt("count",c)
                        .commit();

                Intent intent = new Intent();
                intent.setClass(pay.this, MainPage.class);
                startActivity(intent);
            }
        });

        barcodeDetector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.QR_CODE).build();
        cameraSource=new CameraSource.Builder(this,barcodeDetector)
                .setRequestedPreviewSize(300,300).build();

        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback(){
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED)
                    return;
                try{
                    cameraSource.start(holder);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>(){

            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> qrCodes=detections.getDetectedItems();
                if(qrCodes.size()!=0){
                    textView.post(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(qrCodes.valueAt(0).displayValue);
                        }
                    });
                }
            }
        });
    }
}
