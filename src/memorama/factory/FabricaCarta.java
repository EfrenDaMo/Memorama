/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memorama.factory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;

import memorama.config.Datos;
import memorama.core.Carta;
import memorama.util.Utilidades;

/**
 *
 * @author efren
 */
public class FabricaCarta {
    public static ArrayList<Carta> crearCartas() {
        ArrayList<Carta> cartas = new ArrayList<>();
        ArrayList<Carta> cartasTemp = new ArrayList<>();

        for (int i = 0; i < Datos.TOTAL_PAREJAS; i++) {
            BufferedImage imagenDelantera = cargarImagenDelantera(i);

            cartasTemp.add(new Carta(i, Datos.ANCHO_CARTA, Datos.ALTO_CARTA, imagenDelantera));
            cartasTemp.add(new Carta(i, Datos.ANCHO_CARTA, Datos.ALTO_CARTA, imagenDelantera));
        }

        Collections.shuffle(cartasTemp);
        asignarPosicionesACartas(cartasTemp, cartas);

        return cartas;
    }

    private static BufferedImage cargarImagenDelantera(int id) {
        try {
            return Utilidades.cargarImagen("imagen" + (id % 21) + ".png",
                    Datos.ANCHO_CARTA,
                    Datos.ALTO_CARTA);
        } catch (Exception e) {
            return crearImagenFallback(id);
        }
    }

    private static BufferedImage crearImagenFallback(int id) {
        BufferedImage img = new BufferedImage(Datos.ANCHO_CARTA, Datos.ALTO_CARTA, BufferedImage.TYPE_INT_ARGB);
        Graphics g = img.getGraphics();

        g.setColor(new Color((id * 50) % 256, (id * 100) % 256, (id * 150) % 256));
        g.fillRect(0, 0, Datos.ANCHO_CARTA, Datos.ALTO_CARTA);
        g.dispose();

        return img;
    }

    private static void asignarPosicionesACartas(ArrayList<Carta> source, ArrayList<Carta> target) {
        int index = 0;

        for (int fila = 0; fila < Datos.FILAS; fila++) {
            for (int columna = 0; columna < Datos.COLUMNAS; columna++) {
                if (index < source.size()) {
                    Carta carta = source.get(index);
                    int y = Datos.MARGEN_Y + (Datos.ALTO_CARTA * fila);
                    int x = Datos.MARGEN_X + (Datos.ANCHO_CARTA * columna);

                    carta.setPosicion(x, y);

                    target.add(carta);
                    index++;
                }
            }
        }
    }
}
