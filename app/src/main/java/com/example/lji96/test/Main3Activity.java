package com.example.lji96.test;

import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(MainActivity.this)
                .setsmall
    }
}
