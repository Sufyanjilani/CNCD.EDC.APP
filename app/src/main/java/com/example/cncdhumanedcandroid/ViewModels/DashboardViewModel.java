package com.example.cncdhumanedcandroid.ViewModels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.cncdhumanedcandroid.Network.RetrofitClientSurvey;
import com.example.cncdhumanedcandroid.OfflineDb.Helper.RealmDatabaseHlper;
import com.example.cncdhumanedcandroid.Session.SessionManager;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardViewModel extends AndroidViewModel {

    RealmDatabaseHlper realDBhelper;
    SessionManager sessionManager;

    MutableLiveData<String> _isLogoutSuccess = new MutableLiveData<>();
    public MutableLiveData<String> dashboardDataResponse = new MutableLiveData<>();

    public DashboardViewModel(@NonNull Application application) {
        super(application);
        realDBhelper  = new RealmDatabaseHlper();
        sessionManager = new SessionManager(getApplication().getApplicationContext());
    }


    public void Logout(){
        JsonObject logoutObject = new JsonObject();
        logoutObject.addProperty("accessToken",sessionManager.getBearer());
        new RetrofitClientSurvey(getApplication().getApplicationContext()).retrofitclient().logout(logoutObject).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                if (response.isSuccessful()){


                    _isLogoutSuccess.setValue("loggedOut");
                    sessionManager.saveBarearToken("null");
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("error",t.getMessage().toString());

                _isLogoutSuccess.setValue("failed");
            }
        });
    }

    public void dashboardData(){
        Call<JsonObject> getData = new RetrofitClientSurvey(getApplication().getApplicationContext()).retrofitclient().getDashboardData();
        getData.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()){
                    if (!response.body().get("error").getAsString().equals("true")){
                        if (response.body().get("data").isJsonObject()){
                            dashboardDataResponse.setValue("success");
                        }else {
                            dashboardDataResponse.setValue(response.body().get("msg").getAsString());
                        }
                    }else{

                        String msg = response.body().get("msg") == null ? "null": response.body().get("msg").getAsString();
                        dashboardDataResponse.setValue(msg);
                        Log.d("msg",msg);
                    }

                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }
}
