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

import java.util.LinkedList;
import java.util.Random;

public class CuentaBasica {
    private int numCuenta;
    private double saldo;
    // 1. Aquí declaramos la estructura de datos para el historial
    private LinkedList<Movimiento> historialMovimientos; 

    public CuentaBasica() {
        Random generador = new Random();
        this.numCuenta = 100000 + generador.nextInt(900000);
        this.saldo = 0.0; 
        this.historialMovimientos = new LinkedList<>(); // Inicializamos la lista vacía
        
        // Registramos la apertura como el primer movimiento
        historialMovimientos.addFirst(new Movimiento("Apertura de Cuenta", 0.0));
    }

    public int getNumCuenta() { return numCuenta; }
    public double getSaldo() { return saldo; }

    public void depositar(double cantidad) {
        if (cantidad > 0) {
            saldo += cantidad;
            // 2. Guardamos el movimiento al principio de la lista (LIFO)
            historialMovimientos.addFirst(new Movimiento("Depósito", cantidad));
            System.out.println("✅ Depósito exitoso. Se agregaron $" + cantidad + " a la cuenta " + numCuenta);
        } else {
            System.out.println("⚠️ Error: La cantidad a depositar debe ser mayor a cero.");
        }
    }

    public boolean retirar(double cantidad) {
        if (cantidad > 0 && saldo >= cantidad) {
            saldo -= cantidad;
            // 3. Guardamos el retiro al principio de la lista
            historialMovimientos.addFirst(new Movimiento("Retiro", cantidad));
            System.out.println("✅ Retiro exitoso. Se retiraron $" + cantidad + " de la cuenta " + numCuenta);
            return true; 
        } else if (cantidad <= 0) {
            System.out.println("⚠️ Error: La cantidad a retirar debe ser mayor a cero.");
            return false;
        } else {
            System.out.println("❌ Fondos insuficientes. Tu saldo actual es de $" + saldo);
            return false; 
        }
    }
    
    // 4. Método nuevo para imprimir el Estado de Cuenta
    public void imprimirEstadoDeCuenta() {
        System.out.println("\n=== ESTADO DE CUENTA: " + numCuenta + " ===");
        System.out.println("Saldo Actual: $" + saldo);
        System.out.println("--- Historial de Movimientos ---");
        
        for (Movimiento mov : historialMovimientos) {
            System.out.println(mov.toString());
        }
        System.out.println("================================");
    }
}