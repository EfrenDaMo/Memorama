/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memorama.ui;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import javax.swing.SwingUtilities;

import memorama.core.EscuchadorJuego;
import memorama.core.EstadoJuego;
import memorama.core.Juego;
import memorama.ui.componentesVistaJuego.PanelInformacion;
import memorama.ui.componentesVistaJuego.PanelJuego;

/**
 *
 * @author efren
 */
public class VistaJuego extends JPanel implements EscuchadorJuego {
    private final Juego juego;
    private final VistaMenuPausa vistaMenuPausa;
    private final PanelInformacion panelJugador1;
    private final PanelJuego panelJuego;
    private final PanelInformacion panelJugador2;

    public VistaJuego(Juego juego) {
        this.juego = juego;
        juego.addEscuchador(this);

        setLayout(new OverlayLayout(this));

        JPanel panelesJuego = new JPanel();
        panelesJuego.setLayout(new BorderLayout());
        panelesJuego.setOpaque(false);

        panelJugador1 = new PanelInformacion(juego.getJugador1(), juego);
        panelJuego = new PanelJuego(juego);
        panelJugador2 = new PanelInformacion(juego.getJugador2(), juego);

        panelesJuego.add(panelJugador1, BorderLayout.WEST);
        panelesJuego.add(panelJuego, BorderLayout.CENTER);
        panelesJuego.add(panelJugador2, BorderLayout.EAST);

        vistaMenuPausa = new VistaMenuPausa(juego);
        vistaMenuPausa.setVisible(false);
        vistaMenuPausa.setOpaque(false);

        add(vistaMenuPausa);
        add(panelesJuego);
    }

    private void mostrarResultados() {
        String mensaje = "";

        if (juego.seFinalizoTiempo()) {
            mensaje = "¡Se Termino el tiempo!";
        } else if (juego.fueAbandonado()) {
            mensaje = "¡Partida abandonada!";
        }

        if (juego.getJugador1().getPuntaje() > juego.getJugador2().getPuntaje()) {
            mensaje = mensaje + "\n¡Gano el jugador 1!";
        } else if (juego.getJugador1().getPuntaje() < juego.getJugador2().getPuntaje()) {
            mensaje = mensaje + "\n¡Gano el jugador 2!";
        } else {
            mensaje = mensaje + "\n¡Empate!";
        }

        JOptionPane.showMessageDialog(
                SwingUtilities.getWindowAncestor(this),
                mensaje,
                "Fin del Juego",
                JOptionPane.INFORMATION_MESSAGE);

        juego.mostrarMenuPrincipal();
    }

    @Override
    public void alCambiarEstadoJuego() {
        if (juego.getEstadoActual() == EstadoJuego.TERMINADO) {
            mostrarResultados();
        }

        vistaMenuPausa.setVisible(juego.getEstadoActual() == EstadoJuego.EN_PAUSA);
        revalidate();
        repaint();
    }
}
