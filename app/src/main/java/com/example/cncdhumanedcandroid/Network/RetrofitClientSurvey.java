package com.example.cncdhumanedcandroid.Network;


import android.content.Context;

import androidx.annotation.NonNull;

import com.example.cncdhumanedcandroid.Session.SessionManager;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientSurvey {

    int DEFAULT_TIMEOUT = 60;
    Context context;
    SessionManager sessionManager;
    public RetrofitClientSurvey(Context context){

        this.context = context;
        sessionManager = new SessionManager(context);
    }




    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    OkHttpClient.Builder okHttpClient = new OkHttpClient().newBuilder().addInterceptor(loggingInterceptor)
            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(new Interceptor() {
        @NonNull
        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            Request.Builder request = chain.request().newBuilder();
            String auth = "Bearer " + sessionManager.getBearer();
            request.addHeader("Authorization",auth);
            return  chain.proceed(request.build());
        }
    });

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(new BaseUrl().getBaseUlr())
            .client(okHttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public ApiEndPoints retrofitclient(){

        return  retrofit.create(ApiEndPoints.class);
    }

}

