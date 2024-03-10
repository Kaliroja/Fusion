package com.jp.fusion.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jp.fusion.model.Movie;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

@Service
@Slf4j
public class RetrievalService {
    private static final String dataFile = "./data/movies-1900s.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();


    static {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }


    public <T> List<T> getData(Class<T> T) {
        List<T> dbDocuments = new ArrayList<>();

        try {
            URL url = RetrievalService.class.getClassLoader().getResource(dataFile);
            if (url != null) {
                File file = new File(url.getFile());
                dbDocuments = objectMapper.readValue(
                        Files.newInputStream(file.toPath(), StandardOpenOption.READ),
                        objectMapper.getTypeFactory().constructCollectionType(List.class, T)
                );
            }
        } catch (Exception ex) {
            log.error("Exception at getData(): ", ex);
        }

        return dbDocuments;
    }
}
