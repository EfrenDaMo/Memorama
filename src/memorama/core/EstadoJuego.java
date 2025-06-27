/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memorama.core;

/**
 *
 * @author efren
 */
public enum EstadoJuego {
    MENU_PRINCIPAL,
    JUGANDO,
    EN_PAUSA,
    TERMINADO;

    public boolean puedeTransicionarA(EstadoJuego nuevoEstado) {
        return switch (this) {
            case MENU_PRINCIPAL -> nuevoEstado == JUGANDO;
            case JUGANDO -> nuevoEstado == EN_PAUSA || nuevoEstado == TERMINADO;
            case EN_PAUSA -> nuevoEstado == JUGANDO || nuevoEstado == MENU_PRINCIPAL;
            case TERMINADO -> nuevoEstado == MENU_PRINCIPAL || nuevoEstado == JUGANDO;
            default -> false;
        };
    }
}
