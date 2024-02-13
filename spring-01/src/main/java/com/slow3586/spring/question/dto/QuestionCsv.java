package com.slow3586.spring.question.dto;

import com.opencsv.bean.CsvBindAndSplitByPosition;
import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

import java.util.List;

@Data
public class QuestionCsv {
    @CsvBindByPosition(position = 0)
    String question;
    @CsvBindByPosition(position = 1)
    int correctAnswerId;
    @CsvBindAndSplitByPosition(position = 2, elementType = String.class, splitOn = ",")
    List<String> answers;
}
