/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memorama.core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import memorama.config.Datos;
import memorama.util.Utilidades;

/**
 *
 * @author efren
 */
public class Juego {
    private final ArrayList<Carta> cartas;
    private final Jugador jugador1;
    private final Jugador jugador2;
    private final Temporizador temporizador;

    private EscuchadorJuego escuchadorJuego;
    private Jugador jugadorActual;
    private Carta primeraSeleccion;
    private Carta segundaSeleccion;
    private Boolean bloqueado;
    private int cartasRestantes;

    public Juego() {
        this.cartas = new ArrayList<>();
        this.jugador1 = new Jugador(1);
        this.jugador2 = new Jugador(2);
        this.jugador1.setTurno(true);
        this.jugadorActual = jugador1;
        this.temporizador = new Temporizador();
        this.cartasRestantes = Datos.TOTAL_PAREJAS * 2;

        inicializarCartas();
    }

    private void inicializarCartas() {
        ArrayList<Carta> cartasTemporales = new ArrayList<>();

        for (int i = 0; i < Datos.TOTAL_PAREJAS; i++) {
            BufferedImage imagenDelantera = cargarImagenParaPareja(i);

            cartasTemporales.add(new Carta(i, Datos.ANCHO_CARTA, Datos.ALTO_CARTA, imagenDelantera));
            cartasTemporales.add(new Carta(i, Datos.ANCHO_CARTA, Datos.ALTO_CARTA, imagenDelantera));
        }

        Collections.shuffle(cartasTemporales);
        asignarPosicionesACartas(cartasTemporales);

    }

    private BufferedImage cargarImagenParaPareja(int id) {
        try {
            return Utilidades.cargarImagen("imagen" + (id % 21) + ".png", Datos.ANCHO_CARTA, Datos.ALTO_CARTA);
        } catch (Exception e) {
            return crearImagenDePrueba(id);
        }
    }

    private BufferedImage crearImagenDePrueba(int id) {
        BufferedImage img = new BufferedImage(Datos.ANCHO_CARTA, Datos.ALTO_CARTA, BufferedImage.TYPE_INT_ARGB);
        Graphics g = img.getGraphics();

        g.setColor(new Color((id * 50) % 256, (id * 100) % 256, (id * 150) % 256));
        g.fillRect(0, 0, Datos.ANCHO_CARTA, Datos.ALTO_CARTA);
        g.dispose();

        return img;
    }

    private void asignarPosicionesACartas(ArrayList<Carta> cartasBarajadas) {
        int index = 0;

        for (int fila = 0; fila < Datos.FILAS; fila++) {
            for (int columna = 0; columna < Datos.COLUMNAS; columna++) {
                if (index < cartasBarajadas.size()) {
                    Carta carta = cartasBarajadas.get(index);
                    int y = Datos.MARGEN_Y + (Datos.ALTO_CARTA * fila);
                    int x = Datos.MARGEN_X + (Datos.ANCHO_CARTA * columna);

                    carta.setPosicion(x, y);

                    cartas.add(carta);
                    index++;
                }
            }
        }
    }

    public void manejarClicCarta(Carta carta) {
        if (bloqueado || carta.isEmparejada() || carta.isVolteada())
            return;

        carta.voltear();

        if (primeraSeleccion == null) {
            primeraSeleccion = carta;
        } else {
            segundaSeleccion = carta;

            comprobarPareja();
        }
    }

    private void comprobarPareja() {
        bloqueado = true;

        if (primeraSeleccion.getId() == segundaSeleccion.getId()) {
            jugadorActual.aumentarPuntaje(1);

            Timer temporizador = new Timer(1000, e -> {
                primeraSeleccion.setEmparejada(true);
                segundaSeleccion.setEmparejada(true);
                cartasRestantes -= 2;

                if (cartasRestantes > 0) {
                    resetearSelecciones();
                    notificarUI();
                    bloqueado = false;
                } else {
                    bloqueado = false;
                    notificarUI();
                    terminarJuego();
                }
            });
            temporizador.setRepeats(false);
            temporizador.start();
        } else {
            cambiarTurno();
        }
    }

    private void cambiarTurno() {
        jugadorActual.setTurno(false);
        jugadorActual = (jugadorActual == jugador1) ? jugador2 : jugador1;
        jugadorActual.setTurno(true);

        Timer temporizador = new Timer(1000, e -> {
            primeraSeleccion.voltear();
            segundaSeleccion.voltear();
            resetearSelecciones();
            bloqueado = false;
            notificarUI();
        });
        temporizador.setRepeats(false);
        temporizador.start();
    }

    private void resetearSelecciones() {
        primeraSeleccion = null;
        segundaSeleccion = null;
    }

    private void terminarJuego() {
        if (jugador1.getPuntaje() > jugador2.getPuntaje()) {
            JOptionPane.showMessageDialog(null, "Gano el jugador 1!");
        } else if (jugador1.getPuntaje() < jugador2.getPuntaje()) {
            JOptionPane.showMessageDialog(null, "Gano el jugador 2!");
        } else {
            JOptionPane.showMessageDialog(null, "Es empate!");
        }
    }

    public void notificarUI() {
        if (escuchadorJuego == null)
            return;

        escuchadorJuego.enCambioDeEstadoJuego();
    }

    public void setEscuchadorJuego(EscuchadorJuego escuchadorJuego) {
        this.escuchadorJuego = escuchadorJuego;
    }

    public ArrayList<Carta> getCartas() {
        return cartas;
    }

    public Jugador getJugador1() {
        return jugador1;
    }

    public Jugador getJugador2() {
        return jugador2;
    }

    public Temporizador getTemporizador() {
        return temporizador;
    }

    public Boolean isBloqueado() {
        return bloqueado;
    }
}
