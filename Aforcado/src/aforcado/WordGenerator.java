/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aforcado;

/**
 *
 * @author roi.castrocalvar
 */
public interface WordGenerator {

    /**
     * Metodo que devuelve una palabra
     *
     * @return
     */
    public String generateWord() throws GenerateWordException;
}
