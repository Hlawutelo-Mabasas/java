package vut;
import java.util.Date;

public class ChatMessage {
    private String sessionId;
    private String content;
    private boolean isUser;
    private Date timestamp;

    public ChatMessage(String sessionId, String content, boolean isUser) {
        this.sessionId = sessionId;
        this.content = content;
        this.isUser = isUser;
        this.timestamp = new Date();
    }

    // Getters
    public String getSessionId() { return sessionId; }
    public String getContent() { return content; }
    public boolean isUser() { return isUser; }
    public Date getTimestamp() { return timestamp; }
}
