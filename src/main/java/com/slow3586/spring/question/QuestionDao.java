package com.slow3586.spring.question;

import com.slow3586.spring.resource.ResourceService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Setter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Repository
public class QuestionDao {
    static String QUESTION_SPLIT_SYMBOL = "*";
    static String ANSWER_SPLIT_SYMBOL = "\n";
    static String CORRECT_ANSWER_SYMBOL = "+";
    ResourceService resourceService;
    QuestionConfig questionConfig;

    public List<Question> findAll() {
        return Arrays.stream(StringUtils.split(
                this.readQuestionsCsvFile(),
                QUESTION_SPLIT_SYMBOL))
            .map(s -> StringUtils.split(s, ANSWER_SPLIT_SYMBOL))
            .map(Arrays::asList)
            .filter(l -> !l.isEmpty())
            .map(qaText -> {
                final String question = StringUtils.trim(
                    StringUtils.removeStart(
                        qaText.get(0),
                        QUESTION_SPLIT_SYMBOL));
                final List<String> answers = qaText.stream()
                    .skip(1)
                    .map(StringUtils::trim)
                    .toList();
                final Integer correctAnswer = answers
                    .stream()
                    .filter(a -> a.startsWith(CORRECT_ANSWER_SYMBOL))
                    .findFirst()
                    .map(s -> answers.indexOf(s) + 1)
                    .orElseThrow(() -> new RuntimeException("No correct answer? " + question));
                return new Question(
                    question,
                    answers.stream()
                        .map(a -> StringUtils.removeStart(a, CORRECT_ANSWER_SYMBOL))
                        .toList(),
                    correctAnswer);
            }).toList();
    }

    protected String readQuestionsCsvFile() {
        return new String(
            resourceService.readResourceToString(questionConfig.getQuestionsCsvFileName()),
            StandardCharsets.UTF_8);
    }
}
