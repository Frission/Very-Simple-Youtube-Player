package com.firatyildiz.youtubeplayer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeStandalonePlayer;

public class StandaloneActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standalone);

        Button buttonPlayVideo = findViewById(R.id.buttonPlayVideo);
        Button buttonPlaylist = findViewById(R.id.buttonPlaylist);

        buttonPlayVideo.setOnClickListener(this);
        buttonPlaylist.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;

        switch (v.getId()) {
            case R.id.buttonPlayVideo:
                intent = YouTubeStandalonePlayer.createVideoIntent(this, getString(R.string.youtube_data_api_key), YoutubeActivity.YOUTUBE_VIDEO_ID, 0, true, false);
                break;
            case R.id.buttonPlaylist:
                intent = YouTubeStandalonePlayer.createPlaylistIntent(this, getString(R.string.youtube_data_api_key), YoutubeActivity.YOUTUBE_PLAYLIST_ID, 0, 0, true, false);
                break;
            default:
                break;
        }

        if (intent != null) {
            startActivity(intent);
        }
    }

}
