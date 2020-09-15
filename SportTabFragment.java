package com.project.khopt.fitassistant;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SportTabFragment extends Fragment {


    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private SportListAdapter mSportListAdapter;

    private FirebaseFirestore mFirebaseFirestore;
    private CollectionReference mCollectionReference;

    private ArrayList<SportElement> sportElementList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.sport_fragment, container, false);
        mRecyclerView = view.findViewById(R.id.sportfragment_recycleview);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mLinearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);


        mFirebaseFirestore = FirebaseFirestore.getInstance();
        sportElementList = new ArrayList<>();

        mFirebaseFirestore.collection("video").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                SportElement sportElement = document.toObject(SportElement.class);
                                sportElementList.add(sportElement);
                                Log.d("TAG", document.getId() + " => " + document.getData());
                            }
                            mSportListAdapter = new SportListAdapter(sportElementList);
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });


        mRecyclerView.setAdapter(mSportListAdapter);

        return view;

    }


    public class SportListAdapter extends  RecyclerView.Adapter<SportViewHolder>{

        private List<SportElement> exerciseList;

        public SportListAdapter(List<SportElement> data){
            exerciseList = data;
        }



        @NonNull
        @Override
        public SportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sport_item, parent, false);
            SportViewHolder viewHolder = new SportViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull final SportViewHolder holder, final int position) {


            holder.initializeExoPlayer(getContext(), exerciseList.get(position));

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











