package com.example.gymtrack.view.Uebungen;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.gymtrack.R;
import com.example.gymtrack.view.ExerciseInputActivity;

public class LegsTrainingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legs_training);

        Button btnSquat = findViewById(R.id.button_squat);
        Button btnLegPress = findViewById(R.id.button_leg_press);
        Button btnLegCurl = findViewById(R.id.button_leg_curl);
        Button btnHipThrust = findViewById(R.id.button_hip_thrust);

        btnSquat.setOnClickListener(v -> openExercise("Kniebeuge"));
        btnLegPress.setOnClickListener(v -> openExercise("Beinpresse"));
        btnLegCurl.setOnClickListener(v -> openExercise("Beinbeuger"));
        btnHipThrust.setOnClickListener(v -> openExercise("Hip Thrust"));

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
        intent.putExtra("exercise_category", "Beine");
        startActivity(intent);
    }
}
