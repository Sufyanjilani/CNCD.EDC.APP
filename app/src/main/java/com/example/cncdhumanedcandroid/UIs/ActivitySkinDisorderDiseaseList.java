package com.example.cncdhumanedcandroid.UIs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cncdhumanedcandroid.databinding.ActivitySkinDisorderDiseaselistBinding;


public class ActivitySkinDisorderDiseaseList extends AppCompatActivity {


    ActivitySkinDisorderDiseaselistBinding skinDisorderDiseaselistBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        skinDisorderDiseaselistBinding = ActivitySkinDisorderDiseaselistBinding.inflate(getLayoutInflater());
        setContentView(skinDisorderDiseaselistBinding.getRoot());


        skinDisorderDiseaselistBinding.acneRasaceoDiseaseLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ActivitySkinDisorderDiseaseList.this,ActivityWebView.class);
                i.putExtra("formID","acne_rosacea_form");
                startActivity(i);
            }
        });


        skinDisorderDiseaselistBinding.alopeciaDiseaseLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ActivitySkinDisorderDiseaseList.this,ActivityWebView.class);
                i.putExtra("formID","alopecia_form");
                startActivity(i);
            }
        });


        skinDisorderDiseaselistBinding.hidrandenitisDiseaseLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ActivitySkinDisorderDiseaseList.this,ActivityWebView.class);
                i.putExtra("formID","HidradenitisSuppurativa");
                startActivity(i);
            }
        });

        skinDisorderDiseaselistBinding.acneVulgarosDiseaseLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ActivitySkinDisorderDiseaseList.this,ActivityWebView.class);
                i.putExtra("formID","AcneVulgaris");
                startActivity(i);
            }
        });

        skinDisorderDiseaselistBinding.vitiligoDiseaseLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ActivitySkinDisorderDiseaseList.this,ActivityWebView.class);
                i.putExtra("formID","Vitiligo");
                startActivity(i);
            }
        });

        skinDisorderDiseaselistBinding.primarySjogrensDiseaseLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ActivitySkinDisorderDiseaseList.this,ActivityWebView.class);
                i.putExtra("formID","PrimarySjogrenSyndrome");
                startActivity(i);
            }
        });



        skinDisorderDiseaselistBinding.CloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

    }
}