package by.gstu.interviewstreet.web.controller.user;


import by.gstu.interviewstreet.domain.User;
import by.gstu.interviewstreet.service.UserService;
import by.gstu.interviewstreet.web.AttrConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.security.Principal;

import static by.gstu.interviewstreet.web.SecurityConstants.EDITOR;
import static by.gstu.interviewstreet.web.SecurityConstants.RESPONDENT;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private  UserService userService;

    protected User getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        return userService.get(username);
    }

    @ModelAttribute(AttrConstants.USER)
    public User addUserInitials(Principal principal) {
        return principal != null
                ? getUserByPrincipal(principal)
                : null;
    }

    @ResponseBody
    @Secured({EDITOR, RESPONDENT})
    @RequestMapping(value = {"/hide-chip"}, method = RequestMethod.GET)
    public ResponseEntity<String> hideChip(HttpSession session) {
        session.setAttribute(AttrConstants.CHIP, false);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
