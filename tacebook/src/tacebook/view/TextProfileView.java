/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tacebook.view;

import java.text.SimpleDateFormat;
import java.util.Scanner;
import tacebook.controller.ProfileController;
import tacebook.model.Comment;
import tacebook.model.Message;
import tacebook.model.Post;
import tacebook.model.Profile;

/**
 * Vista de perfil en modo texto para la aplicación Tacebook.
 * Permite mostrar y gestionar información del perfil y sus interacciones.
 */
public class TextProfileView implements ProfileView {

    private int postsShowed = 10;
    private final ProfileController profileController;
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy 'ás' HH:mm:ss");

    /**
     * Constructor de la vista de perfil en modo texto.
     * 
     * @param controller el controlador del perfil
     */
    public TextProfileView(ProfileController controller) {
        this.profileController = controller;
    }

    /**
     * Muestra la información del perfil, publicaciones, amigos, solicitudes y mensajes.
     * 
     * @param ownProfile indica si es el perfil propio
     * @param profile el perfil a mostrar
     */
    private void showProfileInfo(boolean ownProfile, Profile profile) {
        System.out.println("\n--- PERFIL DE " + profile.getName() + " ---");
        System.out.println("Estado: " + profile.getStatus());

        System.out.println("\nPublicacións:");
        int postIndex = 0;
        for (Post post : profile.getPosts()) {
            System.out.println("[" + postIndex + "] " + formatter.format(post.getDate()) + ": " + post.getText());
            System.out.println("   Autor: " + post.getAuthor().getName());
            System.out.println("   Likes: " + post.getProfileLikes().size());
            for (Comment comment : post.getComments()) {
                System.out.println("      Comentario de " + comment.getSourceProfile().getName() + " ("
                        + formatter.format(comment.getDate()) + "): " + comment.getText());
            }
            postIndex++;
        }

        System.out.println("\nAmizades:");
        for (Profile friend : profile.getFriends()) {
            System.out.println("- " + friend.getName());
        }

        System.out.println("\nSolicitudes de amizade:");
        for (Profile request : profile.getFriendshipRequests()) {
            System.out.println("- " + request.getName());
        }

        System.out.println("\nMensaxes privadas:");
        int msgIndex = 0;
        for (Message message : profile.getMessages()) {
            String from = message.getSourceProfile().getName();
            String to = message.getDestProfile().getName();
            System.out.println("[" + msgIndex + "] De: " + from + ", Para: " + to + ", "
                    + (message.isRead() ? "Lido" : "Non lido") + ", " + formatter.format(message.getDate()));
            msgIndex++;
        }
    }

