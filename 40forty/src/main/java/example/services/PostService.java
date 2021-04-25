package example.services;

import example.models.Post;

import java.util.List;

/**
 * Service layer for Posts- uses the PostDao to update the DB
 *
 * - Add a post with text and optionally media, (should also add it to the User object...or maybe that happens automagically?)
 * - Get all posts
 * - Get all user posts
 * - Update the body of a post (and updates the post in the User's post list... or maybe automagic??)
 * - Add media to a post (and updates the post in the User's post list... or maybe automagic??)
 * - Delete a post (and removes the post from the User's post list... or maybe automagic??) (also deletes all Reactions from the reactions table)
 */
public interface PostService {

    /**
     * Add a new user with Dao layer
     *
     * @param newPost obj
     */
    void addPost(Post newPost);

    /**
     * Get all available post from all users;
     *
     * @return list of posts
     */
    List<Post> getAllPosts();

    /**
     * Get all the posts from a specific user
     *
     * @param userID fk
     * @return list of posts
     */
    List<Post> getAllUserPosts(int userID);

    /**
     * Update a post: text or media only
     *
     * @param updatePost Posts
     */
    void updatePosts(Post updatePost);

    /**
     * Delete a post
     *
     * @param post Posts
     */
    void deletePosts(Post post);
}
