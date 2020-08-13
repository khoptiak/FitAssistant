package com.project.khopt.fitassistant;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TabFragment  extends Fragment {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private SportListAdapter mSportListAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab_fragment, container, false);
        mRecyclerView = view.findViewById(R.id.fragment_recycleview);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mLinearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mSportListAdapter = new SportListAdapter(temporaryDataList());
        mRecyclerView.setAdapter(mSportListAdapter);
        return view;
    }

    private List<SportElemente> temporaryDataList(){
        List<SportElemente> temporaryList = new ArrayList<>();
        String first= "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/";
        String second = "\" frameborder=\"0\" allowfullscreen></iframe>";
        temporaryList.add(new SportElemente(first  + "ex16_HWvYJM"+ second, "Lose Weight and Lose Belly Fat"));
        temporaryList.add(new SportElemente(first  + "oCOzFpxMRuM"+ second, "Upper Body Workout "));
        temporaryList.add(new SportElemente(first  + "oCOzFpxMRuM"+ second, "Upper Body Workout "));

        return temporaryList;
    }

    public class SportListAdapter extends  RecyclerView.Adapter<SportListAdapter.ViewHolder>{

        private List<SportElemente> exerciseList;

        public SportListAdapter(List<SportElemente> data){
            exerciseList = data;
        }

        public class ViewHolder extends RecyclerView.ViewHolder{

            private WebView mWebVideoView;
            private TextView mTextViewVideoName;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                mWebVideoView = itemView.findViewById(R.id.webvideo_elemente);
                mTextViewVideoName = itemView.findViewById(R.id.video_name);

                mWebVideoView.getSettings().setJavaScriptEnabled(true);
                mWebVideoView.setWebChromeClient(new WebChromeClient() {

                } );

            }
        }

        @NonNull
        @Override
        public SportListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sport_elemente, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull final SportListAdapter.ViewHolder holder, final int position) {

            holder.mWebVideoView.loadData( exerciseList.get(position).getUrl(), "text/html" , "utf-8");
                  //  holder.mWebVideoView.setVideoPath(exerciseList.get(position).getUrl());
            holder.mTextViewVideoName.setText(exerciseList.get(position).getName());

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
    }
}

