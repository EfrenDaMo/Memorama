/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memorama.ui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import memorama.core.EscuchadorJuego;
import memorama.core.EstadoJuego;
import memorama.core.Juego;

/**
 *
 * @author efren
 */
public final class InterfazGrafica extends JFrame implements EscuchadorJuego {
    private final Juego juego;
    private JPanel vistaActual;

    public InterfazGrafica(Juego juego) {
        this.juego = juego;
        juego.addEscuchador(this);

        setTitle("MEMORAMA!!!");
        setSize(1280, 720);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVistaActual(new VistaMenuPrincipal(juego));
        configurarEventosTeclado();
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
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    if (juego.getEstadoActual() == EstadoJuego.JUGANDO) {
                        juego.pausarJuego();
                    } else if (juego.getEstadoActual() == EstadoJuego.EN_PAUSA) {
                        juego.resumirJuego();
                    }
                }
            }
        });
        setFocusable(true);
        requestFocusInWindow();
    }

    @Override
    public void alCambiarEstadoJuego() {
        EstadoJuego estado = juego.getEstadoActual();

        switch (estado) {
            case MENU_PRINCIPAL -> setVistaActual(new VistaMenuPrincipal(juego));

            case JUGANDO -> setVistaActual(new VistaJuego(juego));
        }

        requestFocusInWindow();
    }
}
