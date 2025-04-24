package fi.arcada.codechallenge;

import android.os.Bundle;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        double[] temperatures = {17.5, 16.0, 16.5, 15.0, 17.5, 18.0, 15.5, 20.0, 19.5, 16.0};

        // Calculate SMA with window size 3
        double[] smaResults = movingAvg(temperatures, 3);

        // Get reference to TextView
        TextView resultView = findViewById(R.id.resultTextView);

        // Display results in TextView
        resultView.setText("From " + Arrays.toString(temperatures) + " SMA Results: " + Arrays.toString(smaResults));


        // Show Toast
        Toast.makeText(this, "SMA Calculated!", Toast.LENGTH_SHORT).show();
    }

    public double[] movingAvg(double[] values, int window) {
        if (values == null || window <= 0 || values.length < window) {
            return new double[0];
        }

        int smaLength = values.length - window + 1;
        double[] sma = new double[smaLength];

        for (int i = 0; i < smaLength; i++) {
            double sum = 0;
            for (int k = 0; k < window; k++) {
                sum += values[i + k];
            }
            sma[i] = sum / window;
        }
        return sma;
    }
}