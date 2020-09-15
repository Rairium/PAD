package common;

import org.json.JSONObject;

public class JsonParser {
    String id;
    String topic;
    String message;

    public void parseJSON(JSONObject jsonObject){
        this.id = jsonObject.getString("id");
        this.topic = jsonObject.getString("topic");
        this.message = jsonObject.getString("message");
    }

    public String getId() {
        return id;
    }

    public String getTopic() {
        return topic;
    }

    public String getMessage() {
        return message;
    }
}
