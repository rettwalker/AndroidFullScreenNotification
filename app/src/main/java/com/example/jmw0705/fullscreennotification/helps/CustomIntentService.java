package com.example.jmw0705.fullscreennotification.helps;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.jmw0705.fullscreennotification.AlertMessage;
import com.example.jmw0705.fullscreennotification.R;

/**
 * Created by JMW0705 on 8/24/2016.
 */
public class CustomIntentService extends IntentService {
    final String TAG = CustomIntentService.class.toString();

    protected CustomIntentService() {
        super("CustomIntentService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        RemoteViews remoteView = new RemoteViews("com.example.jmw0705.fullscreennotification",R.layout.full_screen_notificiation);
        //remoteView.setTextViewText(R.id.textView3,"TexT");
        Log.d(TAG,"GOT PASSED TO THE CORRECT INTENT");
        Intent newIntent = new Intent(this,AlertMessage.class);
        PendingIntent pendingIntent = PendingIntent.getService(this,0,newIntent,0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.icon)
                .setOngoing(true)
                .setCategory(Notification.CATEGORY_ALARM)
                .setFullScreenIntent(pendingIntent,true)
                .setContent(remoteView)
                .setContentIntent(pendingIntent)
                .setPriority(Notification.PRIORITY_MAX)
                .setVibrate(new long[] {500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500})
                .setCustomHeadsUpContentView(remoteView);


        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        int temp = 001;
        notificationManager.notify(temp,builder.build());
    }
}
