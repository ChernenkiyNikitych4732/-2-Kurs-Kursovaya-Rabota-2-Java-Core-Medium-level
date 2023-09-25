package sky.pro.course2.coursework.controller;

import sky.pro.course2.coursework.model.Question;
import sky.pro.course2.coursework.service.QuestionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/exam")
public class MathQuestionController {

    private final QuestionService service;

    public MathQuestionController(@Qualifier("mathQuestionService") QuestionService service) {
        this.service = service;
    }

    @GetMapping("/math/add")
    public Question addQuestion(@RequestParam("question") String question,
                                @RequestParam("answer") String answer) {
        return service.add(question, answer);
    }

    @GetMapping("/math/remove")
    public Question removeQuestion(@RequestParam("question") String question,
                                   @RequestParam("answer") String answer) {
        Question newQuestion = new Question(question, answer);
        return service.remove(newQuestion);
    }

    @GetMapping("/math")
    public Collection<Question> getQuestions() {
        return service.getAll();
    }
}