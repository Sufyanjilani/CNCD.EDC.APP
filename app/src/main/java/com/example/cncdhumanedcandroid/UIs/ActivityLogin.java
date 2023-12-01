package com.example.cncdhumanedcandroid.UIs;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationRequest;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import com.example.cncdhumanedcandroid.OfflineDb.Helper.RealmDatabaseHlper;
import com.example.cncdhumanedcandroid.Session.SessionManager;
import com.example.cncdhumanedcandroid.Utils.InternetUtils;
import com.example.cncdhumanedcandroid.ViewModels.LoginViewModel;
import com.example.cncdhumanedcandroid.databinding.ActivityLoginBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.OnTokenCanceledListener;

import java.util.ArrayList;

public class ActivityLogin extends AppCompatActivity {

    ActivityLoginBinding activityLoginBinding;
    SessionManager sessionManager;
    LoginViewModel loginViewModel;
    CustomProgressDialog customProgressDialog;
    FusedLocationProviderClient locationProviderClient;
    RealmDatabaseHlper realmDatabaseHlper;
    String appVersion = "";

    String lat,lon = "0.0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(activityLoginBinding.getRoot());
        sessionManager = new SessionManager(this);
        loginViewModel= new ViewModelProvider(this).get(LoginViewModel.class);
        customProgressDialog = new CustomProgressDialog(this);
        setUpLocation();
        getcurrentlocationEnd();
        realmDatabaseHlper = new RealmDatabaseHlper();
        realmDatabaseHlper.InitializeRealm(this);

        loginViewModel.isloginsucces.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

                if (s.equals("Login Successes")){
//                    loadingDialog.ShowCustomLoadingDialog();
                    customProgressDialog.show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {



                            startActivity(new Intent(ActivityLogin.this, ActivitySettingData.class));
//                                loadingDialog.dissmissDialog();
                            customProgressDialog.dismiss();

                        }
                    },2000);
                }
                else{

                    customProgressDialog.show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(ActivityLogin.this,s,Toast.LENGTH_SHORT).show();
                            customProgressDialog.dismiss();
                        }
                    },2000);

                }
            }
        });


        activityLoginBinding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(activityLoginBinding.editTextEmail.getText().toString().equals("")) {

                    Toast.makeText(ActivityLogin.this,"Email is empty",Toast.LENGTH_SHORT).show();
                }
                else if (activityLoginBinding.editTextPassword.getText().toString().equals("")){

                    Toast.makeText(ActivityLogin.this,"Passowrd is empty",Toast.LENGTH_SHORT).show();

                }
                else{
                    customProgressDialog.show();
                    Login(activityLoginBinding.editTextEmail.getText().toString(),activityLoginBinding.editTextPassword.getText().toString());
                }
            }
        });


    }

    public void Login(String email,String password){

        if (InternetUtils.isInternetConnected(getApplicationContext())) {
            // Internet is available
            // Do your internet-related tasks here
            String appversion = "";
            try {
                appversion = getPackageManager()
                        .getPackageInfo(getPackageName(), 0).versionName;
            } catch (PackageManager.NameNotFoundException nameNotFoundException) {

                Log.d("package", nameNotFoundException.getMessage().toString());
            }

            loginViewModel.Login(email,password,appversion,lat+","+lon);
        } else {
            // No internet connection
            // Display a message or handle the lack of internet connection
            androidx.appcompat.app.AlertDialog.Builder dialog = new androidx.appcompat.app.AlertDialog.Builder(ActivityLogin.this);
            dialog.setTitle("No internet Connection present at the moment");
            dialog.setMessage("Do you wish to go to internet settings?");
            dialog.setCancelable(false);
            dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    startActivity(new Intent(WifiManager.ACTION_PICK_WIFI_NETWORK));

                }
            });

            dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    finishAffinity();
                }
            });

            dialog.show();
        }

    }

    public ArrayList<String> getcurrentlocationEnd() {


        ArrayList<String> arrayList = new ArrayList<>();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            String[] permission = {Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION};
            ActivityCompat.requestPermissions(this,permission,4);
        }
        locationProviderClient.getCurrentLocation(LocationRequest.QUALITY_HIGH_ACCURACY, new CancellationToken() {
            @NonNull
            @Override
            public CancellationToken onCanceledRequested(@NonNull OnTokenCanceledListener onTokenCanceledListener) {
                return null;
            }

            @Override
            public boolean isCancellationRequested() {
                return false;
            }


        }).addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                if (location != null) {


                    arrayList.add(String.valueOf(location.getLatitude()));
                    arrayList.add(String.valueOf(location.getLongitude()));

                    lat = String.valueOf(location.getLatitude());
                    lon = String.valueOf(location.getLongitude());



                }
            }
        });

        return arrayList;
    }

    public void setUpLocation() {

        locationProviderClient = LocationServices.getFusedLocationProviderClient(this);


    }
}