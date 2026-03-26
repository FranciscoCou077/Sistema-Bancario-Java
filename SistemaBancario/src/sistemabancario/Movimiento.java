/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabancario;

/**
 *
 * @author Francisco
 * @author Ernesto
 * @author Jaime
 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Movimiento {
    private String tipo; // "Depósito" o "Retiro"
    private double monto;
    private LocalDateTime fechaHora; // Guarda la fecha y hora exacta del sistema

    public Movimiento(String tipo, double monto) {
        this.tipo = tipo;
        this.monto = monto;
        this.fechaHora = LocalDateTime.now(); // Captura el momento exacto en que se crea
    }

    // Este método le da un formato bonito y legible al movimiento cuando lo imprimamos
    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return "[" + fechaHora.format(formato) + "] " + tipo + ": $" + monto;
    }
}