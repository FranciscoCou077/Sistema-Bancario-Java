/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sistemabancario;

import java.util.Scanner;

/**
 *
 * @author Francisco
 * @author Ernesto
 * @author Jaime
 */

public class SistemaBancario {

    public static void main(String[] args) {
        GestorBanco miBanco = new GestorBanco();
        Scanner sn = new Scanner(System.in);

        System.out.println(" BIENVENIDO AL SISTEMA BANCARIO ");

        boolean salir = false;
        while (!salir) {
            System.out.println("\n--- MENÚ DE CAJERO ---");
            System.out.println("1. Registrar Nuevo Cliente");
            System.out.println("2. Buscar Cliente por ID");
            System.out.println("3. Ver todos los clientes registrados");
            System.out.println("4. Abrir nuevo producto (Cuenta / Inversión / Tarjeta)");
            System.out.println("5. Depositar a una cuenta");
            System.out.println("6. Retirar de una cuenta");
            System.out.println("7. Ver Estado de Cuenta (Cuentas Básicas)");
            System.out.println("8. Liquidar Inversión (Cobrar rendimientos)");
            System.out.println("9. Operaciones con Tarjeta de Crédito");
            System.out.println("10. Salir");
            System.out.print("Selecciona una opción: ");

            int opcion = sn.nextInt();
            sn.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    System.out.print("Ingresa el nombre completo del nuevo cliente: ");
                    String nombreNuevo = sn.nextLine();
                    miBanco.registrarCliente(nombreNuevo);
                    break;
                    
                case 2:
                    System.out.print("Ingrese el ID a buscar: ");
                    int buscarId = sn.nextInt(); 
                    sn.nextLine(); 
                    Cliente encontrado = miBanco.buscarCliente(buscarId);
                    if (encontrado != null) {
                        System.out.println(" Cliente encontrado: " + encontrado.getNombre());
                    } else {
                        System.out.println("️ No existe un cliente con ese ID.");
                    }
                    break;
                    
                case 3:
                    miBanco.mostrarTodosLosClientes();
                    break;
                    
                case 4:
                    System.out.print("Ingrese el ID del cliente: ");
                    int idParaCuenta = sn.nextInt();
                    sn.nextLine(); 
                    
                    System.out.println("\n¿Qué producto deseas abrir?");
                    System.out.println("1. Cuenta Básica");
                    System.out.println("2. Cuenta de Inversión");
                    System.out.println("3. Tarjeta de Crédito");
                    System.out.print("Elige una opción (1, 2 o 3): ");
                    int tipoProd = sn.nextInt();
                    sn.nextLine();
                    
                    if (tipoProd == 1) {
                        miBanco.abrirCuentaACliente(idParaCuenta);
                    } else if (tipoProd == 2) {
                        // AQUÍ ESTÁ LA LÓGICA CORREGIDA PARA RESTAR LOS FONDOS
                        Cliente clienteInv = miBanco.buscarCliente(idParaCuenta);
                        
                        if (clienteInv != null && clienteInv.tieneCuentaBasica()) {
                            clienteInv.mostrarCuentas(); 
                            
                            System.out.print("\nIngrese el Número de la CUENTA BÁSICA de donde se tomará el dinero: ");
                            int cuentaOrigen = sn.nextInt();
                            
                            System.out.println("\n--- TASAS DE INVERSIÓN DISPONIBLES ---");
                            System.out.println("- 28 días  -> 10.96% Anual");
                            System.out.println("- 91 días  -> 11.40% Anual");
                            System.out.println("- 182 días -> 12.07% Anual");
                            System.out.print("¿A cuántos días deseas invertir? (28/91/182): ");
                            int diasInv = sn.nextInt();
                            
                            System.out.print("¿Qué cantidad deseas invertir?");
                            double montoInv = sn.nextDouble();
                            sn.nextLine(); 
                            
                            miBanco.abrirCuentaInversionACliente(idParaCuenta, cuentaOrigen, montoInv, diasInv);
                        } else {
                            System.out.println(" POLÍTICA DEL BANCO: El cliente debe tener primero al menos una Cuenta Básica para fondear la inversión.");
                        }
                    } else if (tipoProd == 3) {
                        System.out.print("¿Qué límite de crédito autorizará el banco?");
                        double limite = sn.nextDouble();
                        sn.nextLine();
                        miBanco.abrirTarjetaCreditoACliente(idParaCuenta, limite);
                    } else {
                        System.out.println("️ Opción no válida. Operación cancelada.");
                    }
                    break;

                case 5:
                    System.out.print("Ingrese el ID del cliente que va a depositar: ");
                    int idDep = sn.nextInt();
                    Cliente clienteDep = miBanco.buscarCliente(idDep);
                    
                    if (clienteDep != null) {
                        clienteDep.mostrarCuentas(); 
                        System.out.print("\nIngrese el Número de Cuenta al que desea depositar: ");
                        int cuentaDep = sn.nextInt();
                        System.out.print("Ingrese la cantidad a depositar: $");
                        double montoDep = sn.nextDouble(); 
                        sn.nextLine(); 
                        miBanco.realizarDeposito(idDep, cuentaDep, montoDep);
                    } else {
                        System.out.println("️ Error: Cliente no encontrado.");
                    }
                    break;

                case 6:
                    System.out.print("Ingrese el ID del cliente que va a retirar: ");
                    int idRet = sn.nextInt();
                    Cliente clienteRet = miBanco.buscarCliente(idRet);
                    
                    if (clienteRet != null) {
                        clienteRet.mostrarCuentas(); 
                        System.out.print("\nIngrese el Número de Cuenta de la cual desea retirar: ");
                        int cuentaRet = sn.nextInt();
                        System.out.print("Ingrese la cantidad a retirar: $");
                        double montoRet = sn.nextDouble(); 
                        sn.nextLine(); 
                        miBanco.realizarRetiro(idRet, cuentaRet, montoRet);
                    } else {
                        System.out.println("️ Error: Cliente no encontrado.");
                    }
                    break;

                case 7:
                    System.out.print("Ingrese el ID del cliente: ");
                    int idEst = sn.nextInt();
                    Cliente clienteEst = miBanco.buscarCliente(idEst);
                    
                    if (clienteEst != null) {
                        clienteEst.mostrarCuentas(); 
                        System.out.print("\nIngrese el Número de Cuenta para ver su historial: ");
                        int cuentaEst = sn.nextInt();
                        sn.nextLine(); 
                        miBanco.verEstadoCuenta(idEst, cuentaEst);
                    } else {
                        System.out.println("️ Error: Cliente no encontrado.");
                    }
                    break;

                case 8:
                    System.out.print("Ingrese el ID del cliente: ");
                    int idLiq = sn.nextInt();
                    Cliente clienteLiq = miBanco.buscarCliente(idLiq);
                    
                    if (clienteLiq != null) {
                        clienteLiq.mostrarCuentas();
                        System.out.print("\nIngrese el Número de CONTRATO DE INVERSIÓN a liquidar: ");
                        int cuentaInvLiq = sn.nextInt();
                        System.out.print("Ingrese el Número de su CUENTA BÁSICA donde recibirá el dinero: ");
                        int cuentaBasicaDestino = sn.nextInt();
                        sn.nextLine();
                        
                        miBanco.liquidarInversion(idLiq, cuentaInvLiq, cuentaBasicaDestino);
                    } else {
                        System.out.println("️ Error: Cliente no encontrado.");
                    }
                    break;
                    
                case 9:
                    System.out.print("Ingrese el ID del cliente titular de la tarjeta: ");
                    int idTdc = sn.nextInt();
                    Cliente clienteTdc = miBanco.buscarCliente(idTdc);
                    
                    if (clienteTdc != null) {
                        clienteTdc.mostrarCuentas();
                        System.out.print("\nIngrese el Número de Tarjeta a utilizar: ");
                        int numTdc = sn.nextInt();
                        
                        System.out.println("\n¿Qué operación deseas realizar?");
                        System.out.println("1. Registrar una Compra");
                        System.out.println("2. Pagar la Deuda");
                        System.out.print("Elige una opción (1 o 2): ");
                        int opcTdc = sn.nextInt();
                        
                        if (opcTdc == 1) {
                            System.out.print("Ingresa el monto de la compra: $");
                            double montoCompra = sn.nextDouble();
                            sn.nextLine(); // Limpiar buffer
                            miBanco.registrarCompraTDC(idTdc, numTdc, montoCompra);
                            
                        } else if (opcTdc == 2) {
                            // Verificamos que tenga una cuenta básica para poder pagar
                            if (clienteTdc.tieneCuentaBasica()) {
                                System.out.print("\nIngrese el Número de la CUENTA BÁSICA de donde saldrá el dinero para pagar: ");
                                int cuentaOrigenPago = sn.nextInt();
                                
                                System.out.print("Ingresa el monto a pagar: $");
                                double montoPago = sn.nextDouble();
                                sn.nextLine(); // Limpiar buffer
                                
                                miBanco.pagarTarjetaCredito(idTdc, numTdc, cuentaOrigenPago, montoPago);
                            } else {
                                System.out.println(" POLÍTICA DEL BANCO: Necesitas abrir una Cuenta Básica y depositarle fondos para poder pagar tu tarjeta.");
                                sn.nextLine();
                            }
                        } else {
                            System.out.println("️ Operación inválida.");
                        }
                    } else {
                        System.out.println("️ Error: Cliente no encontrado.");
                    }
                    break;

                case 10:
                    salir = true;
                    System.out.println("Cerrando sesión del cajero...");
                    break;
                    
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }
        sn.close();
    }
}