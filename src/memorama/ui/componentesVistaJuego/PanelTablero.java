/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memorama.ui.componentesVistaJuego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import memorama.config.Datos;
import memorama.core.Carta;
import memorama.core.EscuchadorJuego;
import memorama.core.Juego;
import memorama.util.Utilidades;

/**
 *
 * @author efren
 */
public class PanelTablero extends JPanel implements EscuchadorJuego {
    private final Juego juego;

    public PanelTablero(Juego juego) {
        this.juego = juego;
        juego.setEscuchador(this);

        setLayout(null);
        setOpaque(false);
        inicializar();
        configurarEventosMouse();
    }

    private void inicializar() {
        try {
            Carta.setImagenTrasera(Utilidades.cargarImagen("reversaCarta.png", Datos.ANCHO_CARTA, Datos.ALTO_CARTA));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al cargar el reverso de la image", "Error!",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void configurarEventosMouse() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (juego.estaBloqueado())
                    return;

                for (Carta carta : juego.getCartas()) {
                    if (carta.contienePunto(e.getPoint())) {
                        juego.manejarClicCarta(carta);
                        repaint();
                        break;
                    }
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Carta carta : juego.getCartas()) {
            carta.dibujar(g);
        }
    }

    @Override
    public void alCambiarEstadoJuego() {
        repaint();
    }
}
