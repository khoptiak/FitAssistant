package com.project.khopt.fitassistant.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.khopt.fitassistant.R;
import com.project.khopt.fitassistant.model.BlogElement;
import com.project.khopt.fitassistant.util.FragmentsContract;
import com.project.khopt.fitassistant.util.FragmentsPresenter;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



public class BlogFragment extends Fragment implements FragmentsContract.View{

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
        return view;
    }

    @Override
    public void setDataToFragment(List<Object> data) {

        BlogFragmentAdapter adapter = new BlogFragmentAdapter(data);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public String getViewInfo() {
        String id = Integer.toString(getView().getId());
        return id;
    }


    public class BlogFragmentAdapter extends RecyclerView.Adapter<BlogFragmentAdapter.BlogViewHolder>{

        private final int MAX_TEXT_LENGTH = 150;

        private List<Object> dataList;

        public BlogFragmentAdapter(List<Object> dataList) {
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

            BlogElement blogElement = (BlogElement)  dataList.get(position);
            Picasso.get().load( blogElement.getIconUrl()).into(holder.mImageView);
            holder.mTitleView.setText(checkText(blogElement.getTitle()));
            holder.mContentTextView.setText(blogElement.getContent());
            holder.mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //    Toast.makeText(getContext(), "Item "+ position + "has been clicked", Toast.LENGTH_LONG).show();
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