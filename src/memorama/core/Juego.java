/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memorama.core;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import memorama.factory.FabricaCarta;

/**
 *
 * @author efren
 */
public class Juego {
    private final Jugador jugador1;
    private final Jugador jugador2;
    private final Temporizador temporizador;

    private EscuchadorJuego escuchadorJuego;
    private ArrayList<Carta> cartas;
    private Jugador jugadorActual;
    private Carta primeraSeleccion;
    private Carta segundaSeleccion;
    private boolean bloqueado;
    private int cartasRestantes;

    public Juego() {
        this.cartas = FabricaCarta.crearCartas();
        this.jugador1 = new Jugador(1);
        this.jugador2 = new Jugador(2);
        this.jugador1.setTurno(true);
        this.jugadorActual = jugador1;
        this.temporizador = new Temporizador();
        this.cartasRestantes = cartas.size();
        bloqueado = false;
    }

    public void manejarClicCarta(Carta carta) {
        if (bloqueado || carta.isEmparejada() || carta.isVolteada())
            return;

        carta.voltear();

        if (primeraSeleccion == null) {
            primeraSeleccion = carta;
        } else {
            segundaSeleccion = carta;

            comprobarPareja();
        }
    }

    private void comprobarPareja() {
        bloqueado = true;

        if (primeraSeleccion.getId() == segundaSeleccion.getId()) {
            jugadorActual.aumentarPuntaje(1);

            Timer temporizadorEspera = new Timer(1000, e -> {
                primeraSeleccion.setEmparejada(true);
                segundaSeleccion.setEmparejada(true);
                cartasRestantes -= 2;

                if (cartasRestantes > 0) {
                    resetearSelecciones();
                    notificarUI();
                    bloqueado = false;
                } else {
                    bloqueado = false;
                    notificarUI();
                    terminarJuego();
                }
            });
            temporizadorEspera.setRepeats(false);
            temporizadorEspera.start();
        } else {
            cambiarTurno();
        }
    }

    private void cambiarTurno() {
        jugadorActual.setTurno(false);
        jugadorActual = (jugadorActual == jugador1) ? jugador2 : jugador1;
        jugadorActual.setTurno(true);

        Timer temporizadorEspera = new Timer(1000, e -> {
            primeraSeleccion.voltear();
            segundaSeleccion.voltear();
            resetearSelecciones();
            bloqueado = false;
            notificarUI();
        });
        temporizadorEspera.setRepeats(false);
        temporizadorEspera.start();
    }

    private void resetearSelecciones() {
        primeraSeleccion = null;
        segundaSeleccion = null;
    }

    private void terminarJuego() {
        if (jugador1.getPuntaje() > jugador2.getPuntaje()) {
            JOptionPane.showMessageDialog(null, "Gano el jugador 1!");
        } else if (jugador1.getPuntaje() < jugador2.getPuntaje()) {
            JOptionPane.showMessageDialog(null, "Gano el jugador 2!");
        } else {
            JOptionPane.showMessageDialog(null, "Es empate!");
        }
    }

    public void notificarUI() {
        if (escuchadorJuego == null)
            return;

        escuchadorJuego.enCambioDeEstadoJuego();
    }

    public void reiniciarJuego() {
        this.cartas = FabricaCarta.crearCartas();
        this.cartasRestantes = cartas.size();
        jugador1.resetearPuntaje();
        jugador2.resetearPuntaje();
    }

    public void setEscuchadorJuego(EscuchadorJuego escuchadorJuego) {
        this.escuchadorJuego = escuchadorJuego;
    }

    public ArrayList<Carta> getCartas() {
        return cartas;
    }

    public Jugador getJugador1() {
        return jugador1;
    }

    public Jugador getJugador2() {
        return jugador2;
    }

    public Temporizador getTemporizador() {
        return temporizador;
    }

    public boolean isBloqueado() {
        return bloqueado;
    }
}
