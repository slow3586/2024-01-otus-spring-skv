package com.slow3586.spring.question;

import lombok.Value;

import java.util.List;

@Value
public class Question {
    String question;
    List<String> answers;
    int correctAnswerId;
}
