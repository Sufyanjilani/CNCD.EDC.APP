package com.example.cncdhumanedcandroid.UIs;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cncdhumanedcandroid.Dialogs.AdFormTypeDialog;
import com.example.cncdhumanedcandroid.OfflineDb.Helper.RealmDatabaseHlper;
import com.example.cncdhumanedcandroid.Session.SessionManager;
import com.example.cncdhumanedcandroid.ViewModels.DashboardViewModel;
import com.example.cncdhumanedcandroid.databinding.ActivityDashboardBinding;


public class ActivityDashboard extends AppCompatActivity {

    ActivityDashboardBinding activityDashboardBinding;
    DashboardViewModel dashboardViewModel;
    CustomProgressDialog customProgressDialog;
    RealmDatabaseHlper realmDatabaseHlper;
    SessionManager sessionManager;

    @Override
    protected void onResume() {
        super.onResume();
        LoadData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDashboardBinding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(activityDashboardBinding.getRoot());
        realmDatabaseHlper = new RealmDatabaseHlper();
        realmDatabaseHlper.InitializeRealm(this);
        sessionManager = new SessionManager(this);
        customProgressDialog = new CustomProgressDialog(this);
        dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
        activityDashboardBinding.userNameTextView.setText(sessionManager.getName());

        activityDashboardBinding.logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logout();
            }
        });

        dashboardViewModel.dashboardDataResponse.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

                if (!s.equals("")){

                    if (s.equals("token_expired")){

                        Toast.makeText(ActivityDashboard.this,s,Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ActivityDashboard.this,ActivityLogin.class));
                        finish();
                    } else if (s.contains("failed")) {

                        customProgressDialog.dismiss();
                        androidx.appcompat.app.AlertDialog.Builder dialog = new androidx.appcompat.app.AlertDialog.Builder(ActivityDashboard.this);
                        dialog.setTitle("Can not communicate with the server");
                        dialog.setMessage("Do you wish to retry?");
                        dialog.setCancelable(false);
                        dialog.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {



                            }
                        });

                        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                finishAffinity();
                            }
                        });

                        dialog.show();


                    } else{

                        Toast.makeText(ActivityDashboard.this,s,Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        LoadData();


    }

    public void Logout(){
        dashboardViewModel.Logout();
        customProgressDialog.show();
        sessionManager.saveBarearToken("null");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                customProgressDialog.dismiss();
                startActivity(new Intent(ActivityDashboard.this,ActivityLogin.class));
                finish();
            }
        },1000);
    }

    public void adForm(View view) {
        AdFormTypeDialog formTypeDialog = new AdFormTypeDialog();
        formTypeDialog.showDialog(ActivityDashboard.this);

    }

    public void LoadData(){
        activityDashboardBinding.dashBoardLayout.setVisibility(View.GONE);
        customProgressDialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                customProgressDialog.dismiss();
                activityDashboardBinding.dashBoardLayout.setVisibility(View.VISIBLE);
            }
        },1500);
    }





}