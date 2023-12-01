package com.example.cncdhumanedcandroid.OfflineDb.Models;

import io.realm.RealmObject;

public class FormModel extends RealmObject {

    String general_basic;
    String general_diet;

    String general_medical;

    public FormModel(String general_basic, String general_diet, String general_medical, String personal_basic, String personal_milk, String personal_medical, String personal_traits, String personal_mik_weight, String personal_diet, String personal_traits_test, String muzzle_form, String pgr_mi, String ckd_form, String nfald_form, String keratoconus_form, String amd_form, String glaucoma_form, String diabeticRetinopathy_form, String ankylosing_form, String takayasu_form, String sle_form, String heart_failure_form, String copd_form, String asthma_form, String juvenile_form, String avascular_nicrosis_form, String interstitial_lung_disease_form, String parkinson_form, String alzheimer_form, String dementia_form, String multiple_sclerosis_form, String amytrophic_lateral_form, String huntingtons_form, String cervical_caner_form, String colorectal_form, String breast_cancer_form, String ovarian_tumor_form, String lung_cancer_form, String prostate_cancer_form, String hidradenitis_form, String acne_vulgaris_form, String vitiligo, String primary_sjogren_syndrome, String cad_form, String renalCellForm, String bloodCancer_form, String acne_rosacea_form, String alopecia_form) {
        this.general_basic = general_basic;
        this.general_diet = general_diet;
        this.general_medical = general_medical;
        this.personal_basic = personal_basic;
        this.personal_milk = personal_milk;
        this.personal_medical = personal_medical;
        this.personal_traits = personal_traits;
        this.personal_mik_weight = personal_mik_weight;
        this.personal_diet = personal_diet;
        this.personal_traits_test = personal_traits_test;
        this.muzzle_form = muzzle_form;
        this.pgr_mi = pgr_mi;
        this.ckd_form = ckd_form;
        this.nfald_form = nfald_form;
        this.keratoconus_form = keratoconus_form;
        this.amd_form = amd_form;
        this.glaucoma_form = glaucoma_form;
        this.diabeticRetinopathy_form = diabeticRetinopathy_form;
        this.ankylosing_form = ankylosing_form;
        this.takayasu_form = takayasu_form;
        this.sle_form = sle_form;
        this.heart_failure_form = heart_failure_form;
        this.copd_form = copd_form;
        this.asthma_form = asthma_form;
        this.juvenile_form = juvenile_form;
        this.avascular_nicrosis_form = avascular_nicrosis_form;
        this.interstitial_lung_disease_form = interstitial_lung_disease_form;
        this.parkinson_form = parkinson_form;
        this.alzheimer_form = alzheimer_form;
        this.dementia_form = dementia_form;
        this.multiple_sclerosis_form = multiple_sclerosis_form;
        this.amytrophic_lateral_form = amytrophic_lateral_form;
        this.huntingtons_form = huntingtons_form;
        this.cervical_caner_form = cervical_caner_form;
        this.colorectal_form = colorectal_form;
        this.breast_cancer_form = breast_cancer_form;
        this.ovarian_tumor_form = ovarian_tumor_form;
        this.lung_cancer_form = lung_cancer_form;
        this.prostate_cancer_form = prostate_cancer_form;
        this.hidradenitis_form = hidradenitis_form;
        this.acne_vulgaris_form = acne_vulgaris_form;
        this.vitiligo = vitiligo;
        this.primary_sjogren_syndrome = primary_sjogren_syndrome;
        this.cad_form = cad_form;
        this.renalCellForm = renalCellForm;
        this.bloodCancer_form = bloodCancer_form;
        this.acne_rosacea_form = acne_rosacea_form;
        this.alopecia_form = alopecia_form;
    }

    String personal_basic;

    String personal_milk;

    String personal_medical;

    String personal_traits;

    String personal_mik_weight;

    String personal_diet;

    String personal_traits_test;

    String muzzle_form;
    String pgr_mi;
    String ckd_form;
    String nfald_form;
    String keratoconus_form;
    String amd_form;

    String glaucoma_form;
    String diabeticRetinopathy_form;

    String ankylosing_form;
    String takayasu_form;
    String sle_form;
    String heart_failure_form;
    String copd_form;
    String asthma_form;

    //new forms
    String juvenile_form;
    String avascular_nicrosis_form;



    String interstitial_lung_disease_form;
    String  parkinson_form;
    String alzheimer_form;
    String dementia_form;
    String multiple_sclerosis_form;
    String amytrophic_lateral_form;
    String huntingtons_form;
    String cervical_caner_form;
    String colorectal_form;
    String breast_cancer_form;
    String ovarian_tumor_form;
    String lung_cancer_form;
    String  prostate_cancer_form;
    String  hidradenitis_form;
    String acne_vulgaris_form;
    String vitiligo;
    String primary_sjogren_syndrome;



