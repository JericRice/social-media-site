package example.services;

import example.models.UserAccount;

import java.util.List;

/**
 * Service layer for Users- uses the UserDao to update the DB
 *
 */
public interface UserService {

    /**
     * Add a new User, through the dao
     *
     * @param newUser the user to add
     */
    void addUser(UserAccount newUser);

    /**
     * Retrieve all Users
     *
     * @return List of all Users in the DB
     */
    List<UserAccount> getAllUsers();

    /**
     * Retrieve a user from their unique userID
     *
     * @param userID the userID
     * @return the unique User
     */
    UserAccount getUserByID(int userID);

    /**
     * Retrieve a user from their unique username
     *
     * @param username the username
     * @return the unique user
     */
    UserAccount getUserByUsername(String username);

    /**
     * Retrieve a user from their unique email
     *
     * @param userEmail the email
     * @return the unique user
     */
    UserAccount getUserByEmail(String userEmail);

    /**
     * update a user by changing all values to what is inside the new user
     * @param updatedUser users object that contains all updated info
     */
    void updateUser(UserAccount updatedUser);

    /**
     * Delete a user
     *
     * Must FIRST delete their posts AND their reactions from corresponding tables
     *
     * @param userID the user's userID
     */
    void deleteUser(int userID);

    /**
     * Checks if a username exists in the db
     *
     * @param username the username to check
     * @return true if yes, false if no
     */
    boolean usernameExists(String username);

//    void insertInitialValues();
}
