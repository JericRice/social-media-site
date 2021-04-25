package example.dao;

import example.models.Post;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository(value = "postDao")
@Transactional
public class PostDaoImpl implements PostDao {

    private SessionFactory sessionFactory;

    /**
     * Create a new post realted to one user that might
     * contain a message and/or media source, being a
     * picture
     * @param post obj
     */
    @Override
    public void createPost(Post post) {
        sessionFactory.getCurrentSession().save(post);
    }

    /**
     * Return all of the post available in the db.
     *
     * @return list of Posts
     */
    @Override
    public List<Post> findAllPosts() {
        return sessionFactory.getCurrentSession().createQuery("from Post", Post.class).list();
    }

    /**
     * Select a post by their unique id.
     *
     * @param postID int
     * @return Posts
     */
    @Override
    public Post findPost(int postID) {

        return sessionFactory.getCurrentSession().get(Post.class, postID);
    }

    /**
     * Return all of the post related to a user id
     *
     * @param userID int fk
     * @return list of Posts
     */
    @Override
    public List<Post> findUserPosts(int userID) {

        //TODO: there might be a better way for this
        String hql = "FROM Post WHERE author_fk = :userID";

        return sessionFactory.getCurrentSession().createQuery(hql).setParameter("userID", userID).list();
    }

    /**
     * Delete a post
     *
     * @param post obj
     */
    @Override
    public void deletePost(Post post) {

        sessionFactory.getCurrentSession().delete(post);
    }


    // CONSTRUCTORS
    public PostDaoImpl() {
    }

    @Autowired
    public PostDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }



    // GETTERS & SETTERS
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    //@Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
