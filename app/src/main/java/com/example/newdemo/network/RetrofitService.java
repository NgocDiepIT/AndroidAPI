package com.example.newdemo.network;

import com.example.newdemo.json_models.request.CreateStatusSendForm;
import com.example.newdemo.json_models.request.LoginSendForm;
import com.example.newdemo.json_models.request.RegisterSendForm;
import com.example.newdemo.json_models.response.Status;
import com.example.newdemo.json_models.response.UserInfor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitService {

    @POST(API.LOGIN)
    @Headers({API.HEADER})
    Call<UserInfor> login(@Body LoginSendForm sendForm);

    @POST(API.REGISTER)
    @Headers({API.HEADER})
    Call<UserInfor> register(@Body RegisterSendForm registerSendForm);

    @GET(API.GET_ALL_POST)
    @Headers({API.HEADER})
    Call<List<Status>> getAllPost(@Query("userId") String userId);

    @POST(API.CREATE_POST)
    @Headers({API.HEADER})
    Call<Status> createPost(@Body CreateStatusSendForm sendForm);

}
