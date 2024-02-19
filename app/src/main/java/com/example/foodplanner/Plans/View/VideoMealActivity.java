package com.example.foodplanner.Plans.View;

import static com.example.foodplanner.Plans.View.DetailsOfMealActivity.VIDEO_URI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.foodplanner.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class VideoMealActivity extends AppCompatActivity {

    private static final String TAG = "VideoMealActivity";

    TextView inst;

    YouTubePlayerView youTubePlayerView;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_meal);
        youTubePlayerView=findViewById(R.id.display_video);
        inst=findViewById(R.id.inst_content);

        Intent listenMessage = getIntent();
        String message = listenMessage.getStringExtra(VIDEO_URI);
        Log.i(TAG, "message: "+message);




        String[] uri = message.split("=");
        Log.i(TAG, "uri: "+uri[1]);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                youTubePlayer.loadVideo(uri[1], 0);
            }
        });


    }
}