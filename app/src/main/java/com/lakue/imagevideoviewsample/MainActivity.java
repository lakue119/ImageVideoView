package com.lakue.imagevideoviewsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.lakue.imagevideo.ImageVideoView;

public class MainActivity extends AppCompatActivity {

    ImageVideoView imageVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageVideoView = findViewById(R.id.imageVideoView);

        imageVideoView.setUrl("http://www.ithinknext.com/mydata/board/files/F201308021823010.mp4");
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(imageVideoView != null){
            imageVideoView.releasePlayer();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(imageVideoView != null){
            imageVideoView.releasePlayer();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(imageVideoView != null){
            imageVideoView.releasePlayer();
        }
    }
}
