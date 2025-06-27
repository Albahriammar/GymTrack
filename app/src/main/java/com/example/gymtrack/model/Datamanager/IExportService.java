package com.example.gymtrack.model.Datamanager;

import com.example.gymtrack.model.Datamanager.ExerciseEntry;
import java.io.File;
import java.util.List;

public interface IExportService {
    boolean exportToCSV(List<ExerciseEntry> entries, File file);
}
