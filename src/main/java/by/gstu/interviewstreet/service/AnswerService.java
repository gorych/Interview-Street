package by.gstu.interviewstreet.service;

import by.gstu.interviewstreet.domain.*;

import java.util.List;
import java.util.Map;

public interface AnswerService {

    long insert(Form form);

    void insertUserAnswers(Interview interview, List<Integer> questions, Map<Integer, String[]> answers, User user);

    List<Answer> get(List<Integer> ids);

    AnswerType getAnswerType(int id);

    void remove(int id);

}


