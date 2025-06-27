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

    public boolean transicionar(EstadoJuego nuevoEstado) {
        if (estadoActual.puedeTransicionarA(nuevoEstado)) {
            estadoActual = nuevoEstado;

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
        transicionar(EstadoJuego.JUGANDO);
    }

    public void pausarJuego() {
        transicionar(EstadoJuego.EN_PAUSA);
    }

    public void renudarJuego() {
        transicionar(EstadoJuego.JUGANDO);
    }

    public void renunciarJuego() {
        transicionar(EstadoJuego.TERMINADO);
    }

    public void regresarAMenu() {
        transicionar(EstadoJuego.MENU_PRINCIPAL);
    }

}
