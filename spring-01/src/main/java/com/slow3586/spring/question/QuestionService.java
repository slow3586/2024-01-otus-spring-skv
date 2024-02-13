package com.slow3586.spring.question;

import com.slow3586.spring.io.OutputService;
import com.slow3586.spring.question.dto.Question;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionService {
    QuestionDao questionDao;
    OutputService outputService;

    public void printQuestions() {
        try {
            final List<Question> allQuestions = questionDao.findAll();
            for (int i = 0; i < allQuestions.size(); i++) {
                final Question question = allQuestions.get(i);
                outputService.info("Question #" + (i + 1)
                    + ": " + question.getQuestion());
                for (int ai = 0; ai < question.getAnswers().size(); ai++) {
                    outputService.info("Answer #" + (ai + 1)
                        + ": "
                        + question.getAnswers().get(ai));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("#printQuestions Ошибка вывода вопросов", e);
        }
    }
}
