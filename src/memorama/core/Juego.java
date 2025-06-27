/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memorama.core;

import java.util.ArrayList;

import memorama.core.Jugador;

/**
 *
 * @author efren
 */
public class Juego {
    private final ArrayList<Carta> cartas;
    private final Jugador jugador1;
    private final Jugador jugador2;

    private Jugador jugadorActual;

    public Juego() {
        this.cartas = new ArrayList<>();
        this.jugador1 = new Jugador(1);
        this.jugador2 = new Jugador(2);
    }
}
