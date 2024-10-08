package com.example.videoplayer;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private VideoView videoView;
    private Button prevButton,playPauseButton,nextButton;
    private int currentIndex = 0;
    private int[] videoResIds = {
        R.raw.testing,
            R.raw.testing1,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.videoView);
        prevButton = findViewById(R.id.btn_prev);
        nextButton = findViewById(R.id.btn_next);
        playPauseButton = findViewById(R.id.btn_play);
        setupVideoPlayer();
        prevButton.setOnClickListener(v->{
            if(currentIndex > 0){
                currentIndex--;
                setupVideoPlayer();
            }
        });
        nextButton.setOnClickListener(v->{
            if(currentIndex < videoResIds.length-1){
                currentIndex++;
                setupVideoPlayer();
            }
        });
        playPauseButton.setOnClickListener(v->{
            if(videoView.isPlaying()){
                videoView.pause();
                playPauseButton.setText("Play");
            } else{
                videoView.start();
                playPauseButton.setText("Pause");
            }
        });
    }
    private void setupVideoPlayer(){
        Uri videoURI =
                Uri.parse("android.resource://"
                        +getPackageName()+"/"
                        +videoResIds[currentIndex]);
        videoView.setVideoURI(videoURI);
        videoView.setOnPreparedListener(mp ->{
            videoView.start();
            playPauseButton.setText("Pause");
        });
        videoView.setOnCompletionListener(mp-> playPauseButton.setText("Play"));
    }
}