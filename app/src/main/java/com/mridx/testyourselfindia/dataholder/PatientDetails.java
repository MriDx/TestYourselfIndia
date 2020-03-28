package com.mridx.testyourselfindia.dataholder;

import android.content.Context;

import java.io.Serializable;

public class PatientDetails implements Serializable {

    private String patient_name, patient_phone, patient_pin, patient_place, patient_language;

    public PatientDetails(String patient_name, String patient_phone, String patient_pin, String patient_place, String patient_language) {
        this.patient_name = patient_name;
        this.patient_phone = patient_phone;
        this.patient_pin = patient_pin;
        this.patient_place = patient_place;
        this.patient_language = patient_language;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public String getPatient_phone() {
        return patient_phone;
    }

    public String getPatient_pin() {
        return patient_pin;
    }

    public String getPatient_place() {
        return patient_place;
    }

    public String getPatient_language() {
        return patient_language;
    }
}
