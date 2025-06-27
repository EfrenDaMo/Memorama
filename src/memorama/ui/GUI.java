/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memorama.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import memorama.core.Juego;

/**
 *
 * @author efren
 */
public class GUI extends JFrame {
    private final Juego juego;

    private JPanel vistaActual;

    public GUI(Juego juego) {
        this.juego = juego;

        setTitle("MEMORAMA!!!");
        setSize(1280, 720);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setVistaActua(new VistaJuego(juego));
        setVisible(true);
    }

    public void setVistaActua(JPanel vistaNueva) {
        if (vistaActual == null) {
            vistaActual = vistaNueva;
            add(vistaActual);
        } else {
            remove(vistaActual);
            vistaActual = vistaNueva;
            add(vistaActual);
        }
    }
}
