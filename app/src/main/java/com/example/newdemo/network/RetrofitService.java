package com.example.newdemo.network;

import com.example.newdemo.json_models.request.LoginSendForm;
import com.example.newdemo.json_models.response.UserInfor;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RetrofitService {

    @POST(API.LOGIN)
    @Headers({API.HEADER})
    Call<UserInfor> login(@Body LoginSendForm sendForm);

}
