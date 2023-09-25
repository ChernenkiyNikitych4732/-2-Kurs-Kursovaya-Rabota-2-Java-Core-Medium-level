package sky.pro.course2.coursework.service.impl;

import sky.pro.course2.coursework.exception.QuestionIllegalArgumentException;
import sky.pro.course2.coursework.model.Question;
import sky.pro.course2.coursework.service.ExaminerService;
import sky.pro.course2.coursework.service.QuestionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService javaQuestionService;
    private final QuestionService mathQuestionService;

    public ExaminerServiceImpl(@Qualifier("javaQuestionService") QuestionService javaQuestionService,
                               @Qualifier("mathQuestionService") QuestionService mathQuestionService) {
        this.javaQuestionService = javaQuestionService;
        this.mathQuestionService = mathQuestionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (javaQuestionService.getAll().size() + mathQuestionService.getAll().size() < amount) {
            throw new QuestionIllegalArgumentException();
        }
        Set<Question> randomQuestions = new HashSet<>();
        Random random = new Random();
        while (randomQuestions.size() < amount) {
            int questionRandom = random.nextInt(2);
            switch (questionRandom) {
                case 0:
                    randomQuestions.add(javaQuestionService.getRandomQuestion());
                    break;
                case 1:
                    randomQuestions.add(mathQuestionService.getRandomQuestion());
                    break;
            }
        }
        return randomQuestions;
    }
}