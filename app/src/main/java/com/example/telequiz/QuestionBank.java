package com.example.telequiz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionBank {

    public static List<Question> obtenerPreguntasPorTema(String tema) {
        List<Question> preguntas = new ArrayList<>();

        switch (tema) {
            case "Redes":
                preguntas.add(new Question("¿Qué protocolo se utiliza para cargar páginas web?",
                        List.of("HTTP", "FTP", "SMTP"), "HTTP"));
                preguntas.add(new Question("¿Cuál de estas es una dirección IP privada?",
                        List.of("192.168.1.1", "8.8.8.8", "172.217.0.0"), "192.168.1.1"));
                preguntas.add(new Question("¿Qué dispositivo conecta redes diferentes?",
                        List.of("Switch", "Router", "Repeater"), "Router"));
                preguntas.add(new Question("¿Qué significa DNS?",
                        List.of("Domain Name Server", "Data Network Service", "Digital Network System"), "Domain Name Server"));
                preguntas.add(new Question("¿Qué tipo de red cubre un área pequeña?",
                        List.of("WAN", "LAN", "MAN"), "LAN"));
                break;

            case "Ciberseguridad":
                preguntas.add(new Question("¿Qué es un Ransomware?",
                        List.of("Un malware que encripta tus archivos", "Un firewall avanzado", "Un tipo de antivirus"), "Un malware que encripta tus archivos"));
                preguntas.add(new Question("¿Cuál es el objetivo del phishing?",
                        List.of("Robar información personal", "Optimizar redes", "Detectar virus"), "Robar información personal"));
                preguntas.add(new Question("¿Qué protocolo cifra las comunicaciones web?",
                        List.of("HTTPS", "HTTP", "FTP"), "HTTPS"));
                preguntas.add(new Question("¿Qué algoritmo de cifrado es asimétrico y se usa comúnmente para firmas digitales?",
                        List.of("RSA", "AES", "SHA-256"), "RSA"));
                preguntas.add(new Question("¿Para qué sirve un firewall?",
                        List.of("Restringir tráfico no autorizado", "Acelerar Wi-Fi", "Conectar redes físicas"), "Restringir tráfico no autorizado"));
                break;

            case "Microondas":
                preguntas.add(new Question("¿En qué rango de frecuencias suelen operar las redes Wi-Fi?",
                        List.of("2.4 GHz y 5 GHz", "700 MHz y 900 MHz", "60 GHz y 80 GHz"), "2.4 GHz y 5 GHz"));
                preguntas.add(new Question("¿Qué problema es común en enlaces de microondas?",
                        List.of("Interferencia por obstáculos", "Sobrecarga de batería", "Fallo de disco duro"), "Interferencia por obstáculos"));
                preguntas.add(new Question("¿Qué es la zona de Fresnel en microondas?",
                        List.of("Una región de propagación donde puede haber interferencia", "Un protocolo de seguridad", "Una frecuencia específica"), "Una región de propagación donde puede haber interferencia"));
                preguntas.add(new Question("¿Qué ventaja tienen las comunicaciones por microondas?",
                        List.of("Alto ancho de banda", "Alto consumo de energía", "Mayor latencia"), "Alto ancho de banda"));
                preguntas.add(new Question("¿Qué dispositivo se usa para enfocar señales de microondas?",
                        List.of("Antena parabólica", "Switch de red", "Router"), "Antena parabólica"));
                break;
        }


        Collections.shuffle(preguntas);
        return preguntas.subList(0, 5);
    }
}