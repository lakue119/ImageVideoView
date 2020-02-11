# ImageVideoView
ImageVideoView is a view that determines whether an image, gif, or video depends on the URL.
* Play the video smoothly using the ExoplayerView.
* Use Glide to manage your images efficiently.
* One view can distinguish images, gifs, and videos.

# Sample

<div>
<img width="40%" src="https://user-images.githubusercontent.com/31702431/74158490-82e5f700-4c5d-11ea-95e8-84679f3fefca.gif">
<img width="40%" src="https://user-images.githubusercontent.com/31702431/74158604-b759b300-4c5d-11ea-9695-e2eca7e7736a.jpeg">
</div>

# Install
Add Jitpack to your repositories in your build.gradle file
<pre>
<code>
  allprojects {
      repositories {
        // ...
        maven { url 'https://jitpack.io' }
      }
  }
</code>
</pre>
Add the below to your dependencies, again in your gradle.build file
<pre>
<code>
android {
        ...
        compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
}

dependencies {
    //Distinguish Image from Video Adds an Image Video View Library
    implementation 'com.github.lakue119:imagevideoview:1.0.1'

    //Just add the ExoPlayer library / additional files needed to play the video.
    implementation 'com.google.android.exoplayer:exoplayer:2.9.6'
    implementation 'com.google.android.exoplayer:exoplayer-core:2.9.6'
    implementation 'com.google.android.exoplayer:exoplayer-dash:2.9.6'
    implementation 'com.google.android.exoplayer:exoplayer-ui:2.9.6'

    //All you need to do is add the Glide library you need to see the image efficiently.
    implementation 'com.github.bumptech.glide:glide:4.9.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.9.0'
}
</code>
</pre>

# Code

```
    <com.lakue.imagevideo.ImageVideoView
        android:id="@+id/imageVideoView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
```

add following code in xml

<pre>
<code>
   imageVideoView.setUrl("Insert the desired image, gif or video");
</code>
</pre>

```
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
```
When using a video, the following code will also end the video when the view is stopped or exited.
