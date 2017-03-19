package com.app.codytutorials.fragment;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.codytutorials.R;

import java.util.concurrent.TimeUnit;


public class AudioPlayerFragment extends AbstractTabFragment   {
    private static final int LAYOUT = R.layout.audio_player;


    private ImageView btn_forward, btn_pause, btn_play, btn_backward;
    private MediaPlayer mediaPlayer;


    private double startTime = 0;
    private double finalTime = 0;

    boolean flagPlay = false;

    private Handler myHandler = new Handler();
    private int forwardTime = 5000;
    private int backwardTime = 5000;
    private SeekBar songProgressBar;
    private TextView songCurrentDurationLabel, songTotalDurationLabel, songTitleLabel;

    public static int oneTimeOnly = 0;

    public static AudioPlayerFragment getInstance(Context context) {
        Bundle args = new Bundle();
        AudioPlayerFragment fragment = new AudioPlayerFragment();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle("Player");

        return fragment;
    }// WorldFragment



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);

        btn_forward = (ImageButton) view.findViewById(R.id.btn_forward);
        btn_pause = (ImageButton) view.findViewById(R.id.btn_pause_disable);
        btn_play = (ImageButton) view.findViewById(R.id.btn_play_disabl);
         btn_backward = (ImageButton) view.findViewById(R.id.btn_backward);

        songCurrentDurationLabel = (TextView) view.findViewById(R.id.songCurrentDurationLabel);
        songTotalDurationLabel = (TextView) view.findViewById(R.id.songTotalDurationLabel);
        songTitleLabel = (TextView) view.findViewById(R.id.songTitleLabel);
        songTitleLabel.setText("Song.mp3");

        mediaPlayer = MediaPlayer.create(getContext(), R.raw.b);
        songProgressBar = (SeekBar) view.findViewById(R.id.seekBar);
        songProgressBar.setClickable(true);
       // songProgressBar.setEnabled(false);
        btn_pause.setEnabled(false);

        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();

                finalTime = mediaPlayer.getDuration();
                startTime = mediaPlayer.getCurrentPosition();

                if (oneTimeOnly == 0) {
                    songProgressBar.setMax((int) finalTime);
                    oneTimeOnly = 1;
                }

                songTotalDurationLabel.setText(String.format("%d min, %d sec",
                        TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                        TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                        finalTime)))
                );

                songCurrentDurationLabel.setText(String.format("%d min, %d sec",
                        TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                        TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                        startTime)))
                );
                // проверка на нажатие кнопки pause, если уже нажата, ничего не меняем.
                if(flagPlay){
                    btn_pause.setImageResource(R.drawable.btn_pause_disable);
                }
                btn_play.setImageResource(R.drawable.btn_play_enable);
                songProgressBar.setProgress((int) startTime);
                myHandler.postDelayed(UpdateSongTime, 100);
                btn_pause.setEnabled(true);
                btn_play.setEnabled(false);
            }
        });

        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // check for already playing
                if(mediaPlayer.isPlaying()){
                    if(mediaPlayer!=null){
                        mediaPlayer.pause();
                        // Меняем изображение кнопок при нажатии
                        btn_pause.setImageResource(R.drawable.btn_pause_enable);
                        btn_play.setImageResource(R.drawable.btn_play_disabl);
                    }
                }
                btn_pause.setEnabled(false);
                btn_play.setEnabled(true);
                flagPlay = true;
            }
        });

        btn_forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = (int) startTime;

                if ((temp + forwardTime) <= finalTime) {
                    startTime = startTime + forwardTime;
                    mediaPlayer.seekTo((int) startTime);
                    Toast.makeText(getContext(), "You have Jumped forward 5 seconds", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Cannot jump forward 5 seconds", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = (int) startTime;

                if ((temp - backwardTime) > 0) {
                    startTime = startTime - backwardTime;
                    mediaPlayer.seekTo((int) startTime);
                    Toast.makeText(getContext(), "You have Jumped backward 5 seconds", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Cannot jump backward 5 seconds", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;

    }// onCreateView

    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            songCurrentDurationLabel.setText(String.format("%d min, %d sec",
                    TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                    toMinutes((long) startTime)))
            );
            songProgressBar.setProgress((int)startTime);
            myHandler.postDelayed(this, 100);

        }
    };


    public void setContext(Context context) {
        this.context = context;
    }
//    /**
//     * Вызывается метод после завершения композиции.
//     */
//    @Override
//    public void onCompletion(MediaPlayer mp) {
//
//    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mediaPlayer.release();
    }
}