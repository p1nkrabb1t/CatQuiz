package com.example.android.catquiz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivityE1 extends AppCompatActivity {
    String name = "";
    int questions = 0;
    int score = 0;
    int levelPointsAwarded = 1;
    int scoreQuestion1 = 0;
    int scoreQuestion2 = 0;
    int scoreQuestion3 = 0;
    int scoreQuestion4 = 0;
    int scoreQuestion5 = 0;
    String resultsList;
    boolean answered1 = false;
    boolean answered2 = false;
    boolean answered3 = false;
    boolean answered4 = false;
    boolean answered5 = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_e1);
        Bundle bundle = getIntent().getExtras();
        questions = bundle.getInt("questions");
        name = bundle.getString("name");
        displayNumOfQuestions();
        chooseButton();
    }

    /**
     * This method is called when the Next / Submit button is clicked.
     */
    public void submitE_A(View view) {
        String q1 = onRadioButtonClickedE1();
        String q2 = checkEntryE2();
        String q3 = onRadioButtonClickedE3();
        String q4 = getCheckedStateE4();
        String q5 = onRadioButtonClickedE5();
        getScore();
        resultsList = createScoreSummary(q1, q2, q3, q4, q5);
        if (answered1 && answered2 && answered3 && answered4 && answered5) {
            nextPage();
        } else {
            Toast.makeText(this, R.string.toast_answer_all_questions, Toast.LENGTH_LONG).show();
        }
    }


    /**
     * This method allows the player to restart the quiz.
     */
    public void restart(View view) {
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }

    /**
     * This method shows the user how far into the quiz they are.
     */
    public void displayNumOfQuestions() {
        TextView questionsDisplay = findViewById(R.id.questionsQty);
        questionsDisplay.setText(getString(R.string.questions1_5) + questions);
    }

    /**
     * This method changes the button text according to whether there are more questions or not.
     */
    public void chooseButton() {
        Button buttonText = findViewById(R.id.submit);
        if (questions == 5) {
            buttonText.setText(R.string.submit_button);
        } else {
            buttonText.setText(R.string.next_button);
        }
    }

    /**
     * This method works out the total score for this set of questions.
     * method was added as if using score = score + 1, a bug was found where if the user goes back and changes answers, new scores were added on top.
     * this way should only allow max of 5 points to be sent to next activity
     */
    public void getScore() {
        score = scoreQuestion1 + scoreQuestion2 + scoreQuestion3 + scoreQuestion4 + scoreQuestion5;
    }

    /**
     * This method takes the users selection for question 1 (Easy) and returns points for a correct answer
     */
    public String onRadioButtonClickedE1() {
        String q1 = "";
        RadioGroup question1 = findViewById(R.id.question_E1);
        int id = question1.getCheckedRadioButtonId();
        switch (id) {
            case R.id.qE1_1:
                q1 = getString(R.string.incorrect);
                scoreQuestion1 = 0;
                answered1 = true;
                break;
            case R.id.qE1_2:
                scoreQuestion1 = levelPointsAwarded;
                q1 = getString(R.string.correct);
                answered1 = true;
                break;
            case R.id.qE1_3:
                q1 = getString(R.string.incorrect);
                scoreQuestion1 = 0;
                answered1 = true;
                break;
            case R.id.qE1_4:
                q1 = getString(R.string.incorrect);
                scoreQuestion1 = 0;
                answered1 = true;
                break;
        }
        return q1;
    }

    /**
     * This method takes the users number input for question 2 and returns points for a correct answer
     */
    public String checkEntryE2() {
        String q2 = "";
        EditText question2 = findViewById(R.id.question_E2);
        if (question2.getText().toString().equals(null) || question2.getText().toString().equals("")) {
            q2 = "";
            answered2 = false;
            return q2;
        } else {
            int lives = Integer.parseInt(question2.getText().toString());
            if (lives == 9) {
                scoreQuestion2 = levelPointsAwarded;
                q2 = getString(R.string.correct);
                answered2 = true;
            } else {
                q2 = getString(R.string.incorrect);
                scoreQuestion2 = 0;
                answered2 = true;
            }
        }
        return q2;
    }

    /**
     * This method takes the users selection for question 3 (Easy) and returns points for a correct answer
     */
    public String onRadioButtonClickedE3() {
        String q3 = "";
        RadioGroup question3 = findViewById(R.id.question_E3);
        int id = question3.getCheckedRadioButtonId();
        switch (id) {
            case R.id.qE3_1:
                q3 = getString(R.string.incorrect);
                scoreQuestion3 = 0;
                answered3 = true;
                break;
            case R.id.qE3_2:
                q3 = getString(R.string.incorrect);
                scoreQuestion3 = 0;
                answered3 = true;
                break;
            case R.id.qE3_3:
                scoreQuestion3 = levelPointsAwarded;
                q3 = getString(R.string.correct);
                answered3 = true;
                break;
        }
        return q3;
    }

    /**
     * This method checks first that at least one box is checked for question 4 (Easy)
     * takes multiple user selections and returns points only if ALL of the correct answers are selected
     */
    private String getCheckedStateE4() {
        String q4 = "";
        CheckBox q41 = findViewById(R.id.qE4_1);
        CheckBox q42 = findViewById(R.id.qE4_2);
        CheckBox q43 = findViewById(R.id.qE4_3);
        CheckBox q44 = findViewById(R.id.qE4_4);
        CheckBox q45 = findViewById(R.id.qE4_5);
        boolean ans4_1 = q41.isChecked();
        boolean ans4_2 = q42.isChecked();
        boolean ans4_3 = q43.isChecked();
        boolean ans4_4 = q44.isChecked();
        boolean ans4_5 = q45.isChecked();

        if (!ans4_1 && !ans4_2 && !ans4_3 && !ans4_4 && !ans4_5) {
            answered4 = false;
            return q4;
        }
        if (ans4_1 && ans4_2 && !ans4_3 && ans4_4 && !ans4_5) {
            scoreQuestion4 = levelPointsAwarded;
            q4 = getString(R.string.correct);
            answered4 = true;
        } else {
            q4 = getString(R.string.incorrect);
            scoreQuestion4 = 0;
            answered4 = true;
        }
        return q4;
    }

    /**
     * This method takes the users selection for question 5 (Easy) and returns points for a correct answer
     */
    public String onRadioButtonClickedE5() {
        String q5 = "";
        RadioGroup question5 = findViewById(R.id.question_E5);
        int id = question5.getCheckedRadioButtonId();
        switch (id) {
            case R.id.qE5_1:
                scoreQuestion5 = levelPointsAwarded;
                q5 = getString(R.string.correct);
                answered5 = true;
                break;
            case R.id.qE5_2:
                q5 = getString(R.string.incorrect);
                scoreQuestion5 = 0;
                answered5 = true;
                break;
        }
        return q5;
    }


    /**
     * This method decides if to take the user to another set of questions or end the quiz
     */
    public void nextPage() {
        if (questions == 5) {
            Intent intent = new Intent(this, ScoreActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("score", score);
            intent.putExtra("questions", questions);
            intent.putExtra("resultsList", resultsList);
            Toast.makeText(this, "You have scored " + score + " out of " + questions, Toast.LENGTH_LONG).show();
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, MainActivityE2.class);
            intent.putExtra("name", name);
            intent.putExtra("score", score);
            intent.putExtra("questions", questions);
            intent.putExtra("resultsList", resultsList);
            startActivity(intent);
        }
    }


    /**
     * This method creates a summary of which questions were answered right or wrong
     */
    private String createScoreSummary(String q1, String q2, String q3, String q4, String q5) {
        resultsList = getString(R.string.results_message);
        resultsList += "\n" + getString(R.string.results_Q1) + q1;
        resultsList += "\n" + getString(R.string.results_Q2) + q2;
        resultsList += "\n" + getString(R.string.results_Q3) + q3;
        resultsList += "\n" + getString(R.string.results_Q4) + q4;
        resultsList += "\n" + getString(R.string.results_Q5) + q5;
        return resultsList;
    }


}

