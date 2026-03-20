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
private String numCliente;
    private String nombre;
    
    // Aplicamos Set como pide el proyecto para evitar cuentas duplicadas
    private Set<CuentaBasica> cuentasBasicas;
    private Set<CuentaInversion> cuentasInversion;
    // private TarjetaCredito tarjeta; // Comentado por ahora, es para los puntos extra

    // Constructor
    public Cliente(String numCliente, String nombre) {
        this.numCliente = numCliente;
        this.nombre = nombre;
        // Iniciaslizo las colecciones para evitar errores NullPointerException
        this.cuentasBasicas = new HashSet<>();
        this.cuentasInversion = new HashSet<>();
    }

    // Métodos del programa correspondientes del diseño del UML 
    public void modificarDatos(String nuevoNombre) {
        this.nombre = nuevoNombre;
    }

    public double obtenerSaldoTotal() {
        double total = 0.0;
        // Aquí iteraremos sobre las cuentas para sumar el saldo más adelante
        return total;
    }

    // Getters
    public String getNumCliente() {
        return numCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public Set<CuentaBasica> getCuentasBasicas() {
        return cuentasBasicas;
    }

    public Set<CuentaInversion> getCuentasInversion() {
        return cuentasInversion;
    }
}