package com.slow3586.spring.resource;

import com.slow3586.spring.Main;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
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
