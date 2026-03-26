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

public class TarjetaCredito {
    private int numTarjeta;
    private double limiteCredito;
    private double deudaActual;
    private LinkedList<Movimiento> historial;

    public TarjetaCredito(double limiteCredito) {
        Random generador = new Random();
        this.numTarjeta = 400000 + generador.nextInt(99999); 
        this.limiteCredito = limiteCredito;
        this.deudaActual = 0.0; 
        this.historial = new LinkedList<>();
        
        historial.addFirst(new Movimiento("Apertura TDC - Límite autorizado", limiteCredito));
    }

    public int getNumTarjeta() { return numTarjeta; }
    public double getLimiteCredito() { return limiteCredito; }
    public double getDeudaActual() { return deudaActual; }
    public double getCreditoDisponible() { return limiteCredito - deudaActual; }

    public boolean realizarCompra(double monto) {
        if (monto > 0 && (deudaActual + monto) <= limiteCredito) {
            deudaActual += monto;
            historial.addFirst(new Movimiento("Compra con Tarjeta", monto));
            System.out.println(" Compra autorizada. Cargaste $" + monto + " a la tarjeta " + numTarjeta);
            return true;
        } else if (monto <= 0) {
            System.out.println("️ Error: El monto de la compra debe ser mayor a cero.");
            return false;
        } else {
            System.out.println(" Compra declinada. Límite de crédito excedido. Disponible: $" + getCreditoDisponible());
            return false;
        }
    }

    public void abonarDeuda(double monto) {
        if (monto > 0) {
            deudaActual -= monto;
            historial.addFirst(new Movimiento("Pago a Tarjeta", monto));
            System.out.println(" Pago aplicado con éxito. Tu deuda actual ahora es de $" + deudaActual);
        } else {
            System.out.println("️ Error: El abono debe ser mayor a cero.");
        }
    }
    
    public void imprimirEstadoDeCuenta() {
        System.out.println("\n=== ESTADO DE CUENTA TDC: " + numTarjeta + " ===");
        System.out.println("Límite de Crédito: $" + limiteCredito);
        System.out.println("Deuda Actual: $" + deudaActual);
        System.out.println("Crédito Disponible para Compras: $" + getCreditoDisponible());
        System.out.println("--- Historial de Movimientos ---");
        for (Movimiento mov : historial) {
            System.out.println(mov.toString());
        }
        System.out.println("================================");
    }
}