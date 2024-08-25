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

public class hard extends AppCompatActivity {
    private TextView questionTextView;
    private RadioGroup answersRadioGroup;
    private Button previousButton;
    private Button nextButton;
    private Button submitButton;

    private String[] questions = {
            "What is the maximum speed limit on highways in your state?",
            "When is it legal to pass another vehicle?",
            "What is the recommended distance to keep from the vehicle ahead in adverse weather conditions?",
            "What does a solid yellow line on the road indicate?",
            "What should you do if you encounter black ice while driving?",
            "What is the legal blood alcohol concentration (BAC) limit for drivers in your state?",
            "What is the meaning of a circular traffic sign with a white bar on a red background?",
            "When driving in foggy conditions, what lights should you use?",
            "What should you do if you miss your highway exit?",
            "What is the purpose of anti-lock brakes (ABS) in a vehicle?"
    };

    private String[][] answers = {
            {"60 mph", "65 mph", "70 mph", "75 mph"},
            {"When the vehicle in front of you is traveling below the speed limit", "When you can see at least 100 feet ahead of you", "When there is a broken white line on the road", "When it's safe and legal to do so"},
            {"1 second", "2 seconds", "3 seconds", "4 seconds"},
            {"No passing zone", "Passing zone", "Turning lane", "Bike lane"},
            {"Brake suddenly", "Steer into the skid", "Speed up", "Turn on hazard lights"},
            {"0.05%", "0.08%", "0.10%", "0.12%"},
            {"Do not enter", "Yield", "Stop", "No U-turn"},
            {"High beams", "Low beams", "Parking lights", "Hazard lights"},
            {"Reverse on the highway", "Continue to the next exit", "Make a U-turn", "Drive against the flow of traffic"},
            {"To help you stop more quickly", "To prevent skidding while braking", "To reduce fuel consumption", "To improve steering control"}
    };

    private int[] correctAnswers = {2, 3, 2, 0, 1, 1, 0, 1, 1, 1};

    private int currentQuestionIndex = 0;
    private int[] selectedAnswers = new int[questions.length]; // Array to store selected answers

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard);
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
                Toast.makeText(hard.this, "Quiz completed!", Toast.LENGTH_SHORT).show();
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
        Intent intent = new Intent(hard.this, score.class);
        intent.putExtra("score", score); // Pass the score to the score activity
        startActivity(intent);
    }
}
