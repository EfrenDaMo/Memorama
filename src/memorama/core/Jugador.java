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
    private final PropertyChangeSupport soporte = new PropertyChangeSupport(this);

    public Jugador(int id) {
        this.id = id;
        this.puntaje = 0;
        this.turno = false;
    }

    public void agregarListenerCambioPropiedad(PropertyChangeListener listener) {
        soporte.addPropertyChangeListener(listener);
    }

    public void aumentarPuntaje(int cantidad) {
        if (cantidad < 0)
            throw new IllegalArgumentException("No se puede decrementar el puntaje");

        int anterior = this.puntaje;
        this.puntaje += cantidad;
        soporte.firePropertyChange("puntaje", anterior, puntaje);
    }

    public void reiniciarPuntaje() {
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
        boolean anterior = this.turno;
        this.turno = turno;
        soporte.firePropertyChange("turno", anterior, turno);
    }
}
