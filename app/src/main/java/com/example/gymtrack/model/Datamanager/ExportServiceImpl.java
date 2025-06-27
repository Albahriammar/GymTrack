package com.example.gymtrack.model.Datamanager;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Diese Klasse implementiert den Export-Service.
 * Sie speichert die Trainingsdaten als CSV-Datei.
 */
public class ExportServiceImpl implements IExportService {

    @Override
    public boolean exportToCSV(List<ExerciseEntry> entries, File file) {
        try (FileWriter writer = new FileWriter(file)) {
            // CSV-Kopfzeile
            writer.append("Übung,Kategorie,Gewicht,Wiederholungen,Sätze,Datum\n");

            // Jede Zeile entspricht einem Trainingseintrag
            for (ExerciseEntry e : entries) {
                String line = String.format("%s,%s,%.1f,%d,%d,%s\n",
                        e.getExerciseName(),
                        e.getCategory(),
                        e.getWeight(),
                        e.getReps(),
                        e.getSets(),
                        new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(new Date(e.getTimestamp()))
                );
                writer.append(line);
            }

            writer.flush();
            return true;

        } catch (Exception e) {
            // Fehler beim Schreiben der Datei
            e.printStackTrace();
            return false;
        }
    }
}
