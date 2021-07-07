package ru.otus.dataprocessor;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.otus.model.Measurement;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonStructure;
import java.io.*;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class FileLoader implements Loader {

    private final String fileName;
    private final ObjectMapper mapper = new ObjectMapper();

    public FileLoader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Measurement> load() throws IOException, ClassNotFoundException {
        List<Measurement> measurements;
        var file = new File(Objects.requireNonNull(FileLoader.class.getClassLoader().getResource(fileName)).getPath());
        Class<?> clz = Class.forName(Measurement.class.getName());
        JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, clz);
        /*
          Еще способ
          measurements = mapper.readValue(file, new TypeReference<>() {});
          */

        measurements = mapper.readValue(file, type);
        measurements.forEach(System.out::println);
        return measurements;
    }
}
