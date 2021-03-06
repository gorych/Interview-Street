package by.gstu.interviewstreet.dao.impl;

import by.gstu.interviewstreet.dao.PostDAO;
import by.gstu.interviewstreet.domain.Post;
import org.springframework.stereotype.Repository;

@Repository
public class PostDAOImpl extends GenericDAOImpl<Post, Integer> implements PostDAO {

    @Override
    public Post findByName(String name) {
        return (Post) currentSession().createQuery("FROM Post WHERE name LIKE :name").
                setString("name", name).
                uniqueResult();
    }
}
