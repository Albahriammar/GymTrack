package com.example.gymtrack.model.Datamanager;

import java.io.File;

public interface IImportService {
    boolean importFromCSV(File csvFile);
}