    /**
     * Muestra el menú de opciones del perfil y gestiona la interacción por consola.
     * 
     * @param profile el perfil a mostrar
     */
    @Override
    public void showProfileMenu(Profile profile) {
        boolean ownProfile = profile.getName().equals(profileController.getSessionProfile().getName());
        Scanner scanner = new Scanner(System.in);

        while (true) {
            showProfileInfo(ownProfile, profile);
            System.out.println("\n--- Menú de opcións ---");
            System.out.println("1. Cambiar estado");
            System.out.println("2. Nova publicación");
            System.out.println("3. Comentar publicación");
            System.out.println("4. Dar like a publicación");
            System.out.println("5. Ver biografía doutro perfil");
            System.out.println("6. Enviar solicitude de amizade");
            System.out.println("7. Aceptar solicitude de amizade");
            System.out.println("8. Rexectar solicitude de amizade");
            System.out.println("9. Enviar mensaxe privada");
            System.out.println("10. Ler mensaxe privada");
            System.out.println("11. Eliminar mensaxe privada");
            System.out.println("12. Ver publicacións antigas");
            System.out.println("13. Pechar sesión");
            System.out.print("Escolle unha opción: ");

            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    changeStatus(ownProfile, scanner, profile);
                    break;
                case "2":
                    writeNewPost(scanner, profile);
                    break;
                case "3":
                    commentPost(scanner, profile);
                    break;
                case "4":
                    addLike(scanner, profile);
                    break;
                case "5":
                    showBiography(ownProfile, scanner, profile);
                    break;
                case "6":
                    sendFriendshipRequest(ownProfile, scanner, profile);
                    break;
                case "7":
                    proccessFriendshipRequest(ownProfile, scanner, profile, true);
                    break;
                case "8":
                    proccessFriendshipRequest(ownProfile, scanner, profile, false);
                    break;
                case "9":
                    sendPrivateMessage(ownProfile, scanner, profile);
                    break;
                case "10":
                    readPrivateMessage(ownProfile, scanner, profile);
                    break;
                case "11":
                    deletePrivateMessage(ownProfile, scanner, profile);
                    break;
                case "12":
                    showOldPosts(scanner, profile);
                    break;
                case "13":
                    System.out.println("Sesión pechada.");
                    return;
                default:
                    System.out.println("Opción non válida.");
            }
        }
    }

    /**
     * Permite cambiar el estado del perfil si es el propio.
     * 
     * @param ownProfile indica si es el perfil propio
     * @param scanner el objeto Scanner para leer la entrada
     * @param profile el perfil a modificar
     */
    private void changeStatus(boolean ownProfile, Scanner scanner, Profile profile) {
        if (ownProfile) {
            System.out.print("Novo estado: ");
            String newStatus = scanner.nextLine();
            profileController.updateProfileStatus(newStatus);
        } else {
            System.out.println("Só podes cambiar o teu propio estado.");
        }
    }

    /**
     * Permite seleccionar un elemento de una lista por índice.
     * 
     * @param text texto a mostrar
     * @param maxNumber número máximo permitido
     * @param scanner el objeto Scanner para leer la entrada
     * @return el índice seleccionado
     */
    private int selectElement(String text, int maxNumber, Scanner scanner) {
        if (maxNumber <= 0) {
            System.out.println("Non hai elementos dispoñibles.");
            return -1;
        }
        int num = -1;
        do {
            System.out.print(text);
            num = readNumber(scanner);
        } while (num < 0 || num >= maxNumber);
        return num;
    }

    /**
     * Permite escribir una nueva publicación.
     * 
     * @param scanner el objeto Scanner para leer la entrada
     * @param profile el perfil donde publicar
     */
    private void writeNewPost(Scanner scanner, Profile profile) {
        System.out.print("Texto da publicación: ");
        String text = scanner.nextLine();
        profileController.newPost(text, profile);
    }

    /**
     * Permite comentar una publicación.
     * 
     * @param scanner el objeto Scanner para leer la entrada
     * @param profile el perfil donde comentar
     */
    private void commentPost(Scanner scanner, Profile profile) {
        // Recopilar todos los posts visibles (propios y de amigos si es perfil propio)
        java.util.List<Post> allPosts = new java.util.ArrayList<>(profile.getPosts());
        if (profile.equals(profileController.getSessionProfile())) {
            for (Profile friend : profile.getFriends()) {
                allPosts.addAll(friend.getPosts());
            }
        }
        allPosts.sort((a, b) -> b.getDate().compareTo(a.getDate()));
        
        int index = selectElement("Selecciona publicación: ", allPosts.size(), scanner);
        if (index == -1) return;
        
        System.out.print("Texto do comentario: ");
        String text = scanner.nextLine();
        profileController.newComment(allPosts.get(index), text);
    }

    /**
     * Permite dar like a una publicación.
     * 
     * @param scanner el objeto Scanner para leer la entrada
     * @param profile el perfil donde dar like
     */
    private void addLike(Scanner scanner, Profile profile) {
        int index = selectElement("Selecciona publicación para dar like: ", profile.getPosts().size(), scanner);
        if (index == -1) return;
        profileController.newLike(profile.getPosts().get(index));
    }

    /**
     * Permite mostrar la biografía de otro perfil.
     * 
     * @param ownProfile indica si es el perfil propio
     * @param scanner el objeto Scanner para leer la entrada
     * @param profile el perfil actual
     */
    private void showBiography(boolean ownProfile, Scanner scanner, Profile profile) {
        if (ownProfile) {
            int index = selectElement("Selecciona amizade para ver perfil: ", profile.getFriends().size(), scanner);
            if (index == -1) return;
            profileController.setShownProfile(profile.getFriends().get(index));
        } else {
            profileController.setShownProfile(profileController.getSessionProfile());
        }
    }

    /**
     * Permite enviar una solicitud de amistad.
     * 
     * @param ownProfile indica si es el perfil propio
     * @param scanner el objeto Scanner para leer la entrada
     * @param profile el perfil actual
     */
    private void sendFriendshipRequest(boolean ownProfile, Scanner scanner, Profile profile) {
        System.out.print("Nome do perfil destino: ");
        String name = scanner.nextLine();
        profileController.newFriendshipRequest(name);
    }

    /**
     * Permite aceptar o rechazar una solicitud de amistad.
     * 
     * @param ownProfile indica si es el perfil propio
     * @param scanner el objeto Scanner para leer la entrada
     * @param profile el perfil actual
     * @param accept true para aceptar, false para rechazar
     */
    private void proccessFriendshipRequest(boolean ownProfile, Scanner scanner, Profile profile, boolean accept) {
        if (profile.getFriendshipRequests().isEmpty()) {
            System.out.println("Non tes solicitudes de amizade pendentes.");
            return;
        }
        int index = selectElement("Selecciona solicitude: ", profile.getFriendshipRequests().size(), scanner);
        Profile source = profile.getFriendshipRequests().get(index);
        if (accept) {
            profileController.acceptFriendshipRequest(source);
        } else {
            profileController.rejectFriendshipRequest(source);
        }
    }

    /**
     * Permite enviar un mensaje privado.
     * 
     * @param ownProfile indica si es el perfil propio
     * @param scanner el objeto Scanner para leer la entrada
     * @param profile el perfil actual
     */
    private void sendPrivateMessage(boolean ownProfile, Scanner scanner, Profile profile) {
        Profile dest;
        if (ownProfile) {
            int index = selectElement("Selecciona amizade para enviar mensaxe: ", profile.getFriends().size(), scanner);
            if (index == -1) return;
            dest = profile.getFriends().get(index);
        } else {
            dest = profile;
        }
        System.out.print("Texto da mensaxe: ");
        String text = scanner.nextLine();
        profileController.newMessage(dest, text);
    }

    /**
     * Permite leer un mensaje privado.
     * 
     * @param ownProfile indica si es el perfil propio
     * @param scanner el objeto Scanner para leer la entrada
     * @param profile el perfil actual
     */
    private void readPrivateMessage(boolean ownProfile, Scanner scanner, Profile profile) {
        int index = selectElement("Selecciona mensaxe: ", profile.getMessages().size(), scanner);
        if (index == -1) return;
        Message message = profile.getMessages().get(index);
        System.out.println("\n--- Mensaxe completa ---");
        System.out.println("De: " + message.getSourceProfile().getName());
        System.out.println("Para: " + message.getDestProfile().getName());
        System.out.println("Data: " + formatter.format(message.getDate()));
        System.out.println("Texto: " + message.getText());

        System.out.println("\n1. Responder\n2. Eliminar\n3. Marcar como lida e volver");
        String opt = scanner.nextLine();
        switch (opt) {
            case "1":
                System.out.print("Texto da resposta: ");
                String text = scanner.nextLine();
                profileController.replyMessage(message, text);
                break;
            case "2":
                profileController.deleteMessage(message);
                break;
            default:
                profileController.markMessageAsRead(message);
        }
    }

    /**
     * Permite eliminar un mensaje privado.
     * 
     * @param ownProfile indica si es el perfil propio
     * @param scanner el objeto Scanner para leer la entrada
     * @param profile el perfil actual
     */
    private void deletePrivateMessage(boolean ownProfile, Scanner scanner, Profile profile) {
        int index = selectElement("Selecciona mensaxe para borrar: ", profile.getMessages().size(), scanner);
        if (index == -1) return;
        profileController.deleteMessage(profile.getMessages().get(index));
    }

    /**
     * Permite mostrar publicaciones antiguas.
     * 
     * @param scanner el objeto Scanner para leer la entrada
     * @param profile el perfil actual
     */
    private void showOldPosts(Scanner scanner, Profile profile) {
        System.out.print("Número de publicacións a mostrar: ");
        int n = readNumber(scanner);
        if (n > 0) {
            postsShowed += n;
            profileController.reloadProfile();
        } else {
            System.out.println("Debes introducir un número positivo.");
        }
    }

    /**
     * Lee un número desde la entrada estándar.
     * 
     * @param scanner el objeto Scanner para leer la entrada
     * @return el número leído
     */
    private int readNumber(Scanner scanner) {
        try {
            int number = Integer.parseInt(scanner.nextLine());
            return number;
        } catch (Exception e) {
            System.out.println("Debes introducir un número. Intenta de novo.");
            return readNumber(scanner);
        }
    }

    /**
     * Devuelve el número de publicaciones mostradas.
     * 
     * @return número de publicaciones mostradas
     */
    @Override
    public int getPostsShowed() {
        return postsShowed;
    }

    /**
     * Muestra un mensaje cuando el perfil no se encuentra.
     */
    @Override
    public void showProfileNotFoundMessage() {
        System.out.println("Perfil non atopado.");
    }

    /**
     * Muestra un mensaje cuando se intenta dar like a la propia publicación.
     */
    @Override
    public void showCannotLikeOwnPostMessage() {
        System.out.println("Non podes facer like á túa propia publicación.");
    }

    /**
     * Muestra un mensaje cuando ya se ha dado like a una publicación.
     */
    @Override
    public void showAlreadyLikedPostMessage() {
        System.out.println("Xa fixeches like nesta publicación.");
    }

    /**
     * Muestra un mensaje cuando ya existe amistad con el perfil indicado.
     * 
     * @param profileName nombre del perfil
     */
    @Override
    public void showIsAlreadyFriendMessage(String profileName) {
        System.out.println("Xa tes amizade con " + profileName);
    }

    /**
     * Muestra un mensaje cuando ya existe una solicitud de amistad.
     * 
     * @param profileName nombre del perfil
     */
    public void showExistsFrienshipRequestMessage(String profileName) {
        System.out.println("Xa existe unha solicitude de amizade con " + profileName);
    }

    /**
     * Muestra un mensaje cuando ya hay una solicitud de amistad pendiente.
     * 
     * @param profileName nombre del perfil
     */
    public void showDuplicateFrienshipRequestMessage(String profileName) {
        System.out.println("Xa tes unha solicitude pendente con " + profileName);
    }

    /**
     * Muestra un mensaje cuando se intenta enviarse una solicitud de amistad a sí mismo.
     */
    public void showCannotAddSelfAsFriendMessage() {
        System.out.println("Non podes enviarte unha solicitude de amizade a ti mesmo.");
    }

    /**
     * Muestra un mensaje de error de conexión.
     */
    public void showConnectionErrorMessage() {
        System.out.println("Erro na conexión co almacén de datos!");
    }

    /**
     * Muestra un mensaje de error de lectura de datos.
     */
    public void showReadErrorMessage() {
        System.out.println("Erro na lectura de datos!");
    }

    /**
     * Muestra un mensaje de error de escritura de datos.
     */
    public void showWriteErrorMessage() {
        System.out.println("Erro na escritura dos datos!");
    }

}
