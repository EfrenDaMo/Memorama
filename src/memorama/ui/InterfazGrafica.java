/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memorama.ui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import memorama.core.Juego;
import memorama.ui.VistaJuego;

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
        setVistaActual(new VistaMenuPrincipal(juego));
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

    private void configurarEventosTeclado() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (vistaActual instanceof VistaJuego vistajuego) {
                    if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        if (juego.estaPausado()) {
                            juego.resumirJuego();
                        } else {
                            juego.pausarJuego();
                        }
                    }

					vistajuego.actualizar();
                }
            }
        });
    }
}
