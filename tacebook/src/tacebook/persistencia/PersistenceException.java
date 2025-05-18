/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tacebook.persistencia;

/**
 * Excepción que representa errores en las operaciones de persistencia.
 */
public class PersistenceException extends Exception {

    /**
     * Código de error asociado a la excepción.
     */
    private final int code;

    /**
     * Código de error: error de conexión.
     */
    public static final int CONNECTION_ERROR = 0;
    /**
     * Código de error: no se puede leer.
     */
    public static final int CANNOT_READ = 1;
    /**
     * Código de error: no se puede escribir.
     */
    public static final int CANNOT_WRITE = 2;

    /**
     * Constructor de la excepción de persistencia.
     *
     * @param code    Código del error (CONNECTION_ERROR, CANNOT_READ, CANNOT_WRITE)
     * @param message Mensaje descriptivo del error
     */
    public PersistenceException(int code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * Obtiene el código de error asociado a la excepción.
     *
     * @return código del error
     */
    public int getCode() {
        return code;
    }
}
