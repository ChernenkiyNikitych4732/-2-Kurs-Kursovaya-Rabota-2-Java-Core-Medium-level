package sky.pro.course2.coursework.handler;

import sky.pro.course2.coursework.exception.QuestionNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class JavaQuestionServiceHandler {

    @ExceptionHandler(QuestionNotFoundException.class)
    public String removeWhenQuestionNotFoundException() {
        return "Вопрос не был найден";
    }
}