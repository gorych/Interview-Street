package by.gstu.interviewstreet.dao.impl;

import by.gstu.interviewstreet.dao.UserAnswerDAO;
import by.gstu.interviewstreet.domain.Question;
import by.gstu.interviewstreet.domain.UserAnswer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserAnswerDAOImpl extends AbstractDbDAO implements UserAnswerDAO {

    @Override
    public void saveOrUpdate(UserAnswer userAnswer) {
        getSession().saveOrUpdate(userAnswer);
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<UserAnswer> getAnswersByQuestion(Question question) {
        return getSession()
                .createQuery("FROM UserAnswer WHERE question =:question GROUP BY answerText")
                .setEntity("question", question)
                .list();
    }
}
