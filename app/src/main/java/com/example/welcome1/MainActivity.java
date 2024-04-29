package com.example.welcome1;

import android.widget.Button;
import android.widget.TextView;
import java.util.Map;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> selectedDays = new ArrayList<>();
    private Map<String, Integer> voteCount = new HashMap<>();
    private TextView voteCountTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CheckBox mondayCheckBox = findViewById(R.id.monday_checkbox);
        CheckBox tuesdayCheckBox = findViewById(R.id.tuesday_checkbox);
        CheckBox wednesdayCheckBox = findViewById(R.id.wednesday_checkbox);
        CheckBox thursdayCheckBox = findViewById(R.id.thursday_checkbox);
        CheckBox fridayCheckBox = findViewById(R.id.friday_checkbox);

        // Botones
        Button voteButton = findViewById(R.id.vote_button);
        Button showResultsButton = findViewById(R.id.show_results_button);
        Button restartButton = findViewById(R.id.restart_button);

        voteCountTextView = findViewById(R.id.vote_count);

        voteButton.setOnClickListener(v -> vote());
        showResultsButton.setOnClickListener(v -> showResults());
        restartButton.setOnClickListener(v -> restart());

        CompoundButton.OnCheckedChangeListener onCheckedChangeListener = (buttonView, isChecked) -> {
            String day = "";
            int id = buttonView.getId();
            if (isChecked) {
                if (id == R.id.monday_checkbox) {
                    day = "Lunes";
                } else if (id == R.id.tuesday_checkbox) {
                    day = "Martes";
                } else if (id == R.id.wednesday_checkbox) {
                    day = "Miércoles";
                } else if (id == R.id.thursday_checkbox) {
                    day = "Jueves";
                } else if (id == R.id.friday_checkbox) {
                    day = "Viernes";
                }
                selectedDays.add(day);
            } else {
                if (id == R.id.monday_checkbox) {
                    day = "Lunes";
                } else if (id == R.id.tuesday_checkbox) {
                    day = "Martes";
                } else if (id == R.id.wednesday_checkbox) {
                    day = "Miércoles";
                } else if (id == R.id.thursday_checkbox) {
                    day = "Jueves";
                } else if (id == R.id.friday_checkbox) {
                    day = "Viernes";
                }
                selectedDays.remove(day);
            }
        };

        mondayCheckBox.setOnCheckedChangeListener(onCheckedChangeListener);
        tuesdayCheckBox.setOnCheckedChangeListener(onCheckedChangeListener);
        wednesdayCheckBox.setOnCheckedChangeListener(onCheckedChangeListener);
        thursdayCheckBox.setOnCheckedChangeListener(onCheckedChangeListener);
        fridayCheckBox.setOnCheckedChangeListener(onCheckedChangeListener);
    }

    // COMPORTAMIENTO DE BOTONES

    private void vote() {
        // Incrementar el recuento de votos para cada día seleccionado
        for (String day : selectedDays) {
            voteCount.put(day, voteCount.getOrDefault(day, 0) + 1);
        }

        // Limpiar los CheckBoxes
        clearCheckBoxes();

        // Actualizar el contador de votos
        updateVoteCount();

        Toast.makeText(this, "¡Voto registrado!", Toast.LENGTH_SHORT).show();
    }

    private void showResults() {
        if (voteCount.isEmpty()) {
            Toast.makeText(this, "Por favor, vote antes de mostrar resultados.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Encontrar el día con más votos
        String mostVotedDay = "";
        int maxVotes = 0;
        for (Map.Entry<String, Integer> entry : voteCount.entrySet()) {
            if (entry.getValue() > maxVotes) {
                mostVotedDay = entry.getKey();
                maxVotes = entry.getValue();
            }
        }

        // Mostrar los resultados
        String message = "El día más votado es: " + mostVotedDay + " con " + maxVotes + " votos.";
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void clearCheckBoxes() {
        // Desmarcar todos los CheckBoxes
        CheckBox mondayCheckBox = findViewById(R.id.monday_checkbox);
        CheckBox tuesdayCheckBox = findViewById(R.id.tuesday_checkbox);
        CheckBox wednesdayCheckBox = findViewById(R.id.wednesday_checkbox);
        CheckBox thursdayCheckBox = findViewById(R.id.thursday_checkbox);
        CheckBox fridayCheckBox = findViewById(R.id.friday_checkbox);

        mondayCheckBox.setChecked(false);
        tuesdayCheckBox.setChecked(false);
        wednesdayCheckBox.setChecked(false);
        thursdayCheckBox.setChecked(false);
        fridayCheckBox.setChecked(false);
    }

    private void updateVoteCount() {
        // Mostrar la cantidad de votos registrados
        int totalVotes = 0;
        for (int count : voteCount.values()) {
            totalVotes += count;
        }
        voteCountTextView.setText("Votos registrados: " + totalVotes);
    }

    private void restart() {
        // Reiniciar el conteo de votos y limpiar el texto
        voteCount.clear();
        voteCountTextView.setText("Votos registrados: 0");

        Toast.makeText(this, "Conteo de votos reiniciado.", Toast.LENGTH_SHORT).show();
    }
}
