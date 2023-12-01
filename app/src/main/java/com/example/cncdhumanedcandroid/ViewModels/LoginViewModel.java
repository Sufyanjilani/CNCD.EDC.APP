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

public class LoginViewModel extends AndroidViewModel {

    MutableLiveData<String> _isLoginSuccess = new MutableLiveData<>();
    public MutableLiveData<String> isloginsucces = _isLoginSuccess;

    SessionManager sessionManager;

    RealmDatabaseHlper realmDatabaseHlper;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        sessionManager = new SessionManager(getApplication().getApplicationContext());
        realmDatabaseHlper = new RealmDatabaseHlper();
    }

    public void Login(String username, String password, String appversion, String locationcoordinates) {

        JsonObject loginObject = new JsonObject();
        loginObject.addProperty("username", username);
        loginObject.addProperty("password", password);
        loginObject.addProperty("appVersion", appversion);
        loginObject.addProperty("locationCoordinates", locationcoordinates);


        new RetrofitClientSurvey(getApplication().getApplicationContext()).retrofitclient().login(loginObject).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                if (response.isSuccessful()) {


                    if (!response.body().get("error").getAsString().equals("true")) {

                        _isLoginSuccess.setValue("Login Successes");
                        JsonObject loginObject = response.body();

                        JsonObject data = loginObject.get("data").getAsJsonObject();
                        String accessToken = data.get("accessToken").getAsString();
                        sessionManager.saveBarearToken(accessToken);

                        Log.d("login", loginObject.toString());


                        JsonObject config = data.get("config").getAsJsonObject();
                        JsonObject formconfig = config.get("formConfig").getAsJsonObject();


                        realmDatabaseHlper.InsertFormEntitiesConfig(formconfig.toString());





                        String userId = data.get("userID").getAsString();
                        JsonObject userProfile = data.get("userProfile").getAsJsonObject();
                        String name = userProfile.get("name").getAsString();
                        String email = userProfile.get("email").getAsString();
                        String type = userProfile.get("type").getAsString();

                        sessionManager.SaveUserData(
                                name,
                                email,
                                type,
                                userId
                        );

                    } else {
                        String msg = response.body().get("msg") == null ? "null" : response.body().get("msg").getAsString();
//                            Toast.makeText(ActivityFarmerProfile.this,msg,Toast.LENGTH_SHORT).show();
                        isloginsucces.setValue(msg);
//                            isloginsucces.setValue(response.body().get("msg").getAsString());
                    }
                }

//


            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

                isloginsucces.setValue(t.getMessage());
                Log.d("TAG", t.getMessage().toString());

            }
        });

    }

}
