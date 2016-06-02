package by.gstu.interviewstreet.service;


import by.gstu.interviewstreet.domain.User;
import by.gstu.interviewstreet.domain.UserInterview;

import java.util.Collection;
import java.util.List;

public interface UserService {

    void save(User user);

    User get(String username);

}
