package publisher;

import org.json.JSONObject;

import java.io.*;

public class JsonSerializer implements Serializable {

    public static JSONObject getJSONByData(String id, String topic, String message) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("topic", topic);
        jsonObject.put("message", message);
        return jsonObject;
    }

    public static byte[] serializeJSON(JSONObject jsonObject) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(jsonObject.toString());
        objectOutputStream.flush();
        return byteArrayOutputStream.toByteArray();

    }

    public static JSONObject deserializeJSON(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        ObjectInput input = new ObjectInputStream(byteArrayInputStream);

        String deserializedData = (String) input.readObject();
        return new JSONObject(deserializedData);
    }
}
