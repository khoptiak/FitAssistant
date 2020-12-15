package com.project.khopt.fitassistant.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.khopt.fitassistant.R;
import com.project.khopt.fitassistant.model.NutritionElement;
import com.project.khopt.fitassistant.util.FragmentsContract;
import com.project.khopt.fitassistant.util.FragmentsPresenter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NutritionFragment extends Fragment implements FragmentsContract.View {


    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private FragmentsContract.Presenter mPresenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recycleview, container, false);
        mRecyclerView = view.findViewById(R.id.fragment_recycleview);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mPresenter = new FragmentsPresenter(this);
        mPresenter.getDataFromModel();

        return  view;
    }

    public List<Object> temporaryData(){
        ArrayList<Object> data = new ArrayList<>();
        data.add(new NutritionElement(" Breakfast: Whole-grain toast with vegetables and cheese ", "https://firebasestorage.googleapis.com/v0/b/fitassistant-de8b3.appspot.com/o/images%2FIMG_20191027_221750.jpg?alt=media&token=544be514-e68a-4c0f-a7dc-8826bf753af8"));
        data.add(new NutritionElement("Bla Bla Bla", "https://firebasestorage.googleapis.com/v0/b/fitassistant-de8b3.appspot.com/o/images%2Fque-es-un-blog-1.png?alt=media&token=1a427c32-3bcd-4dba-b317-687bb17c244f"));
        return  data;
    }

    @Override
    public void setDataToFragment(List<Object> data) {

        NutritionListAdapter adapter = new NutritionListAdapter(data);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public String getViewInfo() {
        String id = Integer.toString(getView().getId());
        return id;
    }

    public class NutritionListAdapter extends RecyclerView.Adapter<NutritionListAdapter.NutritionViewHolder> {

        private List<Object> dataList;

        public NutritionListAdapter(List<Object> data) {
            dataList = data;
        }

        @NonNull
        @Override
        public NutritionListAdapter.NutritionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nutrition, parent, false);
            NutritionViewHolder viewHolder = new NutritionViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull NutritionListAdapter.NutritionViewHolder holder, int position) {

            NutritionElement nutritionElement = (NutritionElement) dataList.get(position);
            holder.mTitleView.setText(nutritionElement.getTitle());
            Picasso.get().load(nutritionElement.getIconUrl()).into(holder.mImageView);


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //  Toast.makeText(getContext(), "Item " + position + "was clicked", Toast.LENGTH_LONG).show();
                }
            });

        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }

        public class NutritionViewHolder extends RecyclerView.ViewHolder {


            TextView mTitleView;
            ImageView mImageView;

            public NutritionViewHolder(@NonNull View itemView) {
                super(itemView);

                mTitleView = itemView.findViewById(R.id.nutritionTitleView);
                mImageView = itemView.findViewById(R.id.nutritionImageView);
            }

        }
    }
}