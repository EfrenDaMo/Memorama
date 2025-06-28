/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memorama.core;

/**
 *
 * @author efren
 */
public class MaquinaEstadoJuego {
    private final Juego juego;
    private EstadoJuego estadoActual;

    public MaquinaEstadoJuego(Juego juego) {
        this.juego = juego;
        this.estadoActual = EstadoJuego.MENU_PRINCIPAL;
    }

    public EstadoJuego getEstadoActual() {
        return estadoActual;
    }

    public boolean transicionar(EstadoJuego nuevoEstado) {
        if (estadoActual.puedeTransicionarA(nuevoEstado)) {
            EstadoJuego estadoPrevio = estadoActual;
            estadoActual = nuevoEstado;
            ejecutarAccionesEntrada(estadoPrevio);
            juego.notificarInterfaz();
            return true;
        }

        return false;
    }

    private void ejecutarAccionesEntrada(EstadoJuego estadoPrevio) {
        switch (estadoPrevio) {
            case JUGANDO -> {
				if (estadoActual == EstadoJuego.EN_PAUSA) {
					juego.getTemporizador().pausar();
				} else if (estadoActual == EstadoJuego.TERMINADO) {
					juego.getTemporizador().pausar();
				}
			}
            case EN_PAUSA -> {
				if (estadoActual == EstadoJuego.JUGANDO) {
					juego.getTemporizador().reanudar();
				} else if (estadoActual == EstadoJuego.MENU_PRINCIPAL) {
					juego.reiniciarJuego();
				}
			}
            case TERMINADO -> {
				if (estadoActual == EstadoJuego.JUGANDO) {
					juego.reiniciarJuego();
					juego.getTemporizador().reiniciar();
				}
			}
            case MENU_PRINCIPAL -> {
				if (estadoActual == EstadoJuego.JUGANDO) {
					juego.reiniciarJuego();
				}
			}
        }
    }

    public void iniciarJuego() {
        transicionar(EstadoJuego.JUGANDO);
    }

    public void pausarJuego() {
        transicionar(EstadoJuego.EN_PAUSA);
    }

    public void renudarJuego() {
        transicionar(EstadoJuego.JUGANDO);
    }

    public void renunciarJuego() {
        transicionar(EstadoJuego.TERMINADO);
    }

    public void regresarAMenu() {
        transicionar(EstadoJuego.MENU_PRINCIPAL);
    }

}
