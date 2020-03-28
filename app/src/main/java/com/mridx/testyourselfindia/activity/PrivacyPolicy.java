package com.mridx.testyourselfindia.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.mridx.testyourselfindia.R;


public class PrivacyPolicy extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.privacy_policy);

        CardView acceptBtn = findViewById(R.id.acceptBtn);
        acceptBtn.setOnClickListener(v -> setResultandFinish());

    }

    private void setResultandFinish() {
        Intent intent = new Intent();
        intent.putExtra("accepted", true);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
