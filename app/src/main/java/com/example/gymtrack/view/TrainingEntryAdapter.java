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



public class TrainingEntryAdapter extends RecyclerView.Adapter<TrainingEntryAdapter.ViewHolder> {

    private final List<ExerciseEntry> entryList;
    public interface OnEntryLongClickListener {
        void onLongClick(ExerciseEntry entry);
    }

    private OnEntryLongClickListener longClickListener;

    public void setOnEntryLongClickListener(OnEntryLongClickListener listener) {
        this.longClickListener = listener;
    }

    public TrainingEntryAdapter(List<ExerciseEntry> entryList) {
        this.entryList = entryList;
    }

    @NonNull
    @Override
    public TrainingEntryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_training_entry, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainingEntryAdapter.ViewHolder holder, int position) {
        ExerciseEntry entry = entryList.get(position);

        String dateFormatted = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
                .format(new Date(entry.getTimestamp()));

        String compactLine =
                entry.getWeight() + " kg • " +
                        entry.getSets() + " Sätze • " +
                        entry.getReps() + " Wdh • " +
                        dateFormatted;

        holder.textEntry.setText(compactLine);
        holder.itemView.setOnLongClickListener(v -> {
            if (longClickListener != null) {
                longClickListener.onLongClick(entryList.get(position));
            }
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return entryList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textEntry;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textEntry = itemView.findViewById(R.id.text_entry);
        }
    }
}