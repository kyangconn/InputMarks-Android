package com.kyangconn.inputmarks;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.kyangconn.inputmarks.databinding.ActivitySecondBinding;

import java.util.Objects;
import java.util.Vector;

public class SecondActivity extends AppCompatActivity {

    private Vector<Float> inputs = new Vector<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.kyangconn.inputmarks.databinding.ActivitySecondBinding binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("");

        Button Func_return = findViewById(R.id.func_return);

        ParcelableVector inputMarks = getIntent().getParcelableExtra("input_marks");

        if (inputMarks == null) {
            Log.e("SecondActivity", "Data is null");
            Toast.makeText(SecondActivity.this, "Scores is null", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SecondActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        inputs = inputMarks.getVector();

        setShowMarks();

        Func_return.setOnClickListener(view -> {
            inputs.clear();
            Intent intent = new Intent(SecondActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    public void setShowMarks() {
        TextView value1 = findViewById(R.id.value1);
        TextView value2 = findViewById(R.id.value2);
        TextView value3 = findViewById(R.id.value3);
        TextView value4 = findViewById(R.id.value4);
        TextView value5 = findViewById(R.id.value5);

        if (inputs.size() == 0) {
            value1.setText("N/A");
            value2.setText("N/A");
            value3.setText("N/A");
            value4.setText("N/A");
            value5.setText("N/A");
            return;
        }

        value1.setText(String.valueOf(inputs.size()));

        int i;
        Float tmpFloat = 0F;
        for (i = 0; i < inputs.size(); i++)
            tmpFloat += inputs.get(i);
        value2.setText(String.valueOf(tmpFloat));

        tmpFloat /= inputs.size();
        value3.setText(String.valueOf(tmpFloat));

        tmpFloat = inputs.get(0);
        for (i = 0; i < inputs.size(); i++) {
            if(inputs.get(i) > tmpFloat)
                tmpFloat = inputs.get(i);
        }
        value4.setText(String.valueOf(tmpFloat));

        tmpFloat = inputs.get(0);
        for (i = 0; i < inputs.size(); i++) {
            if(inputs.get(i) < tmpFloat)
                tmpFloat = inputs.get(i);
        }
        value5.setText(String.valueOf(tmpFloat));
    }
}
