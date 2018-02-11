package com.example.android.catquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {

    String name = "";
    int questions = 5;
    int level = 1;
    boolean levelChosen = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    /**
     * This method is called when the start button is clicked.
     */
    public void startQuiz(View view) {
        name = getName();
        onRadioButtonClicked();
        playLevel();
    }


    /**
     * This method gets the player's name.
     */
    public String getName() {
        EditText nameEntry = findViewById(R.id.name);
        String name = nameEntry.getText().toString();
        return name;
    }

    /**
     * This method gets the player's preferred difficulty setting
     */
    public void onRadioButtonClicked() {
        RadioGroup difficulty = findViewById(R.id.difficulty);
        int id = difficulty.getCheckedRadioButtonId();


        switch (id) {
            case R.id.easy:
                level = 1;
                levelChosen = true;
                break;
            case R.id.medium:
                level = 2;
                levelChosen = true;
                break;
            case R.id.hard:
                level = 3;
                levelChosen = true;
                break;
        }
    }


    /**
     * This method is called when the + button is clicked.
     */
    public void increment(View view) {

        if (questions == 10) {
            Context context = getApplicationContext();
            CharSequence text = getString(R.string.max_questions_toast);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        if (questions < 10) {
            questions = questions + 5;
            displayNumOfQuestions(questions);
        }
    }

    /**
     * This method is called when the - button is clicked.
     */
    public void decrement(View view) {

        if (questions == 5) {
            Context context = getApplicationContext();
            CharSequence text = getString(R.string.min_questions_toast);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        if (questions > 5) {
            questions = questions - 5;
            displayNumOfQuestions(questions);
        }
    }


    /**
     * This method displays the number of quiz questions on the scree, as per the users choice
     */
    private void displayNumOfQuestions(int number) {
        TextView questionsTextView = findViewById(R.id.quantity_text_view);
        questionsTextView.setText("" + number);
    }

    /**
     * This method loads the next page of questions based on the users settings
     */
    public void playLevel() {
        if (!levelChosen) {
            Toast.makeText(this, R.string.choose_difficulty_toast, Toast.LENGTH_SHORT).show();
        } else if (level == 1) {
            Intent intent = new Intent(this, MainActivityE1.class);
            intent.putExtra("name", name);
            intent.putExtra("questions", questions);
            startActivity(intent);
        }

        if (level == 2) {
            Intent intent = new Intent(this, MainActivityM1.class);
            intent.putExtra("name", name);
            intent.putExtra("questions", questions);
            startActivity(intent);
        }

        if (level == 3) {
            Intent intent = new Intent(this, MainActivityH1.class);
            intent.putExtra("name", name);
            intent.putExtra("questions", questions);
            startActivity(intent);
        }
    }


}