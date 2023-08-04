package com.kyangconn.inputmarks;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.kyangconn.inputmarks.databinding.ActivityMainBinding;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    private Vector<Float> inputs = new Vector<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.kyangconn.inputmarks.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        Button Enter_mark = findViewById(R.id.enter_array);
        Button Show_mark = findViewById(R.id.show_array);
        Button Clear_mark = findViewById(R.id.func_clear);

        Enter_mark.setOnClickListener(view -> enterMarks());
        Show_mark.setOnClickListener(view -> showMarks());
        Clear_mark.setOnClickListener(view -> inputs.clear());
    }

    public void enterMarks() {
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.popup_input, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setView(dialogView);

        EditText markInput = dialogView.findViewById(R.id.dialog_inputMark);
        Button PopupConfirm = dialogView.findViewById(R.id.popup_enter);
        Button PopupCancel = dialogView.findViewById(R.id.popup_cancel);

        AlertDialog dialog = builder.create();
        dialog.setTitle("Enter Score");

        PopupConfirm.setOnClickListener(v -> {
            float Mark;

            try {
                Mark = Float.parseFloat(markInput.getText().toString().trim());
            } catch (NumberFormatException e) {
                Toast.makeText(MainActivity.this, "Please enter a valid number", Toast.LENGTH_SHORT).show();
                return;
            }

            inputs.add(Mark);
            Toast.makeText(MainActivity.this, "Input Mark success!", Toast.LENGTH_SHORT).show();

            dialog.dismiss();
        });

        PopupCancel.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

    public void showMarks() {
        startActivity(new Intent(this, SecondActivity.class)
                .putExtra("input_marks", new ParcelableVector(inputs)));
    }
}
