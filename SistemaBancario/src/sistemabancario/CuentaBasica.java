/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabancario;

/**
 *
 * @author Francisco
 */

import java.util.ArrayList;
import java.util.List;

public class CuentaBasica {
    private String numCuenta;
    private double saldo;
    
    // Aplicamos List como pide el proyecto para el historial cronológico
    private List<Movimiento> historial;

    // Constructor
    public CuentaBasica(String numCuenta, double depositoInicial) {
        this.numCuenta = numCuenta;
        this.saldo = depositoInicial;
        // Inicializamos la lista para evitar errores de tipo NullPointerException
        this.historial = new ArrayList<>();
    }

    // Métodos para operar la cuenta
    public void depositar(double monto) {
        if (monto > 0) {
            this.saldo += monto;
            // Aquí agregaremos el movimiento al historial más adelante
        }
    }

    public boolean retirar(double monto) {
        if (monto > 0 && this.saldo >= monto) {
            this.saldo -= monto;
            // Aquí agregaremos el movimiento al historial más adelante
            return true; // Retiro exitoso
        }
        return false; // Fondos insuficientes
    }

    // Getters
    public String getNumCuenta() {
        return numCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public List<Movimiento> getHistorial() {
        return historial;
    }
}