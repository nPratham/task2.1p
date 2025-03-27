package com.example.task21p;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private EditText inputValue;
    private Spinner fromSpinner, toSpinner;
    private Button convertButton;
    private TextView resultText;

    private String[] units = { "Inches", "Centimeters", "Feet", "Yards", "Miles", "Kilometers",
            "Pounds", "Kilograms", "Ounces", "Grams", "Tons",
            "Celsius", "Fahrenheit", "Kelvin"};

    private HashMap<String, Double> conversion = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputValue = findViewById(R.id.from_value);
        fromSpinner = findViewById(R.id.from_unit);
        toSpinner = findViewById(R.id.to_unit);
        convertButton = findViewById(R.id.convert_button);
        resultText = findViewById(R.id.to_value);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, units);
        fromSpinner.setAdapter(adapter);
        toSpinner.setAdapter(adapter);

        conversion.put("InchesToCentimeters", 2.54);
        conversion.put("FeetToCentimeters", 30.48);
        conversion.put("YardsToCentimeters", 91.44);
        conversion.put("MilesToKilometers", 1.60934);

        conversion.put("PoundsToKilograms", 0.453592);
        conversion.put("OuncesToGrams", 28.3495);
        conversion.put("TonsToKilograms", 907.185);

        convertButton.setOnClickListener(v -> performConversion());
    }

    private void performConversion() {
        String fromUnit = fromSpinner.getSelectedItem().toString();
        String toUnit = toSpinner.getSelectedItem().toString();

        double input = Double.parseDouble(inputValue.getText().toString());
        double result;

        if (fromUnit.equals(toUnit)) {
            result = input;
        } else if (conversion.containsKey(fromUnit  + toUnit)) {
            result = input * conversion.get(fromUnit  + toUnit);
        } else if (fromUnit.equals("Celsius") && toUnit.equals("Fahrenheit")) {
            result = (input * 9 / 5) + 32;
        } else if (fromUnit.equals("Fahrenheit") && toUnit.equals("Celsius")) {
            result = (input - 32) * 5 / 9;
        } else if (fromUnit.equals("Celsius") && toUnit.equals("Kelvin")) {
            result = (input + 273.15);

        } else if (fromUnit.equals("Kelvin") && toUnit.equals("Celsius")) {
            result = (input - 273.15);
        } else {
            resultText.setText("Invalid conversion");
            return;
        }

        resultText.setText("Result: " + result);
    }
}
