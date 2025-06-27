/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memorama.core;

import javax.swing.*;
import java.awt.event.*;

import memorama.util.Utilidades;

/**
 *
 * @author efren
 */
public class Temporizador {
    private final JLabel etiquetaTiempo;
    private Timer temporizador;
    private int segundosRestantes = 300;

    public Temporizador() {
        etiquetaTiempo = Utilidades.crearEtiqueta("05:00");

        temporizador = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                segundosRestantes--;
                actualizarEtiquetaTiempo();

                if (segundosRestantes <= 0) {
                    temporizador.stop();
                }
            }
        });
    }

    public void iniciar() {
        temporizador.start();
    }

    public void pausar() {
        temporizador.stop();
    }

    public void reiniciar() {
        temporizador.restart();
    }

    public void actualizarEtiquetaTiempo() {
        int mins = segundosRestantes / 60;
        int secs = segundosRestantes % 60;

        etiquetaTiempo.setText(String.format("%02d:%02d", mins, secs));
    }

    public JLabel getEtiquetaTiempo() {
        return etiquetaTiempo;
    }
}
