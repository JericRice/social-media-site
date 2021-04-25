package example.dao;

import example.models.Post;
import example.models.Reaction;
import example.models.UserAccount;

import javax.persistence.NoResultException;
import java.util.List;

public interface ReactionDao {

    /**
     * Add a Reaction to the DB
     * This JUST ADDS it, it has to be re-created in the server before to get a valid id
     *
     * @param reaction the Reaction
     */
    void createReaction(Reaction reaction);

    /**
     * Get a List of all Reactions
     *
     * @return list of global Reactions
     */
    List<Reaction> findAllReactions();

    /**
     * Get a reaction with the id
     *
     * @param reactionID the id of the reaction
     * @return the Reaction
     * @throws NoResultException if there's no reaction found for the id
     */
    Reaction findReaction(int reactionID) throws NoResultException;

    /**
     * Get a user's reaction on a post
     *
     * @param user the user
     * @param post the post
     * @return the Reaction
     * @throws NoResultException if there's no reaction found for the id
     */
    Reaction findReaction(UserAccount user, Post post) throws NoResultException;

    /**
     * Get a List of all Reactions for a specified post
     *
     * @param postID id of post
     * @return List of Reactions for post
     */
    List<Reaction> findPostReactions(int postID);

    /**
     * Get a List of all Reactions made by a specified UserAccount
     *
     * @param userID id of user
     * @return List of Reactions by User
     */
    List<Reaction> findUserReactions(int userID);

    /**
     * Removes a reaction from the DB
     *
     * @param reaction the reaction
     */
    void deleteReaction(Reaction reaction);

    /**
     * Updates the reaction in the db, if it's id already exists
     * Throws a Exception if it does not
     *
     * @param reaction the reaction
     */
    void updateReaction(Reaction reaction);
}
