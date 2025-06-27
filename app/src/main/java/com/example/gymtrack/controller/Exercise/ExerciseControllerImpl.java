package com.example.gymtrack.controller.Exercise;

import android.content.Context;
import android.content.Intent;

import com.example.gymtrack.view.ExerciseInputActivity;

public class ExerciseControllerImpl implements IExerciseController {
    @Override
    public void openExerciseInput(Context context, String exerciseName, String category) {
        Intent intent = new Intent(context, ExerciseInputActivity.class);
        intent.putExtra("exercise_name", exerciseName);
        intent.putExtra("exercise_category", category);
        context.startActivity(intent);
    }
}
