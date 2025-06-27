package com.example.gymtrack.model.Datamanager;

import java.io.File;

/**
 * Diese Schnittstelle beschreibt den Import-Service.
 * Sie wird verwendet, um Trainingsdaten aus einer CSV-Datei zu lesen.
 */
public interface IImportService {

    /**
     * Liest Trainingsdaten aus einer CSV-Datei ein und speichert sie.
     * @param csvFile Datei mit Trainingsdaten
     * @return true, wenn der Import erfolgreich war
     */
    boolean importFromCSV(File csvFile);
}
