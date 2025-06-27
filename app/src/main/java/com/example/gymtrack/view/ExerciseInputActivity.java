package com.example.gymtrack.view;

import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;


import com.example.gymtrack.R;
import com.example.gymtrack.controller.Training.ITrainingController;
import com.example.gymtrack.controller.Training.TrainingControllerImpl;
import com.example.gymtrack.model.Dao.ExerciseDaoImpl;
import com.example.gymtrack.model.Datamanager.ExerciseEntry;
import com.example.gymtrack.model.Datamanager.ExportServiceImpl;
import com.example.gymtrack.model.Datamanager.IExportService;
import com.example.gymtrack.model.Datamanager.IImportService;
import com.example.gymtrack.model.Datamanager.ImportServiceImpl;

import java.util.List;
import java.io.File;

public class ExerciseInputActivity extends AppCompatActivity {

    private RecyclerView recyclerHistory;
    private TrainingEntryAdapter adapter;
    private List<ExerciseEntry> historyList;
    private EditText inputWeight, inputReps, inputSets;
    private Button button_save;
    private TextView textTitle;

    private String exerciseName;
    private String exerciseCategory;
    private ITrainingController controller;
    private IExportService exportService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_input);

        controller = new TrainingControllerImpl(this);
        exportService = new ExportServiceImpl();

        //  ZUERST: Daten vom Intent holen
        exerciseName = getIntent().getStringExtra("exercise_name");
        exerciseCategory = getIntent().getStringExtra("exercise_category");

        //  Dann: ActionBar setzen
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(exerciseName);
        }
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setPopupTheme(androidx.appcompat.R.style.ThemeOverlay_AppCompat_Light);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("");
        }


        // 📦 View-Verbindungen
        recyclerHistory = findViewById(R.id.recycler_history);
        recyclerHistory.setLayoutManager(new LinearLayoutManager(this));

        inputWeight = findViewById(R.id.input_weight);
        inputReps = findViewById(R.id.input_reps);
        inputSets = findViewById(R.id.input_sets);
        button_save = findViewById(R.id.button_save);
        textTitle = findViewById(R.id.text_title);

       // dao = new ExerciseDaoImpl(this);

        // Titel anzeigen
        textTitle.setText(exerciseName);

        // Historie laden
        loadHistory();

        // 👉 Swipe-to-Delete mit Bestätigung
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                ExerciseEntry entryToDelete = historyList.get(position);

                new AlertDialog.Builder(ExerciseInputActivity.this)
                        .setTitle("Eintrag löschen")
                        .setMessage("Möchtest du diesen Eintrag wirklich löschen?")
                        .setPositiveButton("Ja", (dialog, which) -> {
                            controller.deleteEntry(entryToDelete.getId());
                            loadHistory();
                            Toast.makeText(ExerciseInputActivity.this, "Eintrag gelöscht", Toast.LENGTH_SHORT).show();
                        })
                        .setNegativeButton("Abbrechen", (dialog, which) -> {
                            adapter.notifyItemChanged(position);
                        })
                        .setCancelable(false)
                        .show();
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerHistory);

        //  Speichern
        button_save.setOnClickListener(v -> {
            try {
                double weight = Double.parseDouble(inputWeight.getText().toString());
                int reps = Integer.parseInt(inputReps.getText().toString());
                int sets = Integer.parseInt(inputSets.getText().toString());

                ExerciseEntry entry = new ExerciseEntry(
                        exerciseName,
                        exerciseCategory,
                        weight,
                        reps,
                        sets,
                        System.currentTimeMillis()
                );

                controller.saveEntry(entry);
                Toast.makeText(this, "Training gespeichert!", Toast.LENGTH_SHORT).show();

                inputWeight.setText("");
                inputReps.setText("");
                inputSets.setText("");

                loadHistory();
            } catch (Exception e) {
                Toast.makeText(this, "Fehler bei Eingabe", Toast.LENGTH_SHORT).show();
            }
        });

        // Alle löschen

    }

    private void importData() {

        File importDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(importDir, exerciseName + "_training.csv");

        IImportService importService = new ImportServiceImpl(new ExerciseDaoImpl(this));
        boolean success = importService.importFromCSV(file);

        if (success) {
            Toast.makeText(this, "Daten importiert!", Toast.LENGTH_SHORT).show();
            loadHistory();
        } else {
            Toast.makeText(this, "Import fehlgeschlagen", Toast.LENGTH_SHORT).show();
        }
    }
    private void exportData() {
        try {
            File exportDir = android.os.Environment.getExternalStoragePublicDirectory(android.os.Environment.DIRECTORY_DOWNLOADS);
            if (!exportDir.exists()) exportDir.mkdirs();

            File file = new File(exportDir, exerciseName + "_training.csv");
            List<ExerciseEntry> exportList = controller.getEntriesForExercise(exerciseName);

            boolean success = exportService.exportToCSV(exportList, file);

            if (success) {
                Toast.makeText(this, "Exportiert: " + file.getAbsolutePath(), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Export fehlgeschlagen", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Export fehlgeschlagen", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void showEditDialog(ExerciseEntry entry) {
        final EditText inputWeightEdit = new EditText(this);
        final EditText inputRepsEdit = new EditText(this);
        final EditText inputSetsEdit = new EditText(this);

        inputWeightEdit.setHint("Gewicht (kg)");
        inputRepsEdit.setHint("Wiederholungen");
        inputSetsEdit.setHint("Sätze");

        inputWeightEdit.setInputType(android.text.InputType.TYPE_CLASS_NUMBER | android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL);
        inputRepsEdit.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
        inputSetsEdit.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);

        inputWeightEdit.setText(String.valueOf(entry.getWeight()));
        inputRepsEdit.setText(String.valueOf(entry.getReps()));
        inputSetsEdit.setText(String.valueOf(entry.getSets()));

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(30, 20, 30, 10);
        layout.addView(inputWeightEdit);
        layout.addView(inputRepsEdit);
        layout.addView(inputSetsEdit);

        new AlertDialog.Builder(this)
                .setTitle("Eintrag bearbeiten")
                .setView(layout)
                .setPositiveButton("Speichern", (dialog, which) -> {
                    try {
                        double newWeight = Double.parseDouble(inputWeightEdit.getText().toString());
                        int newReps = Integer.parseInt(inputRepsEdit.getText().toString());
                        int newSets = Integer.parseInt(inputSetsEdit.getText().toString());

                        entry.setWeight(newWeight);
                        entry.setReps(newReps);
                        entry.setSets(newSets);

                      controller.updateEntry(entry); // update() musst du im DAO haben!
                        loadHistory();
                        Toast.makeText(this, "Eintrag aktualisiert", Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        Toast.makeText(this, "Ungültige Eingabe", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Abbrechen", null)
                .show();
    }

    // Handle ActionBar Zurück-Pfeil
    @Override
    public boolean onSupportNavigateUp() {
        finish(); // zurück zur vorherigen Activity
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_exercise_input, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_export) {
            exportData(); // deine Methode
            return true;
        } else if (item.getItemId() == R.id.action_import) {
            importData(); // neue Methode (siehe unten)
            return true;
        }
        else if (item.getItemId() == R.id.action_delete_all) {
            confirmDeleteAll(); // Methode aus dem Button-Handler
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void confirmDeleteAll() {
        new AlertDialog.Builder(ExerciseInputActivity.this)
                .setTitle("Alle löschen")
                .setMessage("Möchtest du wirklich alle Einträge für \"" + exerciseName + "\" löschen?")
                .setPositiveButton("Ja", (dialog, which) -> {
                    controller.deleteAll(exerciseName);
                    loadHistory();
                    Toast.makeText(this, "Alle Einträge gelöscht", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Abbrechen", null)
                .show();
    }

    //  Historie laden
    private void loadHistory() {
        historyList = controller.getEntriesForExercise(exerciseName);
        adapter = new TrainingEntryAdapter(historyList);
        adapter.setOnEntryLongClickListener(entry -> {
            showEditDialog(entry);
        });
        recyclerHistory.setAdapter(adapter);
    }

}
