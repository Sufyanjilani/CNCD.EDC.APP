package com.example.cncdhumanedcandroid.Utils;

import android.content.Context;

import com.example.cncdhumanedcandroid.UIs.CustomProgressDialog;


public class CustomDialogHelper {


    CustomProgressDialog customProgressDialog;
    Context context;
    public CustomDialogHelper(Context context){


        this.context = context;
    }

    public void  showCustomProgressDialog() {

        customProgressDialog = new CustomProgressDialog(context);
        customProgressDialog.show();
    }




    public void dismissDialog(){

        customProgressDialog.dismiss();
    }
}

