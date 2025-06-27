package com.example.gymtrack;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.gymtrack.view.ExerciseInputActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ExerciseInputActivityTest {

    @Before
    public void launchActivity() {
        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), ExerciseInputActivity.class);
        intent.putExtra("exercise_name", "Bankdrücken");
        intent.putExtra("exercise_category", "Brust");

        ActivityScenario.launch(intent);
    }

    @Test
    public void testInputsAndSaveButtonVisible() {
        onView(withId(R.id.input_weight)).check(matches(isDisplayed()));
        onView(withId(R.id.input_reps)).check(matches(isDisplayed()));
        onView(withId(R.id.input_sets)).check(matches(isDisplayed()));
        onView(withId(R.id.button_save)).check(matches(isDisplayed()));
    }

    @Test
    public void testEnterValuesAndSave() {
        onView(withId(R.id.input_weight)).perform(typeText("80"), closeSoftKeyboard());
        onView(withId(R.id.input_reps)).perform(typeText("10"), closeSoftKeyboard());
        onView(withId(R.id.input_sets)).perform(typeText("3"), closeSoftKeyboard());

        onView(withId(R.id.button_save)).perform(click());
        // Erwartet: Toast "Training gespeichert!" oder Refresh – du kannst später checken, ob Liste neu geladen wurde
    }
}
