/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memorama.core;

import java.util.ArrayList;
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
    private final MaquinaEstadoJuego maquinaEstado;
    private final ArrayList<EscuchadorJuego> escuchadores = new ArrayList<>();

    private ArrayList<Carta> cartas;
    private Jugador jugadorActual;
    private Carta primeraCartaSeleccionada;
    private Carta segundaCartaSeleccionada;
    private boolean bloqueado;
    private boolean abandonado;
    private boolean finalizoTiempo;
    private int cartasRestantes;

    public Juego() {
        this.cartas = FabricaCarta.crearCartas();
        this.jugador1 = new Jugador(1);
        this.jugador2 = new Jugador(2);
        this.jugador1.setTurno(true);
        this.jugadorActual = jugador1;
        this.temporizador = new Temporizador(this);
        this.cartasRestantes = cartas.size();
        this.maquinaEstado = new MaquinaEstadoJuego(this);
        bloqueado = false;
        finalizoTiempo = false;
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
                    bloqueado = false;
                    reiniciarSelecciones();
                    notificarInterfaz();
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
            bloqueado = false;
            primeraCartaSeleccionada.voltear();
            segundaCartaSeleccionada.voltear();
            reiniciarSelecciones();
            notificarInterfaz();
        });
        temporizadorEspera.setRepeats(false);
        temporizadorEspera.start();
    }

    private void reiniciarSelecciones() {
        primeraCartaSeleccionada = null;
        segundaCartaSeleccionada = null;
    }

    public void reiniciarJuego() {
        this.cartas = FabricaCarta.crearCartas();
        this.cartasRestantes = cartas.size();
        this.abandonado = false;
        jugador1.reiniciarPuntaje();
        jugador2.reiniciarPuntaje();
        temporizador.reiniciar();
        temporizador.iniciar();
    }

    public void iniciarJuego() {
        maquinaEstado.transicionar(EstadoJuego.JUGANDO);
    }

    public void pausarJuego() {
        maquinaEstado.transicionar(EstadoJuego.EN_PAUSA);
    }

    public void resumirJuego() {
        maquinaEstado.transicionar(EstadoJuego.JUGANDO);
    }

    public void mostrarMenuPrincipal() {
        maquinaEstado.transicionar(EstadoJuego.MENU_PRINCIPAL);
    }

    private void finalizarJuego() {
        maquinaEstado.transicionar(EstadoJuego.TERMINADO);
    }

    public void abandonarPartida(Jugador jugador) {
        if (getEstadoActual() != EstadoJuego.JUGANDO)
            return;

        abandonado = true;
        Jugador ganador = (jugador == jugador1) ? jugador2 : jugador1;
        ganador.aumentarPuntaje(cartasRestantes / 2);

        finalizarJuego();
    }

    public void finalizoTiempo() {
        if (getEstadoActual() != EstadoJuego.JUGANDO)
            return;

        finalizoTiempo = true;
        finalizarJuego();
    }

    public void notificarInterfaz() {
        new ArrayList<>(escuchadores).forEach(EscuchadorJuego::alCambiarEstadoJuego);
    }

    public EstadoJuego getEstadoActual() {
        return maquinaEstado.getEstadoActual();
    }

    public void addEscuchador(EscuchadorJuego escuchador) {
        escuchadores.add(escuchador);
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

    public boolean fueAbandonado() {
        return abandonado;
    }

    public boolean seFinalizoTiempo() {
        return finalizoTiempo;
    }
}
