package com.example.android.catquiz;

import android.content.Intent;
import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    String name;
    int score;
    int questions;
    int level;
    String difficulty;
    String resultsList;
    int percentage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("KEY_NAME");
        score = bundle.getInt("KEY_SCORE");
        level = bundle.getInt("KEY_LEVEL");
        questions = bundle.getInt("KEY_NUM_QUESTIONS");
        resultsList = bundle.getString("KEY_RESULTS");
        TextView nameTextView = findViewById(R.id.nameTextview);
        nameTextView.setText(name);
        percentageCorrect();
        displayPraise();
        displayNumOfCorrectQuestions();
        displayDifficulty();
        displayResultsSummary();

    }

    /**
     * This method displays how many of the total questions were answered right or wrong
     */
    public void displayNumOfCorrectQuestions() {
        TextView questionsCorrect = findViewById(R.id.numOfCorrectAnswers);
        questionsCorrect.setText(getString(R.string.correct_answers, score, questions));
    }

    /**
     * This method displays the difficulty level on which the user played
     */
    public void displayDifficulty() {
        if (level == 1) {
            difficulty = getString(R.string.level_1);
        }
        if (level == 2) {
            difficulty = getString(R.string.level_2);
        }
        if (level == 3) {
            difficulty = getString(R.string.level_3);
        }
        TextView difficultyLevel = findViewById(R.id.difficulty);
        difficultyLevel.setText(getString(R.string.display_level, difficulty));
    }

    /**
     * This method changes the top message based on the percentage answered correctly
     */
    public void displayPraise() {
        TextView display = findViewById(R.id.judgement);
        if (percentage == 100) {
            display.setText(R.string.result_banner5);
        } else if (percentage >= 80) {
            display.setText(R.string.result_banner4);
        } else if (percentage < 30) {
            display.setText(R.string.result_banner1);
        } else if (percentage < 60) {
            display.setText(R.string.result_banner2);
        } else {
            display.setText(R.string.result_banner3);
        }
    }

    /**
     * This method shows the percentage of correct answers
     */
    public void percentageCorrect() {
        float pScore = score;
        float pQuestions = questions;
        percentage = (int) ((pScore / pQuestions) * 100.0f);
        TextView questionsPercentage = findViewById(R.id.scorePercentage);
        questionsPercentage.setText(getString(R.string.percent, percentage, "%"));
    }

    /**
     * This method shows a summary of question results
     */
    public void displayResultsSummary() {
        TextView results = findViewById(R.id.resultsSummary);
        results.setText(resultsList);
    }

    /**
     * This method allows the player to send their score to a friend
     */
    public void shareScore(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_title) + name);
        intent.putExtra(Intent.EXTRA_TEXT, name + getString(R.string.email_score_message,
                score, questions, percentage, "%", difficulty) + "\n \n" + resultsList);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * This method allows the player to restart the quiz.
     */
    public void restart(View view) {
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }
}