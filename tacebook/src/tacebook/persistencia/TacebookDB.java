/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tacebook.persistencia;

import java.util.ArrayList;
import java.util.List;
import tacebook.model.Profile;

/**
 * Clase que simula la base de datos con almacenamiento estático de perfiles.
 */
public class TacebookDB {

    /**
     * Colección estática que almacena todos los perfiles.
     */
    public static List<Profile> profiles = new ArrayList<>();

    /**
     * Cierra la conexión con la base de datos (simulada).
     * En esta implementación no es necesario realizar ninguna acción.
     */
    public static void close() {

    }
}
