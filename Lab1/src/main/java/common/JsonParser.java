package common;

import org.json.JSONObject;

public class JsonParser {
    String topic;
    String message;

    public void parseJSON(JSONObject jsonObject) {
        this.topic = jsonObject.getString("topic");
        this.message = jsonObject.getString("message");
    }

    public String getTopic() {
        return topic;
    }

    public String getMessage() {
        return message;
    }
}
