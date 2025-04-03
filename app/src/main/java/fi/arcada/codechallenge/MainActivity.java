package fi.arcada.codechallenge;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Vi vill ha vår datamodel och en RecyclerView adapter
    private List<DataModel> dataList = new ArrayList<>();
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Hitta våra saker!
        EditText inputField1 = findViewById(R.id.inputField1);
        EditText inputField2 = findViewById(R.id.inputField2);
        Button addButton = findViewById(R.id.addButton);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(dataList);
        recyclerView.setAdapter(adapter);

        // Vi trycker på knappen!
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value1 = inputField1.getText().toString();
                String value2 = inputField2.getText().toString();
                // Error handlers!
                if (!value1.isEmpty() && !value2.isEmpty()) {
                    // Ta värdena och sätt dem i vår dataList
                    dataList.add(new DataModel(value1, value2));
                    // berättar till vår adapter att det har kommit en update
                    // Det finns även mer specifika metoder (item update, item add... )
                    adapter.notifyDataSetChanged();
                    inputField1.setText("");
                    inputField2.setText("");

                    //CalcMean
                    double meanValue = calculateMean();
                    //LogCat!
                    Log.d("MeanValue", "Mean value of integers: " + meanValue);
                }
            }
        });
    }

    private double calculateMean() {
        int sum = 0;
        int count = 0;
        for (DataModel data : dataList) {
            // Try & Catch
            try {
                int value = Integer.parseInt(data.getValue2());
                sum += value;
                count++;
            } catch (NumberFormatException e) {
                // Handle the case where the value is not an integer
            }
        }
        return count > 0 ? (double) sum / count : 0;
    }
}