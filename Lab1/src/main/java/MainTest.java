import org.json.JSONObject;

import java.io.IOException;

public class MainTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        JSONObject str = JsonSerializer
                .deserializeJSON(JsonSerializer
                        .serializeJSON(JsonSerializer
                                .getJSONByData(1, "tp1", "hello world")));
        System.out.println(str);
    }
}
