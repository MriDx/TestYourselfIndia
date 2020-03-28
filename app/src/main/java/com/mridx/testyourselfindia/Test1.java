package com.mridx.testyourselfindia;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.app.ActivityOptionsCompat;

public class Test1 extends AppCompatActivity {

    private AppCompatImageView mainInfoLogo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_ui);

        mainInfoLogo = findViewById(R.id.mainInfoLogo);
        mainInfoLogo.setOnClickListener(v -> {
            startNext();
        });
    }

    private void startNext() {
        Intent i = new Intent(this, Test2.class);
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, mainInfoLogo, "mainLogo");
        startActivity(i, optionsCompat.toBundle());
    }
}
