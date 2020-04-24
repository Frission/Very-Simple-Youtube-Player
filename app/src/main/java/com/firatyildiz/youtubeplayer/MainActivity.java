package com.firatyildiz.youtubeplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonPlaySingleVideo = findViewById(R.id.buttonPlaySingleVideo);
        Button buttonStandalone = findViewById(R.id.buttonStandalone);

        buttonPlaySingleVideo.setOnClickListener(this);
        buttonStandalone.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;

        switch (v.getId()) {
            case R.id.buttonPlaySingleVideo:
                intent = new Intent(this, YoutubeActivity.class);
                break;
            case R.id.buttonStandalone:
                intent = new Intent(this, StandaloneActivity.class);
                break;
            default:
                break;
        }

        if (intent != null)
            startActivity(intent);
    }
}
