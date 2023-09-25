package sky.pro.course2.coursework.repository.impl;

import sky.pro.course2.coursework.model.Question;
import org.springframework.stereotype.Repository;
import sky.pro.course2.coursework.repository.QuestionRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Repository
public class MathQuestionRepository implements QuestionRepository {

    private Set<Question> mathQuestions = new HashSet<>();

    @Override
    public Question add(String question, String answer) {
        Question newQuestion = new Question(question, answer);
        mathQuestions.add(newQuestion);
        return newQuestion;
    }

    @Override
    public Question remove(Question question) {
        mathQuestions.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableSet(mathQuestions);
    }
}