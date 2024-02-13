package com.slow3586.spring.resource;

import com.slow3586.spring.Main;
import lombok.SneakyThrows;

import java.io.InputStream;
import java.util.Optional;

public class ResourceService {
    @SneakyThrows
    public byte[] readResourceToString(String name) {
        try (InputStream resourceAsStream = Main.class.getResourceAsStream("/" + name)) {
            return Optional.ofNullable(resourceAsStream)
                .orElseThrow(() -> new RuntimeException("CSV not found! Name=" + name))
                .readAllBytes();
        }
    }
}
