/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tacebook.persistencia;

import tacebook.model.Profile;

public class ProfileDB {

    public static Profile findByName(String name, int numberOfPosts) throws PersistenceException {
        for (Profile p : TacebookDB.profiles) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    public static Profile findByNameAndPassword(String name, String password, int numberOfPosts) throws PersistenceException {
        for (Profile p : TacebookDB.profiles) {
            if (p.getName().equalsIgnoreCase(name) && p.getPassword().equals(password)) {
                return p;
            }
        }
        return null;
    }

    public static void save(Profile profile) throws PersistenceException {
        TacebookDB.profiles.add(profile);
    }

    public static void update(Profile profile) throws PersistenceException {
        // No se necesita acción para esta fase
    }

    public static void saveFrienshipRequest(Profile destProfile, Profile sourceProfile) throws PersistenceException {
        if (!destProfile.getFriendshipRequests().contains(sourceProfile)) {
            destProfile.getFriendshipRequests().add(sourceProfile);
        }
    }

    public static void removeFrienshipRequest(Profile destProfile, Profile sourceProfile) throws PersistenceException {
        destProfile.getFriendshipRequests().remove(sourceProfile);
    }

    public static void saveFriendship(Profile profile1, Profile profile2) throws PersistenceException {
        if (!profile1.getFriends().contains(profile2)) {
            profile1.getFriends().add(profile2);
        }
        if (!profile2.getFriends().contains(profile1)) {
            profile2.getFriends().add(profile1);
        }
    }
}
