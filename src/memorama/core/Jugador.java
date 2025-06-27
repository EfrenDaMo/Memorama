/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memorama.core;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *
 * @author efren
 */
public class Jugador {
    private final int id;

    private int puntaje;
    private boolean turno;

    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    public Jugador(int id) {
        this.id = id;
        this.puntaje = 0;
        this.turno = false;
    }

    public void agregarPropertyChangeListener(PropertyChangeListener l) {
        support.addPropertyChangeListener(l);
    }

    public void aumentarPuntaje(int cantidad) {
        if (cantidad < 0)
            throw new IllegalArgumentException("No se puede decrementar el puntaje");

        int actual = this.puntaje;
        this.puntaje += cantidad;
        support.firePropertyChange("puntaje", actual, puntaje);
    }

    public void resetearPuntaje() {
        puntaje = 0;
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
        boolean actual = this.turno;
        this.turno = turno;
        support.firePropertyChange("turno", actual, turno);
    }
}
