package com.example.jmw0705.fullscreennotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import com.example.jmw0705.fullscreennotification.helps.CustomIntentService;

public class MainActivity extends AppCompatActivity {
    final String TAG = "BOOOO";
    NotificationCompat.Builder builder;
    NotificationManager notificationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Button not1 = (Button) findViewById(R.id.notification1);
        Button not2 = (Button) findViewById(R.id.notification2);
        Button not3 = (Button) findViewById(R.id.notification3);
        Button not4 = (Button) findViewById(R.id.notification4);

        not1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent i = new Intent("com.example.jmw0705.fullscreennotification.USER_ACTION");
                //sendBroadcast(i);
                sendNotification();
            }
        });


    }
    public void sendNotification(){


        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l) {
                Log.d("TIMER","TIMER WORKING");
            }

            @Override
            public void onFinish() {
                Intent newIntent = new Intent(getBaseContext(),AlertMessage.class);
                startActivity(newIntent);
            }
        }.start();


    }
}
