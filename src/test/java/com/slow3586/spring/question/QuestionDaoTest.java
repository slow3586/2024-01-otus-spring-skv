package com.slow3586.spring.question;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

@RunWith(MockitoJUnitRunner.class)
public class QuestionDaoTest extends Mockito {
    @Spy QuestionDao questionDao;

    @Test
    public void testFindAll() throws IOException {
        try (InputStream is = this.getClass().getResourceAsStream("/questions.csv")) {
            doReturn(new String(Objects.requireNonNull(is).readAllBytes(), StandardCharsets.UTF_8))
                .when(questionDao).readQuestionsCsvFile();
            List<Question> result = questionDao.findAll();
            Assert.assertEquals(5, result.size());
        }
    }
}