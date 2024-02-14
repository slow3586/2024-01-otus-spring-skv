package com.slow3586.spring;

import com.slow3586.spring.exam.ExamService;
import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
public class MainService {
    ExamService examService;

    @PostConstruct
    public void init(){
        examService.runExam();
    }
}
