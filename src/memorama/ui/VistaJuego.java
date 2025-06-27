/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memorama.ui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.OverlayLayout;

import memorama.core.Juego;
import memorama.ui.VistaMenuPausa;
import memorama.ui.componentesVistaJuego.PanelInformacion;
import memorama.ui.componentesVistaJuego.PanelJuego;

/**
 *
 * @author efren
 */
public class VistaJuego extends JPanel {
    private final Juego juego;
    private final VistaMenuPausa vistaMenuPausa;

    private PanelInformacion panelJugador1;
    private PanelJuego panelJuego;
    private PanelInformacion panelJugador2;

    public VistaJuego(Juego juego) {
        this.juego = juego;
        setLayout(new OverlayLayout(this));

        JPanel panelesJuego = new JPanel();
        panelesJuego.setLayout(new BorderLayout());

        panelJugador1 = new PanelInformacion(juego.getJugador1(), juego);
        panelJuego = new PanelJuego(juego);
        panelJugador2 = new PanelInformacion(juego.getJugador2(), juego);

        panelesJuego.add(panelJugador1, BorderLayout.WEST);
        panelesJuego.add(panelJuego, BorderLayout.CENTER);
        panelesJuego.add(panelJugador2, BorderLayout.EAST);

        vistaMenuPausa = new VistaMenuPausa(juego);
        vistaMenuPausa.setVisible(false);

        add(panelesJuego);
        add(vistaMenuPausa);
    }

    public void actualizar() {
        vistaMenuPausa.setVisible(juego.estaPausado());
        revalidate();
        repaint();
    }
}
