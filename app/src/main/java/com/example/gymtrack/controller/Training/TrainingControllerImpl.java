package com.example.gymtrack.controller.Training;

import android.content.Context;

import com.example.gymtrack.model.Dao.ExerciseDaoImpl;
import com.example.gymtrack.model.Datamanager.ExerciseEntry;
import com.example.gymtrack.model.Dao.IExerciseDao;

import java.util.List;

public class TrainingControllerImpl implements ITrainingController {

    private final IExerciseDao dao;

    public TrainingControllerImpl(Context context) {
        this.dao = new ExerciseDaoImpl(context);
    }
    // Für Testzwecke
    public TrainingControllerImpl(IExerciseDao dao) {
        this.dao = dao;
    }


    @Override
    public void saveEntry(ExerciseEntry entry) {
        dao.insert(entry);
    }

    @Override
    public void updateEntry(ExerciseEntry entry) {
        dao.update(entry);
    }

    @Override
    public void deleteEntry(int id) {
        dao.delete(id);
    }

    @Override
    public void deleteAll(String exerciseName) {
        dao.deleteAllForExercise(exerciseName);
    }

    @Override
    public List<ExerciseEntry> getAllEntries() {
        return dao.getAllEntries();
    }

    @Override
    public List<ExerciseEntry> getEntriesForExercise(String exerciseName) {
        return dao.getEntriesForExercise(exerciseName);
    }
}
