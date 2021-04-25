package example.models;

/**
 * Live feed model for websockets implementation
 */
public class LiveFeed {

    private String from;
    private String text;

    public LiveFeed() {
    }

    public LiveFeed(String from, String text) {
        this.from = from;
        this.text = text;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "LiveFeed{" +
                "from='" + from + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
