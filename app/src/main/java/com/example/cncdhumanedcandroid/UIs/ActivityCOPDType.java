package com.example.cncdhumanedcandroid.UIs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cncdhumanedcandroid.databinding.ActivityCopdtypeBinding;


public class ActivityCOPDType extends AppCompatActivity {

    ActivityCopdtypeBinding activityCopdtypeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCopdtypeBinding = ActivityCopdtypeBinding.inflate(getLayoutInflater());
        setContentView(activityCopdtypeBinding.getRoot());

        activityCopdtypeBinding.codpDiseaseLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityCOPDType.this, ActivityWebView.class);
                intent.putExtra("formID","copd_form");
                startActivity(intent);
            }
        });

        activityCopdtypeBinding.asthmaDiseaseLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityCOPDType.this, ActivityWebView.class);
                intent.putExtra("formID","asthma_form");
                startActivity(intent);
            }
        });


        activityCopdtypeBinding.asthmaDiseaseLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityCOPDType.this, ActivityWebView.class);
                intent.putExtra("formID","asthma_form");
                startActivity(intent);
            }
        });



        activityCopdtypeBinding.asthmaDiseaseLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityCOPDType.this, ActivityWebView.class);
                intent.putExtra("formID","InterstitialLungDisease");
                startActivity(intent);
            }
        });

        activityCopdtypeBinding.closeeform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }
}