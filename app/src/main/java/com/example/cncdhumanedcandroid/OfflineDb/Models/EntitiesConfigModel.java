package com.example.cncdhumanedcandroid.OfflineDb.Models;

import io.realm.RealmObject;

public class EntitiesConfigModel extends RealmObject {

    public EntitiesConfigModel(){

    }


    public EntitiesConfigModel(String formConfigJson) {
        this.formConfigJson = formConfigJson;
    }

    public String getFormConfigJson() {
        return formConfigJson;
    }

    public void setFormConfigJson(String formConfigJson) {
        this.formConfigJson = formConfigJson;
    }

    String formConfigJson;



}