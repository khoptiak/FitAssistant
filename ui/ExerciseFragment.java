package com.project.khopt.fitassistant.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.project.khopt.fitassistant.R;
import com.project.khopt.fitassistant.model.ExerciseElement;
import com.project.khopt.fitassistant.util.FragmentsContract;
import com.project.khopt.fitassistant.util.FragmentsPresenter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ExerciseFragment extends Fragment implements FragmentsContract.View {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private ExerciseListAdapter mExerciseListAdapter;
    private FragmentsContract.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recycleview, container, false);
        mRecyclerView = view.findViewById(R.id.fragment_recycleview);

        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mLinearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mPresenter = new FragmentsPresenter(this);
        mPresenter.getDataFromModel();


        return view;

    }

    public ArrayList<Object> temporaryList() {
        ArrayList<Object> list = new ArrayList<>();
        list.add(new ExerciseElement("https://firebasestorage.googleapis.com/v0/b/fitassistant-de8b3.appspot.com/o/video%2Ffullbody1.mp4?alt=media&token=de43eb4b-9513-4945-b0cc-0b8f7f355382", "Full Body Workout"));
        list.add(new ExerciseElement("https://firebasestorage.googleapis.com/v0/b/fitassistant-de8b3.appspot.com/o/video%2Ffullbody1.mp4?alt=media&token=de43eb4b-9513-4945-b0cc-0b8f7f355382", "Lower Body Workout"));
        return list;
    }

    @Override
    public void setDataToFragment(List<Object> data) {
        mExerciseListAdapter = new ExerciseListAdapter(data);
        mRecyclerView.setAdapter(mExerciseListAdapter);
    }

    @Override
    public String getViewInfo() {
        String id = Integer.toString(getView().getId());
        return id;
    }


    public class ExerciseListAdapter extends RecyclerView.Adapter<ExerciseListAdapter.ExerciseViewHolder> {

        private List<Object> exerciseList;

        public ExerciseListAdapter(List<Object> data) {
            exerciseList = data;
        }


        @NonNull
        @Override
        public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exercise, parent, false);
            ExerciseViewHolder viewHolder = new ExerciseViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull final ExerciseViewHolder holder, final int position) {


            holder.initializeExoPlayer(getContext(), (ExerciseElement) exerciseList.get(position));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "Item " + position + "was clicked", Toast.LENGTH_LONG).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return exerciseList.size();
        }


        private class ExerciseViewHolder extends RecyclerView.ViewHolder {

            private SimpleExoPlayer mSimpleExoPlayer;
            private PlayerView mPlayerView;
            private TextView mTextViewVideoName;


            private boolean playWhenReady = false;
            private int currentWindow = 0;
            private long playbackPosition = 0;

            public ExerciseViewHolder(@NonNull View itemView) {
                super(itemView);
            }

            public void initializeExoPlayer(Context context, ExerciseElement sportElement) {

                mPlayerView = (PlayerView) itemView.findViewById(R.id.exo_player_item);
                mTextViewVideoName = (TextView) itemView.findViewById(R.id.exoplayer_video_name);
                mTextViewVideoName.setText(sportElement.getTitle());

                mSimpleExoPlayer = ExoPlayerFactory.newSimpleInstance(context);
                mPlayerView.setPlayer(mSimpleExoPlayer);

                Uri uri = Uri.parse(sportElement.getVideoUrl());
                Log.d("Fit", "sport element uri: " + uri.toString());
                MediaSource mediaSource = buildMediaSource(context, uri);
                mSimpleExoPlayer.setPlayWhenReady(playWhenReady);
                mSimpleExoPlayer.seekTo(currentWindow, playbackPosition);
                mSimpleExoPlayer.prepare(mediaSource, false, false);

            }

            private MediaSource buildMediaSource(Context context, Uri uri) {
                DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context, "exoplayer-codelab");
                return new ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(uri);
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
    }


}