    public String getJuvenile_form() {
        return juvenile_form;
    }

    public void setJuvenile_form(String juvenile_form) {
        this.juvenile_form = juvenile_form;
    }

    public String getAvascular_nicrosis_form() {
        return avascular_nicrosis_form;
    }

    public void setAvascular_nicrosis_form(String avascular_nicrosis_form) {
        this.avascular_nicrosis_form = avascular_nicrosis_form;
    }

    public String getInterstitial_lung_disease_form() {
        return interstitial_lung_disease_form;
    }

    public void setInterstitial_lung_disease_form(String interstitial_lung_disease_form) {
        this.interstitial_lung_disease_form = interstitial_lung_disease_form;
    }

    public String getParkinson_form() {
        return parkinson_form;
    }

    public void setParkinson_form(String parkinson_form) {
        this.parkinson_form = parkinson_form;
    }

    public String getAlzheimer_form() {
        return alzheimer_form;
    }

    public void setAlzheimer_form(String alzheimer_form) {
        this.alzheimer_form = alzheimer_form;
    }

    public String getDementia_form() {
        return dementia_form;
    }

    public void setDementia_form(String dementia_form) {
        this.dementia_form = dementia_form;
    }

    public String getMultiple_sclerosis_form() {
        return multiple_sclerosis_form;
    }

    public void setMultiple_sclerosis_form(String multiple_sclerosis_form) {
        this.multiple_sclerosis_form = multiple_sclerosis_form;
    }

    public String getAmytrophic_lateral_form() {
        return amytrophic_lateral_form;
    }

    public void setAmytrophic_lateral_form(String amytrophic_lateral_form) {
        this.amytrophic_lateral_form = amytrophic_lateral_form;
    }

    public String getHuntingtons_form() {
        return huntingtons_form;
    }

    public void setHuntingtons_form(String huntingtons_form) {
        this.huntingtons_form = huntingtons_form;
    }

    public String getCervical_caner_form() {
        return cervical_caner_form;
    }

    public void setCervical_caner_form(String cervical_caner_form) {
        this.cervical_caner_form = cervical_caner_form;
    }

    public String getColorectal_form() {
        return colorectal_form;
    }

    public void setColorectal_form(String colorectal_form) {
        this.colorectal_form = colorectal_form;
    }

    public String getBreast_cancer_form() {
        return breast_cancer_form;
    }

    public void setBreast_cancer_form(String breast_cancer_form) {
        this.breast_cancer_form = breast_cancer_form;
    }

    public String getOvarian_tumor_form() {
        return ovarian_tumor_form;
    }

    public void setOvarian_tumor_form(String ovarian_tumor_form) {
        this.ovarian_tumor_form = ovarian_tumor_form;
    }

    public String getLung_cancer_form() {
        return lung_cancer_form;
    }

    public void setLung_cancer_form(String lung_cancer_form) {
        this.lung_cancer_form = lung_cancer_form;
    }

    public String getProstate_cancer_form() {
        return prostate_cancer_form;
    }

    public void setProstate_cancer_form(String prostate_cancer_form) {
        this.prostate_cancer_form = prostate_cancer_form;
    }

    public String getHidradenitis_form() {
        return hidradenitis_form;
    }

    public void setHidradenitis_form(String hidradenitis_form) {
        this.hidradenitis_form = hidradenitis_form;
    }

    public String getAcne_vulgaris_form() {
        return acne_vulgaris_form;
    }

    public void setAcne_vulgaris_form(String acne_vulgaris_form) {
        this.acne_vulgaris_form = acne_vulgaris_form;
    }

    public String getVitiligo() {
        return vitiligo;
    }

    public void setVitiligo(String vitiligo) {
        this.vitiligo = vitiligo;
    }

    public String getPrimary_sjogren_syndrome() {
        return primary_sjogren_syndrome;
    }

    public void setPrimary_sjogren_syndrome(String primary_sjogren_syndrome) {
        this.primary_sjogren_syndrome = primary_sjogren_syndrome;
    }

    String cad_form;

    String renalCellForm;
    String bloodCancer_form;
    String acne_rosacea_form;
    String alopecia_form;

    public String getRenalCellForm() {
        return renalCellForm;
    }

    public void setRenalCellForm(String renalCellForm) {
        this.renalCellForm = renalCellForm;
    }

    public String getBloodCancer_form() {
        return bloodCancer_form;
    }

    public void setBloodCancer_form(String bloodCancer_form) {
        this.bloodCancer_form = bloodCancer_form;
    }

