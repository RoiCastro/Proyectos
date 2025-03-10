/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aforcado;

/**
 *
 * @author roi.castrocalvar
 */
public class ArrayWordGenerator implements WordGenerator{

    private String[] WORDLIST = {"Casa", "Perro", "Jardín", "Escuela", "Manzana", "Mariposa", "Sol", "Río", "Montaña", "Playa", "Coche", "Guitarra", "Biblioteca", "Castillo", "Luna", "Nieve", "Reloj", "Estrella", "Bosque", "Tren"};

    /**
     * Consigue el arreglo de la visibilidad
     *
     * @return
     */
    public String[] getWORDLIST() {
        return WORDLIST;
    }

    /**
     * Devulve el arreglo de la visibilidad
     *
     */
    public void setWORDLIST(String[] WORDLIST) {
        this.WORDLIST = WORDLIST;
    }
    
    @Override
    public String generateWord() throws GenerateWordException{
        int value = new java.util.Random().nextInt(WORDLIST.length);
        return WORDLIST[value];

    }
}
