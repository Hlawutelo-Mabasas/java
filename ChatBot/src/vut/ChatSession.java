package vut;
import java.util.ArrayList;
import java.util.List;

public class ChatSession {
    private String sessionId;
    private List<ChatMessage> messages;

    public ChatSession(String sessionId) {
        this.sessionId = sessionId;
        this.messages = new ArrayList<>();
    }

    public String getSessionId() { return sessionId; }
    public List<ChatMessage> getMessages() { return messages; }
    public void addMessage(ChatMessage msg) { messages.add(msg); }
}
