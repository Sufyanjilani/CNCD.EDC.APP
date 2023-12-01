package com.example.cncdhumanedcandroid.ViewModels;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;


import com.example.cncdhumanedcandroid.Network.RetrofitClientSurvey;
import com.example.cncdhumanedcandroid.OfflineDb.Helper.RealmDatabaseHlper;
import com.example.cncdhumanedcandroid.Utils.LinkExtractor;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingDataViewModel extends AndroidViewModel {

    RealmDatabaseHlper realDBhelper;

    public MutableLiveData<String> surveyformsResponse = new MutableLiveData<>();

    private final MutableLiveData<String> isbackgroundprocessEnded = new MutableLiveData();

    public SettingDataViewModel(@NonNull Application application) {
        super(application);
        realDBhelper = new RealmDatabaseHlper();
    }

    public void getSurveyForm() {

        String formconfigObjectString = realDBhelper.getFormConfig();
        Log.d("loop 2", formconfigObjectString);

        Gson gson = new Gson();


        Map<String, String> map = gson.fromJson(formconfigObjectString, new TypeToken<Map<String, String>>() {
        }.getType());


        Log.d("TAGMAp", map.toString());

        for (int i = 0; i < getMaximumNumber(formconfigObjectString); i++) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                Log.d("value", entry.getValue());
                if (Integer.parseInt(entry.getValue()) == i) {
                    System.out.println("Iteration: " + i + ", Key: " + entry.getKey() + ", Value: " + entry.getValue());
                    FetchForm(Integer.parseInt(entry.getValue()));
                    Log.d("TAG", "condtiion");
                }

            }
        }

    }


    public Integer getMaximumNumber(String json) {

        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);

        // Get all the numbers from the JSON object.
        Set<Integer> numbers = new HashSet<>();
        for (String key : jsonObject.keySet()) {
            String value = jsonObject.get(key).getAsString();
            try {
                int number = Integer.parseInt(value);
                numbers.add(number);
            } catch (NumberFormatException e) {
                // Ignore non-numeric values.
            }
        }

        // Find the maximum number.
        int maximumNumber = Integer.MIN_VALUE;
        for (Integer number : numbers) {
            if (number > maximumNumber) {
                maximumNumber = number;
            }
        }

        // Print the maximum number.
        Log.d("TAg", String.valueOf(maximumNumber));
        return maximumNumber;
    }

    public void callSavedFormsMethod() {

        LoadDataAsyncTask task = new LoadDataAsyncTask();
        task.execute();
        //getSurveyForm();
    }

    public void FetchForm(int i) {

        Log.d("TAG", String.valueOf(i));

        Call<JsonObject> formReader = new RetrofitClientSurvey(getApplication().getApplicationContext()).retrofitclient().getSurvey(String.valueOf(i));
        formReader.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    JsonObject surveyObject = response.body();
                    JsonArray dataObject = surveyObject.get("data").getAsJsonArray();
                    JsonObject formObject = new JsonObject();

                    if (dataObject.size() != 0) {
                        formObject = dataObject.get(0).getAsJsonObject();


                        Log.d("constants.Tag", surveyObject.toString());

                        String questionairId = formObject.get("questionnaireID") == null ? "0" : formObject.get("questionnaireID").getAsString();
                        ;
                        String questionnaireName = formObject.get("questionnaireName") == null ? "null" : formObject.get("questionnaireName").getAsString();
                        String form = formObject.get("questionnaireJSON") == null ? "null" : formObject.get("questionnaireJSON").getAsString();
                        String haveimagesFlag = formObject.get("haveImages").getAsString() == null ? "null" : formObject.get("haveImages").getAsString();


                        String parsedForm = String.valueOf(new JsonParser().parse(form));

                        List<String> links = LinkExtractor.extractLinks(parsedForm);

                        Log.d("constants.Tag", links.toString());


                        Log.d("constants.info", form);

                        realDBhelper.insertSurveyForm(questionairId, questionnaireName, form);


                    } else {
                        Log.d("constants.Tag", "error");
                    }

                }

            }


            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("constants.Tag", t.getMessage());

            }
        });
    }

    class LoadDataAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d("constants.Tag", "Background Operation begins=======");
        }

        @Override
        protected Void doInBackground(Void... voids) {
            getSurveyForm();
            Log.d("constants.Tag", "Background Operation Resumes=======");
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            Log.d("constants.Tag", "Background Operation Ends=======");
            isbackgroundprocessEnded.postValue("background process ended");
//            if (realDBhelper.getFormsCount()>0){
            surveyformsResponse.postValue("success");
//                Log.d(constants.Tag,"got forms "+realDBhelper.getFormsCount());
//            }
//            else{
//                surveyformsResponse.postValue("failed");
//                isbackgroundprocessEnded.postValue("background process ended");
//            }
        }
    }
}
