/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memorama.core;

import memorama.core.EstadoJuego;

/**
 *
 * @author efren
 */
public class MaquinaEstadoJuego {
    private final Juego juego;
    private EstadoJuego estadoActual;

    public MaquinaEstadoJuego(Juego juego) {
        this.juego = juego;
        this.estadoActual = EstadoJuego.MENU_PRINCIPAL;
    }

    public EstadoJuego getEstadoActual() {
        return estadoActual;
    }

    public boolean transicionarA(EstadoJuego estadoNuevo) {
        if (estadoActual.puedeTransicionarA(estadoNuevo)) {
            EstadoJuego estadoPrevio = estadoActual;
            estadoActual = estadoNuevo;

            return true;
        }

        return false;
    }

    private void ejecutarAccionesEntrada(EstadoJuego estadoPrevio) {
        switch (estadoPrevio) {
            case JUGANDO:
                break;
            case EN_PAUSA:
                break;
            case TERMINADO:
                break;
            case MENU_PRINCIPAL:
                break;
        }
    }

    public void iniciarJuego() {
        transicionarA(EstadoJuego.JUGANDO);
    }

    public void pausarJuego() {
        transicionarA(EstadoJuego.EN_PAUSA);
    }

    public void renudarJuego() {
        transicionarA(EstadoJuego.JUGANDO);
    }

    public void renunciarJuego() {
        transicionarA(EstadoJuego.TERMINADO);
    }

    public void regresarAMenu() {
        transicionarA(EstadoJuego.MENU_PRINCIPAL);
    }

}
