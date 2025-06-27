package com.example.gymtrack.model.Datamanager;

/**
 * Diese Klasse beschreibt einen Trainingseintrag (z. B. Bankdrücken 80kg, 3 Sätze).
 * Wird zur Speicherung und Darstellung genutzt.
 */
public class ExerciseEntry {

    private int id;
    private String exerciseName;
    private String category;
    private double weight;
    private int reps;
    private int sets;
    private long timestamp;

    /**
     * Leerer Konstruktor (z. B. für Datenbank-Helper oder Tests).
     */
    public ExerciseEntry() {}

    /**
     * Konstruktor mit allen Feldern (für neue Einträge).
     */
    public ExerciseEntry(String exerciseName, String category, double weight, int reps, int sets, long timestamp) {
        this.exerciseName = exerciseName;
        this.category = category;
        this.weight = weight;
        this.reps = reps;
        this.sets = sets;
        this.timestamp = timestamp;
    }

    // Getter und Setter für alle Felder

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getExerciseName() {
        return exerciseName;
    }
    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getReps() {
        return reps;
    }
    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getSets() {
        return sets;
    }
    public void setSets(int sets) {
        this.sets = sets;
    }

    public long getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
