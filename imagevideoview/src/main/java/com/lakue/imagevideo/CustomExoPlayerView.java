package com.lakue.imagevideo;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;


public class CustomExoPlayerView extends PlayerView {

    SimpleExoPlayer player;
    DataSource.Factory mediaDataSourceFactory;
    DefaultTrackSelector trackSelector;
    TrackGroupArray lastSeenTrackGroupArray;
    AdaptiveTrackSelection.Factory videoTrackSelectionFactory;

    public CustomExoPlayerView(Context context) {
        super(context);
    }

    public CustomExoPlayerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomExoPlayerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void initializePlayer(String url) {
        if (trackSelector != null) {
            Log.i("DATA", "trankSelector : not null");
        } else {
            Log.i("DATA", "trankSelector : null");
        }

        trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);
        mediaDataSourceFactory = new DefaultDataSourceFactory(getContext(), Util.getUserAgent(getContext(), "mediaPlayerSample"));

        ExtractorMediaSource mediaSource = new ExtractorMediaSource.Factory(mediaDataSourceFactory)
                .createMediaSource(Uri.parse(url));

        player = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector);


        player.prepare(mediaSource, false, false);
        player.setPlayWhenReady(true);

        setShutterBackgroundColor(Color.TRANSPARENT);
        setPlayer(player);
        requestFocus();

        lastSeenTrackGroupArray = null;
    }

    //현재 동영상의 시간
    public int getCurrentPosition(){
        return (int) player.getCurrentPosition();
    }

    //동영상이 실행되고있는지 확인
    public boolean isPlaying(){
        return player.getPlayWhenReady();
    }

    //동영상 정지
    public void pause(){
        player.setPlayWhenReady(false);
    }

    //동영상 재생
    public void start(){
        player.setPlayWhenReady(true);
    }

    //동영상 시간 설정
    public void seekTo(int position){
        player.seekTo(position);
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.i("DATADATA","onResume");
        player.setPlayWhenReady(true);
    }
    @Override
    public void onPause() {
        super.onPause();
        Log.i("DATADATA","onPause");
        player.setPlayWhenReady(false);
    }

    //동영상 해제
    public void releasePlayer() {
        player.release();
        trackSelector = null;
    }
}