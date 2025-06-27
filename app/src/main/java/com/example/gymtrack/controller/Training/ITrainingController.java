package com.example.gymtrack.controller.Training;

import com.example.gymtrack.model.Datamanager.ExerciseEntry;
import java.util.List;

/**
 * Interface für alle Methoden zum Verwalten von Trainingsdaten.
 * Wird von TrainingControllerImpl umgesetzt.
 */
public interface ITrainingController {

    /**
     * Neuen Eintrag speichern.
     */
    void saveEntry(ExerciseEntry entry);

    /**
     * Vorhandenen Eintrag ändern.
     */
    void updateEntry(ExerciseEntry entry);

    /**
     * Eintrag nach ID löschen.
     */
    void deleteEntry(int id);

    /**
     * Alle Einträge zu einer Übung löschen.
     */
    void deleteAll(String exerciseName);

    /**
     * Alle gespeicherten Einträge abrufen.
     */
    List<ExerciseEntry> getAllEntries();

    /**
     * Nur Einträge zu einer bestimmten Übung abrufen.
     */
    List<ExerciseEntry> getEntriesForExercise(String exerciseName);
}
