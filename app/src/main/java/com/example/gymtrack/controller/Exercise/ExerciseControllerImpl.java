package com.example.gymtrack.controller.Exercise;

import android.content.Context;
import android.content.Intent;
import com.example.gymtrack.view.ExerciseInputActivity;

/**
 * Dieser Controller öffnet die Eingabeseite für eine Übung.
 * Wird z. B. genutzt, wenn der Nutzer in der App eine neue Übung starten will.
 */
public class ExerciseControllerImpl implements IExerciseController {

    /**
     * Öffnet die Eingabemaske für eine bestimmte Übung und Kategorie.
     * @param context aktueller Android-Kontext (Activity)
     * @param exerciseName Name der Übung (z. B. "Bankdrücken")
     * @param category Kategorie (z. B. "Brust")
     */
    @Override
    public void openExerciseInput(Context context, String exerciseName, String category) {
        Intent intent = new Intent(context, ExerciseInputActivity.class);
        intent.putExtra("exercise_name", exerciseName);
        intent.putExtra("exercise_category", category);
        context.startActivity(intent); // startet die Eingabe-Activity
    }
}
