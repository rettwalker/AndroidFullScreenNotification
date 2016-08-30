package com.example.jmw0705.fullscreennotification;


import android.app.Dialog;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.view.Window;
import android.widget.Button;


public class AlertMessage extends AppCompatActivity {
    Vibrator vibrator;
    MediaPlayer mMediaPlayer;
    AudioManager audioManager;
    int currentVolume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideSystemUI();
        Intent intent = getIntent();
        prepareActivityForAlert();
        long[] pattern = {0, 100, 100};

        if(!intent.getExtras().getBoolean("FULLSCREEN")){
            final Dialog dialog = new Dialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setContentView(R.layout.blank_full_screen);
            Button dialogButton = (Button) dialog.findViewById(R.id.button);
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    vibrator.cancel();
                    mMediaPlayer.stop();
                    mMediaPlayer.release();
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,currentVolume,AudioManager.AUDIOFOCUS_LOSS);
                    Log.d("Volume Exit", audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)+"");
                    dialog.dismiss();
                }
            });

            if(audioManager.getMode()!=AudioManager.MODE_IN_CALL){
                //mMediaPlayer.start();
            }

            if(intent.getExtras().getBoolean("CONSTVIB")){
                vibrator.vibrate(999999999);
            } else {
                vibrator.vibrate(pattern,0);
            }
            dialog.show();

            setContentView(R.layout.transparent);
        } else{

            if(audioManager.getMode()!=AudioManager.MODE_IN_CALL){
                //mMediaPlayer.start();
            }

            if(intent.getExtras().getBoolean("CONSTVIB")){
                vibrator.vibrate(999999999);
            } else {
                vibrator.vibrate(pattern,0);
            }
            
            setContentView(R.layout.full_screen_alert);
            Button fullscreenApproval = (Button) findViewById(R.id.fullscreen_button);
            fullscreenApproval.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    vibrator.cancel();
                    mMediaPlayer.stop();
                    mMediaPlayer.release();
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,currentVolume,AudioManager.AUDIOFOCUS_LOSS);
                    Log.d("Volume Exit", audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)+"");
                }
            });
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideSystemUI();
    }

    // This snippet hides the system bars.
    private void hideSystemUI() {
        //getWindow().addFlags(flags);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }




    public void prepareActivityForAlert(){
        mMediaPlayer = new MediaPlayer();
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        mMediaPlayer = MediaPlayer.create(this,soundUri);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        audioManager = (AudioManager)getSystemService(AUDIO_SERVICE);
        currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        Log.d("Volume", currentVolume+"");
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC),AudioManager.AUDIOFOCUS_GAIN);


    }

    @Override
    protected void onPause(){
        super.onPause();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
