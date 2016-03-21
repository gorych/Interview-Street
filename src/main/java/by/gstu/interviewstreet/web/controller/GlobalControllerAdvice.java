package by.gstu.interviewstreet.web.controller;

import by.gstu.interviewstreet.domain.User;
import by.gstu.interviewstreet.service.UserService;
import by.gstu.interviewstreet.web.AttributeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    UserService userService;

    @ModelAttribute(AttributeConstants.USER_INITIALS)
    public String addUserInitials() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.get(username);

        return user != null ? user.getEmployee().getInitials() : "Аноним";
    }

}
