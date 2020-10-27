package com.project.khopt.fitassistant;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class EatFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recycleview, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.fragment_recycleview);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        EatListAdapter adapter = new EatListAdapter(temporaryData());
        recyclerView.setAdapter(adapter);

        return  view;
    }

    public List<FoodElement> temporaryData(){
        ArrayList<FoodElement> data = new ArrayList<>();
        data.add(new FoodElement(" Breakfast: Whole-grain toast with vegetables and cheese ", "https://firebasestorage.googleapis.com/v0/b/fitassistant-de8b3.appspot.com/o/images%2FIMG_20191027_221750.jpg?alt=media&token=544be514-e68a-4c0f-a7dc-8826bf753af8"));
        data.add(new FoodElement("Bla Bla Bla", "https://firebasestorage.googleapis.com/v0/b/fitassistant-de8b3.appspot.com/o/images%2Fque-es-un-blog-1.png?alt=media&token=1a427c32-3bcd-4dba-b317-687bb17c244f"));
        return  data;
    }

    public class EatListAdapter extends RecyclerView.Adapter<EatListAdapter.EatViewHolder> {

        private List<FoodElement> dataList;

        public EatListAdapter(List<FoodElement> data) {
            dataList = data;
        }

        @NonNull
        @Override
        public EatListAdapter.EatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_eat, parent, false);
            EatViewHolder viewHolder = new EatViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull EatListAdapter.EatViewHolder holder, int position) {

            holder.mTitleView.setText(dataList.get(position).getTitle());
            Picasso.get().load(dataList.get(position).getIconUrl()).into(holder.mImageView);


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "Item " + position + "was clicked", Toast.LENGTH_LONG).show();
                }
            });

        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }

        public class EatViewHolder extends RecyclerView.ViewHolder {


            TextView mTitleView;
            ImageView mImageView;

            public EatViewHolder(@NonNull View itemView) {
                super(itemView);

                mTitleView = itemView.findViewById(R.id.eatTitleView);
                mImageView = itemView.findViewById(R.id.eatImageView);
            }

        }
    }
}