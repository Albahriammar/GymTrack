package com.example.gymtrack.model.Datamanager;

public class ExerciseEntry {
    private int id;
    private String exerciseName;
    private String category;
    private double weight;
    private int reps;
    private int sets;
    private long timestamp;

    // Leerer Konstruktor (für z.B. SQLite-Helper oder Default-Wertsetzung)
    public ExerciseEntry() {
    }

    // Voller Konstruktor

    public ExerciseEntry(String exerciseName, String category, double weight, int reps, int sets, long timestamp) {
        this.exerciseName = exerciseName;
        this.category = category;
        this.weight = weight;
        this.reps = reps;
        this.sets = sets;
        this.timestamp = timestamp;
    }

    // Getter & Setter
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
