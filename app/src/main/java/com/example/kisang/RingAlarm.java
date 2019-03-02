package com.example.kisang;


import android.app.AlarmManager;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;
import java.util.Calendar;



public class RingAlarm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ring_alarm);

        //시간설정
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.set(Calendar.HOUR_OF_DAY,22);
        mCalendar.set(Calendar.MINUTE,48);
        mCalendar.set(Calendar.SECOND,8);

        Intent mAlarmintent = new Intent("com.example.kisang.ALARM_START");

        int requestcode = 0;
        PendingIntent mPendingIntent =
                PendingIntent.getBroadcast(
                        this,
                        requestcode,
                        mAlarmintent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        AlarmManager mAlarmManager =(AlarmManager) getSystemService(Context.ALARM_SERVICE);
        mAlarmManager.set(
                AlarmManager.RTC_WAKEUP,
                mCalendar.getTimeInMillis(),
                mPendingIntent
        );

        //중단버튼 클릭시 홈화면으로 이동
        Button button = (Button) findViewById(R.id.button6);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }
        );
    }
}

//알람 받아오기
class AlarmReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent){

        Intent mServiceintent = new Intent(context, AlarmSoundService.class);
        context.startService(mServiceintent);
    }
}
