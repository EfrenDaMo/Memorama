/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memorama.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import memorama.config.Datos;

import memorama.core.Juego;
import memorama.util.Utilidades;

/**
 *
 * @author efren
 */
public class VistaMenuPrincipal extends JPanel {
    private final Juego juego;

    private JLabel etiquetaTitulo;
    private JButton botonJugar;
    private JButton botonCreditos;
    private JButton botonSalir;

    public VistaMenuPrincipal(Juego juego) {
        this.juego = juego;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        add(etiquetaTitulo = Utilidades.crearEtiquetaMenu("MEMORAMA!!!"), BorderLayout.NORTH);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        botonJugar = Utilidades.crearBotonMenu("Jugar", e -> juego.iniciarJuego(), Datos.TAMANIO_BOTONES_MENU);
        botonJugar.setAlignmentX(Component.CENTER_ALIGNMENT);

        botonCreditos = Utilidades.crearBotonMenu("Creditos", e -> mostrarCreditos(), Datos.TAMANIO_BOTONES_MENU);
        botonCreditos.setAlignmentX(Component.CENTER_ALIGNMENT);

        botonSalir = Utilidades.crearBotonMenu("Salir", e -> System.exit(0), Datos.TAMANIO_BOTONES_MENU);
        botonSalir.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelBotones.add(botonJugar);
        panelBotones.add(Box.createRigidArea(new Dimension(0, 20)));
        panelBotones.add(botonCreditos);
        panelBotones.add(Box.createRigidArea(new Dimension(0, 20)));
        panelBotones.add(botonSalir);

        add(panelBotones, BorderLayout.CENTER);
    }

    private void mostrarCreditos() {
        String mensaje = "<html><div style='text-align: center; font-size: 14px;'>"
                + "<b style='font-size: 16px; color: #2A4A7F;'>Creditos de arte</b><br><br>"
                + "<div style='background: #F0F5FF; padding: 10px; border-radius: 5px;'>"
                + "Arte de Balatro por: <br><b>LocalThunk</b>"
                + "</div></div></html>";

        JOptionPane.showMessageDialog(
                this,
                mensaje,
                "Agradecimientos",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
