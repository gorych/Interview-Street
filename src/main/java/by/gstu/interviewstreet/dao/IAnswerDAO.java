package by.gstu.interviewstreet.dao;

import by.gstu.interviewstreet.domain.Answer;
import by.gstu.interviewstreet.domain.UserAnswer;

import java.util.List;

public interface IAnswerDAO {

    Answer insert(Answer answer);

    void insertUserAnswer(UserAnswer userAnswer);

    List<Answer> getByIds(List<Integer> ids);

    List<UserAnswer> getUserAnswers(int questionId);

    List<Integer> getAnswerCount(int questionId);

    void remove(int id);

}
