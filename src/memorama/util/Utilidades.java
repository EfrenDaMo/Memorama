/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memorama.util;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.function.Consumer;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import java.awt.image.BufferedImage;
import memorama.config.Datos;

/**
 *
 * @author efren
 */
public class Utilidades {
    private static File obtenerArchivo(String ruta) {
        File archivo = new File(ruta);
        if (!archivo.exists()) {
            archivo = new File("src/memorama/recursos/" + ruta);
        }

        return archivo;
    }

    public static BufferedImage cargarImagen(String ruta) throws Exception {
        return ImageIO.read(obtenerArchivo(ruta));
    }

    public static BufferedImage cargarImagen(String ruta, int ancho, int alto) throws Exception {
        File archivo = obtenerArchivo(ruta);
        BufferedImage imagenOriginal = ImageIO.read(archivo);
        BufferedImage imagenEscalada = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = imagenEscalada.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(imagenOriginal, 0, 0, ancho, alto, null);
        g2d.dispose();

        return imagenEscalada;
    }

    public static JLabel crearEtiqueta(String texto) {
        JLabel etiqueta = new JLabel(texto);

        etiqueta.setFont(Datos.FUENTE);
        etiqueta.setHorizontalAlignment(JLabel.CENTER);

        return etiqueta;
    }

    public static JButton crearBoton(String texto, Consumer<ActionEvent> manejador) {
        JButton boton = new JButton(texto);

        boton.addActionListener(e -> manejador.accept(e));

        return boton;
    }

    public static JLabel crearEtiquetaMenu(String texto) {
        JLabel etiqueta = new JLabel(texto);

        etiqueta.setFont(Datos.FUENTE_MENU);
        etiqueta.setHorizontalAlignment(JLabel.CENTER);

        return etiqueta;
    }

    public static JButton crearBotonMenu(String texto, Consumer<ActionEvent> manejador, Dimension tamanio) {
        JButton boton = new JButton(texto);

        boton.setFont(Datos.FUENTE_MENU);
        boton.addActionListener(e -> manejador.accept(e));
        boton.setPreferredSize(tamanio);
        boton.setMaximumSize(tamanio);

        return boton;
    }

    public static void mostrarDialogoError(Component parent, String mensaje, String titulo) {
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(
                    parent,
                    "<html><div style='text-align: center; padding: 10px;'>" +
                            "<b style='color: #D32F2F; font-size: 14px;'>" + mensaje + "</b>" +
                            "</div></html>",
                    titulo,
                    JOptionPane.ERROR_MESSAGE);
        });
    }

    public static void mostrarDialogoAdvertencia(Component parent, String mensaje, String titulo) {
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(
                    parent,
                    "<html><div style='text-align: center; padding: 10px;'>" +
                            "<b style='color: #FF9800; font-size: 14px;'>" + mensaje + "</b>" +
                            "</div></html>",
                    titulo,
                    JOptionPane.WARNING_MESSAGE);
        });
    }

}
