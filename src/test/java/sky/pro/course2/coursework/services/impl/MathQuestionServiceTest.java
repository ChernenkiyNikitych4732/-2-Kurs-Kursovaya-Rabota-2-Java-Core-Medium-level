package sky.pro.course2.coursework.services.impl;

import sky.pro.course2.coursework.exception.QuestionNotFoundException;
import sky.pro.course2.coursework.model.Question;
import sky.pro.course2.coursework.repository.QuestionRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sky.pro.course2.coursework.service.impl.MathQuestionService;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MathQuestionServiceTest {

    @Mock
    private QuestionRepository repository;

    @InjectMocks
    private MathQuestionService questionService;

    private Set<Question> questions;

    private Question question1;

    private Question question2;

    private Question question3;

    private Question question4;

    @BeforeEach
    public void beforeEach() {
        question1 = new Question("Math вопрос 1", "Math ответ 1");
        question2 = new Question("Math вопрос 2", "Math ответ 2");
        question3 = new Question("Math вопрос 3", "Math ответ 3");
        question4 = new Question("Math вопрос 4", "Math ответ 4");

        questions = new HashSet<>();
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);
    }


    @Test
    public void addTest() {
        when(repository.add(question4.getQuestion(), question4.getAnswer())).thenReturn(question4);
        Assertions.assertThat(questionService.add(question4.getQuestion(), question4.getAnswer())).isEqualTo(question4);
    }

    @Test
    public void removeTest() {
        when(repository.getAll()).thenReturn(questions);
        when(repository.remove(question1)).thenReturn(question1);
        Assertions.assertThat(questionService.remove(question1)).isEqualTo(question1);
    }

    @Test
    public void removeWhenNotFoundTest() {
        when(repository.getAll()).thenReturn(questions);
        Assertions.assertThatExceptionOfType(QuestionNotFoundException.class)
                .isThrownBy(() -> questionService.remove(question4));
    }

    @Test
    public void getAllTest() {
        when(repository.getAll()).thenReturn(questions);
        Assertions.assertThat(questionService.getAll())
                .hasSize(3)
                .containsExactlyInAnyOrder(
                        new Question("Math вопрос 1", "Math ответ 1"),
                        new Question("Math вопрос 2", "Math ответ 2"),
                        new Question("Math вопрос 3", "Math ответ 3")
                );
    }
}