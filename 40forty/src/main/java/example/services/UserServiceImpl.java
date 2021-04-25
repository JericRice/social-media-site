package example.services;

import example.dao.UserDaoImpl;
import example.models.Post;
import example.models.Reaction;
import example.models.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDaoImpl userDao;

    /**
     * Add a new User, through the dao
     *
     * @param newUser the user to add
     */
    @Override
    public void addUser(UserAccount newUser) {
        userDao.createUser(newUser);
    }

    /**
     * Retrieve all Users
     *
     * @return List of all Users in the DB
     */
    public List<UserAccount> getAllUsers()
    {
        try{
            return userDao.findAllUsers();
        }catch(NoResultException e){
            e.printStackTrace();
            return new ArrayList<>();
        }

    }

    /**
     * Retrieve a user from their unique userID
     *
     * @param userID the userID
     * @return the unique User
     */
    public UserAccount getUserByID(int userID)
    {
        try{
            return userDao.findUserById(userID);
        }catch(NoResultException e){
            e.printStackTrace();
            return new UserAccount();
        }

    }

    /**
     * Retrieve a user from their unique username
     *
     * @param username the username
     * @return the unique user
     */
    @Override
    public UserAccount getUserByUsername(String username) {
        try {
            return userDao.findUserByUsername(username);
        }catch(NoResultException e){
            return new UserAccount();
        }

    }

    /**
     * Retrieve a user from their unique email
     *
     * @param userEmail the email
     * @return the unique user
     */
    @Override
    public UserAccount getUserByEmail(String userEmail) {
        try {
            return userDao.findUserByEmail(userEmail);
        }catch(NoResultException e){
            e.printStackTrace();
            return new UserAccount();
        }

    }

    /**
     * update a user by changing all values to what is inside the new user
     * @param updatedUser users object that contains all updated info
     */
    @Override
    public void updateUser(UserAccount updatedUser) {
        userDao.updateUser(updatedUser);
    }

    /**
     * Delete a user
     *
     * Must FIRST delete their posts AND their reactions from corresponding tables
     * (or the CascadeType values in the model files might do it automatically or something...?)
     *
     * @param userID the user's userID
     */
    @Override
    public void deleteUser(int userID) {

    }

    /**
     * Checks if a username exists in the db
     *
     * @param username the username to check
     * @return true if yes, false if no
     */
    @Override
    public boolean usernameExists(String username)
    {
        try {
            return (userDao.findUserByUsername(username) != null);
        }
        catch(NoResultException e)
        {
            return false;
        }
    }


    // CONSTRUCTORS
    public UserServiceImpl() {
    }

    public UserServiceImpl(UserDaoImpl userDao) {
        this.userDao = userDao;
    }



    // GETTER & SETTER
    public UserDaoImpl getUserDao() {
        return userDao;
    }

    @Autowired
    public void setUserDao(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

//    public void insertInitialValues(){
//
//        UserAccount firstUser = new UserAccount("test@gmail.com", "username", "password", "John"
//                , "Smith", null);
//
//        // Make a new Post for the firstUser
//        Post firstPost = new Post("hello world", new Timestamp(System.currentTimeMillis()), "", firstUser);
//
//        // Make a new Reaction for the firstReaction from the firstUser
//        Reaction firstReaction = new Reaction(1, firstUser, firstPost);
//
//        // Make Array Lists for reactions and posts
//        ArrayList<Post> firstUserPosts = new ArrayList<>();
//        ArrayList<Reaction> firstPostReactions = new ArrayList<>();
//
//        // Add the reaction to the reaction list, set the reaction list to the post
//        firstPostReactions.add(firstReaction);
//        firstPost.setReactions(firstPostReactions);
//
//        // Add the post to the post list, set the post list to the user
//        firstUserPosts.add(firstPost);
//        firstUser.setPosts(firstUserPosts);
//
//        // Set the reaction list to the user
//        firstUser.setReactions(firstPostReactions);
//
//        // Create user, then post, then reaction
//        userDao.createUser(firstUser);
////        postDao.createPost(firstPost);
////        reactionDao.createReaction(firstReaction);
//    }
}
