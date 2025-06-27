package com.example.gymtrack;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.example.gymtrack.model.Datamanager.ExerciseEntry;
import com.example.gymtrack.model.Dao.ExerciseDaoImpl;
import com.example.gymtrack.model.Dao.IExerciseDao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class ExerciseDaoImplTest {

    private IExerciseDao dao;

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        dao = new ExerciseDaoImpl(context);  // nutzt SQLiteOpenHelper direkt
    }

    @After
    public void tearDown() {
        // Cleanup (Datenbank bleibt auf Gerät, da real, kein Room)
    }

    @Test
    public void insertAndReadEntry() {
        ExerciseEntry entry = new ExerciseEntry("Bankdrücken", "Brust", 80.0, 10, 3, System.currentTimeMillis());
        dao.insert(entry);

        List<ExerciseEntry> results = dao.getEntriesForExercise("Bankdrücken");
        assertFalse(results.isEmpty());
        ExerciseEntry saved = results.get(0);

        assertEquals("Bankdrücken", saved.getExerciseName());
        assertEquals("Brust", saved.getCategory());
        assertEquals(80.0, saved.getWeight(), 0.01);
    }

    @Test
    public void updateEntry() {
        ExerciseEntry entry = new ExerciseEntry("Rudern", "Rücken", 60, 12, 4, System.currentTimeMillis());
        dao.insert(entry);
        List<ExerciseEntry> list = dao.getEntriesForExercise("Rudern");
        ExerciseEntry e = list.get(0);

        e.setWeight(75);
        dao.update(e);

        List<ExerciseEntry> updatedList = dao.getEntriesForExercise("Rudern");
        assertEquals(75.0, updatedList.get(0).getWeight(), 0.01);
    }

    @Test
    public void deleteEntry() {
        ExerciseEntry entry = new ExerciseEntry("Beinpresse", "Beine", 150, 10, 3, System.currentTimeMillis());
        dao.insert(entry);
        List<ExerciseEntry> list = dao.getEntriesForExercise("Beinpresse");
        assertFalse(list.isEmpty());

        int id = list.get(0).getId();
        dao.delete(id);

        List<ExerciseEntry> afterDelete = dao.getEntriesForExercise("Beinpresse");
        assertTrue(afterDelete.isEmpty());
    }

    @Test
    public void deleteAllForExercise() {
        dao.insert(new ExerciseEntry("Butterfly", "Brust", 40, 12, 3, System.currentTimeMillis()));
        dao.insert(new ExerciseEntry("Butterfly", "Brust", 45, 10, 3, System.currentTimeMillis()));

        List<ExerciseEntry> before = dao.getEntriesForExercise("Butterfly");
        assertEquals(2, before.size());

        dao.deleteAllForExercise("Butterfly");
        List<ExerciseEntry> after = dao.getEntriesForExercise("Butterfly");
        assertTrue(after.isEmpty());
    }
}
