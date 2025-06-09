/*
 * Fai clic en nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt para cambiar esta licenza
 * Fai clic en nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java para editar este modelo
 */
package tacebook.model;

import java.util.Date;

/**
 * Clase que representa un mensaje enviado entre perfiles.
 */
public class Message {
    /**
     * Identificador único del mensaje.
     */
    private int id;

    /**
     * Texto del mensaje.
     */
    private String text;

    /**
     * Fecha en la que se envió el mensaje.
     */
    private Date date;

    /**
     * Indica si el mensaje ha sido leído.
     */
    private boolean read;

    /**
     * Perfil que envía el mensaje.
     */
    private Profile sourceProfile;

    /**
     * Perfil que recibe el mensaje.
     */
    private Profile destProfile;

    /**
     * Constructor de la clase Message.
     * 
     * @param id   Identificador del mensaje.
     * @param text Texto del mensaje.
     * @param date Fecha del mensaje.
     * @param read Estado de lectura del mensaje.
     */
    public Message(int id, String text, Date date, boolean read) {
        this.id = id;
        this.text = text;
        this.date = date;
        this.read = read;
    }

    /**
     * Obtiene el identificador del mensaje.
     * @return id del mensaje.
     */
    public int getId() {
        return id;
    }

    /**
     * Obtiene el texto del mensaje.
     * @return texto del mensaje.
     */
    public String getText() {
        return text;
    }

    /**
     * Obtiene la fecha del mensaje.
     * @return fecha del mensaje.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Indica si el mensaje ha sido leído.
     * @return true si el mensaje ha sido leído, false en caso contrario.
     */
    public boolean isRead() {
        return read;
    }

    /**
     * Obtiene el perfil que envió el mensaje.
     * @return perfil emisor.
     */
    public Profile getSourceProfile() {
        return sourceProfile;
    }

    /**
     * Establece el perfil que envió el mensaje.
     * @param sourceProfile perfil emisor.
     */
    public void setSourceProfile(Profile sourceProfile) {
        this.sourceProfile = sourceProfile;
    }

    /**
     * Obtiene el perfil que recibe el mensaje.
     * @return perfil receptor.
     */
    public Profile getDestProfile() {
        return destProfile;
    }

    /**
     * Establece el perfil que recibe el mensaje.
     * @param destProfile perfil receptor.
     */
    public void setDestProfile(Profile destProfile) {
        this.destProfile = destProfile;
    }

    /**
     * Establece el identificador del mensaje.
     * @param id identificador.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Establece el texto del mensaje.
     * @param text texto.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Establece la fecha del mensaje.
     * @param date fecha.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Establece el estado de lectura del mensaje.
     * @param read true si el mensaje ha sido leído, false en caso contrario.
     */
    public void setRead(boolean read) {
        this.read = read;
    }
}
