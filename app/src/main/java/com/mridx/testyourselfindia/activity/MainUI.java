package com.mridx.testyourselfindia.activity;

import android.os.Bundle;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.mridx.testyourselfindia.R;
import com.mridx.testyourselfindia.helper.MainUIHelper;

public class MainUI extends AppCompatActivity {

    public TextInputEditText nameField, phoneField, pinField;
    public RadioGroup placeGroup, languageGroup;
    private MainUIHelper helper;
    public CardView proceedBtn;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_ui);

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        helper = new MainUIHelper(this);
        findViews();
    }

    private void findViews() {
        nameField = findViewById(R.id.nameField);
        phoneField = findViewById(R.id.phoneField);
        pinField = findViewById(R.id.pinField);
        placeGroup = findViewById(R.id.placeRadioGroup);
        languageGroup = findViewById(R.id.languageRadioGroup);
        proceedBtn = findViewById(R.id.proceedBtn);
        //proceedBtn.setAlpha(0.3f);
        helper.assignListener();
    }
}
