/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package memorama;

import memorama.core.Juego;
import memorama.ui.GUI;

/**
 *
 * @author efren
 */
public class Memorama {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Juego juego = new Juego();
        GUI gui = new GUI(juego);

        juego.getTemporizador().empezarTemporizador();
    }

}
