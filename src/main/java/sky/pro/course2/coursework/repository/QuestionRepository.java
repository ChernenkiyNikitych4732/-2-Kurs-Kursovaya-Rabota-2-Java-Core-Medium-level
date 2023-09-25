package sky.pro.course2.coursework.repository;

import sky.pro.course2.coursework.model.Question;

import java.util.Collection;

public interface QuestionRepository {

    public Question add(String question, String answer);

    public Question remove(Question question);

    public Collection<Question> getAll();

}