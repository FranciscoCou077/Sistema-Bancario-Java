/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Francisco
 */
package sistemabancario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CuentaInversion {
    private String numCuenta;
    private double capital;
    private int plazoDias;
    private double porcentajeRendimiento;
    private LocalDate fechaInicio;
    private LocalDate fechaVencimiento;
    
    // Al igual que la básica, usamos List para su propio historial
    private List<Movimiento> historial;

    // Constructor
    public CuentaInversion(String numCuenta, double capital, int plazoDias, double porcentajeRendimiento) {
        this.numCuenta = numCuenta;
        this.capital = capital;
        this.plazoDias = plazoDias;
        this.porcentajeRendimiento = porcentajeRendimiento;
        
        // Manejo de fechas automático
        this.fechaInicio = LocalDate.now(); // Toma la fecha de hoy
        this.fechaVencimiento = this.fechaInicio.plusDays(plazoDias); // Calcula cuándo termina
        
        this.historial = new ArrayList<>();
    }

    // Método para calcular cuánto dinero habrá al final del plazo
    public double calcularRetornoEsperado() {
        double ganancia = capital * (porcentajeRendimiento / 100);
        return capital + ganancia;
    }

    // Getters
    public String getNumCuenta() {
        return numCuenta;
    }

    public double getCapital() {
        return capital;
    }

    public int getPlazoDias() {
        return plazoDias;
    }

    public double getPorcentajeRendimiento() {
        return porcentajeRendimiento;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public List<Movimiento> getHistorial() {
        return historial;
    }
}