package com.slow3586.spring.question.dto;

import lombok.Value;

import java.util.List;

@Value
public class Question {
    String question;
    int correctAnswerId;
    List<String> answers;
}
