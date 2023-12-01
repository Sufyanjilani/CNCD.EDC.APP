package com.example.cncdhumanedcandroid.OfflineDb.Models;

import io.realm.RealmObject;

public class FormEntityModel extends RealmObject {
    String questionnaireID;

    public String getQuestionnaireID() {
        return questionnaireID;
    }

    public void setQuestionnaireID(String questionnaireID) {
        this.questionnaireID = questionnaireID;
    }

    public String getQuestionnaireName() {
        return questionnaireName;
    }

    public void setQuestionnaireName(String questionnaireName) {
        this.questionnaireName = questionnaireName;
    }

    public String getHaveImages() {
        return haveImages;
    }

    public void setHaveImages(String haveImages) {
        this.haveImages = haveImages;
    }

    String questionnaireName;

    String haveImages;
    public FormEntityModel(){

    }


    public FormEntityModel(String questionnaireID, String questionnaireName, String haveImages) {
        this.questionnaireID = questionnaireID;
        this.questionnaireName = questionnaireName;
        this.haveImages = haveImages;
    }



}
