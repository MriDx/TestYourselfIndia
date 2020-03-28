package com.mridx.testyourselfindia.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.core.widget.ContentLoadingProgressBar;

import com.mridx.testyourselfindia.R;
import com.mridx.testyourselfindia.dataholder.PatientDetails;
import com.mridx.testyourselfindia.helper.QuestionHelper;

import java.util.Locale;

public class QuestionUI extends AppCompatActivity {

    public CardView questionHolder;
    private QuestionHelper helper;
    public ContentLoadingProgressBar questionProgress;
    public int language_code = 0;
    private PatientDetails patientDetails;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_ui);

        if (getIntent().getExtras() != null) {
            patientDetails = (PatientDetails) getIntent().getExtras().getSerializable("PATIENT_DETAILS");
        }


        findViews();
        helper = new QuestionHelper(this);
        helper.createView(0);
    }

    private void findViews() {
        questionHolder = findViewById(R.id.questionHolder);
        questionProgress = findViewById(R.id.questionProgress);
        ((AppCompatTextView) findViewById(R.id.patientNameView)).setText(patientDetails.getPatient_name());
    }
}
