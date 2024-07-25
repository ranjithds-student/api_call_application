package com.example.apicallapplication;

import com.example.apicallapplication.model.PostResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;

public interface APIInterface {


    @GET("posts")
    Call<List<PostResponse>> getPosts();
}
