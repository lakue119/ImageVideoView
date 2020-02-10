package com.lakue.imagevideo;


import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class ImageVideoView extends LinearLayout {

    Context mContext;
    CustomExoPlayerView customExoPlayerView;
    ImageView imageView;

    public ImageVideoView(Context context) {
        super(context);
        initView(context);
    }

    public ImageVideoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ImageVideoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
        setBackgroundColor(Color.BLACK);
    }

    //url이 비디오 형식일 경우 exoplayerview를 실행
    //url이 이미지 형식일 경우 imageview load
    public void setUrl(String url) {
        if (isVideo(url)) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            customExoPlayerView = new CustomExoPlayerView(mContext);
            customExoPlayerView.setLayoutParams(params);
            addView(customExoPlayerView);
            customExoPlayerView.initializePlayer(url);
        } else {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(params);
            addView(imageView);
            Glide.with(this).load(url).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(imageView);
        }
    }

    //확장자가 비디오인지 이미지인지 확인
    private Boolean isVideo(String path) {
        String extension = getExtension(path);

        if (extension.equals("mp4") || extension.equals("MP4") || extension.equals("MOV") || extension.equals("mov") || extension.equals("AVI") || extension.equals("avi") ||
                extension.equals("MKV") || extension.equals("mkv") || extension.equals("WMV") || extension.equals("wmv") || extension.equals("TS") || extension.equals("ts") ||
                extension.equals("TP") || extension.equals("tp") || extension.equals("FLV") || extension.equals("flv") || extension.equals("3GP") || extension.equals("3gp") ||
                extension.equals("MPG") || extension.equals("mpg") || extension.equals("MPEG") || extension.equals("mpeg") || extension.equals("MPE") || extension.equals("mpe") ||
                extension.equals("ASF") || extension.equals("asf") || extension.equals("ASX") || extension.equals("asx") || extension.equals("DAT") || extension.equals("dat") ||
                extension.equals("RM") || extension.equals("rm")) {
            return true;
        } else {
            return false;
        }
    }

    //확장자 나누기
    private String getExtension(String url) {
        return url.substring(url.lastIndexOf(".") + 1, url.length());
    }

    //동영상 해제
    public void releasePlayer() {
        if(customExoPlayerView != null){
            customExoPlayerView.releasePlayer();
        }
    }
}
