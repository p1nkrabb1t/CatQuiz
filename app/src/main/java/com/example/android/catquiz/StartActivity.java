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
     * This method saves data which would be lost on screen rotation (destroyed).
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("KEY_NUM_QUESTIONS", questions);
        super.onSaveInstanceState(savedInstanceState);
    }

    /**
     * This method restores the previously saved data after the screen is reloaded in another orientation
     */
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        questions = savedInstanceState.getInt("KEY_NUM_QUESTIONS");
        displayNumOfQuestions(questions);
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
        EditText nameEntry = findViewById(R.id.et_name);
        name = nameEntry.getText().toString();
        return name;
    }

    /**
     * This method gets the player's preferred difficulty setting
     */
    public void onRadioButtonClicked() {
        RadioGroup setDifficulty = findViewById(R.id.rg_set_difficulty);
        int id = setDifficulty.getCheckedRadioButtonId();

        switch (id) {
            case R.id.rb_easy:
                level = 1;
                levelChosen = true;
                break;
            case R.id.rb_medium:
                level = 2;
                levelChosen = true;
                break;
            case R.id.rb_hard:
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
     * This method displays the number of quiz questions on the screen, as per the users choice
     */
    private void displayNumOfQuestions(int number) {
        TextView questionsTextView = findViewById(R.id.tv_set_questions_qty);
        questionsTextView.setText(String.valueOf(number));
    }

    /**
     * This method loads the next page of questions based on the users settings
     */
    public void playLevel() {
        if (!levelChosen) {
            Toast.makeText(this, R.string.choose_difficulty_toast, Toast.LENGTH_SHORT).show();
        } else if (level == 1) {
            Intent intent = new Intent(this, MainActivityE1.class);
            intent.putExtra("KEY_NAME", name);
            intent.putExtra("KEY_NUM_QUESTIONS", questions);
            intent.putExtra("KEY_LEVEL", level);
            startActivity(intent);
        }

        if (level == 2) {
            Intent intent = new Intent(this, MainActivityM1.class);
            intent.putExtra("KEY_NAME", name);
            intent.putExtra("KEY_NUM_QUESTIONS", questions);
            intent.putExtra("KEY_LEVEL", level);
            startActivity(intent);
        }

        if (level == 3) {
            Intent intent = new Intent(this, MainActivityH1.class);
            intent.putExtra("KEY_NAME", name);
            intent.putExtra("KEY_NUM_QUESTIONS", questions);
            intent.putExtra("KEY_LEVEL", level);
            startActivity(intent);
        }
    }


}