package ru.otus.dataprocessor;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class FileSerializer implements Serializer {

    private final ObjectMapper mapper = new ObjectMapper();
    private final String fileName;

    public FileSerializer(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void serialize(Map<String, Double> data) throws IOException {
        var file = new File(fileName);
        Map<String, Double> stringDoubleTreeMap = new TreeMap<>(Comparator.comparing(o -> Integer.valueOf(o.substring(3))));
        stringDoubleTreeMap.putAll(data);
        mapper.writeValue(file, stringDoubleTreeMap);
    }
}
