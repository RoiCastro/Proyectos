/*
 * Fai clic en nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt para cambiar esta licenza
 * Fai clic en nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java para editar este modelo
 */
package tacebook;

/**
 *
 * @author roi.castrocalvar
 */
public class Profile {

    private String name; // Nome do usuario
    private String password; // Contrasinal do usuario
    private String status; // Estado actual do usuario

    /**
     * Obtén o nome do usuario.
     *
     * @return O nome do usuario.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece o nome do usuario.
     *
     * @param name O novo nome do usuario.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtén o contrasinal do usuario.
     *
     * @return O contrasinal do usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece o contrasinal do usuario.
     *
     * @param password O novo contrasinal do usuario.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Obtén o estado actual do usuario.
     *
     * @return O estado do usuario.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Establece o estado actual do usuario.
     *
     * @param status O novo estado do usuario.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Constructor da clase Profile.
     *
     * @param name O nome do usuario.
     * @param password O contrasinal do usuario.
     * @param status O estado inicial do usuario.
     */
    public Profile(String name, String password, String status) {
        this.name = name;
        this.password = password;
        this.status = status;
    }
}