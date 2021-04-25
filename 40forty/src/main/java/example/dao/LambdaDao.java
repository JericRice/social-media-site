package example.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.transaction.Transactional;

@Repository("lambdaDao")
@Transactional
public class LambdaDao {

    private SessionFactory sessionFactory;

    public LambdaDao() {
    }

    public LambdaDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public void deleteAllData(){
        // This would get it in one query...
        // IF IT WORKED !!!
//        @Modifying
//        @Query(
//                value = "truncate table myTable",
//                nativeQuery = true
//        )
//        void truncateMyTable();

        Session session = sessionFactory.getCurrentSession();
        System.out.println("In delete all data() dao");
//        String hql = "DELETE from reaction;" +
//                "Delete from post;";
        String hql = "DELETE FROM Reaction;" +
                "DELETE FROM Post;";
        Query query = session.createQuery(hql);
        query.executeUpdate();
    }


    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
