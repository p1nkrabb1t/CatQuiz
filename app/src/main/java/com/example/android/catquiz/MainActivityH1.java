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


public class MainActivityH1 extends AppCompatActivity {
    String name = "";
    int questions = 0;
    int score = 0;
    int level;
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
        setContentView(R.layout.activity_main_h1);
        Bundle bundle = getIntent().getExtras();
        questions = bundle.getInt("KEY_NUM_QUESTIONS");
        name = bundle.getString("KEY_NAME");
        level = bundle.getInt("KEY_LEVEL");
        displayNumOfQuestions();
        chooseButton();
    }

    /**
     * This method is called when the Next / Submit button is clicked.
     */
    public void submit(View view) {
        String q1 = getCheckedStateH1();
        String q2 = onRadioButtonClickedH2();
        String q3 = onRadioButtonClickedH3();
        String q4 = checkEntryH4();
        String q5 = onRadioButtonClickedH5();

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
        TextView questionsDisplay = findViewById(R.id.tv_questions_qty_message);
        questionsDisplay.setText(getString(R.string.questions1_5, questions));
    }

    /**
     * This method changes the button text according to whether there are more questions or not.
     */
    public void chooseButton() {
        Button buttonText = findViewById(R.id.btn_submit);
        if (questions == 5) {
            buttonText.setText(R.string.submit_button);
        } else {
            buttonText.setText(R.string.next_button);
        }
    }

    /**
     * This method works out the total score for this set of questions.
     * method was added as if using score = score + 1, a bug was found where if the user goes back and changes answers, new scores were added on top.
     * this way the app should only allow max of 5 points to be sent to the next activity
     */
    public void getScore() {
        score = scoreQuestion1 + scoreQuestion2 + scoreQuestion3 + scoreQuestion4 + scoreQuestion5;
    }


    /**
     * This method checks first that at least one box is checked for question 1
     * takes multiple user selections and returns points only if ALL of the correct answers are selected
     */
    private String getCheckedStateH1() {
        String q1 = "";
        CheckBox q11 = findViewById(R.id.cb_qH1_option_1);
        CheckBox q12 = findViewById(R.id.cb_qH1_option_2);
        CheckBox q13 = findViewById(R.id.cb_qH1_option_3);
        CheckBox q14 = findViewById(R.id.cb_qH1_option_4);
        CheckBox q15 = findViewById(R.id.cb_qH1_option_5);
        CheckBox q16 = findViewById(R.id.cb_qH1_option_6);
        boolean ans1_1 = q11.isChecked();
        boolean ans1_2 = q12.isChecked();
        boolean ans1_3 = q13.isChecked();
        boolean ans1_4 = q14.isChecked();
        boolean ans1_5 = q15.isChecked();
        boolean ans1_6 = q16.isChecked();

        if (!ans1_1 && !ans1_2 && !ans1_3 && !ans1_4 && !ans1_5 && !ans1_6) {
            answered1 = false;
            return q1;
        }
        if (!ans1_1 && ans1_2 && ans1_3 && !ans1_4 && ans1_5 && !ans1_6) {
            scoreQuestion1 = levelPointsAwarded;
            q1 = getString(R.string.correct);
            answered1 = true;
        } else {
            q1 = getString(R.string.incorrect);
            scoreQuestion1 = 0;
            answered1 = true;
        }
        return q1;
    }

    /**
     * This method takes the users selection for question 2 and returns points for a correct answer
     */
    public String onRadioButtonClickedH2() {
        String q2 = "";
        RadioGroup question2 = findViewById(R.id.rg_question_H2);
        int id = question2.getCheckedRadioButtonId();
        switch (id) {
            case R.id.rb_qH2_option_1:
                q2 = getString(R.string.correct);
                scoreQuestion2 = levelPointsAwarded;
                answered2 = true;
                break;
            case R.id.rb_qH2_option_2:
                q2 = getString(R.string.incorrect);
                scoreQuestion2 = 0;
                answered2 = true;
                break;
        }
        return q2;
    }

    /**
     * This method takes the users selection for question 3 and returns points for a correct answer
     */
    public String onRadioButtonClickedH3() {
        String q3 = "";
        RadioGroup question3 = findViewById(R.id.rg_question_H3);
        int id = question3.getCheckedRadioButtonId();
        switch (id) {
            case R.id.rb_qH3_option_1:
                q3 = getString(R.string.incorrect);
                scoreQuestion3 = 0;
                answered3 = true;
                break;
            case R.id.rb_qH3_option_2:
                q3 = getString(R.string.incorrect);
                scoreQuestion3 = 0;
                answered3 = true;
                break;
            case R.id.rb_qH3_option_3:
                q3 = getString(R.string.incorrect);
                scoreQuestion3 = 0;
                answered3 = true;
                break;
            case R.id.rb_qH3_option_4:
                q3 = getString(R.string.incorrect);
                scoreQuestion3 = 0;
                answered3 = true;
                break;
            case R.id.rb_qH3_option_5:
                q3 = getString(R.string.correct);
                scoreQuestion3 = levelPointsAwarded;
                answered3 = true;
                break;
            case R.id.rb_qH3_option_6:
                q3 = getString(R.string.incorrect);
                scoreQuestion3 = 0;
                answered3 = true;
                break;
        }
        return q3;
    }

    /**
     * This method takes the users number input for question 4 and returns points for a correct answer
     */
    public String checkEntryH4() {
        String q4 = "";
        EditText question4 = findViewById(R.id.et_question_H4);
        if (question4.getText().toString().equals(null) || question4.getText().toString().equals("")) {
            q4 = "";
            answered4 = false;
            return q4;
        } else {
            int colours = Integer.parseInt(question4.getText().toString());
            if (colours == 3) {
                scoreQuestion4 = levelPointsAwarded;
                q4 = getString(R.string.correct);
                answered4 = true;
            } else {
                q4 = getString(R.string.incorrect);
                scoreQuestion4 = 0;
                answered4 = true;
            }
        }
        return q4;
    }

    /**
     * This method takes the users selection for question 5 and returns points for a correct answer
     */
    public String onRadioButtonClickedH5() {
        String q5 = "";
        RadioGroup questionE3 = findViewById(R.id.rg_question_H5);
        int id = questionE3.getCheckedRadioButtonId();
        switch (id) {
            case R.id.rb_qH5_option_1:
                q5 = getString(R.string.incorrect);
                scoreQuestion5 = 0;
                answered5 = true;
                break;
            case R.id.rb_qH5_option_2:
                q5 = getString(R.string.correct);
                scoreQuestion5 = levelPointsAwarded;
                answered5 = true;
                break;
            case R.id.rb_qH5_option_3:
                q5 = getString(R.string.incorrect);
                scoreQuestion5 = 0;
                answered5 = true;
                break;
            case R.id.rb_qH5_option_4:
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
            intent.putExtra("KEY_NAME", name);
            intent.putExtra("KEY_SCORE", score);
            intent.putExtra("KEY_NUM_QUESTIONS", questions);
            intent.putExtra("KEY_RESULTS", resultsList);
            intent.putExtra("KEY_LEVEL", level);
            Toast.makeText(this, getString(com.example.android.catquiz.R.string.score_toast, score, questions), Toast.LENGTH_LONG).show();
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, MainActivityH2.class);
            intent.putExtra("KEY_NAME", name);
            intent.putExtra("KEY_SCORE", score);
            intent.putExtra("KEY_NUM_QUESTIONS", questions);
            intent.putExtra("KEY_RESULTS", resultsList);
            intent.putExtra("KEY_LEVEL", level);
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