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
    private static Image reverso;

    private final int id;
    private final int alto, ancho;
    private final BufferedImage frente;

    private int x, y;
    private boolean volteada, emparejada;

    public Carta(int id, int ancho, int alto, BufferedImage imagenDelantera) {
        this.id = id;
        this.alto = alto;
        this.ancho = ancho;
        this.frente = imagenDelantera;
    }

    public static void setImagenTrasera(Image imagen) {
        reverso = imagen;
    }

    public void voltear() {
        if (emparejada)
            return;

        volteada = !volteada;
    }

    public void dibujar(Graphics g) {
        if (emparejada)
            return;

        Image imagenActual = volteada ? frente : reverso;
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

    public void setPosicion(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean estaVolteada() {
        return volteada;
    }

    public void marcarComoEmparejada(boolean emparejada) {
        this.emparejada = emparejada;
    }

    public boolean estaEmparejada() {
        return emparejada;
    }

    public boolean contienePunto(Point punto) {
        return new Rectangle(x, y, ancho, alto).contains(punto);
    }
}
