package com.example.cncdhumanedcandroid.UIs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cncdhumanedcandroid.databinding.ActivityParkinsonDiseaseTypeBinding;


public class ActivityNeurologicalDisorderDiseaseType extends AppCompatActivity {

    ActivityParkinsonDiseaseTypeBinding activityParkinsonDiseaseTypeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityParkinsonDiseaseTypeBinding = ActivityParkinsonDiseaseTypeBinding.inflate(getLayoutInflater());
        setContentView(activityParkinsonDiseaseTypeBinding.getRoot());


        activityParkinsonDiseaseTypeBinding.Btncloseeform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        activityParkinsonDiseaseTypeBinding.parkinsonCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityNeurologicalDisorderDiseaseType.this, ActivityWebView.class);
                intent.putExtra("formID","Parkinson");
                startActivity(intent);
            }
        });

        activityParkinsonDiseaseTypeBinding.alzheimercard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ActivityNeurologicalDisorderDiseaseType.this, ActivityWebView.class);
                intent.putExtra("formID","Alzheimer");
                startActivity(intent);
            }
        });

        activityParkinsonDiseaseTypeBinding.dementiacard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ActivityNeurologicalDisorderDiseaseType.this, ActivityWebView.class);
                intent.putExtra("formID","Dementia");
                startActivity(intent);
            }
        });

        activityParkinsonDiseaseTypeBinding.mutipleSclerosisLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ActivityNeurologicalDisorderDiseaseType.this, ActivityWebView.class);
                intent.putExtra("formID","MultipleSclerosis");
                startActivity(intent);
            }
        });
        activityParkinsonDiseaseTypeBinding.amytrophiclateralLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ActivityNeurologicalDisorderDiseaseType.this, ActivityWebView.class);
                intent.putExtra("formID","AmytrophiclateralSclerosis");
                startActivity(intent);
            }
        });


        activityParkinsonDiseaseTypeBinding.huntingtonscardLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ActivityNeurologicalDisorderDiseaseType.this, ActivityWebView.class);
                intent.putExtra("formID","Huntingtons_form");
                startActivity(intent);
            }
        });
    }
}