package com.ostrov.dmvmotorcycletest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import java.util.ArrayList;

public class TopicsActivity extends AppCompatActivity {
    ArrayList<Topic> topics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);

        Intent intent = getIntent();
        Quiz quiz = (Quiz)intent.getSerializableExtra(getString(R.string.intent_quiz));
        if (quiz != null)
            topics = quiz.getTopics();

        RecyclerView rv_topics = findViewById(R.id.rv_topics);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv_topics.setLayoutManager(llm);

        final RVTopicsAdapter adapter = new RVTopicsAdapter(topics, new RVTopicsAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(Topic item) {
                            Intent i = new Intent(TopicsActivity.this, AnswersActivity.class);
                            i.putExtra(getString(R.string.intent_topic), item);
                            startActivity(i);
                        }
                    });
        rv_topics.setAdapter(adapter);
    }
}
