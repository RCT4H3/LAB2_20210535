package com.example.telequiz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btnRedes, btnCiberseguridad, btnMicroondas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRedes = findViewById(R.id.btnRedes);
        btnCiberseguridad = findViewById(R.id.btnCiberseguridad);
        btnMicroondas = findViewById(R.id.btnMicroondas);

        btnRedes.setOnClickListener(v -> startQuiz("Redes"));
        btnCiberseguridad.setOnClickListener(v -> startQuiz("Ciberseguridad"));
        btnMicroondas.setOnClickListener(v -> startQuiz("Microondas"));
    }

    private void startQuiz(String topic) {
        Intent intent = new Intent(MainActivity.this, QuizActivity.class);
        intent.putExtra("tema", topic);
        startActivity(intent);
    }
}