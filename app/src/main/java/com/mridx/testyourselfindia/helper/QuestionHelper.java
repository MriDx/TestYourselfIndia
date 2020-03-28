package com.mridx.testyourselfindia.helper;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatTextView;

import com.google.android.material.radiobutton.MaterialRadioButton;
import com.mridx.testyourselfindia.R;
import com.mridx.testyourselfindia.activity.CompleteActivity;
import com.mridx.testyourselfindia.activity.QuestionUI;
import com.mridx.testyourselfindia.dataholder.AnswerHolder;
import com.mridx.testyourselfindia.dataholder.Questions;

import java.util.ArrayList;

public class QuestionHelper {

    private Context context;
    private QuestionUI questionUI;
    private AppCompatTextView previousText, nextText, questionText;
    private RadioGroup answerGroup;
    public static ArrayList<AnswerHolder> answerList = new ArrayList<>();
    private AnswerHolder answerHolder;
    //public static int questionPosition = 0;
    private Questions questions;

    public QuestionHelper(Context context) {
        this.context = context;
        questionUI = ((QuestionUI) context);
        questions = new Questions(context);
    }

    public void createView(int i) {
        View questionView = LayoutInflater.from(context).inflate(R.layout.question_view, null);
        questionUI.questionHolder.removeAllViews();
        //questionUI.questionHolder.addView(questionView);
        setupView(questionView, i);
        questionUI.questionHolder.addView(questionView);
    }

    private void setupView(View view, int i) {
        showProgress(i);
        previousText = view.findViewById(R.id.previousText);
        nextText = view.findViewById(R.id.nextText);
        questionText = view.findViewById(R.id.questionView);
        questionText.setText(questions.getQuestions()[i]);
        nextText.setOnClickListener(v -> saveAndNext(i));
        if (i > 0) {
            previousText.setVisibility(View.VISIBLE);
            previousText.setOnClickListener(v -> createView(i - 1));
        } else {
            previousText.setVisibility(View.GONE);
        }
        answerGroup = view.findViewById(R.id.answerGroup);
        if (answerList.size() >= i + 1) {
            if (answerList.get(i) != null && answerList.get(i).getAnswer_id().toLowerCase().equalsIgnoreCase("yes")) {
                ((MaterialRadioButton) answerGroup.getChildAt(0)).setChecked(true);
                return;
            }
            ((MaterialRadioButton) answerGroup.getChildAt(1)).setChecked(true);
        }
        if (i == 5) {
            nextText.setText("Done");
            return;
        }
        nextText.setText("Next");
    }

    private void saveAndNext(int i) {
        if (answerGroup.getCheckedRadioButtonId() == -1) {
            return;
        }
        if (answerList.size() >= i + 1 && answerList.get(i) != null) {
            answerList.remove(i);
        }
        answerHolder = new AnswerHolder(String.valueOf(i), ((MaterialRadioButton) answerGroup.findViewById(answerGroup.getCheckedRadioButtonId())).getText().toString());
        answerList.add(i, answerHolder);

        if (i < 5) {
            createView(i + 1);
        } else {
            showProgress(6);
            ProgressDialog progressDialog = new ProgressDialog(context);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Please wait...");
            progressDialog.show();
            startCompleteActivity(progressDialog);
        }

    }

    private void startCompleteActivity(ProgressDialog progressDialog) {
        new Handler().postDelayed(() -> {
            progressDialog.dismiss();
            Intent intent = new Intent(context, CompleteActivity.class);
            questionUI.startActivity(intent);
            //intent.putExtra("ANSWER_LIST", answerList);
            questionUI.finish();
        }, 1000 * 2);
    }

    public void showProgress(int i) {
        questionUI.questionProgress.setProgress((int) ((Float.parseFloat("100") / 6) * i));
    }

}
