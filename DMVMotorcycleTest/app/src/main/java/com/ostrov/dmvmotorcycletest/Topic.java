package com.ostrov.dmvmotorcycletest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.ArrayList;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "title",
        "questions"
})
public class Topic implements Serializable {
    @JsonProperty("title")
    private String title;
    @JsonProperty("questions")
    private ArrayList<Question> questions;

    /**  Constructor */
    Topic() {
        questions = new ArrayList<>();
    }

    /** Get text of topic */
    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    /**  Set text of topic */
    @JsonProperty("title")
    void setTitle(String title) {
        this.title = title;
    }

    /** * Get list of questions */
    @JsonProperty("questions")
    public ArrayList<Question> getQuestions() {
        return questions;
    }

    /** Add questions */
    @JsonProperty("questions")
    void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    /** Get questions count */
    @JsonIgnore
    public int getCountOfQuestions() {
        return questions.size();
    }
}
