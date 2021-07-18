package ru.otus.dataprocessor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import ru.otus.model.Measurement;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FileLoader implements Loader {

    private final String fileName;
    private final ObjectMapper mapper = new ObjectMapper();

    public FileLoader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Measurement> load() throws IOException {
        /*
        var file = new File(Objects.requireNonNull(FileLoader.class.getClassLoader().getResource(fileName)).getPath());

        Class<?> clz = Class.forName(Measurement.class.getName());
        JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, clz);

          Еще способ
          measurements = mapper.readValue(file, new TypeReference<>() {});
          */
        var gson = new Gson();

        Measurement[] measurements = gson.fromJson(new FileReader(Objects.requireNonNull(FileLoader.class.getClassLoader().getResource(fileName)).getPath()), Measurement[].class);

        return Arrays.asList(measurements);
    }
}
