package by.gstu.interviewstreet.service.impl;

import by.gstu.interviewstreet.dao.UserAnswerDAO;
import by.gstu.interviewstreet.dao.UserInterviewDAO;
import by.gstu.interviewstreet.domain.*;
import by.gstu.interviewstreet.service.UserAnswerService;
import by.gstu.interviewstreet.web.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserAnswerServiceImpl implements UserAnswerService {

    @Autowired
    UserAnswerDAO userAnswerDAO;

    @Autowired
    UserInterviewDAO userInterviewDAO;

    @Override
    @Transactional
    public void save(User user, Interview interview, List<Answer> answers) {
        List<Question> existQuestions = interview.getQuestions();
        UserInterview userInterview = userInterviewDAO.getByUserAndInterview(user.getPassportData(), interview.getHash());

        if(userInterview.getPassed()){
            throw new IllegalArgumentException("User already passed this interview.");
        }

        for (Answer answer : answers) {
            Question question = answer.getQuestion();

            int index = existQuestions.indexOf(question);
            if (index < 0) {
                continue;
            }

            question = existQuestions.get(index);
            List<Answer> existAnswers = question.getAnswers();

            if (existAnswers.contains(answer)) {
                userAnswerDAO.save(new UserAnswer(user, question, interview, answer, answer.getText(), DateUtils.getToday()));

                /*Set flag that user passed interview*/
                userInterview.setPassed(true);
                userInterviewDAO.saveOrUpdate(userInterview);
            }
        }
    }
}
