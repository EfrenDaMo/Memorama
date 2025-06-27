/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memorama.core;

/**
 *
 * @author efren
 */
public class Jugador {
    private final int id;

    private int puntaje;
    private boolean turno;

    public Jugador(int id) {
        this.id = id;
        this.puntaje = 0;
        this.turno = false;
    }

    public void aumentarPuntaje(int cantidad) {
        if (cantidad < 0)
            throw new IllegalArgumentException("No se puede decrementar el puntaje");

        puntaje += cantidad;
    }

    public int getId() {
        return id;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public boolean esTurno() {
        return turno;
    }

    public void setTurno(boolean turno) {
        this.turno = turno;
    }
}
