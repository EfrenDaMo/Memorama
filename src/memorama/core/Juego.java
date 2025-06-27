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

    private EscuchadorJuego escuchador;
    private ArrayList<Carta> cartas;
    private Jugador jugadorActual;
    private Carta primeraCartaSeleccionada;
    private Carta segundaCartaSeleccionada;
    private boolean pausado;
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
        if (bloqueado || carta.estaEmparejada() || carta.estaVolteada())
            return;

        carta.voltear();

        if (primeraCartaSeleccionada == null) {
            primeraCartaSeleccionada = carta;
        } else {
            segundaCartaSeleccionada = carta;

            verificarPareja();
        }
    }

    private void verificarPareja() {
        bloqueado = true;

        if (primeraCartaSeleccionada.getId() == segundaCartaSeleccionada.getId()) {
            jugadorActual.aumentarPuntaje(1);

            Timer temporizadorEspera = new Timer(1000, e -> {
                primeraCartaSeleccionada.marcarComoEmparejada(true);
                segundaCartaSeleccionada.marcarComoEmparejada(true);
                cartasRestantes -= 2;

                if (cartasRestantes > 0) {
                    reiniciarSelecciones();
                    notificarInterfaz();
                    bloqueado = false;
                } else {
                    bloqueado = false;
                    notificarInterfaz();
                    finalizarJuego();
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
            primeraCartaSeleccionada.voltear();
            segundaCartaSeleccionada.voltear();
            reiniciarSelecciones();
            notificarInterfaz();
            bloqueado = false;
        });
        temporizadorEspera.setRepeats(false);
        temporizadorEspera.start();
    }

    private void reiniciarSelecciones() {
        primeraCartaSeleccionada = null;
        segundaCartaSeleccionada = null;
    }

    private void finalizarJuego() {
        if (jugador1.getPuntaje() > jugador2.getPuntaje()) {
            JOptionPane.showMessageDialog(null, "¡Gano el jugador 1!");
        } else if (jugador1.getPuntaje() < jugador2.getPuntaje()) {
            JOptionPane.showMessageDialog(null, "¡Gano el jugador 2!");
        } else {
            JOptionPane.showMessageDialog(null, "¡Empate!");
        }
    }

    public void notificarInterfaz() {
        if (escuchador == null)
            return;

        escuchador.alCambiarEstadoJuego();
    }

    public void reiniciarJuego() {
        this.cartas = FabricaCarta.crearCartas();
        this.cartasRestantes = cartas.size();
        jugador1.reiniciarPuntaje();
        jugador2.reiniciarPuntaje();
    }

    public void iniciarJuego() {
        temporizador.iniciar();
        System.out.println("Juego iniciado");
    }

    public void mostrarMenuPrincipal() {
        System.out.println("Menu principal");
    }

    public void pausarJuego() {
        pausado = true;
    }

    public void resumirJuego() {
        pausado = false;
    }

    public void setEscuchador(EscuchadorJuego escuchador) {
        this.escuchador = escuchador;
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

    public boolean estaBloqueado() {
        return bloqueado;
    }

    public boolean estaPausado() {
        return pausado;
    }
}
