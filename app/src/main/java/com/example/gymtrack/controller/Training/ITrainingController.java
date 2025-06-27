package com.example.gymtrack.controller.Training;

import com.example.gymtrack.model.Datamanager.ExerciseEntry;
import java.util.List;

public interface ITrainingController {
    void saveEntry(ExerciseEntry entry);
    void updateEntry(ExerciseEntry entry);
    void deleteEntry(int id);
    void deleteAll(String exerciseName);

    List<ExerciseEntry> getAllEntries();
    List<ExerciseEntry> getEntriesForExercise(String exerciseName);


}
