package com.example.gymtrack.model.Datamanager;


import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ExportServiceImpl implements IExportService {

    @Override
    public boolean exportToCSV(List<ExerciseEntry> entries, File file) {
        try (FileWriter writer = new FileWriter(file)) {
            writer.append("Übung,Kategorie,Gewicht,Wiederholungen,Sätze,Datum\n");
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
            e.printStackTrace();
            return false;
        }
    }
}
