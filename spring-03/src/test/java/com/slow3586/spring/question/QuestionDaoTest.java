package com.slow3586.spring.question;

import com.slow3586.spring.question.dto.QuestionCsv;
import com.slow3586.spring.resource.ResourceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class QuestionDaoTest extends Mockito {
    @SuppressWarnings("unused")
    @Mock
    ResourceService resourceService;
    @SuppressWarnings("unused")
    @Mock QuestionConfig questionConfig;
    @Spy @InjectMocks
    QuestionDao questionDao;

    @Test
    public void testGetParseCsvResult() throws IOException {
        try (InputStream is = this.getClass().getResourceAsStream("/questions.csv")) {
            doReturn(Objects.requireNonNull(is).readAllBytes())
                .when(questionDao).readQuestionsCsvFile();
            List<QuestionCsv> result = questionDao.getParseCsvResult();
            assertEquals(5, result.size());
            assertEquals("Question #1", result.get(0).getQuestion());
            assertEquals("Answer #1", result.get(0).getAnswers().get(0));
            assertEquals(1, result.get(0).getCorrectAnswerId());
        }
    }
}