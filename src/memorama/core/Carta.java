/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memorama.core;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *
 * @author efren
 */
public class Carta {
    private static Image imagenTrasera;

    private final int id;
    private final int alto, ancho;
    private final BufferedImage imagenDelantera;

    private int x, y;
    private boolean volteada, emparejada;

    public Carta(int id, int ancho, int alto, BufferedImage imagenDelantera) {
        this.id = id;
        this.alto = alto;
        this.ancho = ancho;
        this.imagenDelantera = imagenDelantera;
    }

    public static void setImagenTrasera(Image imagen) {
        imagenTrasera = imagen;
    }

    public void voltear() {
        if (emparejada)
            return;

        volteada = !volteada;
    }

    public void dibujar(Graphics g) {
        if (emparejada)
            return;

        Image imagenActual = volteada ? imagenDelantera : imagenTrasera;
        g.drawImage(imagenActual, x, y, ancho, alto, null);
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setEmparejada(boolean emparejada) {
        this.emparejada = emparejada;
    }

    public void setPosicion(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isVolteada() {
        return volteada;
    }

    public boolean isEmparejada() {
        return emparejada;
    }

    public boolean hasPuntos(Point punto) {
        return new Rectangle(x, y, ancho, alto).contains(punto);
    }
}
