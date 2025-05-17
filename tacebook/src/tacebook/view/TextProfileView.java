/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tacebook.view;

import tacebook.model.Post;
import tacebook.model.Profile;
import tacebook.model.Message;
import tacebook.model.Comment;
import tacebook.controller.ProfileController;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class TextProfileView implements ProfileView{

    private int postsShowed = 10;
    private final ProfileController profileController;
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy 'ás' HH:mm:ss");

    public TextProfileView(ProfileController controller) {
        this.profileController = controller;
    }

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
                System.out.println("      Comentario de " + comment.getSourceProfile().getName() + " (" + formatter.format(comment.getDate()) + "): " + comment.getText());
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
            System.out.println("[" + msgIndex + "] De: " + from + ", Para: " + to + ", " + (message.isRead() ? "Lido" : "Non lido") + ", " + formatter.format(message.getDate()));
            msgIndex++;
        }
    }

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

    private void changeStatus(boolean ownProfile, Scanner scanner, Profile profile) {
        if (ownProfile) {
            System.out.print("Novo estado: ");
            String newStatus = scanner.nextLine();
            profileController.updateProfileStatus(newStatus);
        } else {
            System.out.println("Só podes cambiar o teu propio estado.");
        }
    }

    private int selectElement(String text, int maxNumber, Scanner scanner) {
        int num = -1;
        do {
            System.out.print(text);
            num = readNumber(scanner);
        } while (num < 0 || num >= maxNumber);
        return num;
    }

    private void writeNewPost(Scanner scanner, Profile profile) {
        System.out.print("Texto da publicación: ");
        String text = scanner.nextLine();
        profileController.newPost(text, profile);
    }

    private void commentPost(Scanner scanner, Profile profile) {
        int index = selectElement("Selecciona publicación: ", profile.getPosts().size(), scanner);
        System.out.print("Texto do comentario: ");
        String text = scanner.nextLine();
        profileController.newComment(profile.getPosts().get(index), text);
    }

    private void addLike(Scanner scanner, Profile profile) {
        int index = selectElement("Selecciona publicación para dar like: ", profile.getPosts().size(), scanner);
        profileController.newLike(profile.getPosts().get(index));
    }

    private void showBiography(boolean ownProfile, Scanner scanner, Profile profile) {
        if (ownProfile) {
            int index = selectElement("Selecciona amizade para ver perfil: ", profile.getFriends().size(), scanner);
            profileController.setShownProfile(profile.getFriends().get(index));
        } else {
            profileController.setShownProfile(profileController.getSessionProfile());
        }
    }

    private void sendFriendshipRequest(boolean ownProfile, Scanner scanner, Profile profile) {
        System.out.print("Nome do perfil destino: ");
        String name = scanner.nextLine();
        profileController.newFriendshipRequest(name);
    }

    private void proccessFriendshipRequest(boolean ownProfile, Scanner scanner, Profile profile, boolean accept) {
        int index = selectElement("Selecciona solicitude: ", profile.getFriendshipRequests().size(), scanner);
        Profile source = profile.getFriendshipRequests().get(index);
        if (accept) {
            profileController.acceptFriendshipRequest(source);
        } else {
            profileController.rejectFriendshipRequest(source);
        }
    }

    private void sendPrivateMessage(boolean ownProfile, Scanner scanner, Profile profile) {
        Profile dest;
        if (ownProfile) {
            int index = selectElement("Selecciona amizade para enviar mensaxe: ", profile.getFriends().size(), scanner);
            dest = profile.getFriends().get(index);
        } else {
            dest = profile;
        }
        System.out.print("Texto da mensaxe: ");
        String text = scanner.nextLine();
        profileController.newMessage(dest, text);
    }

    private void readPrivateMessage(boolean ownProfile, Scanner scanner, Profile profile) {
        int index = selectElement("Selecciona mensaxe: ", profile.getMessages().size(), scanner);
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

    private void deletePrivateMessage(boolean ownProfile, Scanner scanner, Profile profile) {
        int index = selectElement("Selecciona mensaxe para borrar: ", profile.getMessages().size(), scanner);
        profileController.deleteMessage(profile.getMessages().get(index));
    }

    private void showOldPosts(Scanner scanner, Profile profile) {
        System.out.print("Número de publicacións a mostrar: ");
        int n = readNumber(scanner);
        postsShowed += n;
        profileController.reloadProfile();
    }

    private int readNumber(Scanner scanner) {
        try {
            int number = scanner.nextInt();
            scanner.nextLine(); // limpar salto de liña
            return number;
        } catch (Exception e) {
            scanner.nextLine(); // limpar entrada incorrecta
            System.out.println("Debes introducir un número. Intenta de novo.");
            return readNumber(scanner);
        }
    }

    public int getPostsShowed() {
        return postsShowed;
    }

    public void showProfileNotFoundMessage() {
        System.out.println("Perfil non atopado.");
    }

    public void showCannotLikeOwnPostMessage() {
        System.out.println("Non podes facer like á túa propia publicación.");
    }

    public void showAlreadyLikedPostMessage() {
        System.out.println("Xa fixeches like nesta publicación.");
    }

    public void showIsAlreadyFriendMessage(String profileName) {
        System.out.println("Xa tes amizade con " + profileName);
    }

    public void showExistsFrienshipRequestMessage(String profileName) {
        System.out.println("Xa existe unha solicitude de amizade con " + profileName);
    }

    public void showDuplicateFrienshipRequestMessage(String profileName) {
        System.out.println("Xa tes unha solicitude pendente con " + profileName);
    }

    public void showConnectionErrorMessage() {
        System.out.println("Erro na conexión co almacén de datos!");
    }

    public void showReadErrorMessage() {
        System.out.println("Erro na lectura de datos!");
    }

    public void showWriteErrorMessage() {
        System.out.println("Erro na escritura dos datos!");
    }

}
