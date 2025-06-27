/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memorama.core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

import memorama.util.Utilidades;

/**
 *
 * @author efren
 */
public class Temporizador {
    private final JLabel etiquetaTemporizador;
    private Timer temporizadorDeJuego;
    private int segundosRestantes = 300;

    public Temporizador() {
        etiquetaTemporizador = Utilidades.crearEtiqueta("05:00");

        temporizadorDeJuego = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                segundosRestantes--;
                actualizarEtiquetaTemporizador();

                if (segundosRestantes <= 0) {
                    temporizadorDeJuego.stop();
                }
            }
        });
    }

    public void empezarTemporizador() {
        temporizadorDeJuego.start();
    }

    public void pausarTemporizador() {
        temporizadorDeJuego.stop();
    }

    public void reiniciarTemporizador() {
        temporizadorDeJuego.restart();
    }

    public void actualizarEtiquetaTemporizador() {
        int mins = segundosRestantes / 60;
        int secs = segundosRestantes % 60;

        etiquetaTemporizador.setText(String.format("%02d:%02d", mins, secs));
    }

    public JLabel getEtiquetaTemporizador() {
        return etiquetaTemporizador;
    }
}
