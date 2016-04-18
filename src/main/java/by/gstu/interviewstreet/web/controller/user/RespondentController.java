package by.gstu.interviewstreet.web.controller.user;

import by.gstu.interviewstreet.domain.Interview;
import by.gstu.interviewstreet.domain.Question;
import by.gstu.interviewstreet.domain.User;
import by.gstu.interviewstreet.domain.UserInterview;
import by.gstu.interviewstreet.security.UserRoleConstants;
import by.gstu.interviewstreet.service.UserInterviewService;
import by.gstu.interviewstreet.web.AttrConstants;
import by.gstu.interviewstreet.web.util.ControllerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/respondent")
@Secured(UserRoleConstants.RESPONDENT)
public class RespondentController extends UserController {

    @Autowired
    UserInterviewService userInterviewService;

    @RequestMapping("/dashboard")
    public String showDashboard(Principal principal, Model model) {
        User user = getUserByPrincipal(principal);
        List<UserInterview> availableInterview = user.getInterviewsForPassing()
                .stream().filter(ui -> !ui.getInterview().getHide() && !ui.getInterview().getIsDeadline())
                .collect(Collectors.toCollection(ArrayList::new));

        model.addAttribute(AttrConstants.USER_INTERVIEWS, availableInterview);

        return "dashboard";
    }

    @RequestMapping("/{hash}/interview")
    public String showDashboard(@PathVariable String hash, Principal principal, Model model) {
        UserInterview userInterview = userInterviewService.getByUserAndInterview(principal.getName(), hash);
        if (userInterview == null) {
            return "redirect:/dashboard";
        }

        Interview interview = userInterview.getInterview();
        List<Question> sortedQuestions = ControllerUtils.sortQuestionList(interview.getQuestions());

        model.addAttribute(AttrConstants.INTERVIEW, interview);
        model.addAttribute(AttrConstants.QUESTIONS, sortedQuestions);

        return "interview";
    }

}
