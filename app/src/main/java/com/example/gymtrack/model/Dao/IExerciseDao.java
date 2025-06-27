package com.example.gymtrack.model.Dao;

import com.example.gymtrack.model.Datamanager.ExerciseEntry;

import java.util.List;

/**
 * Dieses Interface beschreibt alle Methoden zum Umgang mit der Trainings-Datenbank.
 * Es wird in ExerciseDaoImpl umgesetzt.
 */
public interface IExerciseDao {

    /**
     * Fügt einen neuen Eintrag in die Datenbank ein.
     */
    void insert(ExerciseEntry entry);

    /**
     * Aktualisiert einen bestehenden Eintrag.
     */
    void update(ExerciseEntry entry);

    /**
     * Löscht einen Eintrag anhand seiner ID.
     */
    void delete(int id);

    /**
     * Löscht alle Einträge zu einer bestimmten Übung.
     */
    void deleteAllForExercise(String exerciseName);

    /**
     * Gibt alle Trainingsdaten zurück.
     */
    List<ExerciseEntry> getAllEntries();

    /**
     * Gibt nur die Daten einer bestimmten Übung zurück.
     */
    List<ExerciseEntry> getEntriesForExercise(String exerciseName);
}
