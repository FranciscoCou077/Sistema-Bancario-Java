/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabancario;

/**
 *
 * @author Francisco
 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Movimiento {
    private LocalDateTime fechaHora;
    private String tipo; // Ejemplo: "Depósito", "Retiro", "Apertura Inversión"
    private double monto;

    // Constructor
    public Movimiento(String tipo, double monto) {
        // Guarda el momento exacto de la transacción automáticamente
        this.fechaHora = LocalDateTime.now(); 
        this.tipo = tipo;
        this.monto = monto;
    }

    // Getters
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public String getTipo() {
        return tipo;
    }

    public double getMonto() {
        return monto;
    }

    // Este método formatea el movimiento para que se vea bonito al imprimirlo
    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return fechaHora.format(formato) + " | " + tipo + " | $" + String.format("%.2f", monto);
    }
}