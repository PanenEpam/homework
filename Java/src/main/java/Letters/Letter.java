package Letters;

public class Letter {
    private String address;
    private String topic;
    private String text;

    public Letter(String address, String topic, String text) {
        this.address = address;
        this.topic = topic;
        this.text = text;
    }

    public String getAddress() {
        return address;
    }

    public String getTopic() {
        return topic;
    }

    public String getText() {
        return text;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setText(String text) {
        this.text = text;
    }
}
