/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memorama.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import memorama.core.Juego;
import memorama.config.Datos;
import memorama.util.Utilidades;

/**
 *
 * @author efren
 */
public class VistaMenuPausa extends JPanel {
    private final Juego juego;

    private JLabel etiquetaTitulo;
    private JButton botonResumir;
    private JButton botonMenuPrincipal;
    private JButton botonSalir;

    public VistaMenuPausa(Juego juego) {
        this.juego = juego;
        setOpaque(false);

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout());

        JPanel overlay = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(0, 0, 0, 150));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        overlay.setOpaque(false);

        JPanel panelMenu = new JPanel(new BorderLayout());
        panelMenu.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        panelMenu.setOpaque(false);

        etiquetaTitulo = Utilidades.crearEtiquetaMenu("EN PAUSA");
        panelMenu.add(etiquetaTitulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		panelBotones.setOpaque(false);

        botonResumir = Utilidades.crearBotonMenu("Resumir", e -> juego.resumirJuego(),
                Datos.TAMANIO_BOTONES_MENU);
        botonResumir.setAlignmentX(Component.CENTER_ALIGNMENT);

        botonMenuPrincipal = Utilidades.crearBotonMenu("Menú Principal", e -> {
            juego.mostrarMenuPrincipal();
        }, Datos.TAMANIO_BOTONES_MENU);
        botonMenuPrincipal.setAlignmentX(Component.CENTER_ALIGNMENT);

        botonSalir = Utilidades.crearBotonMenu("Salir", e -> System.exit(0), Datos.TAMANIO_BOTONES_MENU);
        botonSalir.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelBotones.add(botonResumir);
        panelBotones.add(Box.createRigidArea(new Dimension(0, 20)));
        panelBotones.add(botonMenuPrincipal);
        panelBotones.add(Box.createRigidArea(new Dimension(0, 20)));
        panelBotones.add(botonSalir);

		panelMenu.add(panelBotones, BorderLayout.CENTER);

        overlay.add(panelMenu);
        add(overlay);
    }
}
