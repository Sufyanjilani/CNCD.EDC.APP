package com.example.cncdhumanedcandroid.UIs;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cncdhumanedcandroid.OfflineDb.Helper.RealmDatabaseHlper;
import com.example.cncdhumanedcandroid.Session.SessionManager;
import com.example.cncdhumanedcandroid.ViewModels.SettingDataViewModel;
import com.example.cncdhumanedcandroid.databinding.ActivitySettingDataBinding;


public class ActivitySettingData extends AppCompatActivity {

    ActivitySettingDataBinding activitySettingDataBinding;

    SettingDataViewModel settingDataViewModel;
    RealmDatabaseHlper realmDatabaseHlper;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySettingDataBinding = ActivitySettingDataBinding.inflate(getLayoutInflater());
        setContentView(activitySettingDataBinding.getRoot());
        settingDataViewModel = new ViewModelProvider(this).get(SettingDataViewModel.class);
        realmDatabaseHlper = new RealmDatabaseHlper();
        realmDatabaseHlper.InitializeRealm(this);
        sessionManager = new SessionManager(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(ActivitySettingData.this, ActivityDashboard.class));
                finish();
            }
        },1000);

        CheckIsDataSavedOffline();
    }

    public void CheckIsDataSavedOffline(){


        settingDataViewModel.callSavedFormsMethod();
        settingDataViewModel.surveyformsResponse.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

                if (s.equals("success")){

                    activitySettingDataBinding.txtprogress.setText("Almost Complete...");

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {


                            if (sessionManager.checkIsApplicationFirstTime()) {
                                sessionManager.ApplicationFirstTime();
                            }

                            startActivity(new Intent(ActivitySettingData.this, ActivityDashboard.class));
                            finish();
                        }
                    },1000);


                }
                else if (s.equals("")){
                    activitySettingDataBinding.txtprogress.setText("Sorry! there was some problem...");

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            startActivity(new Intent(ActivitySettingData.this, ActivityDashboard.class));
                            finish();
                        }
                    },1000);

                }
            }
        });

    }
}