package com.mridx.testyourselfindia.helper;

import android.content.Context;
import android.util.Log;

import com.mridx.testyourselfindia.activity.CompleteActivity;
import com.mridx.testyourselfindia.dataholder.AnswerHolder;

import java.util.ArrayList;

public class CompleteActivityHelper {

    private Context context;
    private CompleteActivity completeActivity;
    private ArrayList<AnswerHolder> answerList = new ArrayList<>();

    public CompleteActivityHelper(Context context) {
        this.context = context;
        completeActivity = ((CompleteActivity)context);
    }


    public void start(ArrayList<AnswerHolder> answerList) {
        this.answerList = answerList;
        Log.d("mridx", "start: " + answerList.size());
        completeActivity.mainReport.setText(generateReport());
        answerList.clear();
    }

    private String generateReport() {
        if (answerList.get(0).getAnswer_id().toLowerCase().equalsIgnoreCase("yes") || answerList.get(1).getAnswer_id().toLowerCase().equalsIgnoreCase("yes")) {
            if (answerList.get(3).getAnswer_id().toLowerCase().equalsIgnoreCase("yes") || answerList.get(4).getAnswer_id().equalsIgnoreCase("yes")) {
                return "You might have (Or Not) symptoms of corona virus as you have travelled recently or met people having COVID-19 symptoms and currently having cough or fever. Kindly contact nearest healthcare facility";
            }
        } else if (answerList.get(3).getAnswer_id().toLowerCase().equalsIgnoreCase("yes") || answerList.get(4).getAnswer_id().equalsIgnoreCase("yes")) {
            return "You are not likely suspect of COVID-19. Don't panic. Stay hygiene";
        }
        return "You are not likely suspect of COVID-19. Don't panic. Stay hygiene";
    }

}
