package com.mridx.testyourselfindia.dataholder;


import java.io.Serializable;

public class AnswerHolder implements Serializable {

    private String q_id, answer_id;

    public AnswerHolder(String q_id, String answer_id) {
        this.q_id = q_id;
        this.answer_id = answer_id;
    }

    public String getQ_id() {
        return q_id;
    }

    public String getAnswer_id() {
        return answer_id;
    }
}
