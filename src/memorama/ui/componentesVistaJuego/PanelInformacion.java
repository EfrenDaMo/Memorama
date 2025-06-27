/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memorama.ui.componentesVistaJuego;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import memorama.config.Datos;
import memorama.core.Jugador;
import memorama.util.Utilidades;

/**
 *
 * @author efren
 */
public class PanelInformacion extends JPanel {
    private final Jugador jugador;

    private JLabel etiquetaJugador;
    private JLabel etiquetaPuntaje;
    private JLabel etiquetaTurno;
    private JButton botonPausar;
    private JButton botonRenunciar;

    public PanelInformacion(Jugador jugador) {
        this.jugador = jugador;

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(213, 720));
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        etiquetaJugador = Utilidades.crearEtiqueta("Jugador " + jugador.getId());
        etiquetaPuntaje = Utilidades.crearEtiqueta("00");
        etiquetaTurno = Utilidades.crearEtiqueta("Turno: " + (jugador.esTurno() ? "Si" : "No"));
        botonPausar = new JButton("Pausa");
        botonRenunciar = new JButton("Renunciar");

        JPanel centro = new JPanel(new BorderLayout());
        JPanel sur = new JPanel(new BorderLayout());

        centro.add(etiquetaPuntaje, BorderLayout.NORTH);
        centro.add(etiquetaTurno, BorderLayout.SOUTH);
        sur.add(botonPausar, BorderLayout.WEST);
        sur.add(botonRenunciar, BorderLayout.EAST);

        add(etiquetaJugador, BorderLayout.NORTH);
        add(centro, BorderLayout.CENTER);
        add(sur, BorderLayout.SOUTH);
    }

    public void actualizar() {
        etiquetaPuntaje.setText(String.valueOf(jugador.getPuntaje()));
        etiquetaTurno.setText("Turno: " + (jugador.esTurno() ? "Si" : "No"));
        repaint();
    }
}
