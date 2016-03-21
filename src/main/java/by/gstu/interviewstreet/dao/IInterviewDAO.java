package by.gstu.interviewstreet.dao;

import by.gstu.interviewstreet.domain.Form;
import by.gstu.interviewstreet.domain.Interview;
import by.gstu.interviewstreet.domain.User;
import by.gstu.interviewstreet.domain.UserInterview;

import java.util.List;


public interface IInterviewDAO {

    List<Interview> getAll();

    List<UserInterview> getUserInterviews(User user);

    List<Form> getInterviewQuestions(int interviewId);

    List<Form> getInterviewQuestions(long hash);

    List<List<Form>> getInterviewAnswers(List<Form> questionForm);

    long getQuestionCount(int interviewId);

    Interview getById(int id);

    List<Interview> getByType(int typeId);

    Interview getByHash(long hash);

    void save(Interview interview);

    void remove(Interview interview);

    void lock(int interviewId);

    void pass(int interviewId, int userId);

}
