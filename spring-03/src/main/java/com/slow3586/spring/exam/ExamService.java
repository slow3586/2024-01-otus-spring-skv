package com.slow3586.spring.exam;

import com.slow3586.spring.io.InputService;
import com.slow3586.spring.io.OutputService;
import com.slow3586.spring.question.QuestionService;
import com.slow3586.spring.question.dto.Question;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class ExamService {
    ExamConfig examConfig;
    QuestionService questionService;
    InputService inputService;
    OutputService outputService;

    public void runExam() {
        final List<Question> allQuestions = questionService.findAllQuestions();
        if (allQuestions.isEmpty()) {
            throw new RuntimeException("No questions!");
        }

        final int correctAnswersRequired = examConfig.getCorrectAnswersRequired();
        if (correctAnswersRequired <= 0) {
            throw new RuntimeException("Incorrect value of \"correctAnswersRequired\"!");
        }

        outputService.info("Enter your name");
        outputService.info("Welcome, " + inputService.readInputString());

        int correct = 0;
        for (int i = 0; i < allQuestions.size(); i++) {
            Question question = allQuestions.get(i);
            outputService.info("Question #" + (i + 1)
                + ": " + question.getQuestion());
            for (int ai = 0; ai < question.getAnswers().size(); ai++) {
                outputService.info("Answer #" + (ai + 1)
                    + ": "
                    + question.getAnswers().get(ai));
            }
            if (inputService.readInputInt().equals(question.getCorrectAnswerId())) {
                outputService.info("Correct!");
                correct++;
            } else {
                outputService.info("Wrong!");
            }

            if (correct >= correctAnswersRequired) {
                this.congratulate();
                return;
            }
        }

        this.ridicule();
    }

    protected void congratulate() {
        outputService.info("You won!");
    }

    protected void ridicule() {
        outputService.info("You failed!");
    }
}
