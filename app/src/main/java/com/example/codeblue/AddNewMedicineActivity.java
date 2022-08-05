package com.example.codeblue;


import androidx.appcompat.app.AppCompatActivity;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

import static java.lang.Math.abs;


public class AddNewMedicineActivity extends AppCompatActivity {

    int sethour, setminute;
    private Button settime, addbutton;
    private AutoCompleteTextView medicinetypes, dosage;
    String usersettime;
    MaterialTimePicker timePicker;
    MaterialTimePicker.Builder timebuilder;
    long usersettimeinmilliseconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_medicine);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel("Medicine Notification Channel", "Medicine Notification Channel", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }


        String[] typesofmedicine = new String[]{"Tablet", "Syrup", "Injection", "Capsule", "Drop", "Cream"};
        String[] dosages = new String[]{"Grams(g)", "Milligrams(mg)", "Millilitre(ml)"};
        ArrayAdapter<String> typesofmedicineadapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_menu, typesofmedicine);
        ArrayAdapter<String> dosageadapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_menu, dosages);
        medicinetypes = (AutoCompleteTextView) findViewById(R.id.typeofmedicineTextView);
        dosage = (AutoCompleteTextView) findViewById(R.id.dosageTextView);
        settime = (Button) findViewById(R.id.settimebtn);
        addbutton = (Button) findViewById(R.id.addbtn);
        medicinetypes.setAdapter(typesofmedicineadapter);
        dosage.setAdapter(dosageadapter);
        timePicker = new MaterialTimePicker();
        timebuilder = new MaterialTimePicker.Builder();

        settime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                timePicker = timebuilder.setTimeFormat(TimeFormat.CLOCK_12H).setHour(hour).setMinute(minute).build();
                timePicker.show(getSupportFragmentManager(), "TIME_PICKER");

                timePicker.addOnPositiveButtonClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sethour = timePicker.getHour();
                        setminute = timePicker.getMinute();
                        String amorpm;
                        if(sethour == 0)
                            amorpm = "AM";
                        else if(sethour == 12)
                        {
                            amorpm = "PM";
                        }
                        else if (sethour > 12 ) {
                            sethour = sethour % 12;
                            amorpm = "PM";
                        } else
                            amorpm = "AM";
                        usersettime = String.format("%02d:%02d", sethour, setminute) + " " + amorpm;
                        settime.setText(usersettime);
                    }
                });
            }
        });

        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddNewMedicineActivity.this, NotificationBroadcastReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(AddNewMedicineActivity.this, 0, intent, 0);
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+usersettimeinmilliseconds, pendingIntent);
            }
        });

    }
    }


