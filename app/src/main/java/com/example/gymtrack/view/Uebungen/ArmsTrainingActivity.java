package com.example.gymtrack.view.Uebungen;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.gymtrack.R;
import com.example.gymtrack.view.ExerciseInputActivity;

public class ArmsTrainingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arms_training);

        Button btnCurls = findViewById(R.id.button_curls);
        Button btnHammer = findViewById(R.id.button_hammer);
        Button btnPushdowns = findViewById(R.id.button_pushdowns);
        Button btnDips = findViewById(R.id.button_dips);

        btnCurls.setOnClickListener(v -> openExercise("Bizeps Curls"));
        btnHammer.setOnClickListener(v -> openExercise("Hammer Curls"));
        btnPushdowns.setOnClickListener(v -> openExercise("Trizeps Pushdowns"));
        btnDips.setOnClickListener(v -> openExercise("Trizeps Dips"));

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
        intent.putExtra("exercise_category", "Arme");
        startActivity(intent);
    }
}
