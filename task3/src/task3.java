import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class task3 {
    public static void main(String[] args) {
        String testsPath = args[0];
        String valuesPath = args[1];
        String testsJson = readJSONFile(testsPath);
        String valuesJson = readJSONFile(valuesPath);

        JsonParser parser = new JsonParser();
        JsonObject testsData = parser.parse(testsJson).getAsJsonObject();
        JsonObject valuesData = parser.parse(valuesJson).getAsJsonObject();

        JsonArray testsArray = testsData.getAsJsonArray("tests");
        JsonArray valuesArray = valuesData.getAsJsonArray("values");

        JsonObject updatedTestsData = new JsonObject();

        JsonObject valuesMapping = new JsonObject();
        for (int i = 0; i < valuesArray.size(); i++) {
            JsonObject value = valuesArray.get(i).getAsJsonObject();
            int valueId = value.get("id").getAsInt();
            valuesMapping.addProperty(Integer.toString(valueId), value.get("value").getAsString());
        }

        for (int i = 0; i < testsArray.size(); i++) {
            JsonObject test = testsArray.get(i).getAsJsonObject();
            updateTestResults(test, valuesMapping);
            updatedTestsData.add(Integer.toString(test.get("id").getAsInt()), test);
        }

        JsonObject updatedData = new JsonObject();
        updatedData.add("tests", updatedTestsData);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String updatedJSON = gson.toJson(updatedData);

        try (FileWriter fileWriter = new FileWriter("..\\..\\..\\report.json")) {
            fileWriter.write(updatedJSON);
            System.out.println("Данные сохранены в файл report.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void updateTestResults(JsonObject test, JsonObject valuesMapping) {
        int testId = test.get("id").getAsInt();

        if (valuesMapping.has(Integer.toString(testId))) {
            test.addProperty("value", valuesMapping.get(Integer.toString(testId)).getAsString());
        }

        if (test.has("values")) {
            JsonArray testValues = test.getAsJsonArray("values");
            for (int j = 0; j < testValues.size(); j++) {
                JsonObject testValue = testValues.get(j).getAsJsonObject();
                int testValueId = testValue.get("id").getAsInt();
                if (valuesMapping.has(Integer.toString(testValueId))) {
                    testValue.addProperty("value", valuesMapping.get(Integer.toString(testValueId)).getAsString());
                }
                updateTestResults(testValue, valuesMapping);
            }
        }
    }

    private static String readJSONFile(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
