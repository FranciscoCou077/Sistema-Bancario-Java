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

public class GestorBanco {
    
    // Aplicamos Map como pide el proyecto. 
    // La llave (String) es el numCliente y el valor (Cliente) es el objeto con sus datos.
    private Map<String, Cliente> directorioClientes;

    // Constructor
    public GestorBanco() {
        // Inicializamos el mapa
        this.directorioClientes = new HashMap<>();
    }

    // Método para agregar un cliente al banco
    public boolean registrarCliente(Cliente nuevoCliente) {
        // Verificamos que el número de cliente no exista ya en el sistema
        if (directorioClientes.containsKey(nuevoCliente.getNumCliente())) {
            return false; // El cliente ya existe
        }
        // Si no existe, lo guardamos en el mapa
        directorioClientes.put(nuevoCliente.getNumCliente(), nuevoCliente);
        return true; // Registro exitoso
    }

    // Método para buscar un cliente a velocidad instantánea
    public Cliente buscarCliente(String numCliente) {
        // El método .get() busca la llave y te devuelve al cliente completo
        return directorioClientes.get(numCliente); 
    }

    // Getter por si en el futuro necesitamos ver a todos los clientes
    public Map<String, Cliente> getDirectorioClientes() {
        return directorioClientes;
    }
}