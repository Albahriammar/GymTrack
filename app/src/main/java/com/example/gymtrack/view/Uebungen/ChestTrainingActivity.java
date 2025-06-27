package com.example.gymtrack.view.Uebungen;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.gymtrack.R;
import com.example.gymtrack.view.ExerciseInputActivity;
import androidx.appcompat.widget.Toolbar;

/**
 * Diese Activity zeigt Übungen für die Brusttraining.
 * Aufbau ist identisch wie ArmsTrainingActivity.
 */
public class ChestTrainingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chest_training);

        Button btnBench = findViewById(R.id.button_bench_press);
        Button btnIncline = findViewById(R.id.button_incline_press);
        Button btnFly = findViewById(R.id.button_fly);
        Button btnCrossover = findViewById(R.id.button_crossover);

        btnBench.setOnClickListener(v -> openExercise("Bankdrücken"));
        btnIncline.setOnClickListener(v -> openExercise("Schrägbankdrücken"));
        btnFly.setOnClickListener(v -> openExercise("Butterfly"));
        btnCrossover.setOnClickListener(v -> openExercise("Kabelzug Überkreuz"));

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
        intent.putExtra("exercise_category", "Brust");
        startActivity(intent);
    }
}
