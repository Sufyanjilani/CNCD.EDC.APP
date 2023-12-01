package com.example.cncdhumanedcandroid.UIs;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.cncdhumanedcandroid.R;
import com.example.cncdhumanedcandroid.Session.SessionManager;
import com.example.cncdhumanedcandroid.databinding.ActivitySplashBinding;

public class ActivitySplash extends AppCompatActivity {

    ActivitySplashBinding activitySplashBinding;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySplashBinding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(activitySplashBinding.getRoot());
        sessionManager = new SessionManager(this);
        if(CheckScreenSize()) {

            firstFadeIn();
        }
        else{
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("This App can't run on this device please install in a device with larger screen size");
            alert.setCancelable(false);
            alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    finishAffinity();
                }
            });
            alert.show();
        }



    }

    public void CheckUser(){

        if (!sessionManager.getBearer().equals("null")){


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {


                    if (sessionManager.checkIsApplicationFirstTime()){
                        Intent i = new Intent(ActivitySplash.this, ActivitySettingData.class);
                        startActivity(i);
                        finish();

                    }
                    else {

                        Intent i = new Intent(ActivitySplash.this, ActivityDashboard.class);
                        startActivity(i);
                        finish();
                    }

                }
            },2000);


        }
        else{
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(ActivitySplash.this, ActivityLogin.class);
                    startActivity(i);
                    finish();

                }
            },2000);

        }
    }


    private void firstFadeIn() {

        Handler handler = new Handler();
        ImageView topImageView = findViewById(R.id.topImageView);


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                topImageView.setVisibility(View.VISIBLE);
                topImageView.startAnimation(AnimationUtils.loadAnimation(ActivitySplash.this, R.anim.fade_in_org));

                secondFadeIn();

            }
        }, 1000);


    }

    private void secondFadeIn() {

        Handler handler = new Handler();
        ImageView secondImageView = findViewById(R.id.secondImageView);


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                secondImageView.setVisibility(View.VISIBLE);
                secondImageView.startAnimation(AnimationUtils.loadAnimation(ActivitySplash.this, R.anim.fade_in_org));
                LottieAnimationView animation_view = findViewById(R.id.animation_view);
                animation_view.setVisibility(View.VISIBLE);

                //thirdFadeIn();
//                GeneralUtils.setLanguage(ActivitySplash.this, "en");
//                sessionManager.RememberLanguage("en");
                CheckUser();

            }
        }, 1000);


    }
    public Boolean CheckScreenSize(){

        Boolean istablet = false;
        DisplayMetrics metrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(metrics);

        float yInches= metrics.heightPixels/metrics.ydpi;
        float xInches= metrics.widthPixels/metrics.xdpi;
        double diagonalInches = Math.sqrt(xInches*xInches + yInches*yInches);
        if (diagonalInches>=6.9){


            Log.d("size", String.valueOf(diagonalInches));
            istablet = true;

        }else{

            istablet = false;
        }

        return  istablet;
    }


}
