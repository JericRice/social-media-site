package example.services;

import example.models.Post;
import example.models.Reaction;
import example.models.UserAccount;

import java.util.List;

/**
 * Service layer for Reactions- uses the PostDao to update the DB
 *
 * - Get all Reactions
 * - Get all Reactions for a Post
 * - Get all Reactions that a User has made
 * - Get a Reaction from the Reaction ID
 * - Add a positive Reaction to a Post
 * - Add a negative Reaction to a Post
 * - Delete a Reaction from a Post
 */
public interface ReactionService {

    /**
     * Adds a Reaction to the DB
     *
     * @param reaction the reaction
     */
    void addReaction(Reaction reaction);

    /**
     * Creates a new positive (1) Reaction from a User to a Post
     * @param user the user
     * @param post the post
     */
    void addPositiveReaction(UserAccount user, Post post);

    /**
     * Creates a new negative (0) Reaction from a User to a Post
     * @param user the user
     * @param post the post
     */
    void addNegativeReaction(UserAccount user, Post post);

    /**
     * Get all the Reactions in the DB
     * @return List of all Reactions
     */
    List<Reaction> getAllReactions();

    /**
     * Get all Reactions for a Post in the DB
     * @param post the post
     * @return List of all Reactions for given Post
     */
    List<Reaction> getAllPostReactions(Post post);

    /**
     * Get all Reactions that a User has made
     * @param user the user
     * @return List of all User Reactions
     */
    List<Reaction> getAllUserReactions(UserAccount user);

    /**
     * Get a Reaction with the ID
     * @param reactionID the ID of the Reaction
     * @return the Reaction object
     */
    Reaction getReaction(int reactionID);

    /**
     * Get a Reaction on a post, if it exists
     *
     * @param user the user
     * @param post the post
     * @return the reaction
     */
    Reaction getUserReactionOnPost(UserAccount user, Post post);

    /**
     * Remove a Reaction from the DB
     *
     * @param reaction the reaction
     */
    void deleteReaction(Reaction reaction);

    /**
     * Remove a Reaction from the DB
     *
     * @param reactionID the reaction id
     */
    void deleteReaction(int reactionID);

    /**
     * Change a reaction to the opposite
     *
     * @param reaction the reaction
     */
    Reaction toggleReaction(Reaction reaction);

    /**
     * Updates a reaction if it's id exists
     *
     * @param reaction the reaction with new value
     * @return the same reaction if good, null if bad
     */
    Reaction updateReaction(Reaction reaction);
}
