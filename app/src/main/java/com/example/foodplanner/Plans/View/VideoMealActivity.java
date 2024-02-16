package com.example.foodplanner.Plans.View;

import static com.example.foodplanner.HomeScreen.View.HomeFragment.EXTRA_MEAL;
import static com.example.foodplanner.Plans.View.DetailsOfMealActivity.VIDEO_URI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.foodplanner.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class VideoMealActivity extends AppCompatActivity {

    private static final String TAG = "VideoMealActivity";
    WebView videoView;

    YouTubePlayerView youTubePlayerView;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_meal);
        youTubePlayerView=findViewById(R.id.display_video);

        Intent listenMessage = getIntent();
        String message = listenMessage.getStringExtra(VIDEO_URI);
        Log.i(TAG, "message: "+message);


        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                super.onReady(youTubePlayer);
            }
        });

//        String video = "<html><body><iframe width=\"100%\" height=\"100%\" src=\"" + message + "\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe></body></html>";
//        videoView.loadData(video, "text/html","utf-8");
//        videoView.getSettings().setJavaScriptEnabled(true);
//        videoView.setWebChromeClient(new WebChromeClient());

//        String videoId = "tUesv5u5bvA"; // Example video ID
//        String videoUrl = "https://www.youtube.com/embed/" + videoId;
//
//        String html = "<html><body><iframe width=\"100%\" height=\"100%\" src=\"" + message + "\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe></body></html>";
//
//        videoView.loadData(html, "text/html", "utf-8");
//        videoView.getSettings().setJavaScriptEnabled(true);
//        videoView.setWebChromeClient(new WebChromeClient());
    }
}