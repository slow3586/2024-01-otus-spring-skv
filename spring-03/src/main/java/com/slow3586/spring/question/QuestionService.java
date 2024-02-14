package com.slow3586.spring.question;

import com.slow3586.spring.io.OutputService;
import com.slow3586.spring.question.dto.Question;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class QuestionService {
    QuestionDao questionDao;

    public List<Question> findAllQuestions() {
        return questionDao.findAll();
    }
}