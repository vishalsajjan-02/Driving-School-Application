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

public class medium extends AppCompatActivity {
    private TextView questionTextView;
    private RadioGroup answersRadioGroup;
    private Button previousButton;
    private Button nextButton;
    private Button submitButton;

    private String[] questions = {
            "What is the legal drinking age in your country?",
            "When should you use your hazard lights?",
            "What is the purpose of traffic signs?",
            "What is the penalty for driving under the influence (DUI)?",
            "What should you do if you witness a car accident?",
            "What does a red octagonal traffic sign mean?",
            "What does a solid yellow line on the road indicate?",
            "When is it necessary to yield the right of way?",
            "What is the meaning of a circular traffic sign with a white bar on a red background?",
            "What does a green traffic light indicate?"
    };

    private String[][] answers = {
            {"18 years", "21 years", "25 years", "30 years"},
            {"During heavy rain", "When you need to stop suddenly", "When you are driving slowly", "When your vehicle breaks down"},
            {"To guide drivers", "To regulate traffic", "To provide information", "All of the above"},
            {"Fine and community service", "License suspension and jail time", "Warning and fine", "License revocation and imprisonment"},
            {"Continue driving", "Stop and provide assistance", "Ignore it", "Call the police"},
            {"Stop", "Yield", "No entry", "Warning"},
            {"No passing zone", "Passing zone", "Turning lane", "Bike lane"},
            {"When merging onto a highway", "When exiting a highway", "When turning left", "When pedestrians are crossing"},
            {"Do not enter", "Yield", "Stop", "No U-turn"},
            {"Stop", "Proceed with caution", "Go", "Turn left"}
    };

    private int[] correctAnswers = {0, 3, 3, 1, 1, 0, 0, 2, 0, 2};

    private int currentQuestionIndex = 0;
    private int[] selectedAnswers = new int[questions.length]; // Array to store selected answers

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medium);
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
                Toast.makeText(medium.this, "Quiz completed!", Toast.LENGTH_SHORT).show();
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
        Intent intent = new Intent(medium.this, score.class);
        intent.putExtra("score", score); // Pass the score to the score activity
        startActivity(intent);
    }
}
