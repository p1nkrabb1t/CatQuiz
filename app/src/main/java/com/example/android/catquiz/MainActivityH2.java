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


public class MainActivityH2 extends AppCompatActivity {
    String name;
    int questions;
    int score = 0;
    int levelPointsAwarded = 1;
    int scoreQuestionE6 = 0;
    int scoreQuestionE7 = 0;
    int scoreQuestionE8 = 0;
    int scoreQuestionE9 = 0;
    int scoreQuestionE10 = 0;
    String resultsList;
    boolean answeredE6 = false;
    boolean answeredE7 = false;
    boolean answeredE8 = false;
    boolean answeredE9 = false;
    boolean answeredE10 = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_h2);
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
        String qE6 = onRadioButtonClickedE1();
        String qE7 = checkNumberE2();
        String qE8 = onRadioButtonClickedE3();
        String qE9 = getCheckedStateE4();
        String qE10 = onRadioButtonClickedE5();
        getScore();
        resultsList = createScoreSummary(qE6, qE7, qE8, qE9, qE10);
        if (answeredE6 && answeredE7 && answeredE8 && answeredE9 && answeredE10) {
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
        Button buttonText = findViewById(R.id.submitE_A);
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
        Bundle bundle = getIntent().getExtras();
        score = bundle.getInt("score") + scoreQuestionE6 + scoreQuestionE7 + scoreQuestionE8 + scoreQuestionE9 + scoreQuestionE10;
    }

    /**
     * This method takes the users selection for question 1 (Easy) and returns points for a correct answer
     */
    public String onRadioButtonClickedE1() {
        String qE6 = "";
        RadioGroup questionE1 = findViewById(R.id.question_E1);
        int id = questionE1.getCheckedRadioButtonId();
        switch (id) {
            case R.id.qE1_1:
                qE6 = getString(R.string.incorrect);
                scoreQuestionE6 = 0;
                answeredE6 = true;
                break;
            case R.id.qE1_2:
                scoreQuestionE6 = levelPointsAwarded;
                qE6 = getString(R.string.correct);
                answeredE6 = true;
                break;
            case R.id.qE1_3:
                qE6 = getString(R.string.incorrect);
                scoreQuestionE6 = 0;
                answeredE6 = true;
                break;
            case R.id.qE1_4:
                qE6 = getString(R.string.incorrect);
                scoreQuestionE6 = 0;
                answeredE6 = true;
                break;
        }
        return qE6;
    }

    /**
     * This method takes the users number input for question 2 and returns points for a correct answer
     */
    public String checkNumberE2() {
        String qE7 = "";
        EditText livesQuestion = findViewById(R.id.question_E2);
        if (livesQuestion.getText().toString().equals(null) || livesQuestion.getText().toString().equals("")) {
            qE7 = "";
            return qE7;
        } else {
            int lives = Integer.parseInt(livesQuestion.getText().toString());
            if (lives == 9) {
                scoreQuestionE7 = levelPointsAwarded;
                qE7 = getString(R.string.correct);
                answeredE7 = true;
            } else {
                qE7 = getString(R.string.incorrect);
                scoreQuestionE7 = 0;
                answeredE7 = true;
            }
        }
        return qE7;
    }

    /**
     * This method takes the users selection for question 3 (Easy) and returns points for a correct answer
     */
    public String onRadioButtonClickedE3() {
        String qE8 = "";
        RadioGroup questionE3 = findViewById(R.id.question_E3);
        int id = questionE3.getCheckedRadioButtonId();
        switch (id) {
            case R.id.qE3_1:
                qE8 = getString(R.string.incorrect);
                scoreQuestionE8 = 0;
                answeredE8 = true;
                break;
            case R.id.qE3_2:
                qE8 = getString(R.string.incorrect);
                scoreQuestionE8 = 0;
                answeredE8 = true;
                break;
            case R.id.qE3_3:
                scoreQuestionE8 = levelPointsAwarded;
                qE8 = getString(R.string.correct);
                answeredE8 = true;
                break;
        }
        return qE8;
    }

    /**
     * This method checks first that at least one box is checked for question 4 (Easy)
     * takes multiple user selections and returns points only if ALL of the correct answers are selected
     */
    private String getCheckedStateE4() {
        String qE9 = "";
        CheckBox qE91 = findViewById(R.id.qE4_1);
        CheckBox qE92 = findViewById(R.id.qE4_2);
        CheckBox qE93 = findViewById(R.id.qE4_3);
        CheckBox qE94 = findViewById(R.id.qE4_4);
        CheckBox qE95 = findViewById(R.id.qE4_5);
        boolean easy9_1 = qE91.isChecked();
        boolean easy9_2 = qE92.isChecked();
        boolean easy9_3 = qE93.isChecked();
        boolean easy9_4 = qE94.isChecked();
        boolean easy9_5 = qE95.isChecked();

        if (!easy9_1 && !easy9_2 && !easy9_3 && !easy9_4 && !easy9_5) {
            return qE9;
        }
        if (easy9_1 && easy9_2 && !easy9_3 && easy9_4 && !easy9_5) {
            scoreQuestionE9 = levelPointsAwarded;
            qE9 = getString(R.string.correct);
            answeredE9 = true;
        } else {
            qE9 = getString(R.string.incorrect);
            scoreQuestionE9 = 0;
            answeredE9 = true;
        }
        return qE9;
    }

    /**
     * This method takes the users selection for question 5 (Easy) and returns points for a correct answer
     */
    public String onRadioButtonClickedE5() {
        String qE10 = "";
        RadioGroup questionE5 = findViewById(R.id.question_E5);
        int id = questionE5.getCheckedRadioButtonId();
        switch (id) {
            case R.id.qE5_1:
                scoreQuestionE10 = levelPointsAwarded;
                qE10 = getString(R.string.correct);
                answeredE10 = true;
                break;
            case R.id.qE5_2:
                qE10 = getString(R.string.incorrect);
                scoreQuestionE10 = 0;
                answeredE10 = true;
                break;
        }
        return qE10;
    }


    /**
     * This method decides if to take the user to another set of questions or end the quiz
     */
    public void nextPage() {
        //if (questions == 10) {
        Intent intent = new Intent(this, ScoreActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("score", score);
        intent.putExtra("questions", questions);
        intent.putExtra("resultsList", resultsList);
        Toast.makeText(this, "You have scored " + score + " out of " + questions, Toast.LENGTH_LONG).show();
        startActivity(intent);
//        } else {
//            Intent intent = new Intent(this, MainActivityE2.class);
//            intent.putExtra("name", name);
//            intent.putExtra("score", score);
//            intent.putExtra("questions", questions);
//            startActivity(intent);
//        }
    }


    /**
     * This method creates a summary of which questions were answered right or wrong
     */
    private String createScoreSummary(String qE6, String qE7, String qE8, String qE9, String qE10) {
        Bundle bundle = getIntent().getExtras();
        resultsList = bundle.getString("resultsList");

        resultsList += "\n" + getString(R.string.results_Q6) + qE6;
        resultsList += "\n" + getString(R.string.results_Q7) + qE7;
        resultsList += "\n" + getString(R.string.results_Q8) + qE8;
        resultsList += "\n" + getString(R.string.results_Q9) + qE9;
        resultsList += "\n" + getString(R.string.results_Q10) + qE10;
        return resultsList;

    }


}