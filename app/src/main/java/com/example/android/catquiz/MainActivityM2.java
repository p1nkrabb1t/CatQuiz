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


public class MainActivityM2 extends AppCompatActivity {
    String name;
    int questions;
    int score = 0;
    int levelPointsAwarded = 1;
    int scoreQuestion6 = 0;
    int scoreQuestion7 = 0;
    int scoreQuestion8 = 0;
    int scoreQuestion9 = 0;
    int scoreQuestion10 = 0;
    String resultsList;
    boolean answered6 = false;
    boolean answered7 = false;
    boolean answered8 = false;
    boolean answered9 = false;
    boolean answered10 = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_m2);
        Bundle bundle = getIntent().getExtras();
        questions = bundle.getInt("questions");
        name = bundle.getString("name");
        displayNumOfQuestions();
        chooseButton();
    }

    /**
     * This method is called when the Next / Submit button is clicked.
     */
    public void submit(View view) {
        String q6 = onRadioButtonClickedM6();
        String q7 = checkEntryM7();
        String q8 = onRadioButtonClickedM8();
        String q9 = getCheckedStateM9();
        String q10 = onRadioButtonClickedM10();
        getScore();
        resultsList = createScoreSummary(q6, q7, q8, q9, q10);
        if (answered6 && answered7 && answered8 && answered9 && answered10) {
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
        questionsDisplay.setText(getString(R.string.questions6_10) + questions);
    }

    /**
     * This method changes the button text according to whether there are more questions or not.
     */
    public void chooseButton() {
        Button buttonText = findViewById(R.id.submit);
        if (questions == 10) {
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
        Bundle bundle = getIntent().getExtras();
        score = bundle.getInt("score") + scoreQuestion6 + scoreQuestion7 + scoreQuestion8 + scoreQuestion9 + scoreQuestion10;
    }

    /**
     * This method takes the users selection for question 6 (Medium) and returns points for a correct answer
     */
    public String onRadioButtonClickedM6() {
        String q6 = "";
        RadioGroup questionM6 = findViewById(R.id.question_M6);
        int id = questionM6.getCheckedRadioButtonId();
        switch (id) {
            case R.id.qM6_1:
                q6 = getString(R.string.incorrect);
                scoreQuestion6 = 0;
                answered6 = true;
                break;
            case R.id.qM6_2:
                scoreQuestion6 = levelPointsAwarded;
                q6 = getString(R.string.correct);
                answered6 = true;
                break;
            case R.id.qM6_3:
                q6 = getString(R.string.incorrect);
                scoreQuestion6 = 0;
                answered6 = true;
                break;
            case R.id.qM6_4:
                q6 = getString(R.string.incorrect);
                scoreQuestion6 = 0;
                answered6 = true;
                break;
        }
        return q6;
    }

    /**
     * This method takes the users number input for question 7 and returns points for a correct answer
     */
    public String checkEntryM7() {
        String q7 = "";
        EditText question7 = findViewById(R.id.question_M7);
        if (question7.getText().toString().equals(null) || question7.getText().toString().equals("")) {
            q7 = "";
            return q7;
        } else {
            String answer = question7.getText().toString();
            if (answer.equals("Manx") || answer.equals("Manx Cat")
                    || answer.equals("Cymric") || answer.equals("Cymric Cat")
                    || answer.equals("Japanese Bobtail") || answer.equals("American Bobtail")
                    || answer.equals("Kurilian Bobtail")|| answer.equals("Pixie Bob")
                    || answer.equals("Highlander")|| answer.equals("Highlander Lynx")){
                scoreQuestion7 = levelPointsAwarded;
                q7 = getString(R.string.correct);
                answered7 = true;
            } else {
                q7 = getString(R.string.incorrect);
                scoreQuestion7 = 0;
                answered7 = true;
            }
        }
        return q7;
    }

    /**
     * This method takes the users selection for question 8 (Medium) and returns points for a correct answer
     */
    public String onRadioButtonClickedM8() {
        String q8 = "";
        RadioGroup questionM8 = findViewById(R.id.question_M8);
        int id = questionM8.getCheckedRadioButtonId();
        switch (id) {
            case R.id.qM8_1:
                q8 = getString(R.string.incorrect);
                scoreQuestion8 = 0;
                answered8 = true;
                break;
            case R.id.qM8_2:
                q8 = getString(R.string.incorrect);
                scoreQuestion8 = 0;
                answered8 = true;
                break;
            case R.id.qM8_3:
                scoreQuestion8 = levelPointsAwarded;
                q8 = getString(R.string.correct);
                answered8 = true;
                break;
            case R.id.qM8_4:
                q8 = getString(R.string.incorrect);
                scoreQuestion8 = 0;
                answered8 = true;
                break;
        }
        return q8;
    }

    /**
     * This method checks first that at least one box is checked for question 9 (Medium)
     * takes multiple user selections and returns points only if ALL of the correct answers are selected
     */
    private String getCheckedStateM9() {
        String q9 = "";
        CheckBox q91 = findViewById(R.id.qM9_1);
        CheckBox q92 = findViewById(R.id.qM9_2);
        CheckBox q93 = findViewById(R.id.qM9_3);
        CheckBox q94 = findViewById(R.id.qM9_4);
        CheckBox q95 = findViewById(R.id.qM9_5);
        boolean ans_1 = q91.isChecked();
        boolean ans_2 = q92.isChecked();
        boolean ans_3 = q93.isChecked();
        boolean ans_4 = q94.isChecked();
        boolean ans_5 = q95.isChecked();

        if (!ans_1 && !ans_2 && !ans_3 && !ans_4 && !ans_5) {
            return q9;
        }
        if (ans_1 && ans_2 && !ans_3 && ans_4 && !ans_5) {
            scoreQuestion9 = levelPointsAwarded;
            q9 = getString(R.string.correct);
            answered9 = true;
        } else {
            q9 = getString(R.string.incorrect);
            scoreQuestion9 = 0;
            answered9 = true;
        }
        return q9;
    }

    /**
     * This method takes the users selection for question 10 (Medium) and returns points for a correct answer
     */
    public String onRadioButtonClickedM10() {
        String q10 = "";
        RadioGroup questionE5 = findViewById(R.id.question_M10);
        int id = questionE5.getCheckedRadioButtonId();
        switch (id) {
            case R.id.qM10_1:
                scoreQuestion10 = levelPointsAwarded;
                q10 = getString(R.string.correct);
                answered10 = true;
                break;
            case R.id.qM10_2:
                q10 = getString(R.string.incorrect);
                scoreQuestion10 = 0;
                answered10 = true;
                break;
        }
        return q10;
    }


    /**
     * This method decides if to take the user to another set of questions or end the quiz
     */
    public void nextPage() {
        if (questions == 10) {
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
            startActivity(intent);
        }
    }


    /**
     * This method creates a summary of which questions were answered right or wrong
     */
    private String createScoreSummary(String q6, String q7, String q8, String q9, String q10) {
        Bundle bundle = getIntent().getExtras();
        resultsList = bundle.getString("resultsList");

        resultsList += "\n" + getString(R.string.results_Q6) + q6;
        resultsList += "\n" + getString(R.string.results_Q7) + q7;
        resultsList += "\n" + getString(R.string.results_Q8) + q8;
        resultsList += "\n" + getString(R.string.results_Q9) + q9;
        resultsList += "\n" + getString(R.string.results_Q10) + q10;
        return resultsList;

    }


}