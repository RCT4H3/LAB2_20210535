package com.example.telequiz;

import java.util.List;

public class Question {
    private String texto;
    private List<String> opciones;
    private String respuestaCorrecta;

    public Question(String texto, List<String> opciones, String respuestaCorrecta) {
        this.texto = texto;
        this.opciones = opciones;
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public String getTexto() {
        return texto;
    }

    public List<String> getOpciones() {
        return opciones;
    }

    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }
}
