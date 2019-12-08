package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sendid = (Button) findViewById(R.id.sendid);


        sendid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText id =(EditText) findViewById(R.id.inputid);
                String id_ =id.getText().toString();
                SharedPreferences saveid = getSharedPreferences("saveid",MODE_PRIVATE);
                saveid.edit()
                        .putString("ID",id_)
                        .commit();


                Intent intent =new Intent(MainActivity.this,MainPage.class);
                startActivity(intent);
            }
        });
    }
}
