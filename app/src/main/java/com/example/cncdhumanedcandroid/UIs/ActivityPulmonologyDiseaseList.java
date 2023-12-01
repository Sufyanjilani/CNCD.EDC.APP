package com.example.cncdhumanedcandroid.UIs;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cncdhumanedcandroid.databinding.ActivityPulmonologyDiseaseListBinding;


public class ActivityPulmonologyDiseaseList extends AppCompatActivity {


    ActivityPulmonologyDiseaseListBinding pulmonologyDiseaseListBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pulmonologyDiseaseListBinding = ActivityPulmonologyDiseaseListBinding.inflate(getLayoutInflater());
        setContentView(pulmonologyDiseaseListBinding.getRoot());


    }
}