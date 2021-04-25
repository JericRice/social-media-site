package example.dao;

import example.models.Post;

import java.util.List;

public interface PostDao {

    /**
     * Create a new post realted to one user that might
     * contain a message and/or media source, being a
     * picture
     * @param post obj
     */
    void createPost(Post post);

    /**
     * Return all of the post available in the db.
     *
     * @return list of Posts
     */
    List<Post> findAllPosts();

    /**
     * Select a post by their unique id.
     *
     * @param postID int
     * @return Posts
     */
    Post findPost(int postID);

    /**
     * Return all of the post related to a user id
     *
     * @param userID int
     * @return list of Posts
     */
    List<Post> findUserPosts(int userID);

    /**
     * Delete a post
     *
     * @param post obj
     */
    void deletePost(Post post);
}
