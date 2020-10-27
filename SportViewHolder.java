package com.project.khopt.fitassistant;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.firebase.database.DatabaseReference;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SportViewHolder extends RecyclerView.ViewHolder {

    private SimpleExoPlayer mSimpleExoPlayer;
    private PlayerView mPlayerView;
    private TextView mTextViewVideoName;


    private boolean playWhenReady = false;
    private int currentWindow = 0;
    private long playbackPosition = 0;

    public SportViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void initializeExoPlayer(Context context, SportElement sportElement) {

       mPlayerView = (PlayerView) itemView.findViewById(R.id.exo_player_item);
       mTextViewVideoName = (TextView) itemView.findViewById(R.id.exoplayer_video_name);
       mTextViewVideoName.setText(sportElement.getName());

        mSimpleExoPlayer =   ExoPlayerFactory.newSimpleInstance(context);
        mPlayerView.setPlayer(mSimpleExoPlayer);

        Uri uri  = Uri.parse(sportElement.getUrl());
        Log.d("Fit","sport element uri: " + uri.toString());
        MediaSource mediaSource = buildMediaSource(context, uri);
        mSimpleExoPlayer.setPlayWhenReady(playWhenReady);
        mSimpleExoPlayer.seekTo(currentWindow, playbackPosition);
        mSimpleExoPlayer.prepare(mediaSource, false, false);

    }
    private MediaSource buildMediaSource(Context context, Uri uri){
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory( context , "exoplayer-codelab");
        return  new ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(uri);
    }
    private void releasePlayer() {
        if (mSimpleExoPlayer != null) {
            playWhenReady = mSimpleExoPlayer.getPlayWhenReady();
            playbackPosition = mSimpleExoPlayer.getCurrentPosition();
            currentWindow = mSimpleExoPlayer.getCurrentWindowIndex();
            mSimpleExoPlayer.release();
            mSimpleExoPlayer = null;
        }
    }


}
