package com.example.gymtrack;

import com.example.gymtrack.model.Datamanager.ExerciseEntry;
import com.example.gymtrack.model.Datamanager.ExportServiceImpl;
import com.example.gymtrack.model.Datamanager.IExportService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ExportServiceTest {

    private IExportService exportService;
    private File testFile;

    @Before
    public void setUp() {
        exportService = new ExportServiceImpl();
        testFile = new File("test_export.csv");
    }

    @After
    public void tearDown() {
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    @Test
    public void exportToCSV_createsCorrectFile() throws Exception {
        ExerciseEntry entry = new ExerciseEntry(
                "Bankdrücken", "Brust", 100.0, 10, 3, 1721900000000L // 25.07.2024
        );
        List<ExerciseEntry> entries = Arrays.asList(entry);

        boolean success = exportService.exportToCSV(entries, testFile);
        assertTrue(success);
        assertTrue(testFile.exists());

        try (BufferedReader reader = new BufferedReader(new FileReader(testFile))) {
            String header = reader.readLine();
            assertEquals("Übung,Kategorie,Gewicht,Wiederholungen,Sätze,Datum", header);

            String dataLine = reader.readLine();
            assertTrue(dataLine.contains("Bankdrücken"));
            assertTrue(dataLine.contains("Brust"));
            assertTrue(dataLine.contains("100.0"));
            assertTrue(dataLine.contains("25.07.2024")); // Datum muss formatiert sein
        }
    }
}
