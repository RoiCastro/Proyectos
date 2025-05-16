/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tacebook.persistencia;

/**
 * Excepción que representa erros nas operacións de persistencia.
 */
public class PersistenceException extends Exception {

    private final int code;

    // Códigos de erro predefinidos
    public static final int CONECTION_ERROR = 0;
    public static final int CANNOT_READ = 1;
    public static final int CANNOT_WRITE = 2;

    /**
     * Construtor da excepción de persistencia.
     *
     * @param code Código do erro (CONECTION_ERROR, CANNOT_READ, CANNOT_WRITE)
     * @param message Mensaxe descritiva do erro
     */
    public PersistenceException(int code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * Obtén o código de erro asociado á excepción.
     *
     * @return código do erro
     */
    public int getCode() {
        return code;
    }
}
