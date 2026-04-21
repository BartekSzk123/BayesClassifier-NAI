public class Observation {
    private String type;
    private String[] attributes;

    public Observation(String type, String[] attributes) {
        this.type = type;
        this.attributes = attributes;
    }

    public String getType() {
        return type;
    }

    public String[] getAttributes() {
        return attributes;
    }
}
