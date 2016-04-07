package by.gstu.interviewstreet.service.impl;

import by.gstu.interviewstreet.dao.PostDAO;
import by.gstu.interviewstreet.domain.Post;
import by.gstu.interviewstreet.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostDAO postDAO;

    @Override
    @Transactional
    public List<Post> getAll() {
        return postDAO.getAll();
    }
}
