/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package aforcado.ui;

/**
 *
 * @author roi.castrocalvar
 */
public class GenerateWordException extends Exception {

    private boolean visible;

    /**
     * Consigue el valor de la visibilidad
     *
     * @return
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Devulve el valor de la visibilidad
     *
     * @param visible
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * Constructs an instance of <code>GenerateWordException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     * @param visible si el mensaje sera visible para el usuario.
     */
    public GenerateWordException(String msg, boolean visible) {
        super(msg);
        this.visible = visible;
    }

}
