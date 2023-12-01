package com.example.cncdhumanedcandroid.UIs;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;

import com.example.cncdhumanedcandroid.R;


public class CustomProgressDialog extends Dialog {

    public CustomProgressDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_custom_progress_dialog);
        setCancelable(false);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }

}