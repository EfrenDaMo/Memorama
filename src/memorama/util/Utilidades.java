/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memorama.util;

import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 *
 * @author efren
 */
public class Utilidades {
    private static File obtenerArchivo(String ruta) {
        File archivo = new File(ruta);
        if (!archivo.exists())
            archivo = new File("src/memoramvp/recursos/" + ruta);

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
}
