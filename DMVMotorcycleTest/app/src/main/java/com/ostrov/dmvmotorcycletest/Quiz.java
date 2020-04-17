package com.ostrov.dmvmotorcycletest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "title",
        "topics"
})
public class Quiz {
    @JsonProperty("title")
    private String title;
    @JsonProperty("topics")
    private ArrayList<Topic> topics;

    /**  Constructor */
    Quiz() {
        topics = new ArrayList<>();
    }

    /** Get quiz title */
    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    /** Set quiz title */
    @JsonProperty("title")
    void setTitle(String title) {
        this.title = title;
    }

    /** Get list of topics */
    @JsonProperty("topics")
    public ArrayList<Topic> getTopics() {
        return topics;
    }

    /** Add topics */
    @JsonProperty("topics")
    void setTopics(ArrayList<Topic> topics) {
        this.topics = topics;
    }

    /** Get topics count */
    @JsonIgnore
    public int getCountOfTopics() {
        return topics.size();
    }
}
