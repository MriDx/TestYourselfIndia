package com.mridx.testyourselfindia.helper;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;

import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mridx.testyourselfindia.R;
import com.mridx.testyourselfindia.activity.CompleteActivity;
import com.mridx.testyourselfindia.dataholder.AnswerHolder;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.mridx.testyourselfindia.helper.MainUIHelper.patientDetails;

public class CompleteActivityHelper {

    private Context context;
    private CompleteActivity completeActivity;
    private ArrayList<AnswerHolder> answerList = new ArrayList<>();
    private FirebaseFirestore firestore;

    public CompleteActivityHelper(Context context) {
        this.context = context;
        completeActivity = ((CompleteActivity)context);
        firestore = FirebaseFirestore.getInstance();
    }


    public void start(ArrayList<AnswerHolder> answerList) {
        this.answerList = answerList;
        Log.d("mridx", "start: " + answerList.size());
        completeActivity.mainReport.setText(generateReport());
        uploadData();
        //answerList.clear();
    }

    private void uploadData() {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setCancelable(false);
        alertDialog.setMessage(completeActivity.getString(R.string.uploadMessage));
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, completeActivity.getString(R.string.yes), (dialog, which) -> {
            uploadData(true);
            alertDialog.dismiss();
        });
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, completeActivity.getString(R.string.no), (dialog, which) -> alertDialog.dismiss());
        alertDialog.show();
    }

    private void uploadData(boolean b) {
        if (b) {
            generateAndSave();
        }
    }

    private void generateAndSave() {
        firestore.collection("data").add(generateData());
        answerList.clear();
    }

    private Map<String, Object> generateData() {
        Map<String, Object> data = new HashMap<>();
        data.put("name", patientDetails.getPatient_name());
        data.put("phone", patientDetails.getPatient_phone());
        data.put("pin", patientDetails.getPatient_pin());
        data.put("place", patientDetails.getPatient_place());
        data.put("language", patientDetails.getPatient_language());
        data.put("entry", FieldValue.serverTimestamp());
        data.put("entryTime", new Date().getTime());

        //answer data
        data.put("answers", getAnswers());

        return data;
    }

    private ArrayList<Object> getAnswers() {
        ArrayList<Object> answerArray = new ArrayList<>();
        for (int i = 0; i < answerList.size(); i++) {
            Map<String, Object> answers = new HashMap<>();
            answers.put("question_id", answerList.get(i).getQ_id());
            answers.put("answer", answerList.get(i).getAnswer_id());
            answerArray.add(answers);
        }

        return answerArray;
    }

    private String generateReport() {
        if (answerList.get(0).getAnswer_id().toLowerCase().equalsIgnoreCase("yes") || answerList.get(1).getAnswer_id().toLowerCase().equalsIgnoreCase("yes")) {
            if (answerList.get(2).getAnswer_id().toLowerCase().equalsIgnoreCase("yes") || answerList.get(3).getAnswer_id().equalsIgnoreCase("yes")) {
                return completeActivity.getString(R.string.positive);
            }
        } else if (answerList.get(3).getAnswer_id().toLowerCase().equalsIgnoreCase("yes") || answerList.get(4).getAnswer_id().equalsIgnoreCase("yes")) {
            return completeActivity.getString(R.string.natural);
        }
        return completeActivity.getString(R.string.natural);
    }

}
