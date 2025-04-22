package fi.arcada.codechallenge;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private boolean isInitialLaunch = true;
    private TextView appCountTextView;
    private TextView welcomeTextView;
    private TextView savedTextView; // Add a TextView to display the saved text
    private FloatingActionButton settingsButton;

    private SharedPreferences sharedPreferences;

    private static final String PREFS_NAME = "AppCounterPrefs";
    private static final String APP_COUNTER_KEY = "appCounter";
    private static final String SAVED_TEXT_KEY = "savedText"; // Key for the saved text

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appCountTextView = findViewById(R.id.appCount);
        welcomeTextView = findViewById(R.id.welcomeView);
        savedTextView = findViewById(R.id.savedTextView); // Initialize the new TextView

        settingsButton = findViewById(R.id.myFloatingActionButton);

        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        welcomeTextView.setText("Hello!");

        // Retrieve and increment the app counter
        int appCounter = sharedPreferences.getInt(APP_COUNTER_KEY, 0);
        appCounter++;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(APP_COUNTER_KEY, appCounter);
        editor.apply();
        appCountTextView.setText(String.valueOf(appCounter));

        // Retrieve and display saved text from SharedPreferences
        String savedText = sharedPreferences.getString(SAVED_TEXT_KEY, "No saved text");
        savedTextView.setText(savedText);

        settingsButton.setOnClickListener(v -> openSettings());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isInitialLaunch) {
            welcomeTextView.setText("Hello again");
        } else {
            isInitialLaunch = false;
        }

        // Retrieve and display the saved text again in case it was updated
        String savedText = sharedPreferences.getString(SAVED_TEXT_KEY, "No saved text");
        savedTextView.setText(savedText);
    }

    private void openSettings() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}
