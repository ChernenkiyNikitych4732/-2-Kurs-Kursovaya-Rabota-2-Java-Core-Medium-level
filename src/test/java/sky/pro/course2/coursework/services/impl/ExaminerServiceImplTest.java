package sky.pro.course2.coursework.services.impl;

import sky.pro.course2.coursework.exception.QuestionIllegalArgumentException;
import sky.pro.course2.coursework.model.Question;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sky.pro.course2.coursework.service.impl.ExaminerServiceImpl;
import sky.pro.course2.coursework.service.impl.JavaQuestionService;
import sky.pro.course2.coursework.service.impl.MathQuestionService;

import java.util.Collection;
import java.util.Set;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {

    @Mock
    private JavaQuestionService javaQuestionService;

    @Mock
    private MathQuestionService mathQuestionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    Question javaQuestion1;
    Question javaQuestion2;
    Question mathQuestion1;
    Question mathQuestion2;
    @BeforeEach
    public void beforeEach() {
        examinerService = new ExaminerServiceImpl(javaQuestionService, mathQuestionService);

        javaQuestion1 = new Question("Java вопрос 1", "Java ответ 1");
        javaQuestion2 = new Question("Java вопрос 2", "Java ответ 2");
        mathQuestion1 = new Question("Math вопрос 1", "Math ответ 1");
        mathQuestion2 = new Question("Math вопрос 2", "Math ответ 2");

    }


    @Test
    public void getQuestionsTest() {

        when(javaQuestionService.getAll()).thenReturn(Set.of(javaQuestion1, javaQuestion2));
        when(mathQuestionService.getAll()).thenReturn(Set.of(mathQuestion1, mathQuestion2));
        when(javaQuestionService.getRandomQuestion()).thenReturn(javaQuestion1);
        when(mathQuestionService.getRandomQuestion()).thenReturn(mathQuestion1);

        Collection<Question> result = examinerService.getQuestions(2);

        Assertions.assertThat(result).contains(javaQuestion1);
        Assertions.assertThat(result).contains(mathQuestion1);

        verify(javaQuestionService, atLeast(1)).getRandomQuestion();
        verify(mathQuestionService, atLeast(1)).getRandomQuestion();

    }

    @Test
    public void getQuestionsIllegalArgumentTest() {
        Assertions.assertThatExceptionOfType(QuestionIllegalArgumentException.class).isThrownBy(() -> examinerService.getQuestions(2));
    }
}