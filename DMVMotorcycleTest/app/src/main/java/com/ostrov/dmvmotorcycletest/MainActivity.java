package com.ostrov.dmvmotorcycletest;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;

import com.fasterxml.jackson.databind.ObjectMapper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView textViewResult;
    Quiz quiz;
    RecyclerView rv;
    LinearLayoutManager llm;
    RVAdapter adapter;
    ArrayList<Question> questions;
    int countQuestions = 0;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        textViewResult = findViewById(R.id.textViewResult);
        rv = findViewById(R.id.rv);
        llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        try {
            String json = loadJSONFromAsset();
            ObjectMapper objectMapper = new ObjectMapper();
            quiz = objectMapper.readValue(json, Quiz.class);
            countQuestions = quiz.getCountOfTopics();

            initializeAdapter();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Load data from json file */
    private String loadJSONFromAsset() {
        String json = null;
        try {
            String FILE = getString(R.string.file);
            InputStream is = this.getAssets().open(FILE);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, getString(R.string.standard_charsets));
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    /** Initialize adapter for test */
    private void initializeAdapter() {
        questions = generateListOfQuestions(quiz);
        adapter = new RVAdapter(questions, this);
        rv.setAdapter(adapter);
    }

    /** Create new list of questions */
    private ArrayList<Question> generateListOfQuestions(Quiz quiz) {
        ArrayList<Question> newQuestions = new ArrayList<>();
        if (quiz != null && quiz.getCountOfTopics() >= 10)
            for (int i = 0; i < countQuestions; i++) {
                int qCount = quiz.getTopics().get(i).getCountOfQuestions();
                int qRandom = (new Random()).nextInt(qCount);
                newQuestions.add(quiz.getTopics().get(i).getQuestions().get(qRandom));
            }
        return  newQuestions;
    }

    /** Check your answers */
    public void onClickCheckAnswers(View view) {
        if (!isSetAllAnswers())
            return;

        new AlertDialog.Builder(this)
                .setMessage(R.string.question_check)
                .setPositiveButton(R.string.submit, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        llm.scrollToPosition(0);
                        adapter.setIsChecked();
                        gradeAnswers();
                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .show();
    }

    /** Check what you answer on all questions */
    private boolean isSetAllAnswers() {
        for (Question q : questions)
            if (q.getSelectedAnswer() == 0) {
                new AlertDialog.Builder(this)
                        .setMessage(R.string.alert)
                        .setPositiveButton(R.string.ok, null)
                        .show();
                return false;
            }
        return true;
    }

    /** Grade your answers */
    private void gradeAnswers() {
        int countCorrectAnswers = 0;
        for (Question q : questions)
            if (q.getCorrectAnswerNumber() == q.getSelectedAnswer())
                countCorrectAnswers++;

        String textResult = "";

        if (countCorrectAnswers >= countQuestions * 0.8) {
            textResult = getString(R.string.pass);
            textViewResult.setBackgroundColor(getColor(R.color.pass));
        }
        else {
            textResult = getString(R.string.fail);
            textViewResult.setBackgroundColor(getColor(R.color.fail));
        }
        textResult = String.format("%s: %d/%d", textResult, countCorrectAnswers, countQuestions);
        textViewResult.setText(textResult);
        textViewResult.setVisibility(View.VISIBLE);
    }

    /** Start new test */
    public void onClickNewTest(MenuItem item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.question_start);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                textViewResult.setText("");
                textViewResult.setVisibility(View.GONE);
                for (Question q : questions)
                    q.clearSelectedAnswer();
                initializeAdapter();
            }
        });
        builder.setNegativeButton(R.string.cancel, null);
        builder.create().show();
    }
}
