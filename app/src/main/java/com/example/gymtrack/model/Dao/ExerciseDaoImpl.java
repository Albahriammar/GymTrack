package com.example.gymtrack.model.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.gymtrack.model.Datamanager.ExerciseEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Diese Klasse ist die Umsetzung von IExerciseDao.
 * Sie verwaltet das Speichern, Abrufen, Löschen und Bearbeiten von Trainingsdaten in SQLite.
 */
public class ExerciseDaoImpl extends SQLiteOpenHelper implements IExerciseDao {

    private static final String DATABASE_NAME = "gymtrack.db"; // Name der Datenbank
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "exercise_entries"; // Tabelle für Trainingseinträge

    /**
     * Erstellt das DAO mit SQLite-Unterstützung.
     * @param context der App-Kontext
     */
    public ExerciseDaoImpl(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Wird beim ersten Start der App ausgeführt – erstellt die Datenbank.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "exerciseName TEXT, " +
                "category TEXT, " +
                "weight REAL, " +
                "reps INTEGER, " +
                "sets INTEGER, " +
                "timestamp INTEGER)";
        db.execSQL(createTable);
    }

    /**
     * Wird ausgeführt, wenn die Datenbank-Version erhöht wird.
     * Aktuell wird dabei die alte Tabelle gelöscht und neu erstellt.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    /**
     * Fügt einen neuen Trainingseintrag ein.
     */
    @Override
    public void insert(ExerciseEntry entry) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("exerciseName", entry.getExerciseName());
        values.put("category", entry.getCategory());
        values.put("weight", entry.getWeight());
        values.put("reps", entry.getReps());
        values.put("sets", entry.getSets());
        values.put("timestamp", entry.getTimestamp());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    /**
     * Aktualisiert einen bestehenden Eintrag.
     */
    @Override
    public void update(ExerciseEntry entry) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("exerciseName", entry.getExerciseName());
        values.put("category", entry.getCategory());
        values.put("weight", entry.getWeight());
        values.put("reps", entry.getReps());
        values.put("sets", entry.getSets());
        values.put("timestamp", entry.getTimestamp());

        db.update(TABLE_NAME, values, "id=?", new String[]{String.valueOf(entry.getId())});
        db.close();
    }

    /**
     * Löscht einen Eintrag anhand seiner ID.
     */
    @Override
    public void delete(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "id=?", new String[]{String.valueOf(id)});
        db.close();
    }

    /**
     * Löscht alle Einträge zu einer bestimmten Übung.
     */
    @Override
    public void deleteAllForExercise(String exerciseName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "exerciseName = ?", new String[]{exerciseName});
        db.close();
    }

    /**
     * Gibt alle gespeicherten Trainingseinträge zurück.
     */
    @Override
    public List<ExerciseEntry> getAllEntries() {
        List<ExerciseEntry> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        while (cursor.moveToNext()) {
            ExerciseEntry entry = buildEntryFromCursor(cursor);
            list.add(entry);
        }

        cursor.close();
        db.close();
        return list;
    }

    /**
     * Gibt alle Einträge zu einer bestimmten Übung zurück.
     */
    @Override
    public List<ExerciseEntry> getEntriesForExercise(String exerciseName) {
        List<ExerciseEntry> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, "exerciseName=?",
                new String[]{exerciseName}, null, null, "timestamp DESC");

        while (cursor.moveToNext()) {
            ExerciseEntry entry = buildEntryFromCursor(cursor);
            list.add(entry);
        }

        cursor.close();
        db.close();
        return list;
    }

    /**
     * Hilfsmethode: Erstellt ein ExerciseEntry-Objekt aus einer Datenbankzeile (Cursor).
     */
    private ExerciseEntry buildEntryFromCursor(Cursor cursor) {
        ExerciseEntry entry = new ExerciseEntry();
        entry.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
        entry.setExerciseName(cursor.getString(cursor.getColumnIndexOrThrow("exerciseName")));
        entry.setCategory(cursor.getString(cursor.getColumnIndexOrThrow("category")));
        entry.setWeight(cursor.getDouble(cursor.getColumnIndexOrThrow("weight")));
        entry.setReps(cursor.getInt(cursor.getColumnIndexOrThrow("reps")));
        entry.setSets(cursor.getInt(cursor.getColumnIndexOrThrow("sets")));
        entry.setTimestamp(cursor.getLong(cursor.getColumnIndexOrThrow("timestamp")));
        return entry;
    }
}
