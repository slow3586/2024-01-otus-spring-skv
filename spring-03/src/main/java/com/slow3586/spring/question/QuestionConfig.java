package com.slow3586.spring.question;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QuestionConfig {
    @NonFinal
    @Value("${questionsCsvFileName}")
    String questionsCsvFileName;
}
