package com.firatyildiz.youtubeplayer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubeActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    static final String YOUTUBE_VIDEO_ID = "ru-O5L2uxho";
    private static final String TAG = "YoutubeActivity";
    static final String YOUTUBE_PLAYLIST_ID = "PLsTtn4LLNUhFaw4YxepYuqrPjhoSElzvd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_youtube);

        @SuppressLint("InflateParams")
        ConstraintLayout layout = (ConstraintLayout) getLayoutInflater().inflate(R.layout.activity_youtube, null);
        setContentView(layout);

//        Button button1 = new Button(this);
//        button1.setLayoutParams(new ConstraintLayout.LayoutParams(300, 80));
//        button1.setText("Button added");
//        layout.addView(button1);

        YouTubePlayerView playerView = new YouTubePlayerView(this);
        playerView.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        layout.addView(playerView);

        playerView.initialize(getString(R.string.youtube_data_api_key), this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        Toast.makeText(this, "Initialized Youtube Player successfuly", Toast.LENGTH_LONG).show();

        if(!wasRestored) {
            youTubePlayer.cueVideo(YOUTUBE_VIDEO_ID);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        final int REQUEST_CODE = 1;

        if(youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(this, REQUEST_CODE).show();
        } else {
            String errorMessage = String.format("There was an error initializing the YouTobe Player (%1$s)", youTubeInitializationResult.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }

    private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onPlaying() {
            Log.d(TAG, "onPlaying: playing video");
            Toast.makeText(YoutubeActivity.this, "Playing video..", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onPaused() {
            Log.d(TAG, "onPaused: pausing video");
        }

        @Override
        public void onStopped() {
            Log.d(TAG, "onStopped: stopping video");
        }

        @Override
        public void onBuffering(boolean b) {
            Log.d(TAG, "onBuffering: internet is meh..");
        }

        @Override
        public void onSeekTo(int i) {
            Log.d(TAG, "onSeekTo: impatient are we?");
        }
    };

    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onLoading() {
            Log.d(TAG, "onLoading: loading...");
        }

        @Override
        public void onLoaded(String s) {
            Log.d(TAG, "onLoaded: video id: " + s);
        }

        @Override
        public void onAdStarted() {
            Log.d(TAG, "onAdStarted: sellouts..");
        }

        @Override
        public void onVideoStarted() {
            Log.d(TAG, "onVideoStarted: let's goo");
        }

        @Override
        public void onVideoEnded() {
            Log.d(TAG, "onVideoEnded: nice");
        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {
            Log.e(TAG, "onError: " + errorReason.toString());
        }
    };
}
