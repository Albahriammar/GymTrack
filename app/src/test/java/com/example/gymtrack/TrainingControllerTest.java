package com.example.gymtrack;

import static org.mockito.Mockito.*;

import com.example.gymtrack.controller.Training.TrainingControllerImpl;
import com.example.gymtrack.model.Datamanager.ExerciseEntry;
import com.example.gymtrack.model.Dao.IExerciseDao;
import com.example.gymtrack.controller.Training.ITrainingController;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TrainingControllerTest {

    private IExerciseDao daoMock;
    private ITrainingController controller;

    @Before
    public void setUp() {
        daoMock = mock(IExerciseDao.class);
        controller = new TrainingControllerImpl(daoMock); // ACHTUNG: du brauchst dafür einen Konstruktor mit DAO!
    }

    @Test
    public void testSaveEntryDelegatesToDao() {
        ExerciseEntry entry = new ExerciseEntry("Bankdrücken", "Brust", 80, 10, 3, System.currentTimeMillis());
        controller.saveEntry(entry);
        verify(daoMock).insert(entry);
    }

    @Test
    public void testGetAllEntriesReturnsCorrectList() {
        List<ExerciseEntry> mockList = Arrays.asList(
                new ExerciseEntry("Bankdrücken", "Brust", 80, 10, 3, System.currentTimeMillis())
        );
        when(daoMock.getAllEntries()).thenReturn(mockList);

        List<ExerciseEntry> result = controller.getAllEntries();
        assertEquals(1, result.size());
        assertEquals("Bankdrücken", result.get(0).getExerciseName());
    }

    @Test
    public void testDeleteEntryCallsDao() {
        controller.deleteEntry(5);
        verify(daoMock).delete(5);
    }
}
