package com.example.retrofit.data;


import static androidx.core.content.ContextCompat.startActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit.InfoUserActivity;
import com.example.retrofit.MainActivity;
import com.example.retrofit.R;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    public interface OnUserClickListener{
        void onUserClick(Post post, int position);
    }
    private OnUserClickListener onClickListener;
    private final LayoutInflater inflater;
    private List<Post> postList;
    public PostAdapter(Context context, List<Post> postList, OnUserClickListener onClickListener){
        this.onClickListener = onClickListener;
        this.inflater = LayoutInflater.from(context);
        this.postList = postList;
    }

    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_post, parent, false);
        return new ViewHolder(view);
    }
    @SuppressLint("NotifyDataSetChanged")
    public  void changeData(List<Post> posts){
        this.postList = posts;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Post post = postList.get(position);
        holder.userId.setText("User ID: " + post.getUserId());
        holder.id.setText("ID Post: " + post.getId());
        holder.title.setText("Title: " + post.getTitle());
        holder.body.setText("Body: " + post.getBody());
        holder.body.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onUserClick(post,position);

            }
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView userId, id, body, title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userId = itemView.findViewById(R.id.showUserId);
            id = itemView.findViewById(R.id.showIdPost);
            title = itemView.findViewById(R.id.showTitlePost);
            body = itemView.findViewById(R.id.showBodyPost);
        }
    }
}
