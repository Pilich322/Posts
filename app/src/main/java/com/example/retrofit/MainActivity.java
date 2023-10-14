package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofit.api.ApiClient;
import com.example.retrofit.api.ApiInterface;
import com.example.retrofit.data.Post;
import com.example.retrofit.data.PostAdapter;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    List<Post> postList = new ArrayList<>();
    PostAdapter postAdapter;
    RecyclerView recyclerView;

    ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        Toast.makeText(MainActivity.this, "Нажми на любой пост, чтобы увидеть инф-цию о пользователе что его написал", Toast.LENGTH_SHORT).show();
        getPosts();
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, RecyclerView.VERTICAL);
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.border_item_recyclerview, null);
        if (drawable != null) {
            dividerItemDecoration.setDrawable(drawable);
        }
        recyclerView.addItemDecoration(dividerItemDecoration);
        PostAdapter.OnUserClickListener onUserClickListener = (post, position) -> {

            Post selectedPost = postList.get(position);
            Intent intent = new Intent(MainActivity.this, InfoUserActivity.class);
            intent.putExtra("userId", selectedPost.getUserId());
            startActivity(intent);
        };
        postAdapter = new PostAdapter(getApplicationContext(), postList,onUserClickListener);
    }

    public void getPosts(){
        Call<List<Post>> listPost = apiInterface.getPosts();
        listPost.enqueue(new Callback<List<Post>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()){
                    Log.e("CODE", response.code()+"");
                    return;
                }
                postList = response.body();
                postAdapter.changeData(postList);
                recyclerView.setAdapter(postAdapter);
            }
            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.w("Code", t.getLocalizedMessage()+ "");
            }
        });
    }
}