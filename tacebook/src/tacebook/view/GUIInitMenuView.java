/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tacebook.view;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import tacebook.controller.InitMenuController;

/**
 * Vista gráfica para el menú inicial de la aplicación Tacebook.
 * Permite al usuario iniciar sesión o registrarse mediante cuadros de diálogo.
 *
 * @author Roi
 */
public class GUIInitMenuView implements InitMenuView {

    private InitMenuController controller;

    /**
     * Crea una nueva instancia de GUIInitMenuView.
     * 
     * @param controller el controlador del menú inicial
     */
    public GUIInitMenuView(InitMenuController controller) {
        this.controller = controller;
    }

    /**
     * Muestra el menú de inicio de sesión y registro.
     * 
     * @return true si se debe salir del bucle principal, false para continuar
     */
    @Override
    public boolean showLoginMenu() {
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        Object[] message = {
                "Nome de usuario:", usernameField,
                "Contrasinal:", passwordField
        };

        Object[] options = { "Saír", "Rexistrarse", "Iniciar sesión" };

        int option = JOptionPane.showOptionDialog(
                null, message, "Entrar en tacebook",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[2]);

        switch (option) {
            case 0, JOptionPane.CLOSED_OPTION -> {
                return true; // Saír do programa → salir = true no bucle
            }
            case 1 -> {
                controller.register();
                return true; // <-- Añadido: salir del bucle tras registro
            }
            case 2 -> {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                controller.login(username, password);
                return true;
            }
            default -> {

            }
        }

        return false; // Continuar no bucle
    }

    /**
     * Muestra un cuadro de diálogo para introducir un nuevo nombre de usuario
     * cuando el anterior ya existe.
     * 
     * @return el nuevo nombre de usuario introducido
     */
    @Override
    public String showNewNameMenu() {
        return JOptionPane.showInputDialog(null, "O nome de usuario xa existe. Introduce outro:");
    }

    /**
     * Muestra el menú de registro de usuario.
     */
    @Override
    public boolean showRegisterMenu() {
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JPasswordField repeatPasswordField = new JPasswordField();
        JTextField statusField = new JTextField();

        Object[] message = {
                "Nome de usuario:", usernameField,
                "Contrasinal:", passwordField,
                "Repite o contrasinal:", repeatPasswordField,
                "Estado:", statusField
        };

        int option = JOptionPane.showConfirmDialog(
                null, message, "Rexistrarse", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String repeatPassword = new String(repeatPasswordField.getPassword());
            String status = statusField.getText();

            if (!password.equals(repeatPassword)) {
                JOptionPane.showMessageDialog(null, 
                    "Os contrasinais non coinciden.", 
                    "Erro", 
                    JOptionPane.ERROR_MESSAGE);
                return false; // Volver al menú principal sin registrar
            }

            controller.createProfile(username, password, status); // chama ao controlador
            return true;
        }
        return false; // Si cancela, volver al menú principal
    }

    /**
     * Muestra un mensaje de error cuando el login es incorrecto.
     */
    @Override
    public void showLoginErrorMessage() {
        JOptionPane.showMessageDialog(null, 
            "Usuario ou contrasinal incorrectos.", 
            "Erro de acceso", 
            JOptionPane.ERROR_MESSAGE);
        // No llamar a showLoginMenu() aquí, el bucle principal lo hará
    }

    /**
     * Muestra un mensaje de error de conexión.
     */
    @Override
    public void showConnectionErrorMessage() {
        javax.swing.JOptionPane.showMessageDialog(null, "Erro na conexión co almacén de datos!", "Erro", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Muestra un mensaje de error de lectura de datos.
     */
    @Override
    public void showReadErrorMessage() {
        javax.swing.JOptionPane.showMessageDialog(null, "Erro na lectura de datos!", "Erro", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Muestra un mensaje de error de escritura de datos.
     */
    @Override
    public void showWriteErrorMessage() {
        javax.swing.JOptionPane.showMessageDialog(null, "Erro na escritura dos datos!", "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
