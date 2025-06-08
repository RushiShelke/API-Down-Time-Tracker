package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;

public class ApiConfigLoader {

    public static List<ApiService> loadApiConfig() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        InputStream inputStream = ApiConfigLoader.class
                .getClassLoader()
                .getResourceAsStream("apis.json");

        if (inputStream == null) {
            throw new RuntimeException("apis.json not found in resources folder.");
        }

        return mapper.readValue(inputStream, new TypeReference<List<ApiService>>() {});
    }
}
