/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memorama.ui.componentesVistaJuego;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import memorama.core.Juego;
import memorama.core.Jugador;
import memorama.util.Utilidades;

/**
 *
 * @author efren
 */
public class PanelInformacion extends JPanel {
    private final Juego juego;
    private final Jugador jugador;

    private JLabel etiquetaJugador;
    private JLabel etiquetaPuntaje;
    private JLabel etiquetaTurno;
    private JButton botonPausar;
    private JButton botonAbandonar;

    public PanelInformacion(Jugador jugador, Juego juego) {
        this.jugador = jugador;
        this.juego = juego;

        jugador.agregarListenerCambioPropiedad(e -> actualizar());
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(213, 720));
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        etiquetaJugador = Utilidades.crearEtiqueta("Jugador " + jugador.getId());
        etiquetaPuntaje = Utilidades.crearEtiqueta(String.valueOf(jugador.getPuntaje()));
        etiquetaTurno = Utilidades.crearEtiqueta("Turno: " + (jugador.esTurno() ? "Si" : "No"));

        botonPausar = Utilidades.crearBoton("Pausar", e -> juego.pausarJuego());
        botonAbandonar = Utilidades.crearBoton("Abandonar", e -> {
            juego.abandonarPartida(jugador);
        });

        JPanel centro = new JPanel(new BorderLayout());
        centro.add(etiquetaPuntaje, BorderLayout.NORTH);
        centro.add(etiquetaTurno, BorderLayout.SOUTH);

        JPanel sur = new JPanel(new BorderLayout());
        sur.add(botonPausar, BorderLayout.WEST);
        sur.add(botonAbandonar, BorderLayout.EAST);

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
