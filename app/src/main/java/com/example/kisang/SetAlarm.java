package com.example.kisang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TimePicker;

public class SetAlarm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_alarm);
    }

    public void onSaveBtnClicked(View v) {
        //시간 정보들을 저장한다.
        TimePicker timePicker = (TimePicker)findViewById(R.id.timePicker); // initiate a time
        timePicker.setIs24HourView(false);
        int hour = timePicker.getCurrentHour();
        int minute = timePicker.getCurrentMinute();
        Log.v("SetAlarm", hour+" : "+minute);

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("hour", hour);
        intent.putExtra("minute", minute);

        setResult(RESULT_OK, intent);
        finish();
        //activity_main.xml에 알람시간과 반복주기, switch on/off panel하나를 띄운다.
    }

    public void onCancelBtnClicked(View v) {
        finish();
    }

}
