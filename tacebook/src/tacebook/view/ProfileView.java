/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tacebook.view;

import tacebook.model.Profile;

/**
 * Interfaz para la vista de perfil en la aplicación Tacebook.
 * Define los métodos que deben implementar las vistas de perfil.
 *
 * @author Roi
 */
public interface ProfileView {

    /**
     * Devuelve el número de publicaciones mostradas.
     * @return número de publicaciones mostradas
     */
    public int getPostsShowed();

    /**
     * Muestra el menú de perfil para el usuario.
     * @param profile el perfil a mostrar
     */
    public void showProfileMenu(Profile profile);

    /**
     * Muestra un mensaje cuando el perfil no se encuentra.
     */
    public void showProfileNotFoundMessage();

    /**
     * Muestra un mensaje cuando se intenta dar like a la propia publicación.
     */
    public void showCannotLikeOwnPostMessage();

    /**
     * Muestra un mensaje cuando ya se ha dado like a una publicación.
     */
    public void showAlreadyLikedPostMessage();

    /**
     * Muestra un mensaje cuando ya existe amistad con el perfil indicado.
     * @param profileName nombre del perfil
     */
    public void showIsAlreadyFriendMessage(String profileName);

    /**
     * Muestra un mensaje cuando ya existe una solicitud de amistad.
     * @param profileName nombre del perfil
     */
    public void showExistsFrienshipRequestMessage(String profileName);

    /**
     * Muestra un mensaje cuando ya hay una solicitud de amistad pendiente.
     * @param profileName nombre del perfil
     */
    public void showDuplicateFrienshipRequestMessage(String profileName);

    /**
     * Muestra un mensaje cuando se intenta enviarse una solicitud de amistad a sí mismo.
     */
    public void showCannotAddSelfAsFriendMessage();

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
}
