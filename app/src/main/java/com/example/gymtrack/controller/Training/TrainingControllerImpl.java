package com.example.gymtrack.controller.Training;

import android.content.Context;
import com.example.gymtrack.model.Dao.ExerciseDaoImpl;
import com.example.gymtrack.model.Datamanager.ExerciseEntry;
import com.example.gymtrack.model.Dao.IExerciseDao;
import java.util.List;

/**
 * Dieser Controller verarbeitet alle Trainingsdaten.
 * Speichern, Laden, Aktualisieren und Löschen von Übungen.
 */
public class TrainingControllerImpl implements ITrainingController {

    private final IExerciseDao dao;

    /**
     * Konstruktor mit Context, um echten DAO zu nutzen (für die App).
     * @param context aktueller App-Kontext
     */
    public TrainingControllerImpl(Context context) {
        this.dao = new ExerciseDaoImpl(context);
    }

    /**
     * Konstruktor mit DAO für Tests. Erlaubt Unit-Tests mit Mock-Objekten.
     */
    public TrainingControllerImpl(IExerciseDao dao) {
        this.dao = dao;
    }

    /**
     * Speichert einen neuen Eintrag in der Datenbank.
     */
    @Override
    public void saveEntry(ExerciseEntry entry) {
        dao.insert(entry);
    }

    /**
     * Aktualisiert einen vorhandenen Eintrag.
     */
    @Override
    public void updateEntry(ExerciseEntry entry) {
        dao.update(entry);
    }

    /**
     * Löscht einen Eintrag nach ID.
     */
    @Override
    public void deleteEntry(int id) {
        dao.delete(id);
    }

    /**
     * Löscht alle Einträge zu einer bestimmten Übung.
     */
    @Override
    public void deleteAll(String exerciseName) {
        dao.deleteAllForExercise(exerciseName);
    }

    /**
     * Holt alle gespeicherten Trainingseinträge.
     */
    @Override
    public List<ExerciseEntry> getAllEntries() {
        return dao.getAllEntries();
    }

    /**
     * Holt nur Einträge zu einer bestimmten Übung (z. B. nur "Bankdrücken").
     */
    @Override
    public List<ExerciseEntry> getEntriesForExercise(String exerciseName) {
        return dao.getEntriesForExercise(exerciseName);
    }
}
