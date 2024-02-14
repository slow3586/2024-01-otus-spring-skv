package com.slow3586.spring.io;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class InputService {
    final Scanner in = new Scanner(System.in);

    @SneakyThrows
    public String readInputString() {
        String input = null;
        while (StringUtils.isBlank(input)) {
            input = in.nextLine();
        }
        return input;
    }

    @SneakyThrows
    public Integer readInputInt() {
        Integer input = null;
        while (input == null) {
            try {
                input = Integer.parseInt(this.readInputString());
            } catch (Exception e) {
                log.error("Enter a number!");
            }
        }
        return input;
    }
}
