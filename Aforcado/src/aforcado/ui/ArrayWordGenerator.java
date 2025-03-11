/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aforcado.ui;

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
public String generateWord() throws GenerateWordException {
    // Comprobamos se WORDLIST está baleiro
    if (WORDLIST == null || WORDLIST.length == 0) {
        throw new GenerateWordException("A lista de palabras está baleira ou non accesible.", true);
    }

    // Se non está baleiro, escoller unha palabra aleatoria
    int value = new java.util.Random().nextInt(WORDLIST.length);
    return WORDLIST[value];
}

}
