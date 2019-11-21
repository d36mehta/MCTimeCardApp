package com.example.android.timecard2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Tab6Fragment extends Fragment {
    private static final String TAG = "Tab1Fragment";

    TextView textView;
    Button start, pause, reset;
    long milliTime, startTime, timeBuff, updateTime = 0L;
    Handler handler;
    int hour, sec, min, milli;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab6, container, false);

        textView = (TextView) view.findViewById(R.id.textView6);
        start = (Button) view.findViewById(R.id.btnStart6);
        pause = (Button) view.findViewById(R.id.btnPause6);
        reset = (Button) view.findViewById(R.id.btnReset6);

        handler = new Handler();


        //Start Button
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startTime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable, 0);

                reset.setEnabled(false);
            }
        });

        //Pause Button
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeBuff += milliTime;
                handler.removeCallbacks(runnable);

                Date date = Calendar.getInstance().getTime();

                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                String fDate = df.format(date);

                String time = textView.getText().toString();
                sendEmail(time,fDate);

                reset.setEnabled(true);
            }
        });

        //Reset Button
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                milliTime = 0L;
                startTime = 0L;
                timeBuff = 0L;
                updateTime = 0L;
                sec = 0;
                min = 0;
                milli = 0;

                textView.setText("00:00:00");
            }
        });

        return view;
    }

    //email
    protected void sendEmail(String time, String fDate) {
        Log.i("Send email", "");

        String[] TO = {"mctimecard1883@gmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");

        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Amber's Time");
        emailIntent.putExtra(Intent.EXTRA_TEXT, time + " on " + fDate);

        if (emailIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(Intent.createChooser(emailIntent,"Send"));
        }
    }

    //runnable

    public Runnable runnable = new Runnable() {

        public void run() {

            milliTime = SystemClock.uptimeMillis() - startTime;
            updateTime = timeBuff+milliTime;
            sec = (int)(updateTime/1000);
            min = sec/60;
            hour = min/60;
            min = min%60;
            sec = sec%60;
            milli = (int)(updateTime%1000);

            textView.setText (String.format("%02d", hour) +":"
                    + String.format("%02d", min) + ":"
                    + String.format ("%02d", sec));

            handler.postDelayed(this,0);

        }
    };
}
