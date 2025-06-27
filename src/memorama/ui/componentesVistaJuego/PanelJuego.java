/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memorama.ui.componentesVistaJuego;

import javax.swing.JLabel;
import javax.swing.JPanel;

import memorama.core.Juego;
import memorama.util.Utilidades;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author efren
 */
public class PanelJuego extends JPanel {
    private final Juego juego;

    private JLabel etiquetaTiempo;
    private BufferedImage imagenFondo;
    private PanelTablero panelTablero;

    public PanelJuego(Juego juego) {
        this.juego = juego;

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(854, 300));
        cargarRecursos();
    }

    private void cargarRecursos() {
        try {
            imagenFondo = Utilidades.cargarImagen("fondoTablero.png");
        } catch (Exception e) {
            imagenFondo = null;
            Utilidades.mostrarDialogoAdvertencia(
                    null,
                    "No se pudo cargar el fondo de tablero usando alternativa",
                    "Falta de recurso");
        }

        etiquetaTiempo = juego.getTemporizador().getEtiquetaTiempo();
        panelTablero = new PanelTablero(juego);

        add(etiquetaTiempo, BorderLayout.NORTH);
        add(panelTablero, BorderLayout.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagenFondo != null) {
            g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
        } else {
            g.setColor(Color.GRAY);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}
