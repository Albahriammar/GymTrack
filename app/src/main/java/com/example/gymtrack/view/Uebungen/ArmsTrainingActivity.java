package com.example.gymtrack.view.Uebungen;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.gymtrack.R;
import com.example.gymtrack.view.ExerciseInputActivity;

/**
 * Zeigt die Auswahlseite für Arm-Übungen (z. B. Bizeps Curls, Dips).
 * Jeder Button startet die Eingabeseite mit dem passenden Übungsnamen.
 */
public class ArmsTrainingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arms_training);

        // Buttons zu den einzelnen Übungen
        Button btnCurls = findViewById(R.id.button_curls);
        Button btnHammer = findViewById(R.id.button_hammer);
        Button btnPushdowns = findViewById(R.id.button_pushdowns);
        Button btnDips = findViewById(R.id.button_dips);

        // Klick-Aktionen: öffnet jeweils die Eingabeseite für die gewählte Übung
        btnCurls.setOnClickListener(v -> openExercise("Bizeps Curls"));
        btnHammer.setOnClickListener(v -> openExercise("Hammer Curls"));
        btnPushdowns.setOnClickListener(v -> openExercise("Trizeps Pushdowns"));
        btnDips.setOnClickListener(v -> openExercise("Trizeps Dips"));

        // Toolbar oben einrichten (zurück-Pfeil, Titel verstecken)
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("");
        }
    }

    /**
     * Wenn der Zurück-Pfeil in der Toolbar gedrückt wird → schließt die Activity.
     */
    @Override
    public boolean onSupportNavigateUp() {
        finish(); // zurück zur vorherigen Seite
        return true;
    }

    /**
     * Startet die Eingabeseite für eine bestimmte Übung.
     * @param name Name der Übung (z. B. "Hammer Curls")
     */
    private void openExercise(String name) {
        Intent intent = new Intent(this, ExerciseInputActivity.class);
        intent.putExtra("exercise_name", name);
        intent.putExtra("exercise_category", "Arme"); // fest definiert für diese Activity
        startActivity(intent);
    }
}
