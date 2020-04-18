package com.ostrov.dmvmotorcycletest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class AnswersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answers);

        Intent intent = getIntent();
        Topic topic = (Topic) intent.getSerializableExtra(getString(R.string.intent_topic));

        RecyclerView rv = findViewById(R.id.rv_answers);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        if (topic != null) {
            RVQuestionsAdapter adapter = new RVQuestionsAdapter(topic.getQuestions(), this, false);
            adapter.setIsChecked();
            rv.setAdapter(adapter);
        }
    }
}
