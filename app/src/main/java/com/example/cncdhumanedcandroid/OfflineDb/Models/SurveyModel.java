package com.example.cncdhumanedcandroid.OfflineDb.Models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class SurveyModel extends RealmObject {

    @PrimaryKey
    String id;

    String name, type, formPages;

    public SurveyModel() {

    }

    public SurveyModel(String id, String name, String type, String formPages) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.formPages = formPages;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFormPages() {
        return formPages;
    }

    public void setFormPages(String formPages) {
        this.formPages = formPages;
    }
}
