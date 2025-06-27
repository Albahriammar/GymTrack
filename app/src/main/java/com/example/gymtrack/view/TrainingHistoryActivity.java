package com.example.gymtrack.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymtrack.R;
import com.example.gymtrack.model.Dao.ExerciseDaoImpl;
import com.example.gymtrack.model.Datamanager.ExerciseEntry;

import java.util.List;

public class TrainingHistoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TrainingEntryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_history);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ExerciseDaoImpl dao = new ExerciseDaoImpl(this);
        List<ExerciseEntry> history = dao.getAllEntries(); // später filtern

        adapter = new TrainingEntryAdapter(history);
        recyclerView.setAdapter(adapter);
    }
}