    public String getAcne_rosacea_form() {
        return acne_rosacea_form;
    }

    public void setAcne_rosacea_form(String acne_rosacea_form) {
        this.acne_rosacea_form = acne_rosacea_form;
    }

    public String getAlopecia_form() {
        return alopecia_form;
    }

    public void setAlopecia_form(String alopecia_form) {
        this.alopecia_form = alopecia_form;
    }

    public FormModel(){

    }



    public String getGeneral_basic() {
        return general_basic;
    }

    public void setGeneral_basic(String general_basic) {
        this.general_basic = general_basic;
    }

    public String getGeneral_diet() {
        return general_diet;
    }

    public void setGeneral_diet(String general_diet) {
        this.general_diet = general_diet;
    }

    public String getGeneral_medical() {
        return general_medical;
    }

    public void setGeneral_medical(String general_medical) {
        this.general_medical = general_medical;
    }

    public String getPersonal_basic() {
        return personal_basic;
    }

    public void setPersonal_basic(String personal_basic) {
        this.personal_basic = personal_basic;
    }

    public String getPersonal_milk() {
        return personal_milk;
    }

    public void setPersonal_milk(String personal_milk) {
        this.personal_milk = personal_milk;
    }

    public String getPersonal_medical() {
        return personal_medical;
    }

    public void setPersonal_medical(String personal_medical) {
        this.personal_medical = personal_medical;
    }

    public String getPersonal_traits() {
        return personal_traits;
    }

    public void setPersonal_traits(String personal_traits) {
        this.personal_traits = personal_traits;
    }

    public String getPersonal_mik_weight() {
        return personal_mik_weight;
    }

    public void setPersonal_mik_weight(String personal_mik_weight) {
        this.personal_mik_weight = personal_mik_weight;
    }

    public String getPersonal_diet() {
        return personal_diet;
    }

    public void setPersonal_diet(String personal_diet) {
        this.personal_diet = personal_diet;
    }

    public String getPersonal_traits_test() {
        return personal_traits_test;
    }

    public void setPersonal_traits_test(String personal_traits_test) {
        this.personal_traits_test = personal_traits_test;
    }

    public String getMuzzle_form() {
        return muzzle_form;
    }

    public void setMuzzle_form(String muzzle_form) {
        this.muzzle_form = muzzle_form;
    }

    public String getPgr_mi() {
        return pgr_mi;
    }

    public void setPgr_mi(String pgr_mi) {
        this.pgr_mi = pgr_mi;
    }

    public String getCkd_form() {
        return ckd_form;
    }

    public void setCkd_form(String ckd_form) {
        this.ckd_form = ckd_form;
    }

    public String getNfald_form() {
        return nfald_form;
    }

    public void setNfald_form(String nfald_form) {
        this.nfald_form = nfald_form;
    }

    public String getKeratoconus_form() {
        return keratoconus_form;
    }

    public void setKeratoconus_form(String keratoconus_form) {
        this.keratoconus_form = keratoconus_form;
    }

    public String getAmd_form() {
        return amd_form;
    }

    public void setAmd_form(String amd_form) {
        this.amd_form = amd_form;
    }

    public String getGlaucoma_form() {
        return glaucoma_form;
    }

    public void setGlaucoma_form(String glaucoma_form) {
        this.glaucoma_form = glaucoma_form;
    }

    public String getDiabeticRetinopathy_form() {
        return diabeticRetinopathy_form;
    }

    public void setDiabeticRetinopathy_form(String diabeticRetinopathy_form) {
        this.diabeticRetinopathy_form = diabeticRetinopathy_form;
    }

    public String getAnkylosing_form() {
        return ankylosing_form;
    }

    public void setAnkylosing_form(String ankylosing_form) {
        this.ankylosing_form = ankylosing_form;
    }

    public String getTakayasu_form() {
        return takayasu_form;
    }

    public void setTakayasu_form(String takayasu_form) {
        this.takayasu_form = takayasu_form;
    }

    public String getSle_form() {
        return sle_form;
    }

    public void setSle_form(String sle_form) {
        this.sle_form = sle_form;
    }

    public String getHeart_failure_form() {
        return heart_failure_form;
    }

    public void setHeart_failure_form(String heart_failure_form) {
        this.heart_failure_form = heart_failure_form;
    }

    public String getCopd_form() {
        return copd_form;
    }

    public void setCopd_form(String copd_form) {
        this.copd_form = copd_form;
    }

    public String getAsthma_form() {
        return asthma_form;
    }

    public void setAsthma_form(String asthma_form) {
        this.asthma_form = asthma_form;
    }

    public String getCad_form() {
        return cad_form;
    }

    public void setCad_form(String cad_form) {
        this.cad_form = cad_form;
    }
}
