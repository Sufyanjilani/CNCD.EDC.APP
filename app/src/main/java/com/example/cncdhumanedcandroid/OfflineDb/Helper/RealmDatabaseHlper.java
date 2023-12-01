package com.example.cncdhumanedcandroid.OfflineDb.Helper;

import android.content.Context;
import android.util.Log;

import com.example.cncdhumanedcandroid.OfflineDb.DbMigration;
import com.example.cncdhumanedcandroid.OfflineDb.Models.EntitiesConfigModel;
import com.example.cncdhumanedcandroid.OfflineDb.Models.FormModel;
import com.example.cncdhumanedcandroid.OfflineDb.Models.SurveyFormCompleted;
import com.example.cncdhumanedcandroid.OfflineDb.Models.SurveyModel;
import com.example.cncdhumanedcandroid.Session.SessionManager;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class RealmDatabaseHlper {

    Realm realm;

    Context context;

    SessionManager sessionManager;


    public RealmDatabaseHlper(Context ctx) {

        this.context = ctx;

        sessionManager = new SessionManager(ctx);
    }


    public RealmDatabaseHlper() {

    }


    public Realm InitializeRealm(Context context) {

        realm.init(context);
        RealmConfiguration configuration = new RealmConfiguration.Builder().name("default.realm").allowQueriesOnUiThread(true).allowWritesOnUiThread(true).migration(new DbMigration(4)).deleteRealmIfMigrationNeeded().schemaVersion(4).build();
        realm.setDefaultConfiguration(configuration);

        return realm;
    }


    public void InsertEntities(String general_basic,

                               String general_diet,

                               String generaL_medical,

                               String personal_basic,

                               String personal_milk,

                               String personal_medical,

                               String personal_traits,

                               String personal_mik_weight,

                               String personal_diet,

                               String personal_traits_test,

                               String personal_muzzle,

                               String pgr_mi, String ckd_form, String nfald_form, String keratoconus_form, String amd_form, String glaucoma_form, String diabeticRetinopathy_form, String ankylosing_form, String takayasu_form, String sle_form, String heart_failure_form, String copd_form, String asthma_form, String cad_form, String renalCellForm, String bloodCancer_form, String acne_rosacea_form, String alopecia_form, String psoriatic_arthritis_form, String juvenile_form, String avascular_nicrosis_form, String interstitial_lung_disease_form, String parkinson_form, String alzheimer_form, String dementia_form, String multiple_sclerosis_form, String amytrophic_lateral_form, String huntingtons_form, String cervical_caner_form, String colorectal_form, String breast_cancer_form, String ovarian_tumor_form, String lung_cancer_form, String prostate_cancer_form, String hidradenitis_form, String acne_vulgaris_form, String vitiligo, String primary_sjogren_syndrome) {

        Realm realm = Realm.getDefaultInstance();
        FormModel formModel = new FormModel(

                general_basic, general_diet, personal_basic, personal_milk, personal_medical, personal_traits, personal_traits, personal_mik_weight, personal_diet, personal_traits_test, personal_muzzle, pgr_mi, ckd_form, nfald_form, keratoconus_form, amd_form, glaucoma_form, diabeticRetinopathy_form, ankylosing_form, takayasu_form, sle_form, heart_failure_form, copd_form, asthma_form, juvenile_form, avascular_nicrosis_form, interstitial_lung_disease_form, parkinson_form, alzheimer_form, dementia_form, multiple_sclerosis_form, amytrophic_lateral_form, huntingtons_form, cervical_caner_form, colorectal_form, breast_cancer_form, ovarian_tumor_form, lung_cancer_form, prostate_cancer_form, hidradenitis_form, acne_vulgaris_form, vitiligo, primary_sjogren_syndrome, cad_form, renalCellForm, bloodCancer_form, acne_rosacea_form, alopecia_form


        );

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(formModel);
            }

        });

        realm.close();
    }

    public void insertSurveyForm(String id, String name, String formPages) {

        realm = Realm.getDefaultInstance();

        SurveyModel surveyModel = new SurveyModel(id, name, "type", formPages);
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(surveyModel);
            }
        });

        realm.close();

    }

    public String readDataSurvey(String id) {

        Realm realm = Realm.getDefaultInstance();
        SurveyModel tasks = realm.where(SurveyModel.class).equalTo("id", id).findFirst();

        if (tasks != null) {

            String survey_pages = tasks.getFormPages();

            Log.d("TAG", survey_pages.toString());

            realm.close();

            return survey_pages;
        } else {

            return "";
        }


    }

    public void InsertCompletedForm(int formid, String formstartime, String formEndtime, String coordinate_start_lat, String coordinate_start_lon, String coordinate_end_lat, String coordinate_end_lon, String appversion, String completedform) {

        Realm realm = Realm.getDefaultInstance();


        SurveyFormCompleted formCompleted = new SurveyFormCompleted(formid, formstartime, formEndtime, coordinate_start_lat, coordinate_start_lon, coordinate_end_lat, coordinate_end_lon, completedform, appversion);
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(formCompleted);
                Log.d("constants.info", "Form");
            }

        });

        realm.close();
    }

    public ArrayList<String> readCompletedForm() {

        Realm realm = Realm.getDefaultInstance();
        RealmResults<SurveyFormCompleted> tasks = realm.where(SurveyFormCompleted.class).findAll();
        Log.d("TAG", tasks.toString());
        ArrayList<String> generatedResponse = new ArrayList<>();


        for (SurveyFormCompleted completed : tasks) {

            int formid = completed.getId();
            String startTime = completed.getSurvey_start_time();
            String endTime = completed.getSurvey_end_time();
            String appversion = completed.getAppversion();
            String start_coordinate_latitude = completed.getCoordinates_start_latitude();
            String start_coordinate_longitude = completed.getCoordinates_start_longitude();
            String end_coordinate_latitude = completed.getCoordinates_end_latitude();
            String end_coordinate_longitude = completed.getCoordinates_end_longitude();
            String pages = completed.getFormPagesCompleted();

            generatedResponse.add(0, startTime);
            generatedResponse.add(1, endTime);
            generatedResponse.add(2, appversion);
            generatedResponse.add(3, start_coordinate_latitude);
            generatedResponse.add(4, start_coordinate_longitude);
            generatedResponse.add(5, end_coordinate_latitude);
            generatedResponse.add(6, end_coordinate_longitude);
            generatedResponse.add(7, pages);
            generatedResponse.add(8, String.valueOf(formid));

        }

        Log.d("CompletedForm", generatedResponse.toString());

        realm.close();

        return generatedResponse;

    }

    public String getFormName(String id) {


        Realm realm = Realm.getDefaultInstance();
        SurveyModel tasks = realm.where(SurveyModel.class).equalTo("id", id).findFirst();

        if (tasks != null) {

            String forName = tasks.getName();

            Log.d("TAG", "readtask");

            realm.close();

            return forName;
        } else {

            return "";
        }
    }

    public JsonObject getEntityObject() {

        Realm realm = Realm.getDefaultInstance();
        RealmResults<FormModel> tasks = realm.where(FormModel.class).findAll();
        JsonObject databasereadObject = new JsonObject();

        for (FormModel model : tasks) {

            databasereadObject.addProperty("general_basic", model.getGeneral_basic());
            databasereadObject.addProperty("general_diet", model.getGeneral_diet());
            databasereadObject.addProperty("general_medical", model.getGeneral_medical());
            databasereadObject.addProperty("personal_basic", model.getPersonal_basic());
            databasereadObject.addProperty("personal_milk", model.getPersonal_milk());
            databasereadObject.addProperty("personal_medical", model.getPersonal_medical());
            databasereadObject.addProperty("personal_traits", model.getPersonal_traits());
            databasereadObject.addProperty("personal_mik_weight", model.getPersonal_mik_weight());
            databasereadObject.addProperty("personal_diet", model.getPersonal_diet());
            databasereadObject.addProperty("personal_traits_test", model.getPersonal_traits_test());
            databasereadObject.addProperty("personal_muzzle", model.getMuzzle_form());
            databasereadObject.addProperty("pgr_mi", model.getPgr_mi());
            databasereadObject.addProperty("ckd_form", model.getCkd_form());
            databasereadObject.addProperty("nfald_form", model.getNfald_form());
            databasereadObject.addProperty("keratoconus_form", model.getKeratoconus_form());
            databasereadObject.addProperty("amd_form", model.getAmd_form());
            databasereadObject.addProperty("glaucoma_form", model.getGlaucoma_form());
            databasereadObject.addProperty("diabeticRetinopathy_form", model.getDiabeticRetinopathy_form());
            databasereadObject.addProperty("ankylosing_form", model.getAnkylosing_form());
            databasereadObject.addProperty("takayasu_form", model.getTakayasu_form());
            databasereadObject.addProperty("sle_form", model.getSle_form());
            databasereadObject.addProperty("heart_failure_form", model.getHeart_failure_form());
            databasereadObject.addProperty("copd_form", model.getCopd_form());
            databasereadObject.addProperty("asthma_form", model.getAsthma_form());
            databasereadObject.addProperty("cad_form", model.getCad_form());
            databasereadObject.addProperty("renalCell_form", model.getRenalCellForm());
            databasereadObject.addProperty("bloodCancer_form", model.getBloodCancer_form());
            databasereadObject.addProperty("acne_rosacea_form", model.getAcne_rosacea_form());
            databasereadObject.addProperty("alopecia_form", model.getAlopecia_form());
            databasereadObject.addProperty("juvenile_form", model.getJuvenile_form());

            databasereadObject.addProperty("avascular_nicrosis_form", model.getAvascular_nicrosis_form());

            databasereadObject.addProperty("interstitial_lung_disease_form", model.getInterstitial_lung_disease_form());
            databasereadObject.addProperty("parkinson_form", model.getParkinson_form());
            databasereadObject.addProperty("alzheimer_form", model.getAlzheimer_form());
            databasereadObject.addProperty("dementia_form", model.getDementia_form());
            databasereadObject.addProperty("multiple_sclerosis_form", model.getMultiple_sclerosis_form());
            databasereadObject.addProperty("amytrophic_lateral_form", model.getAmytrophic_lateral_form());
            databasereadObject.addProperty("huntingtons_form", model.getHuntingtons_form());
            databasereadObject.addProperty("cervical_caner_form", model.getCervical_caner_form());
            databasereadObject.addProperty("colorectal_form", model.getColorectal_form());
            databasereadObject.addProperty("breast_cancer_form", model.getBreast_cancer_form());

            databasereadObject.addProperty("ovarian_tumor_form", model.getOvarian_tumor_form());
            databasereadObject.addProperty("colorectal_form", model.getColorectal_form());

            databasereadObject.addProperty("lung_cancer_form", model.getLung_cancer_form());

            databasereadObject.addProperty("prostate_cancer_form", model.getProstate_cancer_form());


            databasereadObject.addProperty("hidradenitis_form", model.getHidradenitis_form());


            databasereadObject.addProperty("acne_vulgaris_form", model.getAcne_vulgaris_form());
            databasereadObject.addProperty("primary_sjogren_syndrome", model.getPrimary_sjogren_syndrome());


         /*   juvenile_form,
                    avascular_nicrosis_form,
                    interstitial_lung_disease_form,
                    parkinson_form,
                    alzheimer_form,
                    dementia_form,
                    multiple_sclerosis_form,
                    amytrophic_lateral_form,
                    huntingtons_form,
                    cervical_caner_form,
                    colorectal_form,
                    breast_cancer_form,
                    ovarian_tumor_form,
                    lung_cancer_form,
                    prostate_cancer_form,
                    hidradenitis_form,
                    acne_vulgaris_form,
                    acne_vulgaris_form,
                    primary_sjogren_syndrome*/

        }

        realm.close();

        return databasereadObject;


    }


    public void InsertFormEntitiesConfig(String formconfig) {
        Realm realm = Realm.getDefaultInstance();

        EntitiesConfigModel entitiesModel = new EntitiesConfigModel(formconfig);

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(entitiesModel);
            }

        });

        realm.close();
    }


    public String getFormConfig() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<EntitiesConfigModel> tasks = realm.where(EntitiesConfigModel.class).findAll();

        if (tasks != null) {

            String formConfig = tasks.first().getFormConfigJson();

            Log.d("TAG", "read task");

            realm.close();

            return formConfig;
        } else {

            return "";
        }
    }


}
