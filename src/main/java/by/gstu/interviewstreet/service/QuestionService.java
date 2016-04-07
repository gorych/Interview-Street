package by.gstu.interviewstreet.service;


import by.gstu.interviewstreet.domain.AnswerType;
import by.gstu.interviewstreet.domain.Interview;
import by.gstu.interviewstreet.domain.Question;
import by.gstu.interviewstreet.domain.QuestionType;

import java.util.List;
import java.util.Map;

public interface QuestionService {

    Question get(int id);

    QuestionType getType(int typeId);

    List<Question> getAllOrderByNumber(String hash);

    Question addDefaultQuestion(Interview interview, QuestionType questionType, int number);

    void move(int questId, int number);

    Map<String, Object> getValueMapForDuplicateQuestionForm(Question question, Question duplicate);

    void remove(Question question);

}
