package example.dao;

import example.models.Post;
import example.models.Reaction;
import example.models.UserAccount;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;

@Repository("reactionDao")
@Transactional
public class ReactionDaoImpl implements ReactionDao {

    private SessionFactory sessionFactory;

    @Override
    public void createReaction(Reaction reaction) {
        sessionFactory.getCurrentSession().save(reaction);
    }

    @Override
    public List<Reaction> findAllReactions() {
        return sessionFactory.getCurrentSession().createQuery("from Reaction", Reaction.class).list();
    }

    @Override
    public Reaction findReaction(int reactionID) throws NoResultException {
        String hql = "FROM Reaction WHERE reaction_id = :reactionID";
        return sessionFactory.getCurrentSession().createQuery(hql, Reaction.class).setParameter("reactionID", reactionID).getSingleResult();
    }

    @Override
    public Reaction findReaction(UserAccount user, Post post) throws NoResultException {
        String hql = "FROM Reaction WHERE post_fk = :postID AND reactor_fk = :userID";
        return sessionFactory.getCurrentSession().createQuery(hql, Reaction.class)
                .setParameter("postID", post.getPostID())
                .setParameter("userID", user.getUserID())
                .getSingleResult();
    }

    @Override
    public List<Reaction> findPostReactions(int postID) {
        String hql = "FROM Reaction WHERE post_fk = :postID";
        return sessionFactory.getCurrentSession().createQuery(hql, Reaction.class).setParameter("postID", postID).list();
    }

    @Override
    public List<Reaction> findUserReactions(int userID) {
        String hql = "FROM Reaction WHERE reactor_fk = :userID";
        return sessionFactory.getCurrentSession().createQuery(hql, Reaction.class).setParameter("userID", userID).list();
    }

    @Override
    public void deleteReaction(Reaction reaction) {
        sessionFactory.getCurrentSession().delete(reaction);
    }

    @Override
    public void updateReaction(Reaction reaction)
    {
        sessionFactory.getCurrentSession().update(reaction);
    }


    // CONSTRUCTORS
    public ReactionDaoImpl() {

    }

    public ReactionDaoImpl(SessionFactory sessionFactory) {

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
