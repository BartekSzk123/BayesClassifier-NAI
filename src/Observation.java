import java.util.Map;

public class Observation {
    private String type;
    private Map<String,String> attributes;

    public Observation(String type, Map<String,String> attributes) {
        this.type = type;
        this.attributes = attributes;
    }

    public String getType() {
        return type;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }
}
