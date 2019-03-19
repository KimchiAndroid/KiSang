package com.example.kisang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ArrayList<Time> alarmTimeList;
    ArrayList<String> alarmTimeStringList;
    ArrayAdapter<String> adapter;
    ListView listView;
    Intent addIntent;

    static final int REQ_ADD_ALARM = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alarmTimeList = new ArrayList<>();
        alarmTimeStringList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alarmTimeStringList);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

    public void onAddBtnClicked(View v) {
        addIntent = new Intent(getApplicationContext(), SetAlarm.class);
        startActivityForResult(addIntent, REQ_ADD_ALARM);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
//        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == REQ_ADD_ALARM) {
            if (resultCode == RESULT_OK) {
                addIntent = getIntent();
                int hour = addIntent.getIntExtra("hour", -1);
                int minute = addIntent.getIntExtra("minute", -1);

                Log.v("MainActivity", hour+" : "+minute);
                Time tmpTime = new Time(hour, minute);
                alarmTimeList.add(tmpTime);
                alarmTimeStringList.add(tmpTime.getHour()+" : "+tmpTime.getMinute());
                adapter.notifyDataSetChanged();
            }
        }
    }

//        List<String> list = new ArrayList<>();
//
//         = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1, list);
//
//        listview.setAdapter(adapter);
}


