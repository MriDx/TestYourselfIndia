package com.mridx.testyourselfindia.dataholder;

import android.content.Context;

import com.mridx.testyourselfindia.R;

public class Questions {

    private Context context;

    public Questions(Context context) {
        this.context = context;
    }

    public String[] getQuestions() {
        return context.getResources().getStringArray(R.array.questions);
    }
}
