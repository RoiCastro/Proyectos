/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aforcado.generator;

import aforcado.ui.GenerateWordException;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

/**
 *
 * @author Roi
 */
public class GUIKeyboardWordGenerator implements WordGenerator {

    @Override
    public String generateWord() throws GenerateWordException {
        // Crear o JPasswordField para a entrada da palabra
        JPasswordField passwordField = new JPasswordField(10);

        // Mostrar o JOptionPane directamente cun JPasswordField
        int option = JOptionPane.showConfirmDialog(null, passwordField,
                "Introduza a palabra",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        // Se o usuario cancela ou pecha o cadro de diálogo, lanzar excepción
        if (option == JOptionPane.CANCEL_OPTION || option == JOptionPane.CLOSED_OPTION) {
            throw new GenerateWordException("Operación cancelada ou pechada polo usuario.", true);
        }

        // Obter a palabra introducida
        char[] password = passwordField.getPassword();
        String word = new String(password);  // Convértese de char[] a String

        // Validar a palabra
        if (!isValid(word)) {
            throw new GenerateWordException("A palabra debe conter só letras minúsculas de 'a' a 'z'.", true);
        }

        return word;
    }

    /**
     *
     * @param input
     * @return
     */
    private boolean isValid(String input) {
        // Comprobar se o texto non está vacío e ten exactamente 1 carácter
        if (input.isEmpty()) {
            return false;  // Se está vacío ou ten máis dunha letra, é inválido
        }

        // Comprobar se a letra é alfabética (de 'a' a 'z')
        char letter = input.toLowerCase().charAt(0);  // Convertimos a minúsculas por se a entrada está en maiúsculas
        return (letter >= 'a' && letter <= 'z');  // A letra debe estar no rango de 'a' a 'z'
    }

}
