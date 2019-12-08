package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        String getid = getSharedPreferences("saveid",MODE_PRIVATE)
                .getString("ID","");
        TextView tv=(TextView)findViewById(R.id.tv);
        tv.setText(getid);
    }

    public void click(View view) {
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.pay:
                intent.setClass(this,pay.class);
                startActivity(intent);
                break;
            case R.id.get:
                intent.setClass(this,get.class);
                startActivity(intent);
                break;
            case R.id.show:
                intent.setClass(this,show.class);
                startActivity(intent);
                break;
        }
    }
}
