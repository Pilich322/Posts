package com.example.retrofit.api;

import com.example.retrofit.data.Post;
import com.example.retrofit.data.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("/posts")
    Call <List<Post>> getPosts();

    @GET("/users/{id}")
    Call<List<Users>> getUser(@Path("id") int id);
}
