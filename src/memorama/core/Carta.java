/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memorama.core;

import java.awt.*;
import java.awt.image.BufferedImage;
import memorama.config.Datos;

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

    public static void generarImagenReverosRespaldo() {
        int ancho = Datos.ANCHO_CARTA;
        int alto = Datos.ALTO_CARTA;

        BufferedImage img = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();

        // Color índigo mate base (#4B0082 con opacidad)
        Color indigoMate = new Color(75, 0, 130, 240);

        // Gradiente para efecto mate
        GradientPaint gradiente = new GradientPaint(
                0, 0, indigoMate.brighter(),
                ancho, alto, indigoMate.darker());

        g.setPaint(gradiente);
        g.fillRect(0, 0, ancho, alto);

        // Borde sutil
        g.setStroke(new BasicStroke(3));
        g.setColor(new Color(30, 0, 60));
        g.drawRect(1, 1, ancho - 3, alto - 3);

        // Patrón de diamantes simples
        g.setColor(new Color(255, 255, 255, 30));
        for (int i = -10; i < ancho + 10; i += 15) {
            g.drawLine(i, 0, i + 10, alto);
            g.drawLine(i + 5, 0, i - 5, alto);
        }

        g.dispose();
        reverso = img;
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
