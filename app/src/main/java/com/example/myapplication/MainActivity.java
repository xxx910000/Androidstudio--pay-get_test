package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
        final EditText id =(EditText) findViewById(R.id.inputid);


        sendid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myid = database.getReference("ID");

                myid.setValue(id.getText().toString());

                Intent intent =new Intent(MainActivity.this,MainPage.class);
                startActivity(intent);
            }
        });
    }
}
