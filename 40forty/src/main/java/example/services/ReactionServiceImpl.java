package example.services;

import example.dao.ReactionDao;
import example.dao.ReactionDaoImpl;
import example.models.Post;
import example.models.Reaction;
import example.models.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * Reaction Service, reactionDao = setter autowired
 */
@Service
public class ReactionServiceImpl implements ReactionService {

    private ReactionDao reactionDao;

    public ReactionServiceImpl() {

    }

    public ReactionServiceImpl(ReactionDaoImpl reactionDao) {
        this.reactionDao = reactionDao;
    }

    /**
     * Adds a Reaction to the DB, needs to be NEWLY CREATED IN the SERVER CONTROLLER first so IDs stay consistent
     *
     * @param reaction the reaction, not directly from the angular
     */
    @Override
    public void addReaction(Reaction reaction) {
        reactionDao.createReaction(reaction);
    }

    /**
     * Creates a new positive (1) Reaction from a User to a Post
     *
     * @param user the user
     * @param post the post
     */
    @Override
    public void addPositiveReaction(UserAccount user, Post post) {
        Reaction positive = new Reaction(1, user, post);
        reactionDao.createReaction(positive);
    }

    /**
     * Creates a new negative (-1) Reaction from a User to a Post
     *
     * @param user the user
     * @param post the post
     */
    @Override
    public void addNegativeReaction(UserAccount user, Post post) {
        Reaction negative = new Reaction(-1, user, post);
        reactionDao.createReaction(negative);
    }

    /**
     * Get all the Reactions in the DB
     *
     * @return List of all Reactions
     */
    @Override
    public List<Reaction> getAllReactions() {
        return reactionDao.findAllReactions();
    }

    /**
     * Get all Reactions for a Post in the DB
     *
     * @param post the post
     * @return List of all Reactions for given Post
     */
    @Override
    public List<Reaction> getAllPostReactions(Post post) {
        return reactionDao.findPostReactions(post.getPostID());
    }

    /**
     * Get all Reactions that a User has made
     *
     * @param user the user
     * @return List of all User Reactions
     */
    @Override
    public List<Reaction> getAllUserReactions(UserAccount user) {
        return reactionDao.findUserReactions(user.getUserID());
    }

    /**
     * Get a Reaction with the ID
     *
     * @param reactionID the ID of the Reaction
     * @return the Reaction object
     */
    @Override
    public Reaction getReaction(int reactionID) {
        try{
            return reactionDao.findReaction(reactionID);
        }
        catch(NoResultException e)
        {
            return null;
        }
    }

    /**
     * Get a Reaction on a post, if it exists
     *
     * @param user the user
     * @param post the post
     * @return the reaction
     */
    @Override
    public Reaction getUserReactionOnPost(UserAccount user, Post post) {
        try{
            return reactionDao.findReaction(user, post);
        }
        catch(NoResultException e)
        {
            return null;
        }
    }

    /**
     * Remove a Reaction from the DB
     *
     * @param reaction the reaction
     */
    @Override
    public void deleteReaction(Reaction reaction) {
        reactionDao.deleteReaction(reaction);
    }

    /**
     * Remove a Reaction from the DB
     *
     * Right now it gets it in the DB, then it deletes it
     * Alternative would be to get it on the server, then ping here to delete it
     * ...might be faster if it does it on the same side, implemented here
     *
     * @param reactionID the reaction (object contains user/post information)
     */
    @Override
    public void deleteReaction(int reactionID) {
        Reaction reaction = reactionDao.findReaction(reactionID);
        this.deleteReaction(reaction);
    }

    /**
     * Change a reaction to the opposite
     * Then calls updateReaction to change it in the DB
     *
     * @param reaction the reaction
     */
    @Override
    public Reaction toggleReaction(Reaction reaction)
    {
        int currentValue = reaction.getValue();
        // if the reaction is 1, set it to -1. if it's not 1, set it to 1
        reaction.setValue(currentValue == 1 ? -1 : 1);
        return updateReaction(reaction);
    }

    /**
     * Updates a reaction if it's id exists
     * returns null if it does not
     *
     * @param reaction the reaction with new value, null if empty
     */
    @Override
    public Reaction updateReaction(Reaction reaction)
    {
        try {
            reactionDao.updateReaction(reaction);
            return reaction;
        }
        catch (Exception e) {
            return null;
        }
    }


    public ReactionDao getReactionDao() {
        return reactionDao;
    }

    @Autowired
    public void setReactionDao(ReactionDaoImpl reactionDao) {
        this.reactionDao = reactionDao;
    }
}
