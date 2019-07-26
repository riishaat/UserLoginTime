package com.rishat.loginsql;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telephony.SignalStrength;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TextView name1,time1;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        String time ="Login Time: " +format.format(calendar.getTime());
        TextView textView = findViewById(R.id.time);
        textView.setText(time);


    }
}
