package com.example.gymtrack;

import static org.mockito.Mockito.*;

import com.example.gymtrack.model.Dao.IExerciseDao;
import com.example.gymtrack.model.Datamanager.ExerciseEntry;
import com.example.gymtrack.model.Datamanager.ImportServiceImpl;
import com.example.gymtrack.model.Datamanager.IImportService;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;

public class ImportServiceTest {

    private IExerciseDao mockDao;
    private IImportService importService;

    @Before
    public void setup() {
        mockDao = mock(IExerciseDao.class);
        importService = new ImportServiceImpl(mockDao);
    }

    @Test
    public void testImportFromCSV_successfullyInserts() throws Exception {
        // 1. Dummy CSV-Datei im Temp-Verzeichnis
        File tempFile = File.createTempFile("test", ".csv");
        FileWriter writer = new FileWriter(tempFile);
        writer.write("Name,Category,Weight,Reps,Sets,Date\n"); // Header
        writer.write("Bankdrücken,Brust,80.0,10,3,25.06.2024\n");
        writer.close();

        // 2. Methode aufrufen
        boolean result = importService.importFromCSV(tempFile);

        // 3. Überprüfen, ob dao.insert() aufgerufen wurde
        verify(mockDao, times(1)).insert(any(ExerciseEntry.class));
        assert result;
    }
}
