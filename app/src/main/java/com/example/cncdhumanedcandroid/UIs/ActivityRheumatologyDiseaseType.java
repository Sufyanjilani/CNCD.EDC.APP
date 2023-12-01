package com.example.cncdhumanedcandroid.UIs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cncdhumanedcandroid.databinding.ActivityRheumatologyDiseaseTypeBinding;


public class ActivityRheumatologyDiseaseType extends AppCompatActivity {

    ActivityRheumatologyDiseaseTypeBinding activityRheumatologyDiseaseTypeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRheumatologyDiseaseTypeBinding = ActivityRheumatologyDiseaseTypeBinding.inflate(getLayoutInflater());
        setContentView(activityRheumatologyDiseaseTypeBinding.getRoot());

        activityRheumatologyDiseaseTypeBinding.sleDiseaseLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityRheumatologyDiseaseType.this, ActivityWebView.class);
                intent.putExtra("formID","sle_form");
                startActivity(intent);
            }
        });

        activityRheumatologyDiseaseTypeBinding.ankylosingDiseaseLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityRheumatologyDiseaseType.this, ActivityWebView.class);
                intent.putExtra("formID","ankylosing_form");
                startActivity(intent);
            }
        });

        activityRheumatologyDiseaseTypeBinding.takayasuDiseaseLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityRheumatologyDiseaseType.this, ActivityWebView.class);
                intent.putExtra("formID","takayasu_form");
                startActivity(intent);
            }
        });


        activityRheumatologyDiseaseTypeBinding.jiaCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityRheumatologyDiseaseType.this, ActivityWebView.class);
                intent.putExtra("formID","JuvenileIdiopathicArthritis");
                startActivity(intent);
            }
        });


        activityRheumatologyDiseaseTypeBinding.rheumatoidArthitisCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityRheumatologyDiseaseType.this, ActivityWebView.class);
                intent.putExtra("formID","rheumatoid_form");
                startActivity(intent);
            }
        });

        activityRheumatologyDiseaseTypeBinding.osteoarthritiscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityRheumatologyDiseaseType.this, ActivityWebView.class);
                intent.putExtra("formID","osteoarthritis_form");
                startActivity(intent);
            }
        });

        activityRheumatologyDiseaseTypeBinding.psoraitciArthtiscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(ActivityRheumatologyDiseaseType.this, ActivityWebView.class);
                intent.putExtra("formID","psoriatic_form");
                startActivity(intent);
            }
        });



        activityRheumatologyDiseaseTypeBinding.psoraitciArthtiscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(ActivityRheumatologyDiseaseType.this, ActivityWebView.class);
                intent.putExtra("formID","scleroderma_form");
                startActivity(intent);
            }
        });



        activityRheumatologyDiseaseTypeBinding.btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



}