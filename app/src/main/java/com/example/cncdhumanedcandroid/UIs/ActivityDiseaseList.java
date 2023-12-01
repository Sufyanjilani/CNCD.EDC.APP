package com.example.cncdhumanedcandroid.UIs;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cncdhumanedcandroid.databinding.ActivityDiseaseListBinding;


public class ActivityDiseaseList extends AppCompatActivity {

    ActivityDiseaseListBinding activityDiseaseListBinding;
    CustomProgressDialog customProgressDialog;

    @Override
    protected void onResume() {
        super.onResume();
        LoadData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDiseaseListBinding = ActivityDiseaseListBinding.inflate(getLayoutInflater());
        setContentView(activityDiseaseListBinding.getRoot());
        customProgressDialog = new CustomProgressDialog(this);

        activityDiseaseListBinding.cadDiseaseLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityDiseaseList.this, ActivityWebView.class);
                intent.putExtra("formID", "cad_form");
                startActivity(intent);
            }
        });

        activityDiseaseListBinding.copdDiseaseLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityDiseaseList.this, ActivityCOPDType.class);
                startActivity(intent);
            }
        });

        activityDiseaseListBinding.heartFailureDiseaseLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityDiseaseList.this, ActivityWebView.class);
                intent.putExtra("formID", "heart_failure_form");
                startActivity(intent);
            }
        });

        activityDiseaseListBinding.miDiseaseLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityDiseaseList.this, ActivityWebView.class);
                intent.putExtra("formID", "pgr_mi");
                startActivity(intent);
            }
        });

        activityDiseaseListBinding.nfaldDiseaseLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityDiseaseList.this, ActivityWebView.class);
                intent.putExtra("formID", "nfald_form");
                startActivity(intent);
            }
        });

        activityDiseaseListBinding.ckdDiseaseLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityDiseaseList.this, ActivityWebView.class);
                intent.putExtra("formID", "ckd_form");
                startActivity(intent);
            }
        });

        activityDiseaseListBinding.type2DiabetesLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityDiseaseList.this, ActivityWebView.class);
                intent.putExtra("formID", "diabetes_form");
                startActivity(intent);
            }
        });



        activityDiseaseListBinding.copdDiseaseLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityDiseaseList.this, ActivityCOPDType.class);
                startActivity(intent);
            }
        });

        activityDiseaseListBinding.ophthalmologyDiseaseLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityDiseaseList.this, ActivityOphthalmologyDiseaseType.class);
                startActivity(intent);
            }
        });

        activityDiseaseListBinding.rhematologyDiseaseLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityDiseaseList.this, ActivityRheumatologyDiseaseType.class);
                startActivity(intent);
            }
        });

        activityDiseaseListBinding.parkinsonDiseaseLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityDiseaseList.this, ActivityNeurologicalDisorderDiseaseType.class);
                startActivity(intent);
            }
        });


        activityDiseaseListBinding.CloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        activityDiseaseListBinding.oncologyLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ActivityDiseaseList.this, Oncology_disease_type.class);
                startActivity(intent);

            }
        });

        activityDiseaseListBinding.skindisorderLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ActivityDiseaseList.this, ActivitySkinDisorderDiseaseList.class);
                startActivity(intent);
            }
        });




        LoadData();


    }

    public void LoadData() {
        activityDiseaseListBinding.diseaseTypeLayout.setVisibility(View.GONE);
        customProgressDialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                customProgressDialog.dismiss();
                activityDiseaseListBinding.diseaseTypeLayout.setVisibility(View.VISIBLE);
            }
        }, 1500);
    }
}