package com.mridx.testyourselfindia.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import com.mridx.testyourselfindia.R;
import com.mridx.testyourselfindia.dataholder.AnswerHolder;
import com.mridx.testyourselfindia.helper.CompleteActivityHelper;

import java.util.ArrayList;

import static com.mridx.testyourselfindia.helper.QuestionHelper.answerList;

public class CompleteActivity extends AppCompatActivity {

    private CompleteActivityHelper helper;
    public AppCompatTextView mainReport;
    //private ArrayList<AnswerHolder> answerList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complete_activity);


        findViews();
        helper = new CompleteActivityHelper(this);
        helper.start(answerList);

    }

    private void findViews() {
        mainReport = findViewById(R.id.mainReport);
    }
}
