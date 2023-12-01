package com.example.cncdhumanedcandroid.Dialogs;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.cncdhumanedcandroid.R;
import com.example.cncdhumanedcandroid.Session.SessionManager;
import com.example.cncdhumanedcandroid.UIs.ActivityDiseaseList;


public class AdFormTypeDialog {


    Dialog dialog;
    Context context;
    SessionManager sessionManager;


    public AdFormTypeDialog() {
    }

    public void showDialog(final Context activity) {
        context = activity;
        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.select_form_type_dialog_layout);


        ImageView closeButton = (ImageView) dialog.findViewById(R.id.closeButton);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        sessionManager = new SessionManager(context);
/*

        LinearLayout buttonPgr = (LinearLayout) dialog.findViewById(R.id.buttonPgr);
        buttonPgr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, PGRFormActivity.class));
                dialog.dismiss();

            }
        });
*/
        LinearLayout buttonRecruitment = (LinearLayout) dialog.findViewById(R.id.buttonHospitalRecruitment);
        buttonRecruitment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                context.startActivity(new Intent(context, ActivityDiseaseList.class));
                dialog.dismiss();
            }
        });

        dialog.show();

    }


}
