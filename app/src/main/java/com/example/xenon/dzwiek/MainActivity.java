package com.example.xenon.dzwiek;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Button b1;
    private Button b2;
    private Button b3;
    private MediaPlayer mediaPlayer;
    private MediaRecorder myAudioRecorder;
    private MediaPlayer m;
    private static String mFileName;
    private boolean isplayed=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button)findViewById(R.id.button);
        b2 = (Button)findViewById(R.id.button2);
        b3 = (Button)findViewById(R.id.button3);
        mediaPlayer=new MediaPlayer();
        myAudioRecorder=new MediaRecorder();
        m = new MediaPlayer();
        mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        mFileName += "/audiorecordtest.3gp";
        myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        myAudioRecorder.setOutputFile(mFileName);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    myAudioRecorder.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                myAudioRecorder.start();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudioRecorder.stop();
                myAudioRecorder.release();
                myAudioRecorder = null;
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!isplayed) {
                    try {
                        m.setDataSource(mFileName);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        m.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    isplayed=true;
                    b3.setText("Stop Playing");
                    m.start();
                }else{
                    m.stop();
                    m.release();
                    m = null;
                    isplayed=false;
                    b3.setText("Start Playing");
                }

            }
        });
    }
}

