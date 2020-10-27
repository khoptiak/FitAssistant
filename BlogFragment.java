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



public class BlogFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycleview, container, false);
        mRecyclerView = view.findViewById(R.id.fragment_recycleview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        BlogFragmentAdapter adapter = new BlogFragmentAdapter(temporaryData());
        mRecyclerView.setAdapter(adapter);
        return view;
    }

    public List<BlogElement> temporaryData(){
        ArrayList<BlogElement> data = new ArrayList<>();
        data.add(new BlogElement( "https://firebasestorage.googleapis.com/v0/b/fitassistant-de8b3.appspot.com/o/images%2FIMG_20191027_221750.jpg?alt=media&token=544be514-e68a-4c0f-a7dc-8826bf753af8"
                ,"5 Common Mineral Deficiencies ", "Minerals are chemical compounds or molecules found in nature. Though often mentioned alongside them, minerals are different than vitamins. " +
                "They are inorganic substances that not only play an important role in the earth’s ecosystem but also in the overall health of all living  organisms, including plants, animals, and humans. \n " +
                "Minerals are classified as major if greater than 5 grams of it are present in the human body. If there are less than 5 grams present, the mineral is considered a trace mineral...."));

        data.add(new BlogElement( "https://firebasestorage.googleapis.com/v0/b/fitassistant-de8b3.appspot.com/o/images%2FIMG_20191027_221750.jpg?alt=media&token=544be514-e68a-4c0f-a7dc-8826bf753af8"
                ,"5 Common Mineral Deficiencies ", "Minerals are chemical compounds or molecules found in nature. Though often mentioned alongside them, minerals are different than vitamins. " +
                "They are inorganic substances that not only play an important role in the earth’s ecosystem but also in the overall health of all living organisms, including plants, animals, and humans. \n" +
                "Minerals are classified as major if greater than 5 grams of it are present in the human body. If there are less than 5 grams present, the mineral is considered a trace mineral...."));

        return  data;
    }


    public class BlogFragmentAdapter extends RecyclerView.Adapter<BlogFragmentAdapter.BlogViewHolder>{

        private final int MAX_TEXT_LENGTH = 150;

        private List<BlogElement> dataList;

        public BlogFragmentAdapter(List<BlogElement> dataList) {
            this.dataList = dataList;
        }

        @NonNull
        @Override
        public BlogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_blog, parent, false);
            BlogViewHolder blogViewHolder = new BlogViewHolder(view);
            return blogViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull BlogViewHolder holder, int position) {
            Picasso.get().load(dataList.get(position).getIconUrl()).into(holder.mImageView);
            holder.mTitleView.setText(checkText(dataList.get(position).getTitle()));
            holder.mContentTextView.setText(dataList.get(position).getContent());

            holder.mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "Item "+ position + "has been clicked", Toast.LENGTH_LONG).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }

        public class BlogViewHolder extends  RecyclerView.ViewHolder{

            private ImageView mImageView;
            private TextView mTitleView;
            private TextView mContentTextView;
            public BlogViewHolder(@NonNull View itemView) {
                super(itemView);
                mImageView = itemView.findViewById(R.id.blog_image);
                mTitleView = itemView.findViewById(R.id.blog_titleTextView);
                mContentTextView = itemView.findViewById(R.id.blog_ContentTextView);

            }
        }

        private String checkText(String text){
            text = text.trim();
            if (text.contains("\n")){
                text.replace("\n", " ");

            }
            if (text.length()> MAX_TEXT_LENGTH){
                 text = text.substring(0,MAX_TEXT_LENGTH);
                 text.concat("...");
            }
            return text;
        }
    }
}
