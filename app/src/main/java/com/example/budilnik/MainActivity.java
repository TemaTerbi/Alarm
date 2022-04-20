package com.example.budilnik;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TextView txtDateTime;
    Button btnTime, btnDate;
    Calendar dateTime = Calendar.getInstance();
    Calendar NowTime = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtDateTime = findViewById(R.id.editSeconds);
        btnTime = findViewById(R.id.btn);
      /*  btnTime.setOnClickListener(view ->{
            int seconds = Integer.parseInt(txtDateTime.getText().toString());
            Intent intent = new Intent(MainActivity.this,Alarm.class);
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis() + seconds * 1000L, PendingIntent.getBroadcast(getApplicationContext(),0,intent,0));





        });
        */

       // txtDateTime.setText(DateUtils.formatDateTime(this,dateTime.getTimeInMillis(),DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR | DateUtils.FORMAT_SHOW_TIME));
       txtDateTime.setText( "Сейчашнее время: " +DateUtils.formatDateTime(this,dateTime.getTimeInMillis(),DateUtils.FORMAT_SHOW_TIME));
        TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int Minutes) {
                dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                dateTime.set(Calendar.MINUTE, Minutes);
                long Interval = (dateTime.getTimeInMillis() - System.currentTimeMillis()) / 1000;
                txtDateTime.setText("Выбранное время будильника: "+DateUtils.formatDateTime(getApplicationContext(),dateTime.getTimeInMillis(),
                         DateUtils.FORMAT_SHOW_TIME) + " Будильник прозвонит через " + Interval + " секунд");

                Intent intent = new Intent(MainActivity.this,Alarm.class);
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis() + Interval * 1000l, PendingIntent.getBroadcast(getApplicationContext(),0,intent,0));

            }
        };
        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(MainActivity.this,t,dateTime.get(Calendar.HOUR_OF_DAY),dateTime.get(Calendar.MINUTE),true).show();
            }
        });


    }
}