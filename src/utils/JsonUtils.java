package utils;

import jakarta.servlet.http.HttpServletRequest;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;

public class JsonUtils {

    public static JSONObject convertToJSONObject(HttpServletRequest request) throws IOException{

        StringBuilder stringBuilder = new StringBuilder();
        String line;

        try (BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        }

        String jsonString = stringBuilder.toString();
        return new JSONObject(jsonString);

    }
}
