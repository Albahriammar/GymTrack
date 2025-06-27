package com.example.gymtrack.model.Datamanager;

import android.util.Log;

import com.example.gymtrack.model.Dao.IExerciseDao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Locale;

/**
 * Diese Klasse implementiert den Import-Service.
 * Sie liest eine CSV-Datei und speichert die Daten in der Datenbank.
 */
public class ImportServiceImpl implements IImportService {

    private final IExerciseDao dao;

    /**
     * Konstruktor mit DAO – damit können wir Daten direkt in die Datenbank speichern.
     */
    public ImportServiceImpl(IExerciseDao dao) {
        this.dao = dao;
    }

    @Override
    public boolean importFromCSV(File csvFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // Kopfzeile überspringen
                    continue;
                }

                // CSV wird in Teile gesplittet
                String[] parts = line.split(",");

                if (parts.length != 6) continue; // Zeile ungültig

                String name = parts[0].trim();
                String category = parts[1].trim();
                double weight = Double.parseDouble(parts[2].trim());
                int reps = Integer.parseInt(parts[3].trim());
                int sets = Integer.parseInt(parts[4].trim());
                long timestamp = parseDate(parts[5].trim());

                // Neuen Trainingseintrag erstellen und speichern
                ExerciseEntry entry = new ExerciseEntry(name, category, weight, reps, sets, timestamp);
                dao.insert(entry);
            }

            return true;

        } catch (Exception e) {
            Log.e("ImportService", "Fehler beim Importieren: " + e.getMessage(), e);
            return false;
        }
    }

    /**
     * Konvertiert ein Datums-String im Format dd.MM.yyyy zu einem Timestamp.
     * Wenn Format nicht passt, wird die aktuelle Zeit genutzt.
     */
    private long parseDate(String dateStr) {
        try {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
            return sdf.parse(dateStr).getTime();
        } catch (Exception e) {
            return System.currentTimeMillis(); // Fallback-Zeit
        }
    }
}
