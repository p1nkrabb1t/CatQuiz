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
    int scoreQuestionE1 = 0;
    int scoreQuestionE2 = 0;
    int scoreQuestionE3 = 0;
    int scoreQuestionE4 = 0;
    int scoreQuestionE5 = 0;
    String resultsList;
    boolean answeredE1 = false;
    boolean answeredE2 = false;
    boolean answeredE3 = false;
    boolean answeredE4 = false;
    boolean answeredE5 = false;


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
        String qE1 = onRadioButtonClickedE1();
        String qE2 = checkNumberE2();
        String qE3 = onRadioButtonClickedE3();
        String qE4 = getCheckedStateE4();
        String qE5 = onRadioButtonClickedE5();
        getScore();
        resultsList = createScoreSummary(qE1, qE2, qE3, qE4, qE5);
        if (answeredE1 && answeredE2 && answeredE3 && answeredE4 && answeredE5) {
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
        score = scoreQuestionE1 + scoreQuestionE2 + scoreQuestionE3 + scoreQuestionE4 + scoreQuestionE5;
    }

    /**
     * This method takes the users selection for question 1 (Easy) and returns points for a correct answer
     */
    public String onRadioButtonClickedE1() {
        String qE1 = "";
        RadioGroup questionE1 = findViewById(R.id.question_E1);
        int id = questionE1.getCheckedRadioButtonId();
        switch (id) {
            case R.id.qE1_1:
                qE1 = getString(R.string.incorrect);
                scoreQuestionE1 = 0;
                answeredE1 = true;
                break;
            case R.id.qE1_2:
                scoreQuestionE1 = levelPointsAwarded;
                qE1 = getString(R.string.correct);
                answeredE1 = true;
                break;
            case R.id.qE1_3:
                qE1 = getString(R.string.incorrect);
                scoreQuestionE1 = 0;
                answeredE1 = true;
                break;
            case R.id.qE1_4:
                qE1 = getString(R.string.incorrect);
                scoreQuestionE1 = 0;
                answeredE1 = true;
                break;
        }
        return qE1;
    }

    /**
     * This method takes the users number input for question 2 and returns points for a correct answer
     */
    public String checkNumberE2() {
        String qE2 = "";
        EditText livesQuestion = findViewById(R.id.question_E2);
        if (livesQuestion.getText().toString().equals(null) || livesQuestion.getText().toString().equals("")) {
            qE2 = "";
            return qE2;
        } else {
            int lives = Integer.parseInt(livesQuestion.getText().toString());
            if (lives == 9) {
                scoreQuestionE2 = levelPointsAwarded;
                qE2 = getString(R.string.correct);
                answeredE2 = true;
            } else {
                qE2 = getString(R.string.incorrect);
                scoreQuestionE2 = 0;
                answeredE2 = true;
            }
        }
        return qE2;
    }

    /**
     * This method takes the users selection for question 3 (Easy) and returns points for a correct answer
     */
    public String onRadioButtonClickedE3() {
        String qE3 = "";
        RadioGroup questionE3 = findViewById(R.id.question_E3);
        int id = questionE3.getCheckedRadioButtonId();
        switch (id) {
            case R.id.qE3_1:
                qE3 = getString(R.string.incorrect);
                scoreQuestionE3 = 0;
                answeredE3 = true;
                break;
            case R.id.qE3_2:
                qE3 = getString(R.string.incorrect);
                scoreQuestionE3 = 0;
                answeredE3 = true;
                break;
            case R.id.qE3_3:
                scoreQuestionE3 = levelPointsAwarded;
                qE3 = getString(R.string.correct);
                answeredE3 = true;
                break;
        }
        return qE3;
    }

    /**
     * This method checks first that at least one box is checked for question 4 (Easy)
     * takes multiple user selections and returns points only if ALL of the correct answers are selected
     */
    private String getCheckedStateE4() {
        String qE4 = "";
        CheckBox qE41 = findViewById(R.id.qE4_1);
        CheckBox qE42 = findViewById(R.id.qE4_2);
        CheckBox qE43 = findViewById(R.id.qE4_3);
        CheckBox qE44 = findViewById(R.id.qE4_4);
        CheckBox qE45 = findViewById(R.id.qE4_5);
        boolean easy4_1 = qE41.isChecked();
        boolean easy4_2 = qE42.isChecked();
        boolean easy4_3 = qE43.isChecked();
        boolean easy4_4 = qE44.isChecked();
        boolean easy4_5 = qE45.isChecked();

        if (!easy4_1 && !easy4_2 && !easy4_3 && !easy4_4 && !easy4_5) {
            return qE4;
        }
        if (easy4_1 && easy4_2 && !easy4_3 && easy4_4 && !easy4_5) {
            scoreQuestionE4 = levelPointsAwarded;
            qE4 = getString(R.string.correct);
            answeredE4 = true;
        } else {
            qE4 = getString(R.string.incorrect);
            scoreQuestionE4 = 0;
            answeredE4 = true;
        }
        return qE4;
    }

    /**
     * This method takes the users selection for question 5 (Easy) and returns points for a correct answer
     */
    public String onRadioButtonClickedE5() {
        String qE5 = "";
        RadioGroup questionE5 = findViewById(R.id.question_E5);
        int id = questionE5.getCheckedRadioButtonId();
        switch (id) {
            case R.id.qE5_1:
                scoreQuestionE5 = levelPointsAwarded;
                qE5 = getString(R.string.correct);
                answeredE5 = true;
                break;
            case R.id.qE5_2:
                qE5 = getString(R.string.incorrect);
                scoreQuestionE5 = 0;
                answeredE5 = true;
                break;
        }
        return qE5;
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
    private String createScoreSummary(String qE1, String qE2, String qE3, String qE4, String qE5) {
        resultsList = getString(R.string.results_message);
        resultsList += "\n" + getString(R.string.results_Q1) + qE1;
        resultsList += "\n" + getString(R.string.results_Q2) + qE2;
        resultsList += "\n" + getString(R.string.results_Q3) + qE3;
        resultsList += "\n" + getString(R.string.results_Q4) + qE4;
        resultsList += "\n" + getString(R.string.results_Q5) + qE5;
        return resultsList;

    }


}

