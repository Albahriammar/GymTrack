package com.example.gymtrack.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymtrack.R;
import com.example.gymtrack.model.Dao.ExerciseDaoImpl;
import com.example.gymtrack.model.Datamanager.ExerciseEntry;

import java.util.List;

/**
 * Diese Activity zeigt alle gespeicherten Trainingseinträge.
 * Die Daten kommen direkt aus der lokalen SQLite-Datenbank.
 */
public class TrainingHistoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TrainingEntryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_history);

        // RecyclerView konfigurieren
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Daten holen aus der Datenbank (DAO)
        ExerciseDaoImpl dao = new ExerciseDaoImpl(this);
        List<ExerciseEntry> history = dao.getAllEntries(); // Liste aller Einträge

        // Adapter setzen, um Daten anzuzeigen
        adapter = new TrainingEntryAdapter(history);
        recyclerView.setAdapter(adapter);
    }
}
