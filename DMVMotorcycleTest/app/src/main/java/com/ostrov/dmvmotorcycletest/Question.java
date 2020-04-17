package com.ostrov.dmvmotorcycletest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "question",
        "answers",
        "correctAnswer",
        "imageName"
})
public class Question {
    @JsonProperty("question")
    private String question;
    @JsonProperty("answers")
    private ArrayList<String> answers;
    @JsonProperty("correctAnswer")
    private int correctAnswer;
    @JsonProperty("imageName")
    private String imageName;

    @JsonIgnore
    private int selectedAnswer;

    /** Get selected number of answers */
    @JsonIgnore
    public int getSelectedAnswer() {
        return selectedAnswer;
    }

    /** Set selected number of answers */
    @JsonIgnore
    public void setSelectedAnswer(int selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

    /** Empty number of answers */
    @JsonIgnore
    public void clearSelectedAnswer() {
        this.selectedAnswer = 0;
    }

    /**  Constructor */
    Question() {
        correctAnswer = 0;
        selectedAnswer = 0;
        answers = new ArrayList<>();
    }

    /** Set text of question */
    @JsonProperty("question")
    void setQuestion(String question) {
        this.question = question;
    }

    /** Get text of question */
    @JsonProperty("question")
    public String getQuestion() {
        return question;
    }

    /** Add answers */
    @JsonProperty("answers")
    void setAnswers(ArrayList<String> answers) {
        this.answers = answers;
    }

    /** Get all possible answers */
    @JsonProperty("answers")
    ArrayList<String> getAnswers() {
        return answers;
    }

    /** Add number of correct answer */
    @JsonProperty("correctAnswer")
    void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    /** Get number of correct answer */
    @JsonProperty("correctAnswer")
    int getCorrectAnswerNumber() {
        return correctAnswer;
    }

    /** Get image name */
    @JsonProperty("imageName")
    public String getImageName() {
        return imageName;
    }

    /** Add image name */
    @JsonProperty("imageName")
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    /** Get answers count */
    @JsonIgnore
    int getCountOfAnswers() {
        return answers.size();
    }

    /** Get text of correct answer */
    @JsonIgnore
    String getCorrectAnswer() {
        return answers.get(correctAnswer - 1);
    }
}
