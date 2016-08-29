package com.example.jmw0705.fullscreennotification;


import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;


public class AlertMessage extends AppCompatActivity {
    Vibrator vibrator;
    MediaPlayer mMediaPlayer;
    AudioManager audioManager;
    int currentVolume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMediaPlayer = new MediaPlayer();
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        mMediaPlayer = MediaPlayer.create(this,soundUri);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        audioManager = (AudioManager)getSystemService(AUDIO_SERVICE);
        currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        Log.d("Volume", currentVolume+"");
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC),AudioManager.AUDIOFOCUS_GAIN);

        if(audioManager.getMode()!=AudioManager.MODE_IN_CALL){
            //mMediaPlayer.start();
        }
        vibrator.vibrate(999999999);


        setContentView(R.layout.activity_alert_message);
    }

    @Override
    protected void onPause(){
        super.onPause();
    }
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        mMediaPlayer.stop();
        mMediaPlayer.release();

        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,currentVolume,AudioManager.AUDIOFOCUS_LOSS);
        Log.d("Volume Exit", audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)+"");
    }

}
