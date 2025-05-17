/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tacebook.view;

import tacebook.model.Profile;

/**
 *
 * @author Roi
 */
public interface ProfileView {

    public int getPostsShowed();

    public void showProfileMenu(Profile profile);

    public void showProfileNotFoundMessage();

    public void showCannotLikeOwnPostMessage();

    public void showAlreadyLikedPostMessage();

    public void showIsAlreadyFriendMessage(String profileName);

    public void showExistsFrienshipRequestMessage(String profileName);

    public void showDuplicateFrienshipRequestMessage(String profileName);

    public void showConnectionErrorMessage();

    public void showReadErrorMessage();

    public void showWriteErrorMessage();
}
