package com.example.gymtrack.model.Dao;
import com.example.gymtrack.model.Datamanager.ExerciseEntry;

import java.util.List;

public interface IExerciseDao {

    void insert(ExerciseEntry entry);
    void update(ExerciseEntry entry);
    void delete(int id);
    void deleteAllForExercise(String exerciseName);


    List<ExerciseEntry> getAllEntries();
    List<ExerciseEntry> getEntriesForExercise(String exerciseName);
}
