package com.example.cncdhumanedcandroid.UIs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cncdhumanedcandroid.databinding.ActivityOncologyDiseaselistBinding;


public class Oncology_disease_type extends AppCompatActivity {


    ActivityOncologyDiseaselistBinding diseaselistBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        diseaselistBinding = ActivityOncologyDiseaselistBinding.inflate(getLayoutInflater());
        setContentView(diseaselistBinding.getRoot());

        diseaselistBinding.renalCancerDiseaseLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Oncology_disease_type.this,ActivityWebView.class);
                i.putExtra("formID","renalCell_form");
                startActivity(i);

            }
        });


        diseaselistBinding.BlooodcancerDiseaseLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Oncology_disease_type.this,ActivityWebView.class);
                i.putExtra("formID","bloodCancer_form");
                startActivity(i);

            }
        });




        diseaselistBinding.cervicalDiseaseLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Oncology_disease_type.this,ActivityWebView.class);
                i.putExtra("formID","CervicalCancer");
                startActivity(i);

            }
        });



        diseaselistBinding.colorectalDiseaseLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Oncology_disease_type.this,ActivityWebView.class);
                i.putExtra("formID","ColorectalCarcinoma");
                startActivity(i);

            }
        });


        diseaselistBinding.breastCancerDiseaseLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Oncology_disease_type.this,ActivityWebView.class);
                i.putExtra("formID","BreastCancer");
                startActivity(i);

            }
        });


        diseaselistBinding.ovarianTumorLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Oncology_disease_type.this,ActivityWebView.class);
                i.putExtra("formID","OvarianTumors");
                startActivity(i);

            }
        });

        diseaselistBinding.prostateCancerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Oncology_disease_type.this,ActivityWebView.class);
                i.putExtra("formID","ProstrateCancer");
                startActivity(i);

            }
        });

        diseaselistBinding.lungCancerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Oncology_disease_type.this,ActivityWebView.class);
                i.putExtra("formID","LungCancer");
                startActivity(i);

            }
        });




        diseaselistBinding.CloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });


    }
}