package com.slow3586.spring.question;

import com.opencsv.bean.CsvToBeanBuilder;
import com.slow3586.spring.question.dto.Question;
import com.slow3586.spring.question.dto.QuestionCsv;
import com.slow3586.spring.resource.ResourceService;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.List;

@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionDao {
    ResourceService resourceService;
    QuestionConfig questionConfig;

    public List<Question> findAll() {
        return getParseCsvResult()
            .stream()
            .map(q -> new Question(
                q.getQuestion(),
                q.getCorrectAnswerId(),
                q.getAnswers()))
            .toList();
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    protected List<QuestionCsv> getParseCsvResult() {
        return new CsvToBeanBuilder(
            new BufferedReader(
                new InputStreamReader(
                    new ByteArrayInputStream(
                        this.readQuestionsCsvFile()))))
            .withType(QuestionCsv.class)
            .withSeparator('\t')
            .withIgnoreLeadingWhiteSpace(true)
            .withIgnoreEmptyLine(true)
            .build()
            .parse();
    }

    protected byte[] readQuestionsCsvFile() {
        return resourceService.readResourceToString(questionConfig.getQuestionsCsvFileName());
    }
}
