package com.example.gymtrack.model.Datamanager;

import java.io.File;
import java.util.List;

/**
 * Diese Schnittstelle beschreibt den Export-Service.
 * Er wird genutzt, um Trainingsdaten in eine CSV-Datei zu schreiben.
 */
public interface IExportService {

    /**
     * Exportiert alle Einträge in eine CSV-Datei.
     * @param entries Liste der Trainingsdaten
     * @param file Ziel-Datei, in die geschrieben wird
     * @return true, wenn der Export erfolgreich war
     */
    boolean exportToCSV(List<ExerciseEntry> entries, File file);
}
