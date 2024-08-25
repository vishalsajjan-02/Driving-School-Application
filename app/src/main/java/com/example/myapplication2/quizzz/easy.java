package com.example.myapplication2.quizzz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication2.R;

public class easy extends AppCompatActivity {
    private TextView questionTextView;
    private RadioGroup answersRadioGroup;
    private Button previousButton;
    private Button nextButton;
    private Button submitButton;

    private String[] questions = {
            "What is the minimum age for getting a driving license?",
            "What should you do at a red traffic light?",
            "What is the speed limit in a residential area?",
            "When is it necessary to use your turn signals?",
            "What should you do if you see an emergency vehicle behind you?",
            "What is the purpose of a yield sign?",
            "What should you check before changing lanes?",
            "What is a safe following distance?",
            "What does a flashing yellow light mean?",
            "When should you use high beam headlights?"
    };

    private String[][] answers = {
            {"16 years", "18 years", "21 years", "25 years"},
            {"Stop", "Go", "Slow down", "Honk the horn"},
            {"30 km/h", "50 km/h", "60 km/h", "80 km/h"},
            {"When turning", "When stopping", "When changing lanes", "All of the above"},
            {"Speed up", "Move to the right", "Move to the left", "Stop immediately"},
            {"To stop", "To proceed with caution", "To yield the right of way", "To indicate a school zone"},
            {"Mirrors", "Blind spots", "Traffic around you", "All of the above"},
            {"1 second", "2 seconds", "3 seconds", "4 seconds"},
            {"Stop", "Proceed with caution", "Go", "Slow down and proceed with caution"},
            {"In fog", "In rain", "In a well-lit area", "On an empty road at night"}
    };

    private int[] correctAnswers = {0, 0, 1, 3, 1, 2, 3, 2, 3, 3};

    private int currentQuestionIndex = 0;
    private int[] selectedAnswers = new int[questions.length]; // Array to store selected answers

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy);
        questionTextView = findViewById(R.id.questionTextView);
        answersRadioGroup = findViewById(R.id.answersRadioGroup);
        previousButton = findViewById(R.id.previousButton);
        nextButton = findViewById(R.id.nextButton);
        submitButton = findViewById(R.id.submitButton);

        // Initialize selectedAnswers array with -1 to indicate no selection
        for (int i = 0; i < selectedAnswers.length; i++) {
            selectedAnswers[i] = -1;
        }

        loadQuestion();

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentQuestionIndex > 0) {
                    saveAnswer();
                    currentQuestionIndex--;
                    loadQuestion();
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentQuestionIndex < questions.length - 1) {
                    saveAnswer();
                    currentQuestionIndex++;
                    loadQuestion();
                }
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAnswer();
                checkAnswer();
                Toast.makeText(easy.this, "Quiz completed!", Toast.LENGTH_SHORT).show();
                // Optionally: Restart the quiz or finish the activity
            }
        });
    }

    private void loadQuestion() {
        if (currentQuestionIndex < questions.length) {
            // Set the question number dynamically
            String questionNumber = "" + (currentQuestionIndex + 1);
            questionTextView.setText(questionNumber + ": " + questions[currentQuestionIndex]);

            ((RadioButton) answersRadioGroup.getChildAt(0)).setText(answers[currentQuestionIndex][0]);
            ((RadioButton) answersRadioGroup.getChildAt(1)).setText(answers[currentQuestionIndex][1]);
            ((RadioButton) answersRadioGroup.getChildAt(2)).setText(answers[currentQuestionIndex][2]);
            ((RadioButton) answersRadioGroup.getChildAt(3)).setText(answers[currentQuestionIndex][3]);

            answersRadioGroup.clearCheck();

            if (selectedAnswers[currentQuestionIndex] != -1) {
                ((RadioButton) answersRadioGroup.getChildAt(selectedAnswers[currentQuestionIndex])).setChecked(true);
            }

            previousButton.setVisibility(currentQuestionIndex == 0 ? View.GONE : View.VISIBLE);
            nextButton.setVisibility(currentQuestionIndex == questions.length - 1 ? View.GONE : View.VISIBLE);
            submitButton.setVisibility(currentQuestionIndex == questions.length - 1 ? View.VISIBLE : View.GONE);
        }
    }

    private void saveAnswer() {
        int selectedId = answersRadioGroup.getCheckedRadioButtonId();
        if (selectedId != -1) {
            int answerIndex = answersRadioGroup.indexOfChild(findViewById(selectedId));
            selectedAnswers[currentQuestionIndex] = answerIndex;
        }
    }

    private void checkAnswer() {
        int score = 0;
        for (int i = 0; i < correctAnswers.length; i++) {
            if (selectedAnswers[i] == correctAnswers[i]) {
                score++;
            }
        }
        Intent intent = new Intent(easy.this, score.class);
        intent.putExtra("score", score); // Pass the score to the score activity
        startActivity(intent);
    }
}
