/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memorama.ui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import memorama.core.Juego;
import memorama.ui.componentesVistaJuego.PanelInformacion;
import memorama.ui.componentesVistaJuego.PanelJuego;

/**
 *
 * @author efren
 */
public class VistaJuego extends JPanel {
    private final Juego juego;

    private PanelInformacion panelJugador1;
    private PanelJuego panelJuego;
    private PanelInformacion panelJugador2;

    public VistaJuego(Juego juego) {
        this.juego = juego;

        setLayout(new BorderLayout());

        panelJugador1 = new PanelInformacion(juego.getJugador1());
        panelJuego = new PanelJuego(juego);
        panelJugador2 = new PanelInformacion(juego.getJugador2());

        add(panelJugador1, BorderLayout.WEST);
        add(panelJuego, BorderLayout.CENTER);
        add(panelJugador2, BorderLayout.EAST);
    }
}
