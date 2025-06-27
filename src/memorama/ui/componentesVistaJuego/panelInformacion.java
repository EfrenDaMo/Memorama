/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memorama.ui.componentesVistaJuego;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import memorama.config.Datos;
import memorama.core.Jugador;

/**
 *
 * @author efren
 */
public class panelInformacion extends JPanel {
    private final Jugador jugador;

    private JLabel etiquetaJugador;
    private JLabel etiquetaPuntaje;
    private JLabel etiquetaTurno;
    private JButton botonPausar;
    private JButton botonRenunciar;

    public panelInformacion(Jugador jugador) {
        this.jugador = jugador;

        setLayout(new BorderLayout());
        inicializarComponentes();
    }

    public void inicializarComponentes() {
        etiquetaJugador = crearEtiqueta("Jugador " + jugador.getId());
        etiquetaPuntaje = crearEtiqueta(String.valueOf(jugador.getPuntaje()));
        etiquetaTurno = crearEtiqueta("Turno: " + (jugador.esTurno() ? "Si" : "No"));
        botonPausar = new JButton("Pausa");
        botonRenunciar = new JButton("Renunciar");

        add(etiquetaJugador, BorderLayout.NORTH);
        add(etiquetaPuntaje, BorderLayout.CENTER);
        add(etiquetaTurno, BorderLayout.CENTER);
        add(botonPausar, BorderLayout.SOUTH);
        add(botonRenunciar, BorderLayout.SOUTH);
    }

    public void actualizar() {
        etiquetaPuntaje.setText(String.valueOf(jugador.getPuntaje()));
        etiquetaTurno.setText("Turno: " + (jugador.esTurno() ? "Si" : "No"));
        repaint();
    }

    private JLabel crearEtiqueta(String texto) {
        JLabel etiqueta = new JLabel(texto);

        etiqueta.setFont(Datos.FUENTE);
        etiqueta.setHorizontalTextPosition(JLabel.CENTER);

        return etiqueta;
    }

    private JButton crearBoton(String texto, Consumer<ActionEvent> handler) {
        JButton boton = new JButton(texto);

        boton.addActionListener(e -> handler.accept(e));

        return boton;
    }
}
