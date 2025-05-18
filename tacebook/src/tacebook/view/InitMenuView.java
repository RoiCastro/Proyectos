/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tacebook.view;

/**
 * Interfaz para la vista del menú inicial en la aplicación Tacebook.
 * Define los métodos que deben implementar las vistas del menú inicial.
 *
 * @author Roi
 */
public interface InitMenuView {
    /**
     * Muestra el menú de inicio de sesión.
     * @return true si se debe salir del bucle principal, false para continuar
     */
    public boolean showLoginMenu();

    /**
     * Muestra un mensaje de error cuando el login es incorrecto.
     */
    public void showLoginErrorMessage();

    /**
     * Muestra un mensaje de error de conexión.
     */
    public void showConnectionErrorMessage();

    /**
     * Muestra un mensaje de error de lectura de datos.
     */
    public void showReadErrorMessage();

    /**
     * Muestra un mensaje de error de escritura de datos.
     */
    public void showWriteErrorMessage();

    /**
     * Muestra un menú para cambiar el nombre de usuario.
     * @return el nuevo nombre de usuario introducido
     */
    public String showNewNameMenu();

    /**
     * Muestra el menú de registro de usuario.
     */
    public void showRegisterMenu();
}
