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

public class CuentaInversion {
    private int numCuenta;
    private double capitalInvertido;
    private int plazoDias;
    private double tasaInteresAnual;
    private LinkedList<Movimiento> historial;

    public CuentaInversion(double montoInicial, int plazoDias) {
        Random generador = new Random();
        // Le damos un rango diferente para distinguirlas visualmente de las cuentas básicas
        this.numCuenta = 500000 + generador.nextInt(400000); 
        this.capitalInvertido = montoInicial;
        this.plazoDias = plazoDias;
        this.historial = new LinkedList<>();

        // Asignación de la tasa según el plazo elegido
        if (plazoDias == 28) {
            this.tasaInteresAnual = 0.1096; // 10.96%
        } else if (plazoDias == 91) {
            this.tasaInteresAnual = 0.1140; // 11.40%
        } else if (plazoDias == 182) {
            this.tasaInteresAnual = 0.1207; // 12.07%
        }

        // Se registra el depósito inicial en su propio historial
        historial.addFirst(new Movimiento("Apertura de Inversión (" + plazoDias + " días)", montoInicial));
    }

    public int getNumCuenta() { return numCuenta; }
    public double getCapitalInvertido() { return capitalInvertido; }
    public int getPlazoDias() { 
        return plazoDias; 
    }

    // Método que calcula cuánto dinero habrá al final usando la fórmula financiera estándar
    public void calcularProyeccion() {
        // Fórmula: Ganancia = Capital * Tasa Anual * (Días / 360)
        double ganancia = capitalInvertido * tasaInteresAnual * (plazoDias / 360.0);
        double totalFinal = capitalInvertido + ganancia;
        
        System.out.println("\n--- PROYECCIÓN DE TU INVERSIÓN ---");
        System.out.println("Capital Invertido: $" + capitalInvertido);
        System.out.println("Plazo: " + plazoDias + " días");
        System.out.println("Ganancia estimada: +$" + String.format("%.2f", ganancia));
        System.out.println("Total al finalizar: $" + String.format("%.2f", totalFinal));
        System.out.println("----------------------------------");
    }
    // Método que calcula y devuelve el dinero total a pagar
    public double calcularTotalFinal() {
        double ganancia = capitalInvertido * tasaInteresAnual * (plazoDias / 360.0);
        return capitalInvertido + ganancia;
    }
}