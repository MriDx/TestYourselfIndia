package com.mridx.testyourselfindia.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;

import com.mridx.testyourselfindia.R;
import com.mridx.testyourselfindia.Test1;

public class SplashUI extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_ui);

        CardView splashStartBtn = findViewById(R.id.splashStartBtn);
        splashStartBtn.setOnClickListener(view -> startMain());
        AppCompatTextView privacyPolicy = findViewById(R.id.privacyPolicy);
        privacyPolicy.setText(Html.fromHtml(getString(R.string.htmlString)));
        privacyPolicy.setLinksClickable(true);
    }

    private void startMain() {
        startActivity(new Intent(this, MainUI.class));
        //finish();
    }

    public void openPrivacyPolicy(View v) {
        Intent intent = new Intent(this, PrivacyPolicy.class);
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (resultCode == Activity.RESULT_OK) {
                startMain();
            }
        }
    }
}
