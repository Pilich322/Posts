package com.example.retrofit.api;

import com.example.retrofit.data.Post;
import com.example.retrofit.data.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("/posts")
    Call <List<Post>> getPosts();

    @GET("/users")
    Call<Users> getUser();
}
