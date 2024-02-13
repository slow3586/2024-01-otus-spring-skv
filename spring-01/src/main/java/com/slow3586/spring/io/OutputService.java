package com.slow3586.spring.io;

import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OutputService {
    public void info(String s) {
        log.info(s);
    }
}
