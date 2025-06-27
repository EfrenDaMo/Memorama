/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memorama.config;

import java.awt.Dimension;
import java.awt.Font;

/**
 *
 * @author efren
 */
public class Datos {
    public static final int FILAS = 6;
    public static final int COLUMNAS = 7;
    public static final int MARGEN_Y = 10;
    public static final int MARGEN_X = 155;
    public static final int ALTO_CARTA = 120;
    public static final int ANCHO_CARTA = 90;
    public static final int ALTO_PANTALLA = 1280;
    public static final int ANCHO_PANTALLA = 720;
    public static final int TOTAL_PAREJAS = (FILAS * COLUMNAS) / 2;
    public static final Font FUENTE = new Font("Dialog", Font.BOLD, 24);
    public static final Font FUENTE_MENU = new Font("Dialog", Font.BOLD, 36);
    public static final Dimension TAMANIO_BOTONES_MENU = new Dimension(240, 60);
}
