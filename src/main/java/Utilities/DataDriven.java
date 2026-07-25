package Utilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class DataDriven {

    public static JsonNode jsonReader() {

        ObjectMapper mapper = new ObjectMapper();

        try {

            return mapper.readTree(
                    new File("src/main/resources/testData.json"));

        } catch (IOException e) {

            e.printStackTrace();

        }

        return null;

    }

}