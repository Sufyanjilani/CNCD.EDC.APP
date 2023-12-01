package com.example.cncdhumanedcandroid.ViewModels;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.google.gson.JsonObject;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class WebViewModel extends AndroidViewModel {
    public WebViewModel(@NonNull Application application) {
        super(application);
    }

    public void submitForm(String submitType, Context context, String questionnaireID, String participant_ID, String appVersion, String locationCoordinate, String formJSON, String interviewtakenAt, String interviewTimeStart, String interviewTimeEnd, String locationCoordinatesStart, String locationCoordinatesEnd, String imagePath, List<MultipartBody.Part> imageParts) {


        JsonObject payloadObject = new JsonObject();


        if (submitType.equals("Multipart")) {

            //Multipart Condition


            RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("formdata", payloadObject.toString()).build();

            RequestBody payload;
            payload = RequestBody.create(MediaType.parse("multipart/form-data"), payloadObject.toString());


            //create request to retrofit here

        } else {

            //Json condition

            //create request to retrofit here


        }

    }


}
