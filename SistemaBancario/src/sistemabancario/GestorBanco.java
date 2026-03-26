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

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GestorBanco {
    // El mapa ahora usa Integer en lugar de String como llave
    private Map<Integer, Cliente> directorioClientes;
    private Random generadorAleatorio;

    public GestorBanco() {
        this.directorioClientes = new HashMap<>();
        this.generadorAleatorio = new Random();
    }

    // 2. Generación Automática de ID único (Ej: número entre 10000 y 99999)
    private int generarIdUnico() {
        int nuevoId;
        do {
            nuevoId = 10000 + generadorAleatorio.nextInt(90000);
        } while (directorioClientes.containsKey(nuevoId)); // Repite si el ID ya existe por casualidad
        
        return nuevoId;
    }

    // Ahora solo pedimos el nombre, el sistema se encarga del ID
    public void registrarCliente(String nombre) {
        int idAsignado = generarIdUnico();
        Cliente nuevoCliente = new Cliente(idAsignado, nombre);
        
        directorioClientes.put(idAsignado, nuevoCliente);
        
        System.out.println("\n CLIENTE REGISTRADO CON ÉXITO");
        System.out.println("-> Nombre: " + nombre);
        System.out.println("-> ID Asignado Automáticamente: " + idAsignado);
    }

    // Búsqueda ahora recibe un int
    public Cliente buscarCliente(int numCliente) {
        return directorioClientes.get(numCliente);
    }
    // Método para imprimir a todos los clientes registrados
    public void mostrarTodosLosClientes() {
        System.out.println("\n--- LISTA DE CLIENTES REGISTRADOS ---");
        
        // Verificamos si el mapa está vacío
        if (directorioClientes.isEmpty()) {
            System.out.println("Aún no hay clientes registrados en el sistema.");
        } else {
            // Iteramos sobre todos los valores guardados en el HashMap
            for (Cliente cliente : directorioClientes.values()) {
                System.out.println("ID: " + cliente.getNumCliente() + " | Nombre: " + cliente.getNombre());
            }
        }
        System.out.println("-------------------------------------");
    }
    // Método para abrirle una cuenta a un cliente existente
    public void abrirCuentaACliente(int numCliente) {
        Cliente cliente = buscarCliente(numCliente);
        
        if (cliente != null) {
            // Si el cliente existe, le creamos su cuenta
            CuentaBasica nuevaCuenta = new CuentaBasica();
            cliente.agregarCuenta(nuevaCuenta); // Se la guardamos en su Set
            
            System.out.println("\n CUENTA CREADA CON ÉXITO PARA: " + cliente.getNombre());
            System.out.println("-> Número de Cuenta Asignado: " + nuevaCuenta.getNumCuenta());
            System.out.println("-> Saldo inicial: $" + nuevaCuenta.getSaldo());
        } else {
            System.out.println("\n Error: No se encontró ningún cliente con el ID " + numCliente);
        }
    }
    // Método para procesar un depósito desde el Gestor
    public void realizarDeposito(int idCliente, int numCuenta, double cantidad) {
        Cliente cliente = buscarCliente(idCliente);
        
        if (cliente != null) {
            CuentaBasica cuenta = cliente.buscarCuenta(numCuenta);
            if (cuenta != null) {
                cuenta.depositar(cantidad); // Aquí llamamos al método que ya hiciste en CuentaBasica
            } else {
                System.out.println("️ Error: La cuenta " + numCuenta + " no pertenece a este cliente o no existe.");
            }
        } else {
            System.out.println("️ Error: No se encontró ningún cliente con ese ID.");
        }
    }
    // Método para procesar un retiro desde el Gestor
    public void realizarRetiro(int idCliente, int numCuenta, double cantidad) {
        Cliente cliente = buscarCliente(idCliente);
        
        if (cliente != null) {
            CuentaBasica cuenta = cliente.buscarCuenta(numCuenta);
            if (cuenta != null) {
                // Aquí llamamos al método que ya hiciste en CuentaBasica, que verifica si hay saldo suficiente
                cuenta.retirar(cantidad); 
            } else {
                System.out.println("️ Error: La cuenta " + numCuenta + " no pertenece a este cliente o no existe.");
            }
        } else {
            System.out.println("️ Error: No se encontró ningún cliente con ese ID.");
        }
    }
    // Método para ver el estado de cuenta y su historial
    public void verEstadoCuenta(int idCliente, int numCuenta) {
        Cliente cliente = buscarCliente(idCliente);
        
        if (cliente != null) {
            CuentaBasica cuenta = cliente.buscarCuenta(numCuenta);
            if (cuenta != null) {
                cuenta.imprimirEstadoDeCuenta(); // Llama a la función que acabamos de crear
            } else {
                System.out.println("️ Error: La cuenta " + numCuenta + " no pertenece a este cliente.");
            }
        } else {
            System.out.println("️ Error: No se encontró ningún cliente con ese ID.");
        }
    }
   // Método para abrir Inversión con validación de cuenta básica, monto y plazo
    public void abrirCuentaInversionACliente(int numCliente, double monto, int plazo) {
        Cliente cliente = buscarCliente(numCliente);
        
        if (cliente != null) {
            // REGLA DE NEGOCIO: Validar que el cliente tenga primero una cuenta básica
            if (cliente.tieneCuentaBasica()) {
                if (monto >= 100.0) {
                    if (plazo == 28 || plazo == 91 || plazo == 182) {
                        CuentaInversion nuevaInversion = new CuentaInversion(monto, plazo);
                        cliente.agregarInversion(nuevaInversion);
                        
                        System.out.println("\n CONTRATO DE INVERSIÓN CREADO EXITOSAMENTE");
                        System.out.println("-> Número asignado: " + nuevaInversion.getNumCuenta());
                        nuevaInversion.calcularProyeccion(); 
                    } else {
                        System.out.println("️ Error: Plazo no válido. Solo se admiten 28, 91 o 182 días.");
                    }
                } else {
                    System.out.println("️ Error: El monto mínimo de inversión es de $100.00 MXN.");
                }
            } else {
                // Si entra aquí, es porque su Set de cuentas básicas está vacío
                System.out.println(" POLÍTICA DEL BANCO: Para abrir una inversión, el cliente debe tener primero al menos una Cuenta Básica.");
            }
        } else {
            System.out.println("\n️ Error: No se encontró ningún cliente con el ID " + numCliente);
        }
    }
    // Método para liquidar la inversión y pasar el dinero a la cuenta básica
    public void liquidarInversion(int idCliente, int numInversion, int numCuentaDestino) {
        Cliente cliente = buscarCliente(idCliente);
        
        if (cliente != null) {
            CuentaInversion inversion = cliente.buscarInversion(numInversion);
            if (inversion != null) {
                CuentaBasica cuentaDestino = cliente.buscarCuenta(numCuentaDestino);
                if (cuentaDestino != null) {
                    
                    // 1. Calculamos cuánto le toca
                    double totalAPagar = inversion.calcularTotalFinal();
                    
                    // 2. Le depositamos a su cuenta básica (esto generará un "Movimiento" automático)
                    cuentaDestino.depositar(totalAPagar);
                    
                    // 3. Cerramos/Borramos el contrato de inversión
                    cliente.eliminarInversion(inversion);
                    
                    System.out.println("\n INVERSIÓN LIQUIDADA CON ÉXITO");
                    System.out.println("Se depositaron $" + String.format("%.2f", totalAPagar) + " a tu cuenta " + numCuentaDestino);
                    
                } else {
                    System.out.println("️ Error: La Cuenta Básica destino no existe o no te pertenece.");
                }
            } else {
                System.out.println("️ Error: No se encontró el contrato de inversión No. " + numInversion);
            }
        } else {
            System.out.println("️ Error: Cliente no encontrado.");
        }
    }
    public void abrirTarjetaCreditoACliente(int numCliente, double limiteAutorizado) {
        Cliente cliente = buscarCliente(numCliente);
        if (cliente != null) {
            if (limiteAutorizado >= 1000) { // Regla: mínimo $1000 de límite
                TarjetaCredito nuevaTDC = new TarjetaCredito(limiteAutorizado);
                cliente.agregarTarjeta(nuevaTDC);
                System.out.println("\n TARJETA DE CRÉDITO APROBADA Y ASIGNADA A: " + cliente.getNombre());
                System.out.println("-> Número de Tarjeta: " + nuevaTDC.getNumTarjeta());
                System.out.println("-> Límite Autorizado: $" + nuevaTDC.getLimiteCredito());
            } else {
                System.out.println("️ Error: El límite de crédito mínimo a otorgar es de $1,000 MXN.");
            }
        } else {
            System.out.println("️ Error: Cliente no encontrado.");
        }
    }

    public void usarTarjetaCredito(int idCliente, int numTarjeta, double monto, boolean esCompra) {
        Cliente cliente = buscarCliente(idCliente);
        if (cliente != null) {
            TarjetaCredito tdc = cliente.buscarTarjeta(numTarjeta);
            if (tdc != null) {
                if (esCompra) {
                    tdc.realizarCompra(monto);
                } else {
                    tdc.abonarDeuda(monto);
                }
            } else {
                System.out.println("️ Error: La tarjeta " + numTarjeta + " no existe o no pertenece a este cliente.");
            }
        } else {
            System.out.println("️ Error: Cliente no encontrado.");
        }
    }
}