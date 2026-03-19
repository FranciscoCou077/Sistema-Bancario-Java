/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sistemabancario;

import java.util.Scanner;

/**
 *
 * @author Francisco
 */
public class SistemaBancario {

   public static void main(String[] args) {
        // 1. Instanciamos el "Cerebro" del banco
        GestorBanco miBanco = new GestorBanco();
        Scanner sn = new Scanner(System.in);
        
        System.out.println("--- BIENVENIDO AL SISTEMA BANCARIO UNAM (FI) ---");
        
        // 2. Registro de prueba para que tus compañeros vean algo al iniciar
        Cliente clientePrueba = new Cliente("425121623", "Francisco Coutiño");
        miBanco.registrarCliente(clientePrueba);
        
        boolean salir = false;
        while (!salir) {
            System.out.println("\n--- MENÚ DE CAJERO ---");
            System.out.println("1. Registrar Nuevo Cliente");
            System.out.println("2. Buscar Cliente por ID");
            System.out.println("3. Salir");
            System.out.print("Selecciona una opción: ");
            
            int opcion = sn.nextInt();
            sn.nextLine(); // Limpiar el buffer
            
            switch (opcion) {
                case 1:
                    System.out.print("Ingrese ID del cliente: ");
                    String id = sn.nextLine();
                    System.out.print("Ingrese Nombre completo: ");
                    String nombre = sn.nextLine();
                    
                    Cliente nuevo = new Cliente(id, nombre);
                    if (miBanco.registrarCliente(nuevo)) {
                        System.out.println("✅ Cliente registrado con éxito.");
                    } else {
                        System.out.println("❌ Error: El ID ya existe en el sistema.");
                    }
                    break;
                    
                case 2:
                    System.out.print("Ingrese el ID a buscar: ");
                    String buscarId = sn.nextLine();
                    Cliente encontrado = miBanco.buscarCliente(buscarId);
                    
                    if (encontrado != null) {
                        System.out.println("🔍 Cliente encontrado: " + encontrado.getNombre());
                    } else {
                        System.out.println("⚠️ No existe un cliente con ese ID.");
                    }
                    break;
                    
                case 3:
                    salir = true;
                    System.out.println("Cerrando sesión del cajero...");
                    break;
            }
        }
    }
}