package com.example.jmw0705.fullscreennotification.helps;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by JMW0705 on 8/24/2016.
 */
public class CustomBroadCastReceiver extends BroadcastReceiver {
    final String TAG = CustomBroadCastReceiver.class.getName();
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent newIntent = new Intent(context,CustomIntentService.class);
        Log.d(TAG, "onReceive: HELLO!!!!!!!!");
        context.startService(newIntent);
    }
}
