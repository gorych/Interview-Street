package by.gstu.interviewstreet.service;

import by.gstu.interviewstreet.domain.AnswerType;
import by.gstu.interviewstreet.domain.Interview;
import by.gstu.interviewstreet.domain.Question;

import java.util.List;
import java.util.Map;

public interface InterviewService {

    Interview get(int interviewId);

    Interview get(String hash);

    List<Interview> getAll();

    List<Interview> getAllInRange(int from, int howMany);

    Map<String, Object> getValueMapForCard(int interviewId);

    Interview saveOrUpdate(Interview interview);

    void lockOrUnlock(int id);

    void remove(Interview interview);
}
