package com.example.gymtrack.controller.Exercise;

import android.content.Context;

/**
 * Interface für Navigation von Kategorie zu Eingabemaske.
 * Wird vom Controller ExerciseControllerImpl umgesetzt.
 */
public interface IExerciseController {

    /**
     * Methode zum Öffnen der Eingabeansicht für eine Übung.
     * @param context aktueller Context (z. B. Activity)
     * @param exerciseName Name der Übung
     * @param category Kategorie (z. B. Brust, Rücken)
     */
    void openExerciseInput(Context context, String exerciseName, String category);
}
