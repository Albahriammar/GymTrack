package com.example.gymtrack.view.Uebungen;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.gymtrack.R;
import com.example.gymtrack.view.ExerciseInputActivity;

/**
 * Diese Activity zeigt Übungen für die Schulder.
 * Aufbau ist identisch wie ArmsTrainingActivity.
 */

public class ShoulderTrainingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoulder_training);

        Button btnMilitary = findViewById(R.id.button_military);
        Button btnLateral = findViewById(R.id.button_lateral);
        Button btnFront = findViewById(R.id.button_front);
        Button btnReverse = findViewById(R.id.button_reverse);

        btnMilitary.setOnClickListener(v -> openExercise("Military Press"));
        btnLateral.setOnClickListener(v -> openExercise("Seitheben"));
        btnFront.setOnClickListener(v -> openExercise("Frontheben"));
        btnReverse.setOnClickListener(v -> openExercise("Reverse Flys"));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("");
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish(); // beendet die Activity, kehrt zur vorherigen zurück
        return true;
    }


    private void openExercise(String name) {
        Intent intent = new Intent(this, ExerciseInputActivity.class);
        intent.putExtra("exercise_name", name);
        intent.putExtra("exercise_category", "Schulter");
        startActivity(intent);
    }
}
