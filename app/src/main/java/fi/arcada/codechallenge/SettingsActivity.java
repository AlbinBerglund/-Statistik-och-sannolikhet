package fi.arcada.codechallenge;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;

public class SettingsActivity extends AppCompatActivity {
    private Button saveButton;
    private EditText inputEditText; // EditText to input the text
    private SharedPreferences sharedPreferences;

    private static final String PREFS_NAME = "AppCounterPrefs";
    private static final String SAVED_TEXT_KEY = "savedText"; // Key for the saved text

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        saveButton = findViewById(R.id.saveButton);
        inputEditText = findViewById(R.id.inputEditText); // EditText for user input
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        saveButton.setOnClickListener(v -> {
            // Save the text entered by the user
            String userInput = inputEditText.getText().toString();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(SAVED_TEXT_KEY, userInput);
            editor.apply();
        });
    }
}
