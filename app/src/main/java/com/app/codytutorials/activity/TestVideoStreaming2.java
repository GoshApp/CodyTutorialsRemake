package com.app.codytutorials.activity;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.VideoView;

import com.app.codytutorials.R;

public class TestVideoStreaming2 extends AppCompatActivity implements View.OnClickListener {
    ProgressDialog mDialog;
    VideoView videoView;
    ImageButton btnPlayPause;

    String videoURL = "https://firebasestorage.googleapis.com/v0/b/cody-tutorials.appspot.com/o/_-_3_.mp4?alt=media&token=e0945893-5a60-49b5-a9ce-0c08dfb189bc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_video_streaming);

        videoView = (VideoView)findViewById(R.id.videoView);
        btnPlayPause = (ImageButton)findViewById(R.id.btn_play_pause);
        btnPlayPause.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mDialog = new ProgressDialog(TestVideoStreaming2.this);
        mDialog.setMessage("Пожалуйста, подождите...");
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();

        try {
            if(!videoView.isPlaying()) {
                Uri uri = Uri.parse(videoURL);
                videoView.setVideoURI(uri);
                videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        btnPlayPause.setImageResource(R.drawable.ic_play);

                    }

                });
            }
            else{
                videoView.pause();
                btnPlayPause.setImageResource(R.drawable.ic_play);
            }
        }
        catch (Exception ex){

        }
        videoView.requestFocus();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mDialog.dismiss();
                mp.setLooping(true);

                videoView.start();
                btnPlayPause.setImageResource(R.drawable.ic_pause);


            }
        });
    }
}
