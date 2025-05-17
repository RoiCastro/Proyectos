/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tacebook.persistencia;

import tacebook.model.Post;
import tacebook.model.Comment;

public class CommentDB {

    public static void save(Comment comment) throws PersistenceException {
        Post post = comment.getPost();
        post.getComments().add(0, comment); // Engadir ao comezo da lista
    }
}
