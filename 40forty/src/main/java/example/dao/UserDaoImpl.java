package example.dao;

import example.models.UserAccount;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository("usersDao")
@Transactional
public class UserDaoImpl implements UserDao{

    private SessionFactory sessionFactory;

    /**
     * adds a new User to the DB
     * @param user The user Object to be added to the DB
     */
    @Override
    public void createUser(UserAccount user) {
       // System.out.println(sessionFactory);
        sessionFactory.getCurrentSession().save(user);
    }

    /**
     * returns a list of all users in the DB
     * @return the full list of Users in the DB
     */
    @Override
    public List<UserAccount> findAllUsers() {
        return sessionFactory.getCurrentSession().createQuery("from UserAccount", UserAccount.class).list();
    }

    /**
     * Finds a specific User with user_id matching the id parameter
     * @param id The id to compare to entries in the DB
     * @return The User that matches the id given
     */
    @Override
    public UserAccount findUserById(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM UserAccount WHERE user_id = :id");
        query.setParameter("id", id);
        return (UserAccount)query.getSingleResult();
    }

    /**
     * Finds a specific user from the table that matches the full user object given
     * @param user Users object that represents the User to find in the DB
     * @return the User object representing the entry that matched the parameter
     */
    @Override
    public UserAccount findUser(UserAccount user) {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM UserAccount WHERE user_id = :id");
        query.setParameter("id", user.getUserID());
        return (UserAccount)query.getSingleResult();
    }

    /**
     * !!! This throws a NoResultException, if the user does not exist
     * @param username the username string to check
     * @return possibly a user, possibly a UNCHECKED EXCEPTION
     */
    @Override
    public UserAccount findUserByUsername(String username) throws NoResultException {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM UserAccount WHERE username = :username");
        query.setParameter("username", username);
        return (UserAccount)query.getSingleResult();
    }

    @Override
    public UserAccount findUserByEmail(String email) throws NoResultException {
        System.out.println(email);
        Query query = sessionFactory.getCurrentSession().createQuery("FROM UserAccount WHERE email = :email");
        query.setParameter("email", email);
        //System.out.println(query);
        return (UserAccount)query.getSingleResult();
    }


    /**
     * deletes the user in the Db that is represented by the parameter
     * @param user Users object containing the information of the User that is to be deleted
     */
    @Override
    public void deleteUser(UserAccount user) {
        sessionFactory.getCurrentSession().delete(user);
    }

    /**
     * updates the user record in the table to be the same as the information in the parameter
     * id has to be the same as the user to update!
     *
     * @param updatedUser Users object that contains the information for the update to the DB
     */
    @Override
    public void updateUser(UserAccount updatedUser) {
        sessionFactory.getCurrentSession().update(updatedUser);
    }


    // CONSTRUCTORS
    public UserDaoImpl() {
    }

    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }



    // GETTERS & SETTERS
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
