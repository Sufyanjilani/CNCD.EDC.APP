package com.example.cncdhumanedcandroid.Network;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiEndPoints {

    @POST("auth/login")
    Call<JsonObject> login(@Body JsonObject loginobject);

    @POST("auth/logout")
    Call<JsonObject> logout(@Body JsonObject logout);

    @GET("config/questionnaires/{id}")
    Call<JsonObject> getSurvey(@Path("id") String id);

    @GET("dashboard")
    Call<JsonObject> getDashboardData();

    @GET("config/questionnaires")
    Call<JsonObject> getAllQuestionairesMeta();

}
