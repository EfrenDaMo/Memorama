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
public class InterfazGrafica extends JFrame {
    private final Juego juego;
    private JPanel vistaActual;

    public InterfazGrafica(Juego juego) {
        this.juego = juego;

        setTitle("MEMORAMA!!!");
        setSize(1280, 720);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVistaActual(new VistaJuego(juego));
        setVisible(true);
    }

    public void setVistaActual(JPanel nuevaVista) {
        if (vistaActual != null) {
            remove(vistaActual);
        }

        vistaActual = nuevaVista;
        add(vistaActual);
        revalidate();
        repaint();
    }
}
