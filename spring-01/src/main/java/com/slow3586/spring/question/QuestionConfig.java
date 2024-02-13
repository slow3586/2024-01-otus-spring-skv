package com.slow3586.spring.question;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionConfig {
    String questionsCsvFileName;
}
