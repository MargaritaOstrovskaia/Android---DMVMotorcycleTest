package com.ostrov.dmvmotorcycletest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;

public class QuestionsActivity extends AppCompatActivity {
    ArrayList<Topic> topics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        Intent intent = getIntent();
        String json = intent.getStringExtra(getString(R.string.json));
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Quiz quiz = objectMapper.readValue(json, Quiz.class);
            topics = quiz.getTopics();

            RecyclerView rv_topics = findViewById(R.id.rv_topics);
            LinearLayoutManager llm = new LinearLayoutManager(this);
            rv_topics.setLayoutManager(llm);

            final RVTopicsAdapter adapter = new RVTopicsAdapter(topics, new RVTopicsAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(Topic item) {
                                //add code
                            }
                        });
            rv_topics.setAdapter(adapter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
