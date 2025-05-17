/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tacebook.view;

/**
 *
 * @author Roi
 */
public interface InitMenuView {
    // Método para mostrar el menú de inicio de sesión, que devuelve un valor booleano
    public boolean showLoginMenu();
    
    // Métodos para mostrar mensajes de error, sin retorno (void)
    public void showLoginErrorMessage();
    public void showConnectionErrorMessage();
    public void showReadErrorMessage();
    public void showWriteErrorMessage();
    
    // Método para mostrar un menú para cambiar nombre, que devuelve un String
    public String showNewNameMenu();
    
    // Método para mostrar el menú de registro, sin retorno (void)
    public void showRegisterMenu();
}

