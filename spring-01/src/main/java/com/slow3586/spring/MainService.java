package com.slow3586.spring;

import com.slow3586.spring.question.QuestionService;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MainService {
    QuestionService questionService;

    public void init(){
        questionService.printQuestions();
    }
}
