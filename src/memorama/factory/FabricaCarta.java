/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memorama.factory;

import java.awt.*;
import java.util.*;
import java.awt.image.BufferedImage;

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
        ArrayList<Carta> cartasTemporales = new ArrayList<>();

        for (int i = 0; i < Datos.TOTAL_PAREJAS; i++) {
            BufferedImage frente = cargarImagenFrente(i);

            cartasTemporales.add(new Carta(i, Datos.ANCHO_CARTA, Datos.ALTO_CARTA, frente));
            cartasTemporales.add(new Carta(i, Datos.ANCHO_CARTA, Datos.ALTO_CARTA, frente));
        }

        Collections.shuffle(cartasTemporales);
        asignarPosiciones(cartasTemporales, cartas);

        return cartas;
    }

    private static BufferedImage cargarImagenFrente(int id) {
        try {
            return Utilidades.cargarImagen("imagen" + (id % 21) + ".png",
                    Datos.ANCHO_CARTA,
                    Datos.ALTO_CARTA);
        } catch (Exception e) {
            return crearImagenAlternativa(id);
        }
    }

    private static BufferedImage crearImagenAlternativa(int id) {
        BufferedImage img = new BufferedImage(Datos.ANCHO_CARTA, Datos.ALTO_CARTA, BufferedImage.TYPE_INT_ARGB);
        Graphics g = img.getGraphics();

        g.setColor(new Color((id * 50) % 256, (id * 100) % 256, (id * 150) % 256));
        g.fillRect(0, 0, Datos.ANCHO_CARTA, Datos.ALTO_CARTA);
        g.dispose();

        return img;
    }

    private static void asignarPosiciones(ArrayList<Carta> origen, ArrayList<Carta> destino) {
        int indice = 0;

        for (int fila = 0; fila < Datos.FILAS; fila++) {
            for (int columna = 0; columna < Datos.COLUMNAS; columna++) {
                if (indice < origen.size()) {
                    Carta carta = origen.get(indice);
                    int y = Datos.MARGEN_Y + (Datos.ALTO_CARTA * fila);
                    int x = Datos.MARGEN_X + (Datos.ANCHO_CARTA * columna);

                    carta.setPosicion(x, y);

                    destino.add(carta);
                    indice++;
                }
            }
        }
    }
}
