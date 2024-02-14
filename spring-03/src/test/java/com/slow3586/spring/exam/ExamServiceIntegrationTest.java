package com.slow3586.spring.exam;

import com.slow3586.spring.io.InputService;
import com.slow3586.spring.io.OutputService;
import com.slow3586.spring.question.QuestionConfig;
import com.slow3586.spring.question.QuestionDao;
import com.slow3586.spring.question.QuestionService;
import com.slow3586.spring.resource.ResourceService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ExamServiceIntegrationTest extends Mockito {
    @Mock QuestionConfig questionConfig;
    @Mock ExamConfig examConfig;
    @Mock InputService inputService;
    @Mock OutputService outputService;

    ResourceService resourceService;
    QuestionDao questionDao;
    QuestionService questionService;
    ExamService examService;

    @Before
    public void before() {
        when(questionConfig.getQuestionsCsvFileName()).thenReturn("questions.csv");
        when(examConfig.getCorrectAnswersRequired()).thenReturn(2);
        when(inputService.readInputString()).thenReturn("name");
        resourceService = new ResourceService();
        questionDao = new QuestionDao(resourceService, questionConfig);
        questionService = new QuestionService(questionDao);
        examService = spy(new ExamService(examConfig, questionService, inputService, outputService));
    }

    @Test
    public void testRunExamWon() {
        when(inputService.readInputInt()).thenReturn(1);

        examService.runExam();

        verify(examService).congratulate();
    }

    @Test
    public void testRunExamFailed() {
        when(inputService.readInputInt()).thenReturn(4);

        examService.runExam();

        verify(examService).ridicule();
    }
}