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
        juego.setEscuchadorJuego(this);

        setLayout(null);
        setOpaque(false);
        inicializarComponentes();
        configurarEventosMouse();
    }

    private void inicializarComponentes() {
        try {
            Carta.setImagenTrasera(Utilidades.cargarImagen("reversaCarta.png", Datos.ANCHO_CARTA, Datos.ALTO_CARTA));
        } catch (Exception e) {
        }
    }

    private void configurarEventosMouse() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (juego.isBloqueado())
                    return;

                for (Carta carta : juego.getCartas()) {
                    if (carta.hasPuntos(e.getPoint())) {
                        System.out.println("Carta con id: " + carta.getId() + " se le ha hecho clic");
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
        dibujarCartas(g);
    }

    @Override
    public void enCambioDeEstadoJuego() {
        repaint();
    }

    private void dibujarCartas(Graphics g) {
        for (Carta carta : juego.getCartas()) {
            carta.dibujar(g);
        }
    }
}
