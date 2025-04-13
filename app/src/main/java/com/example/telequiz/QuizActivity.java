package com.example.telequiz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private List<Question> preguntas;
    private List<String> respuestasUsuario;
    private int preguntaActual = 0;
    private int puntaje = 0;
    private String tema;

    private TextView txtTema, txtPregunta, txtPuntaje;
    private Button opc1, opc2, opc3, btnSiguiente, btnAnterior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button btnMenu = findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(v -> {
            finish();
        });

        tema = getIntent().getStringExtra("tema");
        preguntas = QuestionBank.obtenerPreguntasPorTema(tema);
        respuestasUsuario = new ArrayList<>(Collections.nCopies(preguntas.size(), null));

        txtTema = findViewById(R.id.txtTema);
        txtPregunta = findViewById(R.id.txtPregunta);
        txtPuntaje = findViewById(R.id.txtPuntaje);
        opc1 = findViewById(R.id.opc1);
        opc2 = findViewById(R.id.opc2);
        opc3 = findViewById(R.id.opc3);
        btnSiguiente = findViewById(R.id.btnSiguiente);
        btnAnterior = findViewById(R.id.btnAnterior);

        txtTema.setText(tema);
        mostrarPregunta(preguntaActual);

        View.OnClickListener seleccionListener = view -> {
            Button seleccion = (Button) view;
            verificarRespuesta(seleccion.getText().toString());
        };

        opc1.setOnClickListener(seleccionListener);
        opc2.setOnClickListener(seleccionListener);
        opc3.setOnClickListener(seleccionListener);

        btnSiguiente.setOnClickListener(v -> {
            if (preguntaActual < preguntas.size() - 1) {
                preguntaActual++;
                mostrarPregunta(preguntaActual);
            } else {
                finalizarQuiz();
            }
        });

        btnAnterior.setOnClickListener(v -> {
            if (preguntaActual > 0) {
                preguntaActual--;

                TextView txtResultadoFinal = findViewById(R.id.txtResultadoFinal);
                if (txtResultadoFinal.getVisibility() == View.VISIBLE) {
                    txtResultadoFinal.setVisibility(View.GONE);
                    txtPregunta.setVisibility(View.VISIBLE);
                    opc1.setVisibility(View.VISIBLE);
                    opc2.setVisibility(View.VISIBLE);
                    opc3.setVisibility(View.VISIBLE);
                    btnSiguiente.setEnabled(true);
                }

                mostrarPregunta(preguntaActual);
            }
        });

    }

    private void mostrarPregunta(int indice) {
        Question q = preguntas.get(indice);
        txtPregunta.setText((indice + 1) + ". " + q.getTexto());
        List<String> opciones = q.getOpciones();

        opc1.setText(opciones.get(0));
        opc2.setText(opciones.get(1));
        opc3.setText(opciones.get(2));
        txtPuntaje.setText("Puntaje\n" + puntaje);

        Button[] botones = {opc1, opc2, opc3};
        for (Button b : botones) {
            b.setEnabled(true);
            b.setBackgroundTintList(getColorStateList(R.color.original_button_color));
        }

        String respuesta = respuestasUsuario.get(indice);
        if (respuesta != null) {
            marcarBotones(q, respuesta);
            deshabilitarOpciones();
        }
    }

    private void verificarRespuesta(String respuestaSeleccionada) {
        if (!opc1.isEnabled()) return;

        Question actual = preguntas.get(preguntaActual);
        respuestasUsuario.set(preguntaActual, respuestaSeleccionada);

        if (respuestaSeleccionada.equals(actual.getRespuestaCorrecta())) {
            puntaje += 2;
            Toast.makeText(this, "¡Correcto!", Toast.LENGTH_SHORT).show();
        } else {
            puntaje -= 2;
            Toast.makeText(this, "Incorrecto", Toast.LENGTH_SHORT).show();
        }

        txtPuntaje.setText("Puntaje\n" + puntaje);
        marcarBotones(actual, respuestaSeleccionada);
        deshabilitarOpciones();

        if (preguntaActual == preguntas.size() - 1) {
            mostrarResultadoFinal();
        }
    }


    private void marcarBotones(Question pregunta, String seleccionada) {
        String correcta = pregunta.getRespuestaCorrecta();
        Button[] botones = {opc1, opc2, opc3};
        for (Button b : botones) {
            String texto = b.getText().toString();
            if (seleccionada.equals(correcta) && texto.equals(correcta)) {
                b.setBackgroundTintList(getColorStateList(android.R.color.holo_green_dark));
            } else if (!seleccionada.equals(correcta) && texto.equals(seleccionada)) {
                b.setBackgroundTintList(getColorStateList(android.R.color.holo_red_dark));
            } else {
                b.setBackgroundTintList(getColorStateList(R.color.original_button_color));
            }
        }
    }


    private void deshabilitarOpciones() {
        opc1.setEnabled(false);
        opc2.setEnabled(false);
        opc3.setEnabled(false);
    }

    private void finalizarQuiz() {
        txtPregunta.setVisibility(View.GONE);
        opc1.setVisibility(View.GONE);
        opc2.setVisibility(View.GONE);
        opc3.setVisibility(View.GONE);


        TextView txtResultadoFinal = findViewById(R.id.txtResultadoFinal);
        txtResultadoFinal.setVisibility(View.VISIBLE);
        txtResultadoFinal.setText("Puntaje final: " + puntaje);

        if (puntaje > 0) {
            txtResultadoFinal.setBackgroundTintList(getColorStateList(android.R.color.holo_green_dark));
        } else {
            txtResultadoFinal.setBackgroundTintList(getColorStateList(android.R.color.holo_red_dark));
        }

        btnSiguiente.setEnabled(false);
    }

    private void mostrarResultadoFinal() {
        txtPregunta.setVisibility(View.GONE);
        opc1.setVisibility(View.GONE);
        opc2.setVisibility(View.GONE);
        opc3.setVisibility(View.GONE);

        TextView txtResultadoFinal = findViewById(R.id.txtResultadoFinal);
        txtResultadoFinal.setVisibility(View.VISIBLE);
        txtResultadoFinal.setText("Puntaje final: " + puntaje);

        if (puntaje > 0) {
            txtResultadoFinal.setBackgroundTintList(getColorStateList(android.R.color.holo_green_dark));
        } else {
            txtResultadoFinal.setBackgroundTintList(getColorStateList(android.R.color.holo_red_dark));
        }

        btnSiguiente.setEnabled(false);
    }


}
