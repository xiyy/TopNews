package com.xi.liuliu.topnews.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xi.liuliu.topnews.R;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class LiveActivity extends AppCompatActivity {
    private VideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!LibsChecker.checkVitamioLibs(this)) {
            return;
        }
        setContentView(R.layout.activity_live);
        if (Vitamio.isInitialized(this)) {
            mVideoView = (VideoView) findViewById(R.id.video_view_live_activity);
            String url = getIntent().getStringExtra("live_url");
            mVideoView.setVideoURI(Uri.parse(url));
            mVideoView.setVideoQuality(MediaPlayer.VIDEOQUALITY_HIGH);
            MediaController controller = new MediaController(this);
            mVideoView.setMediaController(controller);
            mVideoView.setBufferSize(10240);
            mVideoView.requestFocus();
        }
    }
}
