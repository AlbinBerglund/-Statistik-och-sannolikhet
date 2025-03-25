package fi.arcada.codechallenge;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.ArrayList; // import the ArrayList class

public class MainActivity extends AppCompatActivity {

    private TextView helloTextView;
    private ArrayList<Double> doubles_list =  new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helloTextView = findViewById(R.id.hello);

        doubles_list.add(3.2);
        doubles_list.add(1.2);
        doubles_list.add(3.6);
        doubles_list.add(32.1);
        doubles_list.add(23.5);

        calculate();

    }
    public void calculate() {
        Button button = findViewById(R.id.button_id);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Statistics stats = new Statistics();
                double mean = stats.calcMean(doubles_list);
                helloTextView.setText("Mean: " + mean);
            }
        });
    }


    public class Statistics {

        public double calcMean(ArrayList<Double> dataset) {
            double sum = 0;
            for (double num : dataset) {
                sum += num;
            }
            return sum / dataset.size();
        }
    }
}
