package com.example.cncdhumanedcandroid.UIs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cncdhumanedcandroid.databinding.ActivityOpthalmologyDiseaseTypeBinding;


public class ActivityOphthalmologyDiseaseType extends AppCompatActivity {

    ActivityOpthalmologyDiseaseTypeBinding activityOphthalmologyDiseaseTypeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityOphthalmologyDiseaseTypeBinding = ActivityOpthalmologyDiseaseTypeBinding.inflate(getLayoutInflater());
        setContentView(activityOphthalmologyDiseaseTypeBinding.getRoot());

        activityOphthalmologyDiseaseTypeBinding.keratoconusDiseaseLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityOphthalmologyDiseaseType.this, ActivityWebView.class);
                intent.putExtra("formID","keratoconus_form");
                startActivity(intent);
            }
        });

        activityOphthalmologyDiseaseTypeBinding.amdDiseaseLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityOphthalmologyDiseaseType.this, ActivityWebView.class);
                intent.putExtra("formID","amd_form");
                startActivity(intent);
            }
        });

        activityOphthalmologyDiseaseTypeBinding.glaucomaDiseaseLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityOphthalmologyDiseaseType.this, ActivityWebView.class);
                intent.putExtra("formID","glaucoma_form");
                startActivity(intent);
            }
        });

        activityOphthalmologyDiseaseTypeBinding.diabeticRetinopathyDiseaselayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityOphthalmologyDiseaseType.this, ActivityWebView.class);
                intent.putExtra("formID","diabeticRetinopathy_form");
                startActivity(intent);
            }
        });

        activityOphthalmologyDiseaseTypeBinding.BtnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}