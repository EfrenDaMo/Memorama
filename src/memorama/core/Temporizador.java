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
    private final Juego juego;

    private final JLabel etiquetaTiempo;
    private Timer temporizador;
    private int segundosRestantes = 300;

    public Temporizador(Juego juego) {
        this.juego = juego;
        etiquetaTiempo = Utilidades.crearEtiqueta("05:00");

        temporizador = new Timer(1000, (ActionEvent e) -> {
			segundosRestantes--;
			actualizarEtiquetaTiempo();
			
			if (segundosRestantes <= 0) {
				juego.finalizoTiempo();
				temporizador.stop();
			}
		});
    }

    public void iniciar() {
        if (!temporizador.isRunning()) {
            temporizador.start();
        }
    }

    public void pausar() {
        if (temporizador.isRunning()) {
            temporizador.stop();
        }
    }

    public void reanudar() {
        temporizador.start();
    }

    public void reiniciar() {
        temporizador.stop();
        segundosRestantes = 300;
        actualizarEtiquetaTiempo();
    }

    public void actualizarEtiquetaTiempo() {
        SwingUtilities.invokeLater(() -> {
            try {
                int mins = segundosRestantes / 60;
                int secs = segundosRestantes % 60;
                etiquetaTiempo.setText(String.format("%02d:%02d", mins, secs));
            } catch (Exception e) {
                Utilidades.mostrarDialogoError(
                        null,
                        "Error actualizando tiempo: " + e.getMessage(),
                        "Error de UI");
            }
        });
    }

    public JLabel getEtiquetaTiempo() {
        return etiquetaTiempo;
    }
}
