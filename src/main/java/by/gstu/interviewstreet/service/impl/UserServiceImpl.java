package by.gstu.interviewstreet.service.impl;

import by.gstu.interviewstreet.dao.IUserDAO;
import by.gstu.interviewstreet.security.UserPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    IUserDAO userDAO;

    @Transactional
    public UserDetails loadUserByUsername(String j_username) throws UsernameNotFoundException {
        System.out.println(j_username);
        by.gstu.interviewstreet.domain.User user= userDAO.getUserByPassportData(j_username.toUpperCase());

        if (user == null) {
            throw new UsernameNotFoundException("������������ � ������ ����������� ������� �� ������.");
        }

        int roleIndex = user.getRole().getId();
        String[] roles = UserPosition.values()[roleIndex].getRole();

        Set<SimpleGrantedAuthority> userRoles = new HashSet<>();
        for (String role : roles) {
            userRoles.add(new SimpleGrantedAuthority(role));
        }

        return new User(j_username, j_username, userRoles);
    }

}
