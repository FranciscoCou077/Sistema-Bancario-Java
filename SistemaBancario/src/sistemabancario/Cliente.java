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

import java.util.HashSet;
import java.util.Set;

public class Cliente {
    private int numCliente; 
    private String nombre;
    
    // Las tres colecciones separadas para cada tipo de producto
    private Set<CuentaBasica> cuentas; 
    private Set<CuentaInversion> inversiones; 
    private Set<TarjetaCredito> tarjetas; 

    public Cliente(int numCliente, String nombre) {
        this.numCliente = numCliente;
        this.nombre = nombre;
        // Inicializamos las tres colecciones vacías al crear al cliente
        this.cuentas = new HashSet<>();
        this.inversiones = new HashSet<>();
        this.tarjetas = new HashSet<>();
    }

    // --- GETTERS BÁSICOS ---
    public int getNumCliente() { return numCliente; }
    public String getNombre() { return nombre; }

    // --- MÉTODOS PARA CUENTAS BÁSICAS ---
    public void agregarCuenta(CuentaBasica nuevaCuenta) {
        cuentas.add(nuevaCuenta);
    }

    public CuentaBasica buscarCuenta(int numCuentaBuscar) {
        for (CuentaBasica cuenta : cuentas) {
            if (cuenta.getNumCuenta() == numCuentaBuscar) {
                return cuenta; 
            }
        }
        return null; 
    }

    public boolean tieneCuentaBasica() {
        return !cuentas.isEmpty();
    }

    // --- MÉTODOS PARA CUENTAS DE INVERSIÓN ---
    public void agregarInversion(CuentaInversion nuevaInversion) {
        inversiones.add(nuevaInversion);
    }

    public CuentaInversion buscarInversion(int numCuentaBuscar) {
        for (CuentaInversion inv : inversiones) {
            if (inv.getNumCuenta() == numCuentaBuscar) {
                return inv;
            }
        }
        return null; 
    }

    public void eliminarInversion(CuentaInversion inversionTerminada) {
        inversiones.remove(inversionTerminada);
    }

    // --- MÉTODOS PARA TARJETAS DE CRÉDITO ---
    public void agregarTarjeta(TarjetaCredito nuevaTarjeta) {
        tarjetas.add(nuevaTarjeta);
    }

    public TarjetaCredito buscarTarjeta(int numTarjetaBuscar) {
        for (TarjetaCredito tdc : tarjetas) {
            if (tdc.getNumTarjeta() == numTarjetaBuscar) {
                return tdc;
            }
        }
        return null;
    }

    // --- MÉTODO PARA IMPRIMIR TODOS LOS PRODUCTOS ---
    public void mostrarCuentas() {
        System.out.println("\n--- PRODUCTOS DE " + nombre.toUpperCase() + " ---");
        
        if (cuentas.isEmpty() && inversiones.isEmpty() && tarjetas.isEmpty()) {
            System.out.println("El cliente no tiene productos contratados.");
            return; // Termina la función aquí si no hay nada que mostrar
        }

        if (!cuentas.isEmpty()) {
            System.out.println("CUENTAS BÁSICAS:");
            for (CuentaBasica cuenta : cuentas) {
                System.out.println(" -> No. " + cuenta.getNumCuenta() + " | Saldo: $" + cuenta.getSaldo());
            }
        }

        if (!inversiones.isEmpty()) {
            System.out.println("\nCUENTAS DE INVERSIÓN:");
            for (CuentaInversion inv : inversiones) {
                System.out.println(" -> No. " + inv.getNumCuenta() + " | Capital: $" + inv.getCapitalInvertido() + " a " + inv.getPlazoDias() + " días.");
            }
        }

        if (!tarjetas.isEmpty()) {
            System.out.println("\nTARJETAS DE CRÉDITO:");
            for (TarjetaCredito tdc : tarjetas) {
                System.out.println(" -> No. " + tdc.getNumTarjeta() + " | Deuda: $" + tdc.getDeudaActual() + " / Límite: $" + tdc.getLimiteCredito());
            }
        }
        System.out.println("-----------------------------------");
    }
    
    
}