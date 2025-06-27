package com.example.gymtrack.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymtrack.R;
import com.example.gymtrack.model.Datamanager.ExerciseEntry;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Adapter-Klasse für RecyclerView.
 * Zeigt eine Liste von gespeicherten Trainingseinträgen im kompakten Format.
 */
public class TrainingEntryAdapter extends RecyclerView.Adapter<TrainingEntryAdapter.ViewHolder> {

    private final List<ExerciseEntry> entryList;

    /**
     * Interface für Langklick – damit kann man einen Eintrag z. B. bearbeiten.
     */
    public interface OnEntryLongClickListener {
        void onLongClick(ExerciseEntry entry);
    }

    private OnEntryLongClickListener longClickListener;

    /**
     * Listener setzen, wenn man Langklicks behandeln will (z. B. zum Bearbeiten).
     */
    public void setOnEntryLongClickListener(OnEntryLongClickListener listener) {
        this.longClickListener = listener;
    }

    /**
     * Konstruktor bekommt die Liste der Einträge.
     */
    public TrainingEntryAdapter(List<ExerciseEntry> entryList) {
        this.entryList = entryList;
    }

    /**
     * Erstellt ein neues ViewHolder-Objekt, also eine Zeile in der Liste.
     */
    @NonNull
    @Override
    public TrainingEntryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_training_entry, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Füllt die Inhalte einer einzelnen Zeile mit Daten (Eintrag).
     */
    @Override
    public void onBindViewHolder(@NonNull TrainingEntryAdapter.ViewHolder holder, int position) {
        ExerciseEntry entry = entryList.get(position);

        // Datum formatieren
        String dateFormatted = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
                .format(new Date(entry.getTimestamp()));

        // Kompakte Anzeige der wichtigsten Trainingsinfos
        String compactLine =
                entry.getWeight() + " kg • " +
                        entry.getSets() + " Sätze • " +
                        entry.getReps() + " Wdh • " +
                        dateFormatted;

        holder.textEntry.setText(compactLine);

        // Langklick aktiviert z. B. den Bearbeitungsdialog
        holder.itemView.setOnLongClickListener(v -> {
            if (longClickListener != null) {
                longClickListener.onLongClick(entryList.get(position));
            }
            return true;
        });
    }


     //Anzahl der Elemente in der Liste.

    @Override
    public int getItemCount() {
        return entryList.size();
    }


     //ViewHolder verwaltet den Zugriff auf die Views in einer Listen-Zeile.

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textEntry;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textEntry = itemView.findViewById(R.id.text_entry);
        }
    }
}